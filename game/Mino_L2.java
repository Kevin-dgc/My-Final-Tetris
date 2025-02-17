import java.awt.*;

public class Mino_L2 extends Mino
{
    public boolean isGhost;
    public final int MinoNumber = 2;


    public Mino_L2(boolean ghost){
        int r = 255;
        int g = 192;
        int b = 203;
        
        if(!ghost){
            create(new Color(r, g, b)); // pink
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
        
        b[2].x = b[0].x;
        b[2].y = b[0].y + z;
        
        b[3].x = b[0].x - z;
        b[3].y = b[0].y + z;
    }
    
    public void getDirection1(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - z;
        
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + z;
        
        tempB[3].x = b[0].x - z;
        tempB[3].y = b[0].y + z;
        
        updateXY(1);
    }   

    public void getDirection2(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x + z;
        tempB[1].y = b[0].y;
        
        tempB[2].x = b[0].x - z;
        tempB[2].y = b[0].y;
        
        tempB[3].x = b[0].x - z;
        tempB[3].y = b[0].y - z;
        
        updateXY(2);
    }

    public void getDirection3(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y + z;
        
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - z;
        
        tempB[3].x = b[0].x + z;
        tempB[3].y = b[0].y - z;
        
        updateXY(3);
    }

    public void getDirection4(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x - z;
        tempB[1].y = b[0].y;
        
        tempB[2].x = b[0].x + z;
        tempB[2].y = b[0].y;
        
        tempB[3].x = b[0].x + z;
        tempB[3].y = b[0].y + z;
        
        updateXY(4);
    }

}
