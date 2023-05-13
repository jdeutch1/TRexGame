import javax.swing.*;
import java.awt.*;

public class Cactus extends Obstacle
{
    /** Instance Variables **/
    private Image cactusImage;
    private final int X_DIM = 65;
    private final int Y_DIM = 65;

    // Type specifies whether the cactus is a double or not
    // Determines which image we associate the object with
    private int type;

    public Cactus(TRexGame game, TRexGameViewer t, int type)
    {
        super(1000, 432, 40, game, t);
        this.type = type;
        if (type == 0)
        {
            cactusImage = new ImageIcon("Resources/CactusUp.png").getImage();
        }
        else
        {
            cactusImage = new ImageIcon("Resources/DoubleCactus.png").getImage();
        }
    }

    // Returns the bottom right x-coordinate corner of image
    public int getXDim()
    {
        return getX() + X_DIM;
    }

    // Returns bottom right y-coordinate corner of image
    public int getYDim()
    {
        return getY() + Y_DIM;
    }

    // Draws cactus
    public void draw(Graphics g)
    {
        g.drawImage(cactusImage, getX(), getY(), X_DIM, Y_DIM, getT());
    }
}
