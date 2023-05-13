/* TRexGame.java

    Built in CS2 -- Final Project
    By Jackson Deutch
    May. 2023 @ Menlo School
*/

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TRexGame implements ActionListener, KeyListener
{
    /** Instance Variables **/
    private static final int SLEEP_TIME = 80;
    private TRex tRex;
    private TRexGameViewer window;

    // Enemy sprites
    private Obstacle obstacles[];
    private Cactus c1;
    private Cactus c2;
    private Bird b1;
    private Bird b2;

    // Game tracking variables
    private boolean alive;
    private int score;
    private int highScore;
    private int numCycles;

    public TRexGame()
    {
        window = new TRexGameViewer(this);
        tRex = new TRex(window);

        alive = true;
        score = 0;
        highScore = 0;

        c1 = new Cactus(this, window, 0);
        c2 = new Cactus(this, window, 1);
        b1 = new Bird(this, window);
        b2 = new Bird(this, window);

        // Put enemies into an array for later use
        obstacles = new Obstacle[4];
        obstacles[0] = c1;
        obstacles[1] = c2;
        obstacles[2] = b1;
        obstacles[3] = b2;

        // Activate keyListener
        window.addKeyListener(this);
    }
    public static void main(String[] args)
    {
        TRexGame t = new TRexGame();
        Timer clock = new Timer(SLEEP_TIME, t);
        clock.start();
    }

    // Resets all game variables including the tRex and enemies
    public void reset()
    {
        for (Obstacle o: obstacles)
        {
            o.reset();
        }
        tRex.reset();

        alive = true;
        score = 0;
        numCycles = 0;
    }

    // If game is over, game variables and high score are updated
    public void endGame()
    {
        alive = false;
        if (score > highScore)
        {
            highScore = score;
        }
    }

    // Get game score of current play
    public int getScore(){
        return score;
    }

    // Get high score
    public int getHighScore()
    {
        return highScore;
    }

    // Get tRex object
    public TRex gettRex()
    {
        return tRex;
    }

    // Returns state of tRex
    public boolean isAlive(){
        return alive;
    }

    // Updates score if game is in play
    public void addScore()
    {
        if (isAlive())
        {
            score++;
        }
    }

    // Returns array of enemy sprites
    public Obstacle[] getObstacles(){
        return obstacles;
    }

    // Randomly selects an index from the obstacles array
    public int selectEnemy()
    {
        return (int)(Math.random() * 4);
    }

    // Action Performed function
    @Override
    public void actionPerformed(ActionEvent e)
    {
        window.repaint();

        // Animation for when tRex is jumping or ducking
        if (tRex.getIsJumping())
        {
            tRex.jump();
        }
        if (tRex.getIsDucking())
        {
            tRex.duck();
        }

        // Randomly inserts and an enemy sprite onto the screen
        if (numCycles % 12 == 0)
        {
            int index = selectEnemy();
            while(!obstacles[index].isInPlay())
            {
                obstacles[index].setInPlay(true);
            }
        }

        // Moves enemy sprites that are in play across screen
        for (Obstacle o: obstacles)
        {
            if (o.isInPlay())
            {
                o.move();
            }
            // Checks if game has been won through isTouching method
            // Ends game if tRex is touching an object that is in play
            if (tRex.isTouching(o) && o.isInPlay())
            {
                endGame();
            }
        }
        numCycles++;
        window.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    // Key Pressed function
    @Override
    public void keyPressed(KeyEvent e)
    {
        int keyCode = e.getKeyCode();

        // Key code for jumping (up arrow or space bar)
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_SPACE)
        {
            tRex.setJumping(true);
            tRex.setDucking(false);
        }

        // Key code for ducking (down arrow)
        if (keyCode == KeyEvent.VK_DOWN)
        {
            tRex.setDucking(true);
        }

        // Key code for resetting ('R')
        if(keyCode == KeyEvent.VK_R)
        {
            reset();
        }
        window.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}