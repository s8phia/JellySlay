import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class trap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class trap extends Actor
{
    private static int instances = 0;
    
    public trap() {
        instances ++;
        GreenfootImage image = getImage();
        image.scale(50, 50);
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
}
