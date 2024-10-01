import java.awt.*;
import java.util.ArrayList;
import java.awt.Font;

public class Game {
    private final Bird bird;
    private final ArrayList<Pipe> pipes;
    Font font = new Font("Arial", Font.BOLD, 48);
    private boolean gameOver;

    public Game() {
        bird = new Bird(100, 300);
        pipes = new ArrayList<>();
        gameOver = false;
        pipes.add(new Pipe(400, 390, 200, 150));
    }

    public void update() {
        if (!gameOver) {
            bird.update();

            for (Pipe pipe : pipes) {
                pipe.update();
                if (pipe.isOffScreen()) {
                    pipes.remove(pipe);
                    pipes.add(new Pipe(800, 390, (int) (Math.random() * 300) + 100, 150));
                }
            }

            checkCollisions();
        }
    }

    public Bird getBird() {
        return bird;
    }

    private void checkCollisions() {
        for (Pipe pipe : pipes) {
            // Print the positions for debugging
            // System.out.println("Bird Position: (" + bird.getX() + ", " + bird.getY() + ")");
            // System.out.println("Pipe Position: (" + pipe.getX() + ", " + pipe.getHeight() + ")");

            if (bird.getX() + 30 > pipe.getX() && bird.getX() < pipe.getX() + 270 && (bird.getY() < pipe.getHeight() || bird.getY() + 30 > pipe.getHeight() + pipe.getGap())) {
                gameOver = true;
                System.out.println("Bird Position: (" + bird.getX() + ", " + bird.getY() + ")");
                System.out.println("Pipe Position: (" + pipe.getX() + ", " + pipe.getHeight() + ")");
                System.out.println("Collision detected!");
                break;
            }
        }

        // Check if the bird is out of bounds
        if (bird.getY() > 570 || bird.getY() < 0) {
            gameOver = true; // Set game over flag if the bird goes out of the screen
            System.out.println("Out of bounds!"); // Debug message
        }
    }

    public void draw(Graphics g) {
        bird.draw(g);
        for (Pipe pipe : pipes) {
            pipe.draw(g);
        }
        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString("Game Over!", 250, 300);
        }
    }


}