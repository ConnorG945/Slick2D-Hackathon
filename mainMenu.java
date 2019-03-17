package slick2d.test;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.awt.Font;


public class mainMenu extends BasicGameState {
   //Extend from BasicGameState so we can have the window created
  // public String mouse = "No input yet!";
   public boolean a=false;
   public boolean b=false;
   public boolean c=false;

   public mainMenu(int state) {
       //1st Method Declared
       //Constructor that accepts the parameters of the game state for mainMenu, so 0;

   }

   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       //2nd Method Declared

   }

   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       //3rd Method; Render allows for graphics; Graphics ALWAYS has the variable g; Text can be drawn with it

       Image img = new Image("res/thumbnail.png");
       img.draw(0,5);

       Font awtFont = new Font("Times New Roman", Font.PLAIN, 60);
       TrueTypeFont font = new TrueTypeFont(awtFont, false);
       font.drawString(230, 50, "TRASH HERO", Color.yellow);
       font.drawString(225, 175, "START GAME", Color.red);
       font.drawString(275, 250, "CREDITS", Color.blue);
       font.drawString(250, 325, "CONTROLS", Color.magenta);
       if(a)
           sbg.enterState(1);
       if(b)
           sbg.enterState(2);
       if(c)
           sbg.enterState(3);



       //We set a public string variable named mouse so that we can use it
       //We go to the update method so that we can get the x and y coords of the mouse that Java already is reading


       //KeyBoard Input Arc
       //We draw this image as the sprite that will be moving
       //Now we have to write code so that it can update as it works


   }

   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
       //4th Method; Takes 3 parameters; Updates images on screen; Essentially allows for stuff to move around/ have
       //animation on screen

       int xpos = Mouse.getX();
       int ypos = Mouse.getY();
       Input input;
       input = gc.getInput();
       //mouse = "Mouse position x: " + xpos + " y: " + ypos;

       if(xpos<=524&&xpos>=274&&ypos<=236&&ypos>=194&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
           a=true;
       else
           a=false;
       if(xpos<=563&&xpos>=250&&ypos<=161&&ypos>=117&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
           b=true;
       else
           b=false;
       if(xpos<=603&&xpos>=227&&ypos<=314&&ypos>=268&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
           c=true;
       else
           c=false;


       //Gets x and y coords, and constantly updates the mouse string so that at 50,50, it prints the string
       //That prints the coordinates
       //Should be noted that Slick2D and lwjgl begin their coordinates at the bottom left of the screen,
       //We typically begin at top left because of how we form the window. Just keep in mind
   }

   public int getID() {
       //5th Method; Method that returns the ID of this state; Since mainMenu has ID 0, it returns 0
       return 0;
   }
}

