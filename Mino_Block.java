import java.awt.*;

public class Mino_Block extends Mino
{
    public boolean isGhost;
    public final int MinoNumber = 3;

    
    public Mino_Block(boolean ghost){
        int r = 255;
        int g = 225;
        int b = 0;
        
        if(!ghost){
            create(new Color(r, g, b)); // yellow
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
        
        b[1].x = b[0].x + z;
        b[1].y = b[0].y;
        
        b[2].x = b[0].x;
        b[2].y = b[0].y + z;
        
        b[3].x = b[0].x + z;
        b[3].y = b[0].y + z;
    }
}
