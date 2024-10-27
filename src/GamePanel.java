import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements KeyListener {
    private final Game game;
    private final Image backgroundImage;
    private boolean inGame; // To track if the game is ongoing

    public GamePanel() {
        game = new Game();
        addKeyListener(this);
        setFocusable(true);
        inGame = false; // Start with the home screen

        backgroundImage = Toolkit.getDefaultToolkit().getImage("resources/Background.png");
        setupButtons();
    }

    // Setting up the buttons on the home screen
    private void setupButtons() {
        JButton startButton = new JButton("start");
        startButton.setFont(new Font("Botsmatic Outline", Font.PLAIN, 16));
        JButton exitButton = new JButton("exit");
        exitButton.setFont(new Font("Botsmatic Outline", Font.PLAIN, 16));


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame(); // Start the game when the button is clicked
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        add(startButton);
        add(exitButton);
        startButton.setBounds(350, 250, 100, 50);
        exitButton.setBounds(350, 320, 100, 50);
        setLayout(null); // Use null layout to position buttons manually
    }

    // Method to start the game
    private void startGame() {
        inGame = true; // Set game state to ongoing
        game.reset(); // Reset the game
        removeAll(); // Remove buttons
        revalidate();
        repaint();
    }

    // Getter method to access the Game object
    public Game getGame() {
        return game;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        if (inGame) {
            game.draw(g);
            drawScore(g); // Draw the score while in-game
        } else {
            drawHomeScreen(g); // Draw home screen when game is not ongoing
        }
    }

    // Method to draw the home screen
    private void drawHomeScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Botsmatic Outline", Font.PLAIN, 72));
        g.drawString("flappy bird", 150, 150);
    }

    // Method to draw the score
    private void drawScore(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Botsmatic Outline", Font.BOLD, 24));
        g.drawString("score ", 20, 50);
        g.setFont(new Font("Flappy Bird Font", Font.BOLD, 24));
        g.drawString("" + game.getScore(), 120, 50);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && inGame) {
            game.getBird().jump();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
}
