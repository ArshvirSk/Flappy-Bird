import java.awt.*;

public class Bird {
    private final int x;
    private final Image birdImage;
    private int y;
    private int velocity;

    public Bird(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.velocity = 0;

        birdImage = Toolkit.getDefaultToolkit().getImage("resources/bird.png");
    }

    public void jump() {
        velocity = -10;
    }

    public void update() {
        int gravity = 1;
        velocity += gravity;
        y += velocity;
    }

    public void draw(Graphics g) {
        g.drawImage(birdImage, x, y, 50, 35, null);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}