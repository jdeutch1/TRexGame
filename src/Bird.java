import javax.swing.*;
import java.awt.*;

public class Bird extends Obstacle
{
    /** Instance Variables **/
    private final int X_DIM = 60;
    private final int Y_DIM = 60;
    private Image birdImage;

    public Bird(TRexGame game, TRexGameViewer t)
    {
        super(1000, 340, 40, game, t);
        birdImage = new ImageIcon("Resources/Bird.png").getImage();
    }

    // Returns bottom right x-coordinate of bird image
    public int getXDim()
    {
        return getX() + X_DIM;
    }

    // Returns bottom right y-coordinate of bird image
    public int getYDim()
    {
        return getY() + Y_DIM;
    }

    // Draws bird image
    public void draw(Graphics g)
    {
        g.drawImage(birdImage, getX(), getY(), X_DIM, Y_DIM, getT());
    }
}
