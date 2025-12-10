package avoid;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Character extends JPanel {
    int x = 0;
    int y = 0;
    private JLabel imgLabel;
    int xSpeed = 1;
    GameBoard gameBoard;


    public Character(int x, int y, String imgName, GameBoard gameBoard) {
        this.x = x;
        this.y = y;
        this.gameBoard = gameBoard;

        this.setOpaque(false);
        this.setLayout(null);

        imgLabel = new JLabel();
        imgLabel.setIcon(new ImageIcon(imgName));
        imgLabel.setBounds(0,0,100,200);
        add(imgLabel);

        setBounds(x, y, 100, 200);
    }

    public void move() {
        if(x+xSpeed > 0 && x+xSpeed < gameBoard.getWidth()-100) {
            x=x+xSpeed;
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            xSpeed = -3;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            xSpeed = 3;
        }
    }

    public void keyReleased(KeyEvent e) {
        xSpeed = 0;
    }

}
