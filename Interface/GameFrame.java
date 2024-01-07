package Interface;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    public static final int SC_WIDTH = 900;
    public static final int SC_HEIGHT = 600;
    GamePanel gamePanel;
    public GameFrame(){
        Toolkit SizeOfScreen = this.getToolkit(); // get size of screen
        Dimension dimension = SizeOfScreen.getScreenSize(); //store size of screen
        this.setBounds((dimension.width - SC_WIDTH)/2,(dimension.height - SC_HEIGHT)/2,SC_WIDTH,SC_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // turn off application with close button
        gamePanel = new GamePanel();
        add(gamePanel);
        this.addKeyListener(gamePanel);
    }
    public void startGame(){
        gamePanel.startGame();
    }
    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true); // to show app when running
        gameFrame.startGame();
    }
}
