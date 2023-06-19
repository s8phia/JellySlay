import greenfoot.*;

public class ShopWorld extends World {
    private GameWorld gameWorld;
    private Button upgradeHeartButton;
    private Button upgradeSpeedButton;
    private Button upgradeStrengthButton;
    private Button returnButton;
    private Counter counter;

    public ShopWorld(GameWorld gameWorld, Counter counter) {
        super(850, 550, 1);
        this.gameWorld = gameWorld;
        this.counter = counter;

        upgradeHeartButton = new Button("Buy");
        upgradeSpeedButton = new Button("Buy");
        returnButton = new Button("Return to Game");

        addObject(upgradeHeartButton, 500, 165);
        addObject(upgradeSpeedButton, 500, 350);
        addObject(returnButton, 400, 520);
    }

    public void act() {
        if (Greenfoot.mouseClicked(upgradeHeartButton)) {
            if (counter.coins >= 20) {
                counter.coins -= 20;
                gameWorld.addHeart();
            }
        }
        if (Greenfoot.mouseClicked(upgradeSpeedButton)) {
            if (counter.coins >= 30) {
                counter.coins -= 30;
                gameWorld.upgradeSpeed();
            }
        }
        if (Greenfoot.mouseClicked(returnButton)) {
            Greenfoot.setWorld(gameWorld);
        }
    }

 

}
