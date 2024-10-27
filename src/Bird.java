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

        // Debug: Draw bounding box with label
//        g.setColor(Color.RED);
//        g.drawRect(x, y, 50, 35); // Bounding box for the bird
//        g.drawString("Bird", x, y - 5); // Label the box for clarity
    }

    public Rectangle getBounds() {
        int width = 50;
        int height = 35;
        return new Rectangle(x, y, width, height); // Create a bounding box for the bird
    }

    public int getY() {
        return y;
    }

    public void setY(int newY) {
        this.y = newY;
    }

    public int getX() {
        return x;
    }

    public void setVelocity(int newVelocity) {
        this.velocity = newVelocity;
    }
}
