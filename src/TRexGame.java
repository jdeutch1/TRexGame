import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TRexGame implements ActionListener, KeyListener {
    private static final int SLEEP_TIME = 110;

    private TRex tRex;
    private Cactus c1;
    private Cactus c2;
    private Bird b1;
    private Bird b2;
    private TRexGameViewer window;
    private Cactus cactus[];
    private Obstacle obstacles[];
    private boolean alive;

    private int score;

    public TRexGame() {
        alive = true;
        score = 0;

        window = new TRexGameViewer(this);
        tRex = new TRex(window);
        c1 = new Cactus(this, window, 0);
        c2 = new Cactus(this, window, 1);
        b1 = new Bird(this, window);
        b2 = new Bird(this, window);

        cactus = new Cactus[2];
        cactus[0] = c1;
        cactus[1] = c2;

        obstacles = new Obstacle[4];
        obstacles[0] = c1;
        obstacles[1] = c2;
        obstacles[2] = b1;
        obstacles[3] = b2;
        window.addKeyListener(this);
    }
    public static void main(String[] args) {
        TRexGame t = new TRexGame();
        Timer clock = new Timer(SLEEP_TIME, t);
        clock.start();
    }

    public TRex gettRex()
    {
        return tRex;
    }

    public void endGame(){
        alive = false;
    }

    public boolean isAlive(){
        return alive;
    }

    public int getScore(){
        return score;
    }

    public void addScore()
    {
        score++;
    }

    public Obstacle[] getObstacles(){
        return obstacles;
    }

    public int selectEnemy()
    {
        return (int)(Math.random() * 4);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (tRex.getIsJumping())
        {
            tRex.jump();
        }
        int index = selectEnemy();
        while(obstacles[index].isInPLay())
        {
            obstacles[index].setInPlay(true);
        }
        for (Obstacle o: obstacles)
        {
            if (o.isInPLay())
            {
                o.move();
            }
            if (tRex.isTouching(o))
            {
                endGame();
            }
        }
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_UP)
        {
            tRex.setJumping(true);
        }
        window.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}