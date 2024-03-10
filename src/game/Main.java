package game;

import city.cs.engine.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create the game world
        final GameWorld world = new GameWorld();

        // Create the game view
        final GameView view = new GameView(world, 800, 600, 3, 0);
        view.setZoom(20);

        // Set up the JFrame
        JFrame frame = new JFrame("My 2D Game");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Add keyboard control
        GameController controller = new GameController(world.getPlayer());
        frame.addKeyListener(controller);

        // Start the game
        world.start();

        // Timer to move enemies randomly
        Timer enemyMovementTimer = new Timer(10000, e -> world.moveEnemiesRandomly());
        enemyMovementTimer.start();

        // Timer to spawn enemies randomly
        Timer enemySpawnTimer = new Timer(20000, e -> world.randomEnemySpawn());
        enemySpawnTimer.start();

        // Add a step listener to check for game over
        world.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent e) {
                // Check game state before each physics step
            }

            @Override
            public void postStep(StepEvent e) {
                // Check if the game is over
                if (world.getLives() <= 0) {
                    enemyMovementTimer.stop();
                    enemySpawnTimer.stop();
                    world.stop();
                    frame.removeKeyListener(controller);
                    JOptionPane.showMessageDialog(frame, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
            }
        });
    }
}
