import greenfoot.*;

public class WelcomeWorld extends World
{
    private GreenfootSound bgm;
    public WelcomeWorld()
    {    
        super(850, 550, 1); // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        resizeBackgroundImage();
        bgm = new GreenfootSound ("Let's Get Together Now!.mp3");

    }

    private void resizeBackgroundImage() {
        GreenfootImage backgroundImage = getBackground();

        int worldWidth = getWidth();
        int worldHeight = getHeight();

        backgroundImage.scale(worldWidth, worldHeight);
    }

    public void act () {
        if (Greenfoot.isKeyDown("enter")) {
            bgm.stop();
            Greenfoot.setWorld(new GameWorld());
        }

        if(Greenfoot.isKeyDown("i")){
            Greenfoot.setWorld(new Instruction(this));
        }
    }
    
    public void started () {
        if (bgm != null){
            bgm.playLoop();
        }
    }

    public void stopped () {
        if (bgm!= null){
            bgm.pause();
        }
    }
    
}
