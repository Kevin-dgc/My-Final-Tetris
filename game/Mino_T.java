import java.awt.*;

public class Mino_T extends Mino
{
    public boolean isGhost;
    public final int MinoNumber = 5;

    
    public Mino_T(boolean ghost){
        int r = 160;
        int g = 32;
        int b = 240;
        
        if(!ghost){
            create(new Color(r, g, b)); // purple
        }
        else{
        create(new Color(ghostColor, ghostColor, ghostColor)); // white
        }
        isGhost = ghost;
    }
    
    public int getMinoNumber(){
        return MinoNumber;
    }
    
    public void setXY(int x, int y){
        int z = Block.SIZE;

        b[0].x = x;
        b[0].y = y;
        
        b[1].x = b[0].x;
        b[1].y = b[0].y - z;
        
        b[2].x = b[0].x - z;
        b[2].y = b[0].y;
        
        b[3].x = b[0].x + z;
        b[3].y = b[0].y;
    }
    public void getDirection1(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - z;
        
        tempB[2].x = b[0].x - z;
        tempB[2].y = b[0].y;
        
        tempB[3].x = b[0].x + z;
        tempB[3].y = b[0].y;
        
        updateXY(1);
    }
    
    public void getDirection2(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x + z;
        tempB[1].y = b[0].y;
        
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - z;
        
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y + z;
        
        updateXY(2);
    }
    
    public void getDirection3(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y + z;
        
        tempB[2].x = b[0].x + z;
        tempB[2].y = b[0].y;

        tempB[3].x = b[0].x - z;
        tempB[3].y = b[0].y;
        
        updateXY(3);
    }
    
    public void getDirection4(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x - z;
        tempB[1].y = b[0].y;
        
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + z;
        
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y - z;
        
        updateXY(4);
    }

}
