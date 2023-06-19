import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Instruction extends World
{
    private WelcomeWorld welcomeWorld;
    private Button welcomeWorldButton;

    public Instruction(WelcomeWorld welcomeWorld)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(850, 550, 1);
        this.welcomeWorld = welcomeWorld;

        welcomeWorldButton = new Button("Go Back");

        addObject(welcomeWorldButton, 430, 520);
    }

    public void act() {
        if (Greenfoot.mouseClicked(welcomeWorldButton)) {
            Greenfoot.setWorld(welcomeWorld);
        }
    }
    
}
