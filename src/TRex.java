import javax.swing.*;
import java.awt.*;

public class TRex {

    private int x, y, dy;

    private int X_DIM = 130;
    private int Y_DIM = 106;

    private boolean isJumping;

    private boolean jumpingUp;
    private boolean jumpingDown;

    private boolean isDucking;

    private TRexGameViewer t;
    private Image upRight;
    private Image ducking;
    private Obstacle enemies[];

    public TRex(TRexGameViewer t)
    {
        upRight = new ImageIcon("Resources/TRexUp.png").getImage();
        isJumping = false;
        isDucking = false;
        jumpingUp = true;
        this.t = t;
        x = 0;
        y = 400;
        dy = 50;

    }

    public void jump()
    {
        if (jumpingUp)
        {
            y -= dy;
            if (dy == 0)
            {
                jumpingUp = false;
            }
            else
            {
                dy-=10;
            }
        }
        else {
            dy += 10;
            y += Math.abs(dy);
            if (dy == 50)
            {
                jumpingUp = true;
                isJumping = false;
                dy = 50;
            }
        }
    }

    public boolean getIsJumping()
    {
        return isJumping;
    }

    public void setJumping(boolean jumpState){
        isJumping = jumpState;
    }


    // check if there is intersection between trex and other image
    public boolean isTouching(Obstacle o)
    {
        int tUpperX = x;
        int tUpperY = y;
        int tLowerX = x + X_DIM;
        int tLowerY = y + Y_DIM;

        int oUpperX = o.getX();
        int oUpperY = o.getY();

        //Need to insert w real values
        int oLowerX = o.getXDim();
        int oLowerY = o.getYDim();

        if (tLowerY < oUpperY || tLowerX < oUpperX)
        {
            return false;
        }
        if (oLowerY < tUpperY || oLowerX < tUpperX){
            return false;
        }
        return true;

    }

    public void draw(Graphics g)
    {
        g.drawImage(upRight, x, y, t);


    }

}
