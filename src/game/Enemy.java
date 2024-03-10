package game;

import city.cs.engine.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Enemy extends Walker {
    private static final Shape enemyShape = new PolygonShape(-1, -1, 1, -1, 1, 1, -1, 1);
    private static final BodyImage[] walkingImages = {
        new BodyImage("/Users/fahad_ishaq/eclipse-workspace/Game_2D/src/game/images/__Bat02_Idle_011.png", 2),
//        new BodyImage("/Users/fahad_ishaq/eclipse-workspace/Game_2D/src/game/images/__Bat02_Idle_011.png", 2),
    };
    private int currentImageIndex = 0;
    private Timer animationTimer;
    private GameWorld world;

    public Enemy(final GameWorld world) {
        super(world, enemyShape);
        this.world = world; // Store the reference to the GameWorld
        addImage(walkingImages[currentImageIndex]); // Set the initial image from the walkingImages array

        // Set up the animation timer
        animationTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                animate();
            }
        });
        animationTimer.start();

        // Add a collision listener to handle the collisions
        this.addCollisionListener(new CollisionListener() {
            public void collide(CollisionEvent e) {
                if (e.getOtherBody() == world.getPlayer()) {
                    world.playerHit(); // Call the playerHit method on GameWorld
                }
            }
        });
    }

    // Method to make the enemy move
    public void move(float speed) {
        startWalking(speed);
    }

    // Method to animate the enemy
    public void animate() {
        removeAllImages();
        currentImageIndex = (currentImageIndex + 1) % walkingImages.length;
        addImage(walkingImages[currentImageIndex]);
    }
}
