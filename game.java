package com.company;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.gui.TextField;
import java.awt.Font;

public class game extends BasicGameState {
    //This is where the player is spawned at the beginning of the level.
    int salX = 410;
    int salY = 400;
    //All of these will be initialized in the init method.
    int inventory;
    int level;
    int score;
    Trash[] garbage;
    Trash trashCan;
    Image[] things;
    Image sal;
    Image front;
    Image back;
    Image right;
    Image left;
    Image backGround;
    int runningTime;
    Font awtFont;
    TrueTypeFont font;
    Input input;
    Trash[] powerUps;
    //A public string that will constantly be updated to show the mouse coordinates
    //We declare a new image and variables for it. We proceed to the init method
    public game(int state) {
        //1st Method Declared
        //Constructor that accepts the parameters of the game state for mainMenu, so 0;

    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //Everything here is initialized when the game starts.
        input = gc.getInput();
        //Your score starts at 0.
        score=0;
        //Your inventory is empty.
        inventory=0;
        //You start at level 1
        level=1;
        //The runningTime of the game starts at 0.
        runningTime=0;
        //These initiates the images for the character going in different directions.
        right = new Image("res/right.png");
        left = new Image("res/left.png");
        front = new Image("res/front.png");
        back = new Image("res/back.png");
        //Initiates the character facing forward at the start of the game
        sal = front;
        //These are to print words.
        awtFont= new Font("Times New Roman", Font.PLAIN, 40);
        font=new TrueTypeFont(awtFont, false);
        //This is the trash can image and where it is located on the screen.
        trashCan = new Trash(new Image("res/trash can 1.png"),410,220);
        //The background image.
        backGround = new Image("res/park.png");
        //The player image
        front = new Image("res/front.png");
        //Image array of all the trash images.
        things = new Image[] {new Image("res/bag.png"), new Image("res/bottle.png"), new Image("res/paper.png"), new Image("res/can.png")};
        //This will be a Trash array. Trash is a class with 3 parameters: Image, x-position as an int, and y-position as an int.
        //Level 1 will begin with 4 pieces of trash.
        garbage = new Trash[4];
        //This loop randomly generates the trash in random spots in the game.
        for(int i=0;i<garbage.length;i++)
            garbage[i]=new Trash(things[random(1,4)-1],random(40,760),random(260,460));

        //Generates the powerUps.
        powerUps = new Trash[2];
        for(int i=0;i<powerUps.length;i++)
        {
            powerUps[i]=new Trash(new Image("res/clock2.png"),1000,random(260,460));
        }
        //2nd Method Declared
        //Takes a GameContainer and a StateBasedGame object as parameters
        //We declare a new object of the Image clas; the picture we will be using
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //3rd Method; Render allows for graphics; Graphics ALWAYS has the variable g; Text can be drawn with it
        //Draws the background.
        g.drawImage(backGround, 0, 0);
        //Draws all of the garbage.
        for(int i=0;i<garbage.length;i++)
            g.drawImage(garbage[i].getG(),garbage[i].getX(),garbage[i].getY());


        //Draws the power ups
        for(int i=0;i<powerUps.length;i++)
            g.drawImage(powerUps[i].getG(),powerUps[i].getX(),powerUps[i].getY());


        //Draws the trash can.
        g.drawImage(trashCan.getG(),trashCan.getX(),trashCan.getY());
        //Draws the player.
        g.drawImage(sal,salX,salY);

        //Draws the current level.
        font.drawString(520, 30, "Level: "+level, Color.black);
        //Draws the player's score.
        font.drawString(300, 30, "Score: "+score, Color.black);
       /*Draws the player's time remaining starting from 30 and counting down.
         It starts as black, but when there are 15 seconds remaining, the color changes to orange,
         and when there is 5 seconds remaining, the color changes to red.*/
        if(runningTime>15000)
        {
            if(runningTime>25000)
                font.drawString(50, 30, "Time: "+(30-runningTime/1000), Color.red);
            else
                font.drawString(50, 30, "Time: "+(30-runningTime/1000), Color.orange);
        }
        else
            font.drawString(50, 30, "Time: "+(30-runningTime/1000), Color.black);
        //When the player beats level 4, the win image will be drawn.
        if(level==5)
        {
            g.drawImage(new Image("res/win.png"),0,0);
        }
       /*If the player has not emptied out all the bargage on the screen into the trash can
         before 30 seconds, the lose screen will be drawn. */
        if(runningTime>30000&&!(win(garbage,inventory))) {
            g.drawImage(new Image("res/lose.png"), 0, 0);
        }


    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //4th Method; Takes 3 parameters; Updates images on screen; Essentially allows for stuff to move around/ have
        //animation on screen
        //If a player has beat a level, it resets everything and moves to the next level.
        if(win(garbage,inventory))
        {
            //Increases the level.
            level++;
            //The amount of garbage will increase by 4 every level.
            garbage = new Trash[level*4];
            //Garbage is placed in random positions.
            for(int i=0;i<garbage.length;i++)
                garbage[i]=new Trash(things[random(1,4)-1],random(40,760),random(260,460));
            for(int i=0;i<powerUps.length;i++)
                powerUps[i]=new Trash(new Image("res/clock2.png"),1000,random(260,460));

            //Running time is reset back to 0.
            runningTime=0;
            //Inventory is reset back to 0;
            inventory=0;
            //Player is spawned at the center of the screen.
            salX=410;
            salY=400;
        }
        //This keeps track of the running time in milliseconds.
        runningTime+=delta;

        //PowerUps spawning
        for(int i=0;i<powerUps.length;i++)
        {
            if (powerUps[i].getX() == 1000 && runningTime > 10000 + (i*10000)) {
                powerUps[i].setX(random(40, 760));
            }
        }
       /*WASD controls the character. The speed is affected by the inventory,
         the more garbage is in the inventory, the slower the character is.
         The character also changes states based on what direction it is going.
       */
       if(runningTime<30000) {
           if (input.isKeyDown(Input.KEY_D) && salX < 800 - 16) {
               salX += 4 - inventory;
               sal = right;
           }
           if (input.isKeyDown(Input.KEY_A) && salX > 0) {
               salX -= 4 - inventory;
               sal = left;
           }
           if (input.isKeyDown(Input.KEY_W) && salY > 220) {
               salY -= 4 - inventory;
               sal = back;
           }
           if (input.isKeyDown(Input.KEY_S) && salY < 500 - 32) {
               salY += 4 - inventory;
               sal = front;
           }
       }
       /*This is the collision detection between the character, the trash can, and the garbage.
         If the player collides with garbage, the garbage is taken off the screen by setting its x to 1000,
         and the inventory is increased by 1. If the player collides with the trash can,
         the inventory is reset back to 0.
       */
        for(int i=0;i<garbage.length;i++)
        {
            if(inventory<3)
            {
                if(salX>garbage[i].getX()-10&&  salX<garbage[i].getX()+28&&  salY>garbage[i].getY()-26&&  salY<garbage[i].getY()+20) {
                    garbage[i].setX(1000);
                    inventory++;
                }
            }
        }
        //Collision detection for powerUps. If a clock is collected, 5 seconds will be added to the time.
        for(int i=0;i<powerUps.length;i++)
        {
                if(salX>powerUps[i].getX()-10&&  salX<powerUps[i].getX()+28&&  salY>powerUps[i].getY()-26&&  salY<powerUps[i].getY()+20) {
                    powerUps[i].setX(900);
                    runningTime-=5000;
                }
        }
        //If the player collides with the trash can, the inventory is reset back to 0.
        if(salX>trashCan.getX()-14&&salX<trashCan.getX()+30&&salY>trashCan.getY()-5&&salY<trashCan.getY()+30) {
            score+=inventory;
            inventory = 0;
        }

    }

    //Checks if the level is beat by checking where the trash is on the screen, and wether the invetory is empty.
    public static boolean win(Trash[] x,int a)
    {
        for(int i=0;i<x.length;i++) {
            if(x[i].getX()<1000||a>0)
                return false;
        }
        return true;
    }
    //The random method with two parameters: Min and Max.
    //It returns a random number between min and max, inclusive.
    public static int random(int min, int max)
    {
        return (int)(Math.random() * ((max - min) + 1)) + min;
    }
    public int getID() {
        //5th Method; Method that returns the ID of this state; Since mainMenu has ID 0, it returns 0
        return 3;
    }
}