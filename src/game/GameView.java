package game;

import city.cs.engine.*;

import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {
    private Image background;
    private int lives;
    private int score;
    
    private boolean gameOver; // Add a flag to determine if the game is over


    public GameView(World world, int width, int height, int lives, int score) {
        super(world, width, height);
        this.background = new ImageIcon("/Users/fahad_ishaq/eclipse-workspace/Game_2D/src/game/images/modern-futuristic-sci-fi-background.jpg").getImage();
        this.lives = lives;
        this.score = score;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.drawString("Lives: " + lives, 10, 20);
        g.drawString("Score: " + score, 10, 40);

        if (gameOver) {
            g.drawString("Game Over!", getWidth() / 2, getHeight() / 2); // Display game over message
        }
    }

    public void setLives(int lives) {
        this.lives = lives;
        repaint();
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        repaint(); // Repaint to show game over message
    }
    public void setScore(int score) {
        this.score = score;
        repaint();
    }
}
