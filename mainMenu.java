package com.company;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.awt.Font;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.ShapeRenderer;


public class mainMenu extends BasicGameState {
    //Extend from BasicGameState so we can have the window created
    // public String mouse = "No input yet!";
    Input input;
    int xpos;
    int ypos;
    public boolean a=false;
    public boolean b=false;
    public boolean c=false;
    public boolean d=false;
    public boolean e=false;
    public boolean f=false;
    public boolean h=false;
    public boolean i=false;
    Image img;
    Shape Rect1;
    Shape Rect2;
    Shape Rect3;
    Shape Rect4;
    Shape Rect5;
    Shape Rect6;
    Shape Rect7;
    Shape Rect8;
    Color color1;
    Color color2;
    Color color3;
    Font awtFont;
    TrueTypeFont font;
    GradientFill filler1;
    GradientFill filler2;

    //A public string that will constantly be updated to show the mouse coordinates
    //We declare a new image and variables for it. We proceed to the init method
    public mainMenu(int state) {
        //1st Method Declared
        //Constructor that accepts the parameters of the game state for mainMenu, so 0;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //2nd Method Declared
        input = gc.getInput();
        img = new Image("res/thumbnail.png");
        Rect1=new RoundedRectangle(168,125,500,63,20);
        Rect2=new RoundedRectangle(239,202,320,63,20);
        Rect3=new RoundedRectangle(208,277,404,63,20);
        Rect4=new RoundedRectangle(168,122,500,70,20);
        Rect5=new RoundedRectangle(239,199,320,70,20);
        Rect6=new RoundedRectangle(208,274,404,70,20);
        Rect7=new RoundedRectangle(100,357,622,63,20);
        Rect8=new RoundedRectangle(100,352,622,70,20);


        color1=new Color(150,115,0);
        color2=new Color(171,128,1);
        color3=new Color(58,36,1);
        awtFont = new Font("Calibri", Font.PLAIN, 60);
        font = new TrueTypeFont(awtFont, false);
        filler1=new GradientFill(100,200,color1,500,300,color1);
        filler2=new GradientFill(100,200,color2,500,300,color2);


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


        img.draw(0,0);
        //Image face=new Image("Pictures/TreePic.png");
        //g.drawImage(face,500,50);

        g.fill(Rect1,filler2);
        g.fill(Rect2,filler2);
        g.fill(Rect3,filler2);
        g.fill(Rect7,filler2);
        g.setColor(color3);
        g.draw(Rect1);
        g.draw(Rect2);
        g.draw(Rect3);
        g.draw(Rect7);
        font.drawString(260, 50, "Trash Hero", Color.yellow);
        font.drawString(255, 125, "Start Game", color3);
        font.drawString(290, 200, "Credits", color3);
        font.drawString(280, 275, "Controls", color3);
        font.drawString(210, 350, "Endless Mode", color3);
        if(a)
            sbg.enterState(3);
        if(b)
            sbg.enterState(1);
        if(c)
            sbg.enterState(2);
        if(h)
            sbg.enterState(4);
        if(i){
            g.fill(Rect8,filler1);
            font.drawString(210, 350, "Endless Mode", color3);
        }
        if(d){
            g.fill(Rect4,filler1);
            font.drawString(255, 125, "Start Game", color3);}
        if(e){
            g.fill(Rect5,filler1);
            font.drawString(290, 200, "Credits", color3);}
        if(f){
            g.fill(Rect6,filler1);
            font.drawString(280, 275, "Controls", color3);




        }



        //We set a public string variable named mouse so that we can use it
        //We go to the update method so that we can get the x and y coords of the mouse that Java already is reading


        //KeyBoard Input Arc
        //We draw this image as the sprite that will be moving
        //Now we have to write code so that it can update as it works


    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //4th Method; Takes 3 parameters; Updates images on screen; Essentially allows for stuff to move around/ have
        //animation on screen

        xpos = Mouse.getX();
        ypos = Mouse.getY();

        if(xpos<=661&&xpos>=175&&ypos<=368&&ypos>=314&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
            a=true;
        else
            a=false;
        if(xpos<=558&&xpos>=239&&ypos<=295&&ypos>=231&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
            b=true;
        else
            b=false;
        if(xpos<=611&&xpos>=207&&ypos<=219&&ypos>=158&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
            c=true;
        else
            c=false;
        if(xpos<=661&&xpos>=175&&ypos<=368&&ypos>=314)
            d=true;
        else
            d=false;
        if(xpos<=558&&xpos>=239&&ypos<=295&&ypos>=231)
            e=true;
        else
            e=false;
        if(xpos<=611&&xpos>=207&&ypos<=219&&ypos>=158)
            f=true;
        else
            f=false;
        if(xpos<=722&&xpos>=100&&ypos<=143&&ypos>=79&&input.isMousePressed(input.MOUSE_LEFT_BUTTON))
            h=true;
        else
            h=false;
        if(xpos<=722&&xpos>=100&&ypos<=143&&ypos>=79)
            i=true;
        else
            i=false;




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
