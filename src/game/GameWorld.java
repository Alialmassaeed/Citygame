package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.util.ArrayList;
import java.util.List;

import java.util.Random;


public class GameWorld extends World {
    private Walker player;
    private List<Enemy> enemies;
    private List<StaticBody> platforms;
    private int playerLives; // Track the player's lives
    private int lives = 10; // Add a lives property to track player lives
    private Random random = new Random();

    public GameWorld() {
        super();
        enemies = new ArrayList<>(); // Initialize the enemies list


        enemies = new ArrayList<>();
        platforms = new ArrayList<>();
        setGravity(10);
        playerLives = 3; // Initialize the player with 3 lives

        // Ground
        StaticBody ground = new StaticBody(this, new BoxShape(100, 0.5f));
        ground.setPosition(new Vec2(0, -11.5f));

        // Player
        Shape playerShape = new PolygonShape(-0.5f,-1.0f, 0.5f,-1.0f, 0.5f,1.0f, -0.5f,1.0f);
        player = new Walker(this, playerShape);
        player.setPosition(new Vec2(0, -10));
        player.addImage(new BodyImage("/Users/fahad_ishaq/eclipse-workspace/Game_2D/src/game/images/razaface3.png", 2)); // Update with actual player image path
        spawnEnemiesRandomly(1); // Spawn 3 enemies initially, for example

        // Enemies
        for (int i = 0; i < 3; i++) {
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(i * 5 - 10, -10));
            enemies.add(enemy);
        }

        // Platforms
        for (int i = 0; i < 5; i++) {
            StaticBody platform = new StaticBody(this, new BoxShape(3, 0.5f));
            platform.setPosition(new Vec2(i * 6 - 12, -8 + i));
            platforms.add(platform);
        }
    }
    // Method to spawn a given number of enemies at random locations
    private void spawnEnemiesRandomly(int numberOfEnemies) {
        for (int i = 0; i < numberOfEnemies; i++) {
            float x = -10 + random.nextFloat() * 20; // Random x between -10 and 10
            float y = -10 + random.nextFloat() * 20; // Random y between -10 and 10
            Enemy enemy = new Enemy(this);
            enemy.setPosition(new Vec2(x, y));
            enemies.add(enemy);
        }
    }
    public void moveEnemiesRandomly() {
        for (Enemy enemy : enemies) {
            float speed = -5 + random.nextFloat() * 10; // Random speed between -5 and 5
            enemy.startWalking(speed);
            // Randomly change direction at intervals
            int changeTime = 1000 + random.nextInt(2000); // Change every 1-3 seconds
            new javax.swing.Timer(changeTime, e -> enemy.startWalking(-speed)).start();
        }
    }
    // Call this in a timer to keep spawning enemies
    public void randomEnemySpawn() {
        if (random.nextFloat() < 0.1) { // 10% chance to spawn an enemy each call
            spawnEnemiesRandomly(1);
        }
    }

    // Called when the player collides with an enemy
    public void playerHit() {
        lives--;
        System.out.println("Player has been hit! Lives left: " + lives); // Debug output, replace with actual game logic

        if (lives <= 0) {
            gameOver();
        }
    }
    // Method to call when game is over
    private void gameOver() {
        System.out.println("Game Over!"); // Debug output, replace with actual game logic
        // Stop the world from updating
        stop();
    }

    // Getters
    public Walker getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
 // Getters and setters for lives
    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }


    public List<StaticBody> getPlatforms() {
        return platforms;
    }

    public int getPlayerLives() {
        return playerLives;
    }
}
