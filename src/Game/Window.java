package Game;

import javax.swing.*;

public class Window {

    JFrame frame;

    public Window(Game game,String windowName) {
        frame = new JFrame(windowName);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(game);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public void setVisible(boolean visible){
        this.frame.setVisible(visible);
    }
}
