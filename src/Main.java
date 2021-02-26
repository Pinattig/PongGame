import Game.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Game game = new Game(240,120,3);

        Window window = new Window(game, "Pong Pong");
        window.setVisible(true);

        game.start();
    }
}
