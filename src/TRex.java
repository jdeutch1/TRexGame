import javax.swing.*;
import java.awt.*;

public class TRex
{
    /** Instance Variables **/
    private int X_DIM = 115;
    private int Y_DIM = 100;

    // Dimension in y direction of tRex image when ducking
    private int Y_DIM_DOWN = 75;

    private int x, y, dy, initdY;
    private boolean isJumping;
    private boolean jumpingUp;
    private boolean isDucking;
    private int duckCounter;
    private Obstacle enemies[];
    private TRexGameViewer t;
    private Image upRight;
    private Image upRight2;
    private Image down;

    public TRex(TRexGameViewer t)
    {
        x = 0;
        y = 397;
        dy = 50;
        initdY = 50;

        upRight = new ImageIcon("Resources/TRexUp.png").getImage();
        down = new ImageIcon("Resources/TRexDown.png").getImage();

        isJumping = false;
        jumpingUp = true;

        isDucking = false;
        duckCounter = 0;

        this.t = t;

    }

    // Reset function for tRex
    public void reset()
    {
        x = 0;
        y = 397;
        dy = 50;

        isJumping = false;
        jumpingUp = true;

        isDucking = false;
        duckCounter = 0;
    }

    // Jumping function for tRex
    public void jump()
    {
        // Jumping up means tRex is on the rise
        // This means that the tRex is decelerating coming up
        if (jumpingUp)
        {
            y -= dy;

            // Once dy is 0, this means the tRex is at the peak of its jump
            // Now it is going down
            if (dy == 0)
            {
                jumpingUp = false;
            }
            else
            {
                dy -= 10;
            }
        }
        else
        {
            // tRex is now going down, so it is accelerating
            // Stop going down once dY is back to initial value
            dy += 10;
            y += Math.abs(dy);
            if (dy == initdY)
            {
                jumpingUp = true;
                isJumping = false;
                dy = initdY;
            }
        }
    }

    // Returns if tRex is jumping
    public boolean getIsJumping()
    {
        return isJumping;
    }

    // Set whether tRex is jumping
    public void setJumping(boolean jumpState)
    {
        isJumping = jumpState;
    }

    // Duck method for tRex
    public void duck()
    {
        if (isDucking)
        {
            /* Because ducking is temporary (this is a purposeful decision),
            there is a counter for how many cycles of action performed occur
             before the tRex is not ducking anymore */
            duckCounter++;
            if (duckCounter == 10)
            {
                isDucking = false;
                duckCounter = 0;
            }
        }
    }

    // Set whether tRex is ducking
    public void setDucking(boolean duckState)
    {
        isDucking = duckState;
    }

    // Returns if tRex is ducking
    public boolean getIsDucking()
    {
        return isDucking;
    }

    // Determines whether tRex is touching a given obstacle
    // Returns true if touching, false if not
    public boolean isTouching(Obstacle o)
    {
        /* Determine values of upper left and bottom right coordinates
         for both images (tRex and obstacle) */

        // For tRex, we need to determine if tRex is in a ducking state or not
        // If tRex is ducking its dimensions are different and we must adjust
        int tUpperX = x;
        int tUpperY = y;
        if (isDucking)
        {
            tUpperY += (Y_DIM - Y_DIM_DOWN);
        }
        int tLowerX = x + X_DIM;
        int tLowerY = tUpperY;
        if (isDucking){
            tLowerY += Y_DIM_DOWN;
        }
        else {
            tLowerY += Y_DIM;
        }

        // Obstacle coordinates found through obstacle class
        int oUpperX = o.getX();
        int oUpperY = o.getY();
        int oLowerX = o.getXDim();
        int oLowerY = o.getYDim();

        // Determination of whether images are touching
        // Used partial help from logic present on GeeksForGeeks post
        // https://www.geeksforgeeks.org/find-two-rectangles-overlap/
        if (tLowerY < oUpperY || tLowerX < oUpperX)
        {
            return false;
        }
        if (oLowerY < tUpperY || oLowerX < tUpperX)
        {
            return false;
        }

        /* If none of the above requirements are met, then we know
         that the rectangles (images) must be intersecting */
        return true;
    }

    // Draws tRex
    public void draw(Graphics g)
    {
        if (!isDucking)
        {
            g.drawImage(upRight, x, y, X_DIM, Y_DIM, t);
        }
        else
        {
            g.drawImage(down, x, y + (Y_DIM - Y_DIM_DOWN), X_DIM, Y_DIM_DOWN, t);
        }
    }
}
