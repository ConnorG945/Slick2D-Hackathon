package slick2d.test;
import org.newdawn.slick.Image;
public class Trash{
   Image g;
   int x;
   int y;

   public Trash(Image g,int x,int y) {
       this.g = g;


       this.x = x;
       this.y = y;
   }
   public Image getG()
   { return this.g; }

   public int getX()
   { return this.x; }

   public int getY()
   { return this.y; }
   public void setX(int x)
   { this.x=x; }
}
