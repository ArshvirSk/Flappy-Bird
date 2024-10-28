import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
    private final Game game;
    private final Image backgroundImage;
    private boolean inGame;

    public GamePanel() {
        game = new Game(this);
        addKeyListener(this);
        setFocusable(true);
        inGame = false;

        backgroundImage = Toolkit.getDefaultToolkit().getImage("resources/Background.png");
        setupButtons();
    }

    private void setupButtons() {
        JButton startButton = new JButton("start");
        startButton.setFont(new Font("Botsmatic Outline", Font.PLAIN, 16));
        JButton exitButton = new JButton("exit");
        exitButton.setFont(new Font("Botsmatic Outline", Font.PLAIN, 16));

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(startButton);
        add(exitButton);
        startButton.setBounds(350, 250, 100, 50);
        exitButton.setBounds(350, 320, 100, 50);
        setLayout(null);
    }

    void startGame() {
        inGame = true;
        game.reset();
        removeAll();
        revalidate();
        repaint();
    }

    public Game getGame() {
        return game;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        if (inGame) {
            game.draw(g);
            game.drawScore(g);
        } else {
            drawHomeScreen(g);
            if (game.isGameOver()) {
                game.drawGameOverScreen(g);
            }
        }
    }

    private void drawHomeScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Botsmatic Outline", Font.PLAIN, 72));
        g.drawString("flappy bird", 150, 150);
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