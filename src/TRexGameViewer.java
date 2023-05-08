import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class TRexGameViewer extends JFrame{

    //to fine
    private Image desertBackground;

    private Image tRexImage;
    private final int WINDOW_WIDTH = 1000;
    private final int WINDOW_HEIGHT = 1000;
    private TRexGame t;


    public TRexGameViewer(TRexGame t)
    {
        desertBackground = new ImageIcon("Resources/Background.png").getImage();
        tRexImage = new ImageIcon("Resources/TRexUp.png").getImage();
        this.t = t;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TRexGame");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
        createBufferStrategy(2);
    }

    public void paint(Graphics g) {
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

    public void myPaint(Graphics g) {
        if (t.isAlive()) {
            g.drawImage(desertBackground, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            String score = "Score: " + Integer.toString(t.getScore());
            g.drawString(score, 450, 100);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            g.drawString("TRex Game", 400, 75);
            t.gettRex().draw(g);
            for (Obstacle o : t.getObstacles()) {
                if (o.isInPLay()){
                    o.draw(g);
                }
            }
        }
        else {
            g.setFont(new Font("SansSerif", Font.BOLD, 50));
            g.drawString("GAME OVER", 300, 500);
        }
    }
}
