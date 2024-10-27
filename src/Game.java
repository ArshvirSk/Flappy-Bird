import java.awt.*;
import java.util.ArrayList;

public class Game {
    private final Bird bird;
    private final ArrayList<Pipe> pipes;
    private int score;
    private boolean gameOver;

    public Game() {
        bird = new Bird(100, 300);
        pipes = new ArrayList<>();
        gameOver = false;
        score = 0;
        pipes.add(new Pipe(400, 100, 200, 100));
    }

    public void reset() {
        pipes.clear();
        pipes.add(new Pipe(400, 100, 200, 100));
        bird.setY(300);
        bird.setVelocity(0);
        score = 0;
        gameOver = false;
    }

    public void update() {
        if (!gameOver) {
            bird.update();

            for (Pipe pipe : pipes) {
                pipe.update();
                if (pipe.isOffScreen()) {
                    pipes.remove(pipe);
                    pipes.add(new Pipe(800, 100, (int) (Math.random() * 300) + 100, 100));
                    score++;
                }
            }

            checkCollisions();
        }
    }

    public int getScore() {
        return score;
    }

    public Bird getBird() {
        return bird;
    }

    private void checkCollisions() {
        Rectangle birdBounds = new Rectangle(bird.getX(), bird.getY(), 50, 35);
        for (Pipe pipe : pipes) {
            Rectangle topPipeBounds = new Rectangle(pipe.getX(), 0, pipe.getWidth(), pipe.getHeight());
            Rectangle bottomPipeBounds = new Rectangle(pipe.getX(), pipe.getHeight() + pipe.getGap(), pipe.getWidth(), 600 - (pipe.getHeight() + pipe.getGap()));

            if (topPipeBounds.intersects(birdBounds) || bottomPipeBounds.intersects(birdBounds)) {
                System.out.println("Bird Bounds: " + birdBounds);
                System.out.println("Top Pipe Bounds: " + topPipeBounds);
                System.out.println("Bottom Pipe Bounds: " + bottomPipeBounds);
                gameOver = true;
                System.out.println("Collision detected!");
                break;
            }
        }

        if (bird.getY() > 570 || bird.getY() < 0) {
            gameOver = true;
            System.out.println("Out of bounds!");
        }
    }

    public void draw(Graphics g) {
        bird.draw(g);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Botsmatic Outline", Font.BOLD, 48));
            g.drawString("game over", 250, 300);
        }
    }
}
