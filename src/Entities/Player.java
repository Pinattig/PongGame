package Entities;

import java.awt.*;

public class Player implements Entity{

    Color color;

    int positionX;
    int positionY;

    int height;
    int width;
    int maxWidth;

    public Player(Color color, int maxWidth){
        this.color = color;
        positionX = 100;
        positionY = 115;
        height = 10;
        width = 40;
        this.maxWidth = maxWidth;
    }

    @Override
    public void build(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillRect(positionX,positionY, width, height);
    }

    public void move(boolean right) {
        if(right && positionX < maxWidth-width)
            this.positionX+=5;
        else if(!right && positionX > 0)
            this.positionX-=5;
    }

    public Rectangle getPlayerRectangle(){
        return new Rectangle(positionX, positionY, width, height);
    }
}
