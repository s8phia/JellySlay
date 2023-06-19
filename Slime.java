import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Slime extends Actor
{
    private int hp = 2;
    public int speed = 1;
    private GreenfootImage[] greenSlimeWalk; //array of images of slime's animations
    private int delay;
    private int frame;
    Counter counter; //refrences to the counter object in the world
    
    public Slime(Counter counter) {
        this.counter = counter; // Assign the Counter object to the instance variable
        greenSlimeWalk = new GreenfootImage[6];
        for(int i = 0; i < greenSlimeWalk.length;i ++) {
            greenSlimeWalk[i] = new GreenfootImage("green_slime_walk" + i + ".png");
            greenSlimeWalk[i].scale(50, 36);
        }
        setImage(greenSlimeWalk[0]);
    }

    public void act()
    {
        animateSlimeWalk();
        chasePlayer();
        hitByProjectile();
    }
    
    public void animateSlimeWalk() {
        //method to animate the slime's movement
        if (delay == 0) {
            setImage(greenSlimeWalk[frame]); //set the current animation frame
            frame = (frame + 1) % greenSlimeWalk.length; //increment the frame index and loop back 
            delay = 5; //resets delay
        }
        else {
            delay--; //decrease delay
        }
    }
    
    public void chasePlayer() {
        turnTowards(Player.PlayerX, Player.PlayerY); //turn the slime towards the player's position
        move(speed); //move the slime towards the player
    }
    
    public void hitByProjectile() {
        //checks for if the projectile is intersecting with the slime
        //if yes, remove the projectile and decrease the slime health
        //if hp is less or equal to 0, add to the score and coins and
        //remove the slime from the world 
        Actor projectile = getOneIntersectingObject (Projectile.class);
        if (projectile != null) {
            hp--;
            getWorld().removeObject(projectile);
        }
        if(hp <= 0) {
            counter.score +=20;
            counter.coins += 1;
            getWorld().removeObject(this);
        }
    }
    
}
