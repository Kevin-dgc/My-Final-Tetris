import java.awt.*;

public class Mino_Bar extends Mino
{
    public boolean isGhost;
    public final int MinoNumber = 4;

    public Mino_Bar(boolean ghost){
        int r = 85;
        int g = 206;
        int b = 225;
        
        if(!ghost){
            create(new Color(r, g, b)); // light blue
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
        
        b[1].x = b[0].x - z;
        b[1].y = b[0].y;
        
        b[2].x = b[0].x + z;
        b[2].y = b[0].y;
        
        b[3].x = b[0].x + z + z;
        b[3].y = b[0].y;
    }
        public void getDirection1(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x - z;
        tempB[1].y = b[0].y;
        
        tempB[2].x = b[0].x + z;
        tempB[2].y = b[0].y;
        
        tempB[3].x = b[0].x + z + z;
        tempB[3].y = b[0].y;
        
        updateXY(1);
    }
    public void getDirection2(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y + z;
        
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - z;
        
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y - z - z;
        
        updateXY(2);        
    }
    public void getDirection3(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x + z;
        tempB[1].y = b[0].y;
        
        tempB[2].x = b[0].x - z;
        tempB[2].y = b[0].y;
        
        tempB[3].x = b[0].x - z - z;
        tempB[3].y = b[0].y;
        
        updateXY(3);
    }
    public void getDirection4(){
        int z = Block.SIZE;
        
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
    
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - z;
        
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + z;
        
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y + z + z;
        
        updateXY(4);
    }
}
