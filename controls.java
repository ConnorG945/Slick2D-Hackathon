package com.company;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.awt.Font;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.ShapeRenderer;


public class controls extends BasicGameState {
    //Extend from BasicGameState so we can have the window created
    Mouse mouse;
    public boolean a=false;
    Input input;
    Image img;
    Font awtFont;
    TrueTypeFont font;
    Shape Rect1;
    int xpos;
    int ypos;
    Color color1;
    GradientFill filler1;

    //A public string that will constantly be updated to show the mouse coordinates
    //We declare a new image and variables for it. We proceed to the init method
    public controls(int state) {
        //1st Method Declared
        //Constructor that accepts the parameters of the game state for mainMenu, so 0;

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        input = gc.getInput();
        img = new Image("res/thumbnail.png");
        awtFont = new Font("Times New Roman", Font.PLAIN, 40);
        font = new TrueTypeFont(awtFont, false);
        int xpos;
        int ypos;
        Rect1=new RoundedRectangle(50,59,728,375,20);
        color1=new Color(171,128,1);
        filler1=new GradientFill(57,432,color1,772,72,color1);


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
        img.draw(0,5);
        //g.drawImage(face,500,50);
        g.draw(Rect1);
        g.fill(Rect1,filler1);
        font.drawString(250, 100, "Use the W, A, S, D",Color.black);
        font.drawString(150,150,"         keys to move around.", Color.black);
        font.drawString(230,200,"Pick up all of the trash!", Color.black);
        font.drawString(175,250,"You can only pick up 3 at a time.", Color.black);
        font.drawString(100,300,"The more you have, the slower you go.", Color.black);
        font.drawString(125,350,"Empty out your inventory at the trash.", Color.black);
        font.drawString(550, 400, "BACK →", Color.magenta);
        font.drawString(150,350, "x: "+xpos+" y: "+ypos);



        //We set a public string variable named mouse so that we can use it
        //We go to the update method so that we can get the x and y coords of the mouse that Java already is reading


        //KeyBoard Input Arc
        //We draw this image as the sprite that will be moving
        //Now we have to write code so that it can update as it works


    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //4th Method; Takes 3 parameters; Updates images on screen; Essentially allows for stuff to move around/ have
        //animation on screen

        xpos = mouse.getX();
        ypos = mouse.getY();


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
