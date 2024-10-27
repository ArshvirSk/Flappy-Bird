import java.awt.*;

public class Pipe {
    private final int width;
    private final int height;
    private final int gap;
    private final Image topPipeImage;
    private final Image bottomPipeImage;
    private int x;

    public Pipe(int startX, int width, int height, int gap) {
        topPipeImage = Toolkit.getDefaultToolkit().getImage("resources/pipeTop.png");
        bottomPipeImage = Toolkit.getDefaultToolkit().getImage("resources/pipeBottom.png");

        this.x = startX;
        this.height = height;
        this.gap = gap;
        this.width = width;
    }

    public void update() {
        int speed = 10;
        x -= speed;
    }

    public void draw(Graphics g) {
        g.drawImage(topPipeImage, x, 0, width, height, null);
        g.drawImage(bottomPipeImage, x, height + gap, width, 600 - (height + gap), null);

        // Debug: Draw bounding boxes with labels
//        g.setColor(Color.GREEN);
//        g.drawRect(x, 0, width, height); // Top pipe bounding box
//        g.drawString("Top Pipe", x, height - 5); // Label the top pipe box
//
//        g.drawRect(x, height + gap, width, 600 - (height + gap)); // Bottom pipe bounding box
//        g.drawString("Bottom Pipe", x, height + gap + 15); // Label the bottom pipe box
    }

    public Rectangle getTopBounds() {
        return new Rectangle(x, 0, width, height);
    }

    public Rectangle getBottomBounds() {
        return new Rectangle(x, height + gap, width, 600 - height - gap);
    }

    public int getHeight() {
        return height;
    }

    public int getGap() {
        return gap;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }

    public boolean isOffScreen() {
        return x + width < 0;
    }
}
