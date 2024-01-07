package Interface;

import java.awt.event.KeyEvent;

public class InputManagement {
    public void processKeyBoardPressed(int KeyCode){
        switch (KeyCode){
            case KeyEvent.VK_UP:
            System.out.println("You Pressed Up");
            break;
            case KeyEvent.VK_DOWN:
            System.out.println("You Pressed Down");
            break;
            case KeyEvent.VK_ENTER:
            System.out.println("You Pressed Enter");
            break;
            case KeyEvent.VK_SPACE:
            System.out.println("You Pressed Space");
            break;
        }
    }
    public void processKeyBoardReleased(int KeyCode){
        switch (KeyCode){
            case KeyEvent.VK_UP:
            System.out.println("You Released Up");
            break;
            case KeyEvent.VK_DOWN:
            System.out.println("You Released Down");
            break;
            case KeyEvent.VK_ENTER:
            System.out.println("You Released Enter");
            break;
            case KeyEvent.VK_SPACE:
            System.out.println("You Released Space");
            break;
        }
    }
}
