import javax.swing.*;
import java.awt.*;

public class Bird extends Obstacle
{
    private Image birdImage;

    public Bird(TRexGame game, TRexGameViewer t)
    {
        super(1000, 250, 50, game, t);
        birdImage = new ImageIcon("Resources/Bird.png").getImage();
    }

    public void draw(Graphics g)
    {
        g.drawImage(birdImage, getX(), getY(), getT());
    }
}
