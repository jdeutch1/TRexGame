import java.awt.*;

public class Obstacle {
    private int x;

    private int initX;
    private int y;
    private int dx;
    private boolean inPlay;

    private TRexGameViewer t;
    private TRexGame game;

    public Obstacle(int iX, int iY, int dx, TRexGame game, TRexGameViewer t)
    {
        x = iX;
        initX = iX;
        y = iY;
        this.dx = dx;
        this.game = game;

        inPlay = false;
        this.t = t;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getXDim(){
        return 0;
    }

    public int getYDim(){
        return 0;
    }

    public int getDx()
    {
        return dx;
    }

    public void setX(int x){
        this.x = x;
    }

    public boolean isInPLay(){
        return inPlay;
    }

    public void setInPlay(boolean choice)
    {
        inPlay = choice;
    }

    public void move()
    {
        x -= dx;
        if (x + getXDim() == 0)
        {
            x = initX;

        }
    }

    public TRexGameViewer getT(){
        return t;
    }

    public void draw(Graphics g){

    }
}
