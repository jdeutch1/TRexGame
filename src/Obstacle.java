import java.awt.*;

public class Obstacle
{
    /** Instance Variables **/
    private int x, y, dx;

    // Keeps track of the initial values for these variables
    // Done because the x and dx values are altered during run time
    private int initX;
    private int initdX;

    // Marks if obstacle is on the board
    private boolean inPlay;

    private TRexGameViewer t;
    private TRexGame game;

    public Obstacle(int iX, int iY, int dx, TRexGame game, TRexGameViewer t)
    {
        x = iX;
        initX = iX;
        y = iY;

        this.dx = dx;
        initdX = dx;

        this.game = game;
        this.t = t;

        inPlay = false;
    }

    // Reset method for obstacle
    public void reset()
    {
        x = initX;
        inPlay = false;
        dx = initdX;
    }

    // Returns x position of enemy
    public int getX(){
        return x;
    }

    // Returns y position of enemy
    public int getY(){
        return y;
    }

    public int getXDim(){
        return 0;
    }

    public int getYDim(){
        return 0;
    }

    // Returns Game Viewer
    public TRexGameViewer getT(){
        return t;
    }

    // Returns whether enemy in play
    public boolean isInPlay(){
        return inPlay;
    }

    // Sets whether enemy in play
    public void setInPlay(boolean choice)
    {
        inPlay = choice;
    }

    // Moves enemy
    public void move()
    {
        // X position decremented by dx value
        x -= dx;

        // If enemy is off the screen on the left side, we must reset enemy
        if (getXDim() <= 0)
        {
            x = initX;

            // Randomly sets the speed of the obstacle within a set range
            randomSpeedIncrement();

            inPlay = false;
            game.addScore();
        }
    }

    // Randomly sets the speed of an obstacle within 15 pixels
    public void randomSpeedIncrement()
    {
        dx = (int)(Math.random() * 15 + initdX);
    }

    public void draw(Graphics g)
    {

    }
}
