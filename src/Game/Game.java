package Game;

import Entities.Ball;
import Entities.Player;
import Entities.PlayerIA;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, KeyListener {

    private boolean isRunning;

    private int WIDTH;
    private int HEIGHT;
    private int SCALE;

    private int gameWidth;
    private int gameHeight;

    private BufferedImage layer;

    private Player player;
    private PlayerIA playerIA;
    private Ball ball;

    public Game(int width, int height, int scale){
        this.WIDTH = width;
        this.HEIGHT = height;
        this.SCALE = scale;
        gameWidth = WIDTH*SCALE;
        gameHeight = HEIGHT*SCALE;
        this.setPreferredSize(new Dimension(gameWidth, gameHeight));
        layer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        this.addKeyListener(this);
        this.setFocusable(true);
        player = new Player(Color.BLUE, WIDTH);
        playerIA = new PlayerIA(Color.RED, WIDTH);
        ball = new Ball(Color.yellow, WIDTH, HEIGHT, player, playerIA);
    }

    public synchronized void start(){
        isRunning = true;
        new Thread(this).start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double ns = 1000000000 / 60.0;
        double delta = 0;

        while (isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            now = lastTime;
            if(delta > 1){
                update();
                render();
                delta = 0;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.move(true);
            //toRight = true;
        }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            player.move(false);
            //toLeft = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = layer.getGraphics();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0, gameWidth, gameHeight);

        buildPlayers(graphics);

        graphics = bs.getDrawGraphics();
        graphics.drawImage(layer, 0,0 , gameWidth, gameHeight, null);

        bs.show();
    }

    private void update(){
        ball.move();
        playerIA.move(ball.getXPosition());

        double yBallPosition = ball.getYPosition();
        if(yBallPosition < 0 || yBallPosition > HEIGHT)
            reset();
    }

    private void buildPlayers(Graphics graphics){
        player.build(graphics);
        playerIA.build(graphics);
        ball.build(graphics);
    }

    private synchronized void stop(){}

    private void reset(){
        player = new Player(Color.BLUE, WIDTH);
        playerIA = new PlayerIA(Color.RED, WIDTH);
        ball = new Ball(Color.yellow, WIDTH, HEIGHT, player, playerIA);
    }
}
