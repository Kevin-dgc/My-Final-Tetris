import java.awt.event.*;
import java.lang.*;

public class KeyHandler implements KeyListener{
    
    public static boolean shiftPressed, upPressed, downPressed, leftPressed, rightPressed, enterPressed, backSpacePressed;
    public static boolean paused = false;
    private int num = 0;
    public static boolean endGame = false;
    
    @Override
    public void keyTyped(KeyEvent e){ 
        // not used in this code
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
            upPressed = true;    
        }
         if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
            downPressed = true;
        }
         if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
            leftPressed = true;
        }
         if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
            rightPressed = true;
        }
         if(keyCode == KeyEvent.VK_SHIFT){
            shiftPressed = true;
        }
        if(keyCode == KeyEvent.VK_ENTER){
            if(num % 2 == 0){
                paused = true;
            }
            else if(num % 2 != 0){
                paused = false;
            }
            num++;
            
        }
        if(keyCode == KeyEvent.VK_ESCAPE){
            endGame = true;
        }
        if(keyCode == KeyEvent.VK_SPACE){
            enterPressed = true;
        }
        if(keyCode == KeyEvent.VK_BACK_SPACE){
            backSpacePressed = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        
        if(keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
            upPressed = false;    
        }
         if(keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
            downPressed = false;
        }
         if(keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
            leftPressed = false;
        }
         if(keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
        if(keyCode == KeyEvent.VK_ENTER){
        }
        if(keyCode == KeyEvent.VK_SHIFT){
            shiftPressed = false;
        }
        // range -80.0 to 6.0206 db
        if(keyCode == KeyEvent.VK_MINUS ){
            MusicPlayer.volumeNUM -= 0.5;
            if(MusicPlayer.volumeNUM < 0){
                MusicPlayer.volumeNUM = 0;
            }
            MusicPlayer.newVolume();
        } 
        if(keyCode == KeyEvent.VK_EQUALS){
            MusicPlayer.volumeNUM += 0.5;
            if(MusicPlayer.volumeNUM > 8){
                MusicPlayer.volumeNUM = 8;
            }
            MusicPlayer.newVolume();
        }
        if(keyCode == KeyEvent.VK_SPACE){
            enterPressed = false;
        }
        if(keyCode == KeyEvent.VK_BACK_SPACE){
            backSpacePressed = false;
        }
    }
    
}