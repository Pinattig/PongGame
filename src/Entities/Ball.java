package Entities;

import java.awt.*;
import java.util.Random;

public class Ball implements Entity{
    private Color color;

    private double positionX;
    private double positionY;
    private int width;
    private int height;
    private double directionX;
    private double directionY;

    private int maxWidth;
    private int maxHeight;

    private double speed;

    private Player player;
    private PlayerIA playerIA;

    public Ball(Color color, int maxWidth, int maxHeight, Player player, PlayerIA playerIA){
        this.color = color;
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.player = player;
        this.playerIA = playerIA;
        positionX = 100;
        positionY = maxHeight/2 - 1;
        height = 5;
        width = 5;
        speed = 0.02;
        directionX = new Random().nextGaussian();
        directionY = 1;

    }

    @Override
    public void build(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval((int)positionX,(int)positionY, width, height);
    }

    public void move() {

        if((positionX + (directionX * speed)) + width >= maxWidth){
             directionX *= -1;
        }else if((positionX + (directionX * speed)) + width < 0){
            directionX *= -1;
        }

        detectedCollisionsWithPlayers();

        positionX += directionX * speed;
        positionY += directionY * speed;

    }

    public double getXPosition(){
        return this.positionX;
    }

    public double getYPosition(){
        return this.positionY;
    }

    public void detectedCollisionsWithPlayers(){
        int actualPositionX = (int) (positionX + (directionX * speed));
        int actualPositionY = (int) (positionY + (directionY * speed));
        Rectangle selfRectangle = new Rectangle(actualPositionX, actualPositionY, width, height);

        if(selfRectangle.intersects(player.getPlayerRectangle()))
            directionY *= -1;
        else if(selfRectangle.intersects(playerIA.getPlayerIARectangle()))
            directionY *= -1;
    }
}
