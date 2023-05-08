import javax.swing.*;
import java.awt.*;

public class Cactus extends Obstacle
{
    private Image cactusImage;
    private Image doubleCactus;

    private final int X_DIM = 65;
    private final int Y_DIM = 65;

    private int type;

    public Cactus(TRexGame game, TRexGameViewer t, int type)
    {
        super(1000, 450, 20, game, t);
        this.type = type;
        if (type == 0)
        {
            cactusImage = new ImageIcon("Resources/CactusUp.png").getImage();
        }
        else
        {
            doubleCactus = new ImageIcon("Resources/DoubleCactus.png").getImage();
        }
    }

    public int getXDim()
    {
        return getX() + X_DIM;
    }

    public int getYDim()
    {
        return getY() + Y_DIM;
    }

    public void draw(Graphics g)
    {
        // Need to check if in the range of the board that is only instance we will actually draw
        g.drawImage(cactusImage, getX(), getY(), X_DIM, Y_DIM, getT());
        g.drawImage(cactusImage, getX(), getY(), X_DIM, Y_DIM, getT());
    }
}
