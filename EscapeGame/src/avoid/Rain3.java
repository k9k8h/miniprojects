package avoid;

import java.awt.*;
import java.util.Random;

public class Rain3 {
    int x = 0;
    int y = 0;
    int ySpeed = 3;
    public static final int RADIUS = 20;
    Color color;
    GameBoard gameBoard;

    public Rain3(Color color, GameBoard gameBoard) {
        this.color = color;
        this.gameBoard = gameBoard;
        Random rand = new Random();
        this.x = rand.nextInt(gameBoard.getWidth() - 2 * RADIUS);
        this.y = -2 * RADIUS; // 화면 밖 위에서 시작
    }

    public void move() {
        y += ySpeed;

        // 비가 화면 아래로 내려가면 다시 위로 올림
        if (y > gameBoard.getHeight()) {
            Random rand = new Random();
            y = -2 * RADIUS;
            x = rand.nextInt(gameBoard.getWidth() - 2 * RADIUS);
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval(x,y,RADIUS,RADIUS);
    }

}


