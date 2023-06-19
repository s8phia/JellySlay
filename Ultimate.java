import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Ultimate extends Actor {
    private int maxEnergy = 3;
    private int waitTime = 150;
    
    public Ultimate() {
        updateImage();
    }

    public void act() {
        updateImage();
    }

    private void updateImage() {
        GreenfootImage image;
        
        if(waitTime > 0) {
            waitTime--;
        }

        if (Player.energy == 0) {
            image = new GreenfootImage("energyBar1.png");
        } else if (Player.energy == 1) {
            image = new GreenfootImage("energyBar2.png");
        } else if (Player.energy == 2) {
            image = new GreenfootImage("energyBar3.png");
        } else if (Player.energy == 3) {
            image = new GreenfootImage("energyBar4.png");
            performUltimate();
            if (waitTime == 0) {
                Player.energy = 0; 
            }
  
        } else {
            image = new GreenfootImage("energyBar1.png");
        }

        image.scale(135, 50);
        setImage(image);
    }

    private void performUltimate() {
        Player player = (Player) getWorld().getObjects(Player.class).get(0); // Get reference to the Player object
        int playerX = player.PlayerX; // Get the Player's X position
        int playerY = player.PlayerY; // Get the Player's Y position

        int ultimateCount = 5; 

        for (int i = 0; i < ultimateCount; i++) {
            int angle = i * 60; // Angle offset for each set of projectiles

            // First set of projectiles
            Projectile projectile1 = new Projectile(0);
            getWorld().addObject(projectile1, playerX, playerY);
            projectile1.setRotation(getRotation() + angle);
            projectile1.move(20);

            Projectile projectile2 = new Projectile(0);
            getWorld().addObject(projectile2, playerX, playerY);
            projectile2.setRotation(getRotation() + angle + 180);
            projectile2.move(20);

            // Second set of projectiles
            Projectile projectile3 = new Projectile(0);
            getWorld().addObject(projectile3, playerX, playerY);
            projectile3.setRotation(getRotation() + angle + 30);
            projectile3.move(20); 

            Projectile projectile4 = new Projectile(0);
            getWorld().addObject(projectile4, playerX, playerY);
            projectile4.setRotation(getRotation() + angle - 30);
            projectile4.move(20);

        }
    }

}
