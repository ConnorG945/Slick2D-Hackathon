
package slick2d.test;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.awt.Font;


public class credits extends BasicGameState {
   //Extend from BasicGameState so we can have the window created
   public boolean a=false;

   //A public string that will constantly be updated to show the mouse coordinates
   //We declare a new image and variables for it. We proceed to the init method
   public credits(int state) {
       //1st Method Declared
       //Constructor that accepts the parameters of the game state for mainMenu, so 0;

   }

   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       //2nd Method Declared
       //Takes a GameContainer and a StateBasedGame object as parameters
       //We declare a new object of the Image clas; the picture we will be using

   }

   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       //3rd Method; Render allows for graphics; Graphics ALWAYS has the variable g; Text can be drawn with it
       
       Image img = new Image("res/thumbnail.png");
       img.draw(0,5);
       Font awtFont = new Font("Times New Roman", Font.PLAIN, 50);
       TrueTypeFont font = new TrueTypeFont(awtFont, false);
       font.drawString(125, 50, "Game made by: Nico, Andres", Color.yellow);
       font.drawString(300, 150, "Artist: Wei", Color.red);
       Font awtFont2 = new Font("Times New Roman", Font.PLAIN, 40);
       TrueTypeFont font2 = new TrueTypeFont(awtFont2, false);
       font2.drawString(50, 275, "Title screen, credits, controls made by Connor", Color.blue);
       font.drawString(600, 400, "BACK →", Color.magenta);

   }

   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
       //4th Method; Takes 3 parameters; Updates images on screen; Essentially allows for stuff to move around/ have
       //animation on screen

       int xpos = Mouse.getX();
       int ypos = Mouse.getY();
       Input input;
       input = gc.getInput();
       if(xpos<=735&&xpos>=600&&ypos<=89&&ypos>=57&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
           sbg.enterState(0);
   }
   public int getID() {
       //5th Method; Method that returns the ID of this state; Since mainMenu has ID 0, it returns 0
       return 1;
   }
}
