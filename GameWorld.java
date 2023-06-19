import greenfoot.*;

/* ------INSTRUCTIONS
 * Fairyland has been infested with jellies (green slimes) and your job is to slay as many 
 * jellies as you can. To move, use WASD keys and to shoot, press space. One space or shot
 * is equal to one dmg. The slime has 2 hp, so you'll need to shoot twice. The
 * Player has 6 hearts and if one slime touches you, one heart will be taken away. To collect 
 * the silver energy you see spawning on the ground, use the WASD to move and once you touch the 
 * energy, you will automatically collect them (energy bar will go up). Collecting three
 * energies will unleash an ultimate. To go to shop, press "p". 
 * 
 * Have Fun!
 * 
 * -------CREDITS
 * 
 * -----code-----
 * Ultimate projectile = Tanner Crow on Youtube
 * Shop World + greenfoot music = Jordan Cohen 35 minute shooter ver4
 * Spawning slimes = mrligocki on youtube
 * 
 * ----graphics-----
 * player = by Immunity on itch.io (https://immunitys.itch.io/pixel-top-down-character)
 * slimes = by bacteri on itch.io (https://bacteri.itch.io/slime-pack-anmations)
 * GameWorld background = by aku-jpkg on itch.io, tinted pink by me (https://aku-jpkg.itch.io/rpg-grassland-tileset)
 * All audio used = Omori official soundtrack
 * Silver energy = Daniel Chilonzi on RindPNG (https://www.kindpng.com/imgv/Jhmmib_lightning-bolt-png-pixel-lightning-pixel-art-transparent/)
 * Energy bar = vecteezy.com (https://www.vecteezy.com/vector-art/4829250-energy-bar-in-pixel-art-style)
 * speed potion = http://pixelartmaker.com/art/dd2caa5bf397e44
 * heart potion = craftChloe23 on redBubble
 * Instruct World bg = designed by me
 * arrow = by me
 * red splat = by Alabaster99 on reddit (https://www.reddit.com/r/PixelArt/comments/qfrpda/inktober_day_25_splat/)
 * Shop icon = by V O R T E X on adobe (https://stock.adobe.com/search?k=8+bit+shopping+cart&asset_id=526917681)
 * Welcome world background = by Grabrela on PIXILART (https://www.pixilart.com/art/magic-castle-b7915f03f7239a4)
 * Shop background = by Mann on gifer (https://gifer.com/en/3Mev)
 * endworld bg = designed by me
 * heart icon = from PNGITEM (https://www.pngitem.com/middle/hmibbwx_pink-pixel-heart-png-transparent-png/)
 */

public class GameWorld extends World {
    //initialize game variables and objects
    private GreenfootSound music;
    private Player player;
    private Slime slime;
    private int trapsSpawned;
    Counter counter;
    public Heart[] hearts; 
    private int maxHP;

    public GameWorld() {
        super(850, 550, 1);
        counter = new Counter();
        player = new Player();  
        trapsSpawned = 0;
        maxHP = player.getHP();
        hearts = new Heart[maxHP];
        
        //Create hearts to represent Player's health
        for (int i = 0; i < maxHP; i++) {
            hearts[i] = new Heart();
            addObject(hearts[i], 50 + i * 50, 30);
        }

        addObject(new CounterText(), 720, 55);
        addObject(counter, 800, 55);
        addObject(new Shop(), 60, 510);
        addObject(new Ultimate(), 95, 90);
        addObject(new ShopText(), 60, 465);
        music = new GreenfootSound ("White Surf Style 6.mp3");
        music.playLoop();
        //set the order in which objects are painted on the screen
        setPaintOrder(Counter.class, CounterText.class, Heart.class, Shop.class, ShopText.class, Ultimate.class, Player.class);
    }

    public void act() {
        spawnSlime();
        addObject(player, getWidth() / 2, getHeight() / 2);
        spawnEnergy();
        spawnTrap();
        int currentHP = player.getHP(); //gets players hp 

        if (currentHP < maxHP) {
            //update the heart array based on player's current hp.
            for (int i = currentHP; i < maxHP; i++) {
                removeObject(hearts[i]);
            }
        }
        
        //if players hp is 0, then the world will be set the endworld to display players final score
        if (currentHP <= 0) {
            Greenfoot.delay(80); 
            music.stop();
            Greenfoot.setWorld(new EndWorld(counter));
        }
        
        //enter shop world
        if (Greenfoot.isKeyDown("p")) {
            ShopWorld sw = new ShopWorld(this, counter);
            Greenfoot.setWorld(sw);
        }
    }

    public void spawnSlime() {
        int y = Greenfoot.getRandomNumber(getHeight());
        if (Greenfoot.getRandomNumber(250) == 1) {
            addObject(new Slime(counter), -10, y); // Spawn on the left side
        }  
        if (Greenfoot.getRandomNumber(250) == 1) {
            addObject(new Slime(counter), getWidth(), y); //spawn on the right side
        }
    }

    public void spawnEnergy() {
        //spawn randomly throughout the world
        int y = Greenfoot.getRandomNumber(getHeight());
        int x = Greenfoot.getRandomNumber(getWidth());
        if (Greenfoot.getRandomNumber(370) == 1) {
            addObject(new Energy(), x, y); 
        }          
    }

    public void spawnTrap()
    {
        if (trapsSpawned < 3)
        {
            //calculate the number of traps to spawn
            int trapsToSpawn = 3 - trapsSpawned;
            //this loop spawns the calculated number of traps
            for (int i = 0; i < trapsToSpawn; i++)
            {
                int x = Greenfoot.getRandomNumber(getWidth());
                int y = Greenfoot.getRandomNumber(getHeight());
                //check if there are no traps at the generated coordinate
                //if the space is empty, then add a new trap, then add the amount of traps being
                //spawned to get exactly 3 traps in the world
                if (getObjectsAt(x, y, trap.class).isEmpty())
                {
                    addObject(new trap(), x, y);
                    trapsSpawned++;
                }
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void addHeart() {
        player.addHeart();
    }

    public void upgradeSpeed() {
        player.upgradeSpeed();
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
