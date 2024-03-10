package game;

import city.cs.engine.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter {
    private Walker player;
    private float walkingSpeed = 5;
    private float jumpingSpeed = 10;

    public GameController(Walker player) {
        this.player = player;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_LEFT:
                player.startWalking(-walkingSpeed);
                break;
            case KeyEvent.VK_RIGHT:
                player.startWalking(walkingSpeed);
                break;
            case KeyEvent.VK_SPACE:
                player.jump(jumpingSpeed);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
            player.stopWalking();
        }
    }
}
