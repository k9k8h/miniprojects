package avoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameBoard extends JPanel implements KeyListener {
    Rain rain;
    Rain2 rain2;
    Rain3 rain3;
    Character character;
    private boolean isGameOver = false;

    public GameBoard() {
        character = new Character(500,750,"character.png",this);
        this.add(character);
        this.setBackground(Color.GREEN);
        this.setLayout(null);
        setFocusable(true);
        addKeyListener(this);
    }

    public void setRain(Rain rain, Rain2 rain2, Rain3 rain3) {
        this.rain = rain;
        this.rain2 = rain2;
        this.rain3 = rain3;
    }

    private void move() {
        if(!isGameOver) {
            rain.move();
            rain2.move();
            rain3.move();
            character.move();
            character.setBounds(character.x, character.y, 100, 200);

            if(checkCollision() || checkCollision2() || checkCollision3()) {
                isGameOver = true;
            }

        }
        this.repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        rain.draw(g2d);
        rain2.draw(g2d);
        rain3.draw(g2d);

        if(isGameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            String gameOverMsg = "Game Over!";
            FontMetrics metrics = g2d.getFontMetrics();
            int x = (getWidth() - metrics.stringWidth(gameOverMsg)) / 2;
            int y = (getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
            g2d.drawString(gameOverMsg, x, y);
        }
    }

    private boolean checkCollision() {
        // 비와 캐릭터의 경계(bounds)를 Rectange 객체로 만듭니다.
        Rectangle rainBounds = new Rectangle(rain.x, rain.y, rain.RADIUS * 2, rain.RADIUS * 2);
        Rectangle characterBounds = character.getBounds();

        // 두 객체의 경계가 겹치는지 확인합니다.
        return rainBounds.intersects(characterBounds);
    }
    private boolean checkCollision2() {
        // 비와 캐릭터의 경계(bounds)를 Rectange 객체로 만듭니다.
        Rectangle rainBounds2 = new Rectangle(rain2.x, rain2.y, rain2.RADIUS * 2, rain2.RADIUS * 2);
        Rectangle characterBounds = character.getBounds();

        // 두 객체의 경계가 겹치는지 확인합니다.
        return rainBounds2.intersects(characterBounds);
    }
    private boolean checkCollision3() {
        // 비와 캐릭터의 경계(bounds)를 Rectange 객체로 만듭니다.
        Rectangle rainBounds3 = new Rectangle(rain3.x, rain3.y, rain3.RADIUS * 2, rain3.RADIUS * 2);
        Rectangle characterBounds = character.getBounds();

        // 두 객체의 경계가 겹치는지 확인합니다.
        return rainBounds3.intersects(characterBounds);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        character.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        character.keyReleased(e);
    }

    public static void main(String[] args) {
        JFrame ball = new JFrame("공피하기");
        ball.setSize(100, 100);
        ball.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameBoard game = new GameBoard();

        ball.add(game);
        ball.setVisible(true);

        Rain rain = new Rain(Color.BLUE, game);
        Rain2 rain2 = new Rain2(Color.BLUE, game);
        Rain3 rain3 = new Rain3(Color.BLUE, game);
        game.setRain(rain, rain2, rain3);

        while (true) {
            game.move();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
