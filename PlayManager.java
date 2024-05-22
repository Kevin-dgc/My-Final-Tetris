import java.awt.*;
import java.awt.Graphics2D;
import java.util.*;
import java.lang.*;

public class PlayManager{
    
    public static final int WIDTH = 360;
    public static final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;
    
    //Minos
    public static Mino currentMino;
    final int MINO_START_X;
    final int MINO_START_Y;
    
    public static Mino ghostMino;
    
    public Mino savedMino;
    final int MINO_SAVE_X;
    final int MINO_SAVE_Y;
    
    Mino nextMino;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;
    public static ArrayList<Block> oldBlocks = new ArrayList<Block>();
    public static ArrayList<Integer> bagOfBlocks = new ArrayList<Integer>();
    
    public static int dropInterval = 50;
    public static boolean gameOver;
    boolean died = true;
    public static boolean movedOrTurn = false;
    
    public static int highScore = 0;
    public int level = 1;
    public int lines;
    public static int score;
    
    public PlayManager(){
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;
        
        MINO_START_X = left_x + (WIDTH/2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;
        
        MINO_SAVE_X = 230;
        MINO_SAVE_Y = 140;
        
        NEXTMINO_X = right_x + 175;
        NEXTMINO_Y = top_y + 500;        
        
        int newMinoNum = pickMino();
        // makes new blocks
        currentMino = pickMino(false,newMinoNum);
        //currentMino = new Mino_L2();
        currentMino.setXY(MINO_START_X, MINO_START_Y);
        
        ghostMino = pickMino(true,currentMino.getMinoNumber());
        ghostMino.setXY(currentMino.getX(), currentMino.getY());
        ghostMino.moveToBottom();
                
        newMinoNum = pickMino();
        nextMino = pickMino(false,newMinoNum);
        //nextMino = new Mino_L2();
        nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
    }
    
    private Mino pickMino(boolean ghost, int minoIndex){
        Mino mino = new Mino_T(false);
        switch (minoIndex) {
        case 1:
            mino = new Mino_L1(ghost);break;
        case 2:
            mino = new Mino_L2(ghost);break;
        case 3:
            mino = new Mino_Block(ghost);break;
        case 4:
            mino = new Mino_Bar(ghost);break;
        case 5:
            mino = new Mino_T(ghost);break;
        case 6:
            mino = new Mino_Z1(ghost);break;
        case 7:
            mino = new Mino_Z2(ghost);break;
        }
        return mino;
    }
    
    
    private int pickMino(){
    if (bagOfBlocks.isEmpty()) {
        for (int i = 1; i <= 7; i++) {
            bagOfBlocks.add(i);
        }
    }

    int randomIndex = new Random().nextInt(bagOfBlocks.size());
    int minoIndex = bagOfBlocks.get(randomIndex);
    bagOfBlocks.remove(randomIndex);
    return minoIndex;
    }
    
    public void saveMino() {
        int newMinoNum = pickMino();
        if (savedMino == null) {
            savedMino = currentMino;
            currentMino = nextMino;
            newMinoNum = pickMino();
            nextMino = pickMino(false,newMinoNum);
            nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
        } else {
            Mino tempMino = savedMino;
            savedMino = currentMino;
            currentMino = tempMino;
        }
        currentMino.setXY(MINO_START_X, MINO_START_Y);
        savedMino.setXY(MINO_SAVE_X, MINO_SAVE_Y);
        GamePanel.timesSwaped++;
    }
    
    
    public void update(){
        if(currentMino.active == false){
            oldBlocks.add(currentMino.b[0]);
            oldBlocks.add(currentMino.b[1]);
            oldBlocks.add(currentMino.b[2]);
            oldBlocks.add(currentMino.b[3]);
            
            if(currentMino.b[0].x == MINO_START_X && currentMino.b[0].y == MINO_START_Y){
              gameOver = true;
            }
            
            currentMino.deactivating = false;
            
            currentMino = nextMino;            
            currentMino.setXY(MINO_START_X, MINO_START_Y);
            
            ghostMino = pickMino(true,currentMino.getMinoNumber());
            ghostMino.setXY(currentMino.getX(), currentMino.getY());
            ghostMino.moveToBottom();
            
            
            int newMinoNum = pickMino();
            nextMino = pickMino(false,newMinoNum);
            nextMino.setXY(NEXTMINO_X,NEXTMINO_Y);
            
            checkDelete();
        }
        else{
            currentMino.update();
            ghostMino = pickMino(true,currentMino.getMinoNumber());
            ghostMino.setXY(currentMino.getX(), currentMino.getY());
            
            ghostMino.setList(currentMino.getList());
            
            ghostMino.moveToBottom();
        }
    }
    
    private void checkDelete(){
        int sizeOfList = oldBlocks.size();
        int x = left_x;
        int y = top_y;
        int count = 0;
        int lineCount = 0;
        
        while(x < right_x && y < bottom_y){
            
            for(int index = 0; index < oldBlocks.size(); index++){
                if(oldBlocks.get(index).x == x & oldBlocks.get(index).y == y){
                    count++;
                }
            }
            
            x+= Block.SIZE;
            if( x == right_x){
                
                if(count == 12){
                    lineCount++;
                    lines++;
                    
                    if(lines % 10 == 0 && dropInterval > 1){
                        level++;
                        if(dropInterval > 10){
                            dropInterval -=10;
                        }
                        else if (dropInterval > 1){
                            dropInterval -=1;
                        }
                    }
                    
                    for(int index = 0; index < oldBlocks.size(); index++){
                        if(oldBlocks.get(index).y == y){
                            oldBlocks.remove(index);
                            index--;
                        }
                    }
                    
                    for(int index = 0; index < oldBlocks.size(); index++){
                        if(oldBlocks.get(index).y < y){
                            int num = oldBlocks.get(index).y + Block.SIZE;
                            oldBlocks.get(index).y = num;
                        }
                    }
                }
                count = 0;
                x = left_x;
                y += Block.SIZE;
            }
        }
        if(lineCount > 0){
            int oneLineScore = 10 * level;
            score += oneLineScore + lineCount;
        }
        
        if(oldBlocks.size() != sizeOfList){
            MusicPlayer.playSound(2);
        }
    }
    
    public void draw(Graphics2D g2){
        // draws gid pattern
        //int i = 0;
        //while ((left_x + Block.SIZE * i) < right_x) {
        //    g2.drawRect((left_x + Block.SIZE * i), top_y-4, Block.SIZE, HEIGHT);
        //    i++;
        //}

        
        // draws main box
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4f));
        g2.drawRect(left_x-4, top_y-4, WIDTH+8, HEIGHT+8);
        
