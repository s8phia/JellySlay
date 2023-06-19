import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Projectile extends Actor
{
    private int speed;
    private int rotation;
    
    public Projectile (int rotation) {
        //set rotation to rotation of player
        speed = 7;
        this.rotation = rotation;
        setRotation(rotation);
    }
    

    public void act()
    {
        move(speed);
        if(isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
