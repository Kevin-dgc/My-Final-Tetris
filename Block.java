import java.awt.*;

public class Block extends Rectangle{

    public int x,y;
    public static final int SIZE = 30;
    public static final int Light = 75;
    public Color c;
    public static boolean ghost = false;
    
    public Block(Color c){
        this.c = c;
    }
    
    public Block(Color c, boolean ghost){
        this.c = c;
        this.ghost = ghost;
    }
    
    public void draw(Graphics2D g2){
        int margine = 2;
        g2.setColor(c);
        g2.fillRect(x+margine,y+margine,SIZE-margine-margine,SIZE-margine-margine);
    }
    
    
}