        // draws next box
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x,y,200,200);
        g2.setFont(new Font("Arial", Font.PLAIN,25));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // text
        g2.drawString("NEXT", x+60, y+60);
                
        // draws score box
        g2.drawRect(x, top_y, 250, 300);
        x += 10;
        y = top_y + 50;
        g2.drawString("LEVEL: " + level,x,y); y += 70;
        g2.drawString("LINES: " + lines,x,y); y += 70;
        g2.drawString("SCORE: " + score,x,y); y+= 70;
        g2.drawString("TOP SCORE: " + highScore,x,y);
        
        // draws volume box
        g2.drawString("VOLUME:", left_x - 4, top_y - 15);
        for (int j = 0; j < 8; j++) {
            g2.fillRect(left_x + 135 + (20 * j), top_y - 31, 15, 15);
        }
        g2.setColor(Color.YELLOW);
        if (MusicPlayer.volumeNUM != 0){
          for (int j = 0; j < MusicPlayer.volumeNUM; j++) {
            g2.fillRect(left_x + 135 + (20 * j), top_y - 31, 15, 15);
          }  
        }

        g2.setColor(Color.WHITE);

        // draws saved box
        g2.drawString("SAVED",MINO_SAVE_X-Block.SIZE+3, MINO_SAVE_Y-Block.SIZE-20);
        g2.drawRect(150,top_y,200,200);
        if(savedMino != null){
            savedMino.draw(g2);
        }
        g2.setColor(Color.WHITE);
        
        if(ghostMino != null){
         ghostMino.draw(g2);
        }
        if(currentMino != null){
         currentMino.draw(g2);
        }
        if(nextMino != null){
         nextMino.draw(g2);   
        }
        
        for(int index = 0; index < oldBlocks.size(); index++){
            oldBlocks.get(index).draw(g2);
        }
        
        g2.setColor(Color.yellow);
        g2.setFont(g2.getFont().deriveFont(50f));
        if(gameOver){
            x = left_x+5;
            y = top_y + 320 - Block.SIZE;
            g2.drawString("GAME OVER",x,y);
            g2.drawString("PRESS ←",x,y+(Block.SIZE*2));
            if(died){
                MusicPlayer.playSound(4);
                died = false;
                MusicPlayer.stopBackgroundTheme();
            }
            if(KeyHandler.backSpacePressed){
                savedMino = null;
                died = true;
                oldBlocks.clear();
                gameOver = false;
                currentMino.active = true;
                MusicPlayer.playBackgroundTheme();
                level = 1;
                score = 0;
                lines = 0;
            }
        }
        
        if(KeyHandler.paused && !gameOver){
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PASUSED",x,y);
            MusicPlayer.pauseBackgroundTheme();
        }
        if(!KeyHandler.paused && !gameOver){
            MusicPlayer.resumeBackgroundTheme();
        }        
    }
}