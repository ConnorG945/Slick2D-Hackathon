package slick2d.test;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.gui.TextField;
import java.awt.Font;

public class game extends BasicGameState {
   //Extend from BasicGameState so we can have the window created
   int salX = 410;
   int salY = 400;
   int inventory;
   int level;
   int score;
   Trash[] garbage;
   Trash trashCan;
   Image[] things;
   Image sal;
   Image backGround;
   int runningTime;
   Font awtFont;
   TrueTypeFont font;

   //A public string that will constantly be updated to show the mouse coordinates
   //We declare a new image and variables for it. We proceed to the init method
   public game(int state) {
       //1st Method Declared
       //Constructor that accepts the parameters of the game state for mainMenu, so 0;

   }
   public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
       score=0;
       inventory=0;
       level=1;
       runningTime=0;
       awtFont= new Font("Times New Roman", Font.PLAIN, 40);
       font=new TrueTypeFont(awtFont, false);
       trashCan = new Trash(new Image("res/trash can 1.png"),410,220);
       backGround = new Image("res/park.png");
       sal = new Image("res/front.png");
       things = new Image[] {new Image("res/bag.png"), new Image("res/bottle.png"), new Image("res/paper.png"), new Image("res/can.png")};
       garbage = new Trash[level*4];
       for(int i=0;i<garbage.length;i++)
           garbage[i]=new Trash(things[random(1,4)-1],random(40,760),random(260,460));
       //2nd Method Declared
       //Takes a GameContainer and a StateBasedGame object as parameters
       //We declare a new object of the Image clas; the picture we will be using
   }

   public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
       //3rd Method; Render allows for graphics; Graphics ALWAYS has the variable g; Text can be drawn with it

       g.drawImage(backGround, 0, 0);
       for(int i=0;i<garbage.length;i++)
           g.drawImage(garbage[i].getG(),garbage[i].getX(),garbage[i].getY());
       g.drawImage(trashCan.getG(),trashCan.getX(),trashCan.getY());
       g.drawImage(sal,salX,salY);

       font.drawString(520, 30, "Level: "+level, Color.black);
       font.drawString(300, 30, "Score: "+score, Color.black);
       if(runningTime>15000)
       {
           if(runningTime>25000)
               font.drawString(50, 30, "Level: "+(30-runningTime/1000), Color.red);
           else
               font.drawString(50, 30, "Time: "+(30-runningTime/1000), Color.orange);

       }
       else
           font.drawString(50, 30, "Time: "+(30-runningTime/1000), Color.black);

       if(level==5)
       {
           g.drawImage(new Image("res/win.png"),0,0);
       }

       if(runningTime>30000&&!(win(garbage,inventory))) {
           g.drawImage(new Image("res/lose.png"), 0, 0);
       }


   }

   public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
       //4th Method; Takes 3 parameters; Updates images on screen; Essentially allows for stuff to move around/ have
       //animation on screen

       if(win(garbage,inventory))
       {
           level++;
           garbage = new Trash[level*4];
           for(int i=0;i<garbage.length;i++)
               garbage[i]=new Trash(things[random(1,4)-1],random(40,760),random(260,460));
           runningTime=0;
           inventory=0;
           salX=410;
           salY=400;
       }

       runningTime+=delta;
       Input input;
       input = gc.getInput();
       if(input.isKeyDown(Input.KEY_UP)&&salY>220) {
           salY-=4-inventory;
           sal = new Image("res/back.png");
       }
       if(input.isKeyDown(Input.KEY_DOWN)&&salY<500-32) {
           salY+=4-inventory;
           sal = new Image("res/front.png");
       }
       if(input.isKeyDown(Input.KEY_RIGHT)&&salX<800-16) {
           salX+=4-inventory;
           sal = new Image("res/right.png");
       }
       if(input.isKeyDown(Input.KEY_LEFT)&&salX>0) {
           salX-=4-inventory;
           sal = new Image("res/left.png");
       }
       for(int i=0;i<garbage.length;i++)
       {
           if(inventory<3)
           {
               if(salX>garbage[i].getX()-10&&  salX<garbage[i].getX()+28&&  salY>garbage[i].getY()-26&&  salY<garbage[i].getY()+20) {
                   garbage[i].setX(1000);
                   inventory++;
               }
           }
           if(salX>trashCan.getX()-14&&salX<trashCan.getX()+30&&salY>trashCan.getY()-5&&salY<trashCan.getY()+30) {
               score+=inventory;
               inventory = 0;
           }
       }

    }
   public int getID() {
       //5th Method; Method that returns the ID of this state; Since mainMenu has ID 0, it returns 0
       return 3;
   }
   public static boolean win(Trash[] x,int a)
   {
       for(int i=0;i<x.length;i++) {
           if(x[i].getX()<1000||a>0)
               return false;
       }
       return true;
   }
   public static int random(int min, int max)
   {
       return (int)(Math.random() * ((max - min) + 1)) + min;
   }
}

