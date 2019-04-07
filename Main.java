package com.company;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.Game;
import org.newdawn.slick.state.*;

public class Main extends StateBasedGame {
    //Slick 2D requires that the class inherits all the methods from StateBasedGame
    //The methods are abstract though, so they can be manipulated
    public static final String gameName = "Environmental";
    //A string that can't be changed that is the name of the game;
    //To be displayed on title screen and on window
    //3 Screens; Title Screen, Controls, and Game
    public static final int mainMenu = 0;
    public static final int credits = 1;
    public static final int controls = 2;
    public static final int game = 3;
    public static final int endless=4;
    //public static final int controlScreen = 1;
    //public static final int play = 2;
    //Give each state of the game a integer so it can be called later

    public Main(String gameName) {
        //Calling the constructor for this class
        super(gameName);
        //Adds the title to the top of the window
        //Calls the constructor(?) of the super class, StateBasedGame, which then allows for this
        //To have this string at the top of the window
        this.addState(new mainMenu(mainMenu));
        this.addState(new credits(credits));
        this.addState(new controls(controls));
        this.addState(new game(game));
        this.addState(new endless(endless));
        //this.addState(new controlScreen(controlScreen));
        //this.addState(new play(play));
        //Though the classes are not created yet, the classes are being initalized here
        //As new game states
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        //An abstract method from the super class StateBasedGame that must be declared
        //GameContainer = The main screen; manages FPS and inputs
        this.getState(mainMenu).init(gc, this);
        //this.getState(controlScreen).init(gc,this);
        //this.getState(play).init(gc,this);
        //We initialize the states of the 3 screens for the game
        //We use the getState() method and then initialize it using the dot init() method
        //dot init() takes 2 parameters, the game container and the object, so gc and the this keyword for "this" object
        this.enterState(mainMenu);
        //When a parameter asks for ID, it is asking for the name, like mainMenu, not a #
        //This command says that when the game begins, take them to the menu screen
        //Enter the state that is the mainMenu
    }

    /*
    Everything done here was not done linearly
    Once the try catch loops and initStatesList and Main Class were written,
    the 3 classes, play,ControlScreen and mainMenu, were create in the com.company
    package
    This will serve as step reference for the order in which things were done

    1. Make the class Main inherit from StateBasedGame
    2. Within this declare the variable for the name of the game and the different states
    / Screens that are desired to be created
    3. Create the Main Class by calling its superclass constructor and creating states for the states/screen variables
    That were created in step 2
    4.Create the initStateLists function to initialize the states of the game and enter the state desired to be the 1st
    screen shown
    5. Create an instance of the AppGameContainer and set up the try/catch loop
    6. Within the try catch loop, set the screen dimensions and make the command to start the window when the code is ran
    7. Create the 3 classes of the states declared beforehand

    (These prior steps initialize everything, but nothing game wise is made, therefore, the next steps are actually the game being made

    8. We went into the render class of mainMenu and drew shapes and printed strings to screen with the functions there
    9. We begin to import images into the game; View the creation of an object of Image at the render class in mainMenu
    10. We begin to get mouse input from the user; Go to mainMenu class to see how this is done
    11. Keyboard Input;
     */
    public static void main(String[] args) throws SlickException {
        AppGameContainer appgc;
        //Creating an object of AppGameContainer to actually hold the game
        try {
            appgc = new AppGameContainer(new Main(gameName));
            //Essentially says we're making a window that will hold a game, the one that is being created with the name
            //gameName (Or Golden Portals as that variable is equivalent to)
            appgc.setDisplayMode(800, 500, false);
            appgc.setTargetFrameRate(60);
            //Alternatively, appgc.setDisplayMode(length,width,false) (The last parameter asks if fullscreen is desired
            appgc.start();
            //Start the window when the code runs
        } catch (SlickException e) {
            e.printStackTrace();
            //Whenever making a game using Slick2D, the main method has to have code in a try/catch loop for
            //error handling; e.printStackTrace() is the proper way to see exceptions, but it would also probably work
            //with System.out.println(e);


        }
    }
}





