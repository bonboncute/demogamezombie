package Interface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Effect.Animation;
import Effect.FrameImage;

public class GamePanel extends JPanel implements Runnable,KeyListener {
    private Thread thread;
    private boolean isRunning;
    private InputManagement inputManagement;
    FrameImage frame1,frame2,frame3;
    Animation animat;
    public GamePanel(){
        inputManagement = new InputManagement();
//         try{
//         BufferedImage image1 = ImageIO.read(new File("Data/bo1.png"));
//         BufferedImage image1_1 = image1.getSubimage(0, 0, 79, 74);
//         frame1 = new FrameImage("frame1",image1_1);
//         BufferedImage image2 = ImageIO.read(new File("Data/bo2.png"));
//         BufferedImage image2_1 = image2.getSubimage(0, 0, 79, 74);
//         frame2 = new FrameImage("frame2",image2_1);
//         animat = new Animation();
//         animat.add(frame1, 200*1000000);
//         animat.add(frame2, 200*1000000);
        
//     } catch (IOException ex){}
}
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0, GameFrame.SC_WIDTH, GameFrame.SC_HEIGHT);
        // Graphics2D g2 = (Graphics2D) g;
        // animat.Update(System.nanoTime());
        // animat.draw(200, 100,g2);
    }
    public void startGame(){
        if(thread == null){
            isRunning = true;
            thread = new Thread(this);
            thread.start();
        }
    }
    @Override
    public void run(){
        long fps = 60;
        long period = 1000*1000000/fps;
        long beginTime;
        long sleepTime;
        
        beginTime = System.nanoTime();
        while(isRunning){
            repaint();
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;
            try{
                    if (sleepTime > 0){
                    Thread.sleep(sleepTime/1000000);
                    }
                    else {
                        Thread.sleep(period/2000000);
                    }
            } catch (InterruptedException ex) {}
            beginTime = System.nanoTime();
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        inputManagement.processKeyBoardPressed(e.getKeyCode());
    }
    @Override
    public void keyReleased(KeyEvent e) {
        inputManagement.processKeyBoardReleased(e.getKeyCode());
    }
}
