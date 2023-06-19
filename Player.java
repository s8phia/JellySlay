import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    //variables
    private GreenfootImage image;
    private GreenfootImage [] rightWalk;
    private GreenfootImage [] leftWalk;
    private GreenfootImage [] frontWalk;
    private GreenfootImage [] backWalk;
    private GreenfootImage [] die;
    private double xMove, xDirection, yDirection;
    private int speed, delay;
    private int upgradeAmount;
    private int originalSpeed = 4;
    private int frame;
    private int maxCD, cooldown;
    private int hp;
    private int newRotation;
    public int mouseX, mouseY;
    public static int PlayerX, PlayerY;
    public static int energy = 0;
    private int hitCooldown = 0;
    private int hitDelay = 60;

    public Player(){
        
        //initial values for player
        hp = 6;
        speed = originalSpeed;
        delay = speed;
        
        //array of greenfootimages to animate the different directions of the character
        rightWalk = new GreenfootImage [6];
        leftWalk = new GreenfootImage[6];
        frontWalk = new GreenfootImage [6];
        backWalk = new GreenfootImage [6];
        for (int i = 0; i < rightWalk.length; i++) {
            rightWalk[i] = new GreenfootImage("gwalk_right" + i + ".png");
            rightWalk[i].scale(50, 62);
            leftWalk[i] = new GreenfootImage (rightWalk[i]);
            leftWalk[i].mirrorHorizontally();
        }

        for (int i = 0; i < frontWalk.length; i ++) {
            frontWalk[i] = new GreenfootImage("gwalk_front" + i + ".png");
            frontWalk[i].scale(52,62);
        }

        for (int i = 0; i < backWalk.length; i++) {
            backWalk[i] = new GreenfootImage("gwalk_back" + i +".png");
            backWalk[i].scale(50, 62);
        }

        frame = 0;
        image = rightWalk[frame];
        setImage(image);
        xMove = 0;
        xDirection = 0;
        maxCD = 10;
        cooldown = 0;
    }

    public void act()
    {
        checkKeys();
        PlayerX = getX();
        PlayerY = getY();
        checkEnergy();
        splatSlow();
        youLose();
    }

    private void checkKeys() {
        // this is  a method to check key pressed and update the players movement and
        //animation based on the pressed key
        String key = Greenfoot.getKey();
        if (cooldown > 0) {
            cooldown--;
        }

        if (Greenfoot.isKeyDown("a")) {
            xDirection = -1;
            if (delay == 0) {
                frame++;
                if (frame >= leftWalk.length) {
                    frame = 0;
                }
                delay = speed;
            } else {
                delay--;
            }
            setImage(leftWalk[frame]);
            setLocation(getX() - speed, getY());
            newRotation = 180;
        }

        if (Greenfoot.isKeyDown("d")) {
            xDirection = 1;
            if (delay == 0) {
                frame++;
                if (frame >= leftWalk.length) {
                    frame = 0;
                }
                delay = speed;
            } else {
                delay--;
            }
            setImage(rightWalk[frame]);
            setLocation(getX() + speed, getY());
            newRotation = 0;
        }

        if (Greenfoot.isKeyDown("w")) {
            yDirection = 1;
            if (delay == 0) {
                frame++;
                if (frame >= backWalk.length) {
                    frame = 0;
                }
                delay = speed;
            } else {
                delay--;
            }
            setImage(backWalk[frame]);
            setLocation(getX(), getY() - speed);
            newRotation = 270;
        }

        if (Greenfoot.isKeyDown("s")) {
            yDirection = -1;
            if (delay == 0) {
                frame++;
                if (frame >= frontWalk.length) {
                    frame = 0;
                }
                delay = speed;
            } else {
                delay--;
            }
            setImage(frontWalk[frame]);
            setLocation(getX(), getY() + speed);
            newRotation = 90;
        }

        if(key != null && key.equals("space")){
            if (cooldown == 0) {
                shoot();
                cooldown = maxCD;
            }
        }
    }

    private void shoot () {
        //creates a new projectile to the gameworld 
        Projectile projectile = new Projectile(newRotation);
        getWorld().addObject(projectile, getX(), getY()+10);
    }

    private void youLose() {
        //subtract hp if playyer touches the slime. If hp equals zero, display dying photo
        //and remove all the hearts
        if (isTouching(Slime.class) && hitCooldown <= 0) {
            hp--;
            if (hp <= 0) {
                GreenfootImage image = new GreenfootImage("gdie1.png");
                image.scale(40, 42);
                setImage(image);
                hitCooldown = hitDelay;
                removeHearts();
            }
            hitCooldown = hitDelay;
        }
        if (hitCooldown > 0) {
            hitCooldown--;
        }
    }

    private void removeHearts() {
        GameWorld world = (GameWorld) getWorld();
        Heart[] hearts = world.hearts;
        for (int i = 0; i < hearts.length; i++) {
            world.removeObject(hearts[i]);
        }
    }

    private void splatSlow () {
        //if character steps on the trap class, then the speed gets changed to 0
        // if they are off the class, then go back to whatever speed the character is set to
        //originalspeed + upgradeamount is used as it considers the new speed if players
        //choose to buy the speed upgrade
        if(isTouching(trap.class)){
            speed = 2;
        }
        else {
            speed = originalSpeed + upgradeAmount;
        }
    }

    public void checkEnergy() {
        if(isTouching(Energy.class)) {
            removeTouching(Energy.class);
            energy++;
        }
    }    

    public void addHeart() {
        hp++;
    }

    public void upgradeSpeed() {
        speed++;
        upgradeAmount++;
    }

    public int getHP() {
        return hp;
    }

}
