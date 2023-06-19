import greenfoot.*;

public class Counter extends Actor {
    int score;  // Variable to store the score
    int coins;  // Variable to store the number of coins

    public Counter() {

    }

    public void act() {
        // Create a new GreenfootImage object with the current score and coins
        // The image is displayed as text, with a font size of 35, white color,
        // and a transparent background (black color with alpha value of 0)
        setImage(new GreenfootImage(score + "\n" + coins, 35, Color.WHITE, new Color(0, 0, 0, 0)));
    }

    public void addCoins(int amount) {
        // Method to add a specified amount of coins to the current count
        coins += amount;
    }
}
