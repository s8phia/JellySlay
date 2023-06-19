import greenfoot.*;

public class EndWorld extends World {
    private GreenfootSound music;
    private Counter counter;
    private Button endButton;
    private Button playButton;

    public EndWorld(Counter counter) {
        super(850, 550, 1);
        this.counter = counter;

        endButton = new Button("");
        playButton = new Button("");

        addObject(endButton, 640, 415);
        addObject(playButton, 250, 415);

        music = new GreenfootSound ("A Home For Flowers (Empty).mp3");
        music.playLoop();
    }

    public void act() {
        showScore();
        if (Greenfoot.mouseClicked(endButton)) {
            music.stop();
            Greenfoot.stop();
        }
        if (Greenfoot.mouseClicked(playButton)) {
            music.stop();
            resetGame();
            Greenfoot.stop();
        }
    }

    private void showScore() {
        GreenfootImage background = getBackground();
        GreenfootImage text = new GreenfootImage(" " + counter.score, 60, Color.WHITE, null);
        background.drawImage(text, 500, 274);
    }

    private void resetGame() {
        Greenfoot.setWorld(new WelcomeWorld());
    }

    public void started () {
        if (music != null){
            music.playLoop();
        }
    }

    public void stopped () {
        if (music != null){
            music.pause();
        }
    }

}
