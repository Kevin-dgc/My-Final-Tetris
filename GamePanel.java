import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final int tileSize = 30;
    public final int FPS = 60;
    public static int timesSwaped = 0;
    
    public int playerX = 100;
    public int playerY = 100;
    public int playerSpeed = 4;
    
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    PlayManager pm;

    public GamePanel(){
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addKeyListener(keyH);
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        
        pm = new PlayManager();
    }
    
    public void launchGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        // game loop
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
                pm.movedOrTurn = false;
                if(pm.score > pm.highScore){
                    pm.highScore = pm.score;
                }
            }
            
            if(timer >= 1000000000) {
                //System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
            
            if(keyH.endGame){
               System.exit(0);
            }
        }
    }
    
    private void update(){
        if(KeyHandler.shiftPressed && timesSwaped < 2){
            pm.saveMino();
            KeyHandler.shiftPressed = false;
        }
        if(KeyHandler.paused == false && pm.gameOver == false){
            pm.update();
        }
        
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        pm.draw(g2);
        
        g2.dispose();
    }
}