import java.awt.*;
import java.lang.*;
import javax.swing.*;
import java.util.*;

public class Mino{
    
    public Block b[] = new Block[4];
    public Block tempB[] = new Block[4];
    int autoDropCounter = 0;
    public int direction = 1;
    public boolean leftCollision, rightCollision, bottomCollision;
    public boolean active = true;
    public boolean deactivating;
    int deactiveCoutner = 0;
    private boolean dropppedDown = false;
    public boolean isGhost;
    public int roationNumber = 0;
    public static int ghostColor = 200;

    
    public void create(Color c){
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }
    
    public int getMinoNumber(){
    return 1;
    }
    public void setXY(int x, int y){}
    
    public int getX(){
        return b[0].x;
    }
    public int getY(){
        return b[0].y;
    }

    public Block[] getList(){
        return b;
    }
    
    public void setList(Block[] myList){
        for(int index = 0; index < myList.length; index++){
          this.b[index].x = myList[index].x;
          this.b[index].y = myList[index].y;
        }
    }
    
    
    public void updateXY(int direction){
        
        checkAll();
        
        if(!PlayManager.movedOrTurn && leftCollision == false && rightCollision == false && bottomCollision == false){
            this.direction = direction;
            b[0].x = tempB[0].x;        
            b[0].y = tempB[0].y;
    
            b[1].x = tempB[1].x;        
            b[1].y = tempB[1].y;
            
            b[2].x = tempB[2].x;        
            b[2].y = tempB[2].y;
            
            b[3].x = tempB[3].x;        
            b[3].y = tempB[3].y;
            roationNumber = direction;
            MusicPlayer.playSound(1);
            PlayManager.movedOrTurn = true;
        }
    }
    
    public void getDirection1(){}
    public void getDirection2(){}
    public void getDirection3(){}
    public void getDirection4(){}
    
    public void checkAll(){
        checkOBJCollision();
        checkMovementCollision();
        checkRoationCollision();
    }
    
    
    private void checkOBJCollision(){
        for(int index = 0; index < PlayManager.oldBlocks.size(); index++){
            int targetX = PlayManager.oldBlocks.get(index).x;
            int targetY = PlayManager.oldBlocks.get(index).y;
            
            for(int i = 0; i < b.length; i++){
                if(b[i].y + Block.SIZE == targetY && b[i].x == targetX){
                    bottomCollision = true;
                }
            }
            for(int i = 0; i < b.length; i++){
                if(b[i].x - Block.SIZE == targetX && b[i].y == targetY){
                    leftCollision = true;
                }
            }
             for(int i = 0; i < b.length; i++){
                if(b[i].x + Block.SIZE == targetX && b[i].y == targetY){
                    rightCollision = true;
                }
            }
        }
    }
    public void checkMovementCollision(){
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;
        
        checkOBJCollision();
        
        
        for(int index = 0; index < b.length; index++){
            if(b[index].x == PlayManager.left_x){
                leftCollision = true;
            }
        }
        for(int index = 0; index < b.length; index++){
            if(b[index].x + Block.SIZE == PlayManager.right_x){
                rightCollision = true;
            }
        }
        for(int index = 0; index < b.length; index++){
            if(b[index].y + Block.SIZE == PlayManager.bottom_y){
                bottomCollision = true;
            }
        }
    }
    public void moveToBottom(){
        boolean runningDown = false;
        checkMovementCollision();
        runningDown = bottomCollision;
        while(runningDown == false){
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;
                
            checkMovementCollision();
            runningDown = bottomCollision;
            autoDropCounter = 0;   
        }
        KeyHandler.enterPressed = false;  
        dropppedDown = true;
    }
    public void checkRoationCollision(){
        leftCollision = false;
        rightCollision = false;
        bottomCollision = false;
        

        for(int index = 0; index < tempB.length; index++){
            if(tempB[index].x < PlayManager.left_x){
                leftCollision = true;
            }
        }
        for(int index = 0; index < tempB.length; index++){
            if(tempB[index].x + Block.SIZE > PlayManager.right_x){
                rightCollision = true;
            }
        }
        for(int index = 0; index < tempB.length; index++){
            if(tempB[index].y + Block.SIZE > PlayManager.bottom_y){
                bottomCollision = true;
            }
        }    
        checkOBJCollision();
    }
    private void deactivating(){
        deactiveCoutner++;
        
        if(deactiveCoutner == 45){
            deactiveCoutner = 0;
            checkMovementCollision();
            
            if(bottomCollision){
                active = false;
                GamePanel.timesSwaped = 0;
                MusicPlayer.playSound(3);
            }
        }
    }
    
    public void rotate(){
        checkAll();
        switch(direction){
                case 1: getDirection2();break;
                case 2: getDirection3();break;
                case 3: getDirection4();break;
                case 4: getDirection1();break;
            }
    }
    
    public void update(){
        checkAll();

        if(deactivating){
            deactivating();            
        }
   
        checkMovementCollision();
        
        if(KeyHandler.upPressed){
            this.rotate();
            KeyHandler.upPressed = false;
        }
        if(KeyHandler.downPressed){
            if(bottomCollision == false & !isGhost){
            b[0].y += Block.SIZE;
            b[1].y += Block.SIZE;
            b[2].y += Block.SIZE;
            b[3].y += Block.SIZE;
            KeyHandler.downPressed = false;
            autoDropCounter = 0;   
            }
        }
        if(KeyHandler.leftPressed){
            if(leftCollision == false){
            b[0].x -= Block.SIZE;
            b[1].x -= Block.SIZE;
            b[2].x -= Block.SIZE;
            b[3].x -= Block.SIZE;
            
            KeyHandler.leftPressed = false; 
            PlayManager.movedOrTurn = true;
            }
            
        }
        if(KeyHandler.rightPressed){
            if(rightCollision == false){
            b[0].x += Block.SIZE;
            b[1].x += Block.SIZE;
            b[2].x += Block.SIZE;
            b[3].x += Block.SIZE;
            
            KeyHandler.rightPressed = false; 
            PlayManager.movedOrTurn = true;
            }
            
        }
        // drops all the way
        if(KeyHandler.enterPressed && !dropppedDown && !isGhost){
            moveToBottom();
            MusicPlayer.playSound(3);
            GamePanel.timesSwaped = 0;
            active = false;
        }
        
        if(bottomCollision && KeyHandler.downPressed && !isGhost){
            active = false;
            GamePanel.timesSwaped = 0;
            MusicPlayer.playSound(3);
        }
        else if(bottomCollision && !isGhost){
            deactivating = true;
        }
        else if(!isGhost){
               autoDropCounter++;
            if(autoDropCounter == PlayManager.dropInterval){
                autoDropCounter = 0;
                b[0].y += Block.SIZE;
                b[1].y += Block.SIZE;
                b[2].y += Block.SIZE;
                b[3].y += Block.SIZE;
            } 
        }
        
    }
    public void draw(Graphics2D g2){

        int margin = 2;
        g2.setColor(b[0].c);
        g2.fillRect(b[0].x + margin, b[0].y + margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(b[1].x + margin, b[1].y + margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(b[2].x + margin, b[2].y + margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
        g2.fillRect(b[3].x + margin, b[3].y + margin, Block.SIZE-(margin*2), Block.SIZE-(margin*2));
    }
}