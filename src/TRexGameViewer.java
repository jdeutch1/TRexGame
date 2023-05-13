import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class TRexGameViewer extends JFrame
{
    /** Instance Variables **/
    private Image desertBackground;
    private Image instructions;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private TRexGame t;

    public TRexGameViewer(TRexGame t)
    {
        desertBackground = new ImageIcon("Resources/Background.png").getImage();
        instructions = new ImageIcon("Resources/Instructions.png").getImage();

        this.t = t;

        // JFrame required variables
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TRexGame");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    // Buffering
    public void paint(Graphics g)
    {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;
        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    // Paint method for game
    public void myPaint(Graphics g)
    {
        // Create strings for game score and high score
        String score = "Score: " + Integer.toString(t.getScore());
        String highScore = "High Score: " + Integer.toString(t.getHighScore());

        // Draw game board when game is in play
        if (t.isAlive())
        {
            // Draw background
            g.drawImage(desertBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);

            // Draw scores and title
            g.drawString(score, 450, 100);
            g.drawString(highScore, 900, 50);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.drawString("TRex Game", 400, 75);

            // Draw tRex and all enemies
            t.gettRex().draw(g);
            for (Obstacle o : t.getObstacles())
            {
                if (o.isInPlay())
                {
                    o.draw(g);
                }
            }
        }
        else
        {
            // If game is over, draw instructions and scores

            // Creates blank screen
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 50));

            // Draw scores
            g.drawString(highScore, 290, 120);
            g.drawString(score, 340, 200);

            // Draw instructions
            g.drawImage(instructions, 195, 210, 560, 335, this);
            g.drawString("GAME OVER", 300, 575);
            g.drawString("Press 'R' To Restart", 205, 650);
        }
    }
}
