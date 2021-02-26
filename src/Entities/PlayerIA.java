package Entities;

import java.awt.*;

public class PlayerIA implements Entity{


    private Color color;

    private double positionX;
    private double positionY;

    private int width;
    private int height;

    private int maxWidth;

    public PlayerIA(Color color, int maxWidth){
        this.color = color;
        this.maxWidth = maxWidth;
        positionX = 100;
        positionY = -5;
        height = 10;
        width = 40;
    }

    @Override
    public void build(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect((int)positionX,(int)positionY, width, height);
    }

    public void move(double ballPosition) {
        positionX = (ballPosition - (width/2));
    }

    public Rectangle getPlayerIARectangle(){
        return new Rectangle((int)positionX, (int)positionY, width, height);
    }
}
