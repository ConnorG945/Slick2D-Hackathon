package slick2d.test;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.awt.Font;


public class controls extends BasicGameState {
   //Extend from BasicGameState so we can have the window created
   public String mouse = "No input yet!";
   public boolean a=false;

   //A public string that will constantly be updated to show the mouse coordinates
   //We declare a new image and variables for it. We proceed to the init method
   public controls(int state) {
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
       //And so can images
       // g.drawString("u wanna get fortnited on",69,100);
       //Java Coordinates = Begins at top left, goes increases downward and increases rightward
       //g.drawRect(69,100,225,20);
       //drawRect begins at the specified x,y coords and extends for w pixels in the x and h pixel in the y
       //g.drawOval(100,100,50,100);
       //Same idea for drawOval as drawRect and other shapes
       //Image kira = new Image("res/memeKira.png");
       //g.drawImage(kira,0,0);
       //We create a new object of the image class, Kira and reference the image memeKira.
       //We draw the image from the origin
       //I'm commenting it out because the picture goes over the other parts
       //Now we're going to take the mouse location and store it as a variable, and print it wherever the mouse is\n"
       // +
       Image img = new Image("res/thumbnail.png");
       img.draw(0,5);
       //g.drawImage(face,500,50);
       Font awtFont = new Font("Times New Roman", Font.PLAIN, 40);
       TrueTypeFont font = new TrueTypeFont(awtFont, false);
       font.drawString(250, 100, "Use the up, down, left,",Color.red);
       font.drawString(150,150,"and right arrow keys to move around", Color.red);
       font.drawString(600, 400, "BACK →", Color.magenta);



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
       if(xpos<=735&&xpos>=600&&ypos<=89&&ypos>=57&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
           sbg.enterState(0);

       //Gets x and y coords, and constantly updates the mouse string so that at 50,50, it prints the string
       //That prints the coordinates
       //Should be noted that Slick2D and lwjgl begin their coordinates at the bottom left of the screen,
       //We typically begin at top left because of how we form the window. Just keep in mind
   }

   public int getID() {
       //5th Method; Method that returns the ID of this state; Since mainMenu has ID 0, it returns 0
       return 2;
   }
}
