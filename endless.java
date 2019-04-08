package com.company;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.gui.TextField;
import java.awt.Font;

public class endless extends BasicGameState {
    //This is where the player is spawned at the beginning of the level.
    int salX;
    int salY;
    //All of these will be initialized in the init method.
    int inventory;
    int level;
    int score;
    Trash[] garbage;
    Trash trashCan;
    Trash boots;
    int boot;
    Image[] things;
    Image sal;
    Image front;
    Image back;
    Image right;
    Image left;
    Image backGround;
    int runningTime;
    int totalTime;
    Font awtFont;
    TrueTypeFont font;
    Input input;
    Trash[] clocks;
    boolean inShop;
    Image shop;
    Trash backpack;
    int maxInv;
    Trash net;
    Trash clock;
    int items;
    Image[] ups;
    boolean hasBackpack;
    boolean hasBoots;
    boolean hasClock;
    int extraTime;
    boolean hasNet;
    Music mainLoop;
    boolean mainPlaying;
    Music shopLoop;
    boolean shopPlaying;
    Music powerCollect;
    int speed;
    Image backnet;
    Image frontnet;
    Image leftnet;
    Image rightnet;
    Trash[] coins;
    int currency;
    //A public string that will constantly be updated to show the mouse coordinates
    //We declare a new image and variables for it. We proceed to the init method
    public endless(int state) {
        //1st Method Declared
        //Constructor that accepts the parameters of the game state for mainMenu, so 0;

    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        salX=410;
        salY=400;
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
        totalTime=30000;
        //These initiates the images for the character going in different directions.
        right = new Image("res/right.png");
        left = new Image("res/left.png");
        front = new Image("res/front.png");
        back = new Image("res/back.png");
        backnet = new Image("res/back-net.png");
        frontnet = new Image("res/front-net.png");
        rightnet = new Image("res/right-net.png");
        leftnet = new Image("res/left-net.png");
        //Initiates the character facing forward at the start of the game
        sal = front;
        //These are to print words.
        awtFont= new Font("Times New Roman", Font.PLAIN, 40);
        font=new TrueTypeFont(awtFont, false);
        //This is the trash can image and where it is located on the screen.
        trashCan = new Trash(new Image("res/trash can 1.png"),410,220);
        //The boot number to be added to the speed.
        boot=0;
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

        //Generates the clocks.
        clocks = new Trash[2];
        for(int i=0;i<clocks.length;i++)
        {
            clocks[i]=new Trash(new Image("res/clock2.png"),1000,random(260,460));
        }
        inShop=false;
        shop= new Image("res/shop.png");
        //Generates the boots.
        boots = new Trash(new Image("res/boots2.png"), 390, 360);
        //Generates the backpack
        backpack= new Trash(new Image("res/backpack.png"),94, 165);
        maxInv=3;
        coins = new Trash[random(1,3)];
        for(int i=0;i<coins.length;i++)
        {
            coins[i]=new Trash(new Image("res/coinframe1.png"),1000,random(260,460));
        }
        currency=0;
        //Generates the net
        net=new Trash(new Image("res/net.png"),388, 163);
        //Generates the clock
        clock=new Trash(new Image("res/clock.png"), 98, 365);
        //How many powerups you have
        items=0;
        //Powerups array to be displayed after Items: during the game
        ups=new Image[4];
        hasBackpack=false;
        hasBoots=false;
        hasClock=false;
        extraTime=0;
        hasNet=false;
        mainLoop=new Music("res/Main loop.wav");
        mainPlaying=false;
        shopLoop=new Music("res/Shop loop.wav");
        shopPlaying=false;
        powerCollect=new Music("res/Powerup2.wav");
        speed=4;
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
        //Draws the clocks
        for(int i=0;i<clocks.length;i++)
            g.drawImage(clocks[i].getG(),clocks[i].getX(),clocks[i].getY());
        for(int i=0;i<coins.length;i++)
            g.drawImage(coins[i].getG(),coins[i].getX(),coins[i].getY());
        //Draws the trash can.
        //Draws the trash can.
        g.drawImage(trashCan.getG(),trashCan.getX(),trashCan.getY());
        //Draws the player.
        g.drawImage(sal,salX,salY);

        //Draws the current level.
        font.drawString(520, 20, "Level: "+level, Color.black);
        //Draws the player's score.
        font.drawString(300, 20, "Score: "+score, Color.black);
       /*Draws the player's time remaining starting from 30 and counting down.
         It starts as black, but when there are 15 seconds remaining, the color changes to orange,
         and when there is 5 seconds remaining, the color changes to red.*/
        if(totalTime-runningTime<15000)
        {
            if(totalTime-runningTime<5000)
                font.drawString(50, 20, "Time: "+((totalTime/1000)-(runningTime/1000)), Color.red);
            else
                font.drawString(50, 20, "Time: "+((totalTime/1000)-(runningTime/1000)), Color.orange);
        }
        else
            font.drawString(50, 20, "Time: "+((totalTime/1000)-(runningTime/1000)), Color.black);

        font.drawString(50, 60, "Items:", Color.black);
        for(int i=0;i<items;i++)
        {
            g.drawImage(ups[i],160+30*i,77);
        }

        if(inShop)
        {
            g.drawImage(shop,0,0);
            if(!hasBackpack)
                g.drawImage(backpack.getG(),backpack.getX(),backpack.getY());
            if(!hasNet)
                g.drawImage(net.getG(),net.getX(),net.getY());
            if(!hasBoots)
                g.drawImage(boots.getG(),boots.getX(),boots.getY());
            if(!hasClock)
                g.drawImage(clock.getG(),clock.getX(),clock.getY());
            g.drawImage(sal,salX,salY);
        }


       /*If the player has not emptied out all the bargage on the screen into the trash can
         before 30 seconds, the lose screen will be drawn. */
            if(runningTime>totalTime) {
                g.drawImage(new Image("res/endgame.png"), 0, 0);
                font.drawString(600, 220, "Score: "+score, Color.black);
                font.drawString(270, 460, "Press R to restart", Color.black);
            }


    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //4th Method; Takes 3 parameters; Updates images on screen; Essentially allows for stuff to move around/ have
        //animation on screen
        //If a player has beat a level, it resets everything and moves to the next level.
        if(input.isKeyDown(Input.KEY_R)){
            gc.reinit();
        }
        if (win(garbage, inventory)) {
            if (level % 2 == 0) {
                if (!inShop) {
                    salX = 5;
                    salY = 265;
                }
                inShop = true;
            }
            if (inShop) {
                if (!shopPlaying) {
                    mainLoop.stop();
                    mainPlaying = false;
                    shopLoop.loop();
                    shopPlaying = true;
                }
                if (input.isKeyDown(Input.KEY_D) && salX < 800 - 16) {
                    salX += 4+boot;
                    sal = right;
                }
                if (input.isKeyDown(Input.KEY_A) && salX > 0) {
                    salX -= 4 +boot;
                    sal = left;
                }
                if (input.isKeyDown(Input.KEY_W) && salY > 100) {
                    salY -= 4+boot;
                    sal = back;
                }
                if (input.isKeyDown(Input.KEY_S) && salY < 500 - 32) {
                    salY += 4+boot;
                    sal = front;
                }
                if (!hasBackpack && salX > backpack.getX() - 10 && salX < backpack.getX() + 28 && salY > backpack.getY() - 26 && salY < backpack.getY() + 20) {
                    hasBackpack = true;
                    ups[items] = backpack.getG();
                    items++;
                }
                if (!hasNet && salX > net.getX() - 10 && salX < net.getX() + 28 && salY > net.getY() - 26 && salY < net.getY() + 20) {
                    hasNet = true;
                    ups[items] = net.getG();
                    items++;
                }
                if (!hasBoots && salX > boots.getX() - 10 && salX < boots.getX() + 28 && salY > boots.getY() - 26 && salY < boots.getY() + 20) {
                    hasBoots = true;
                    ups[items] = boots.getG();
                    items++;
                    boot=2;
                }
                if (!hasClock && salX > clock.getX() - 10 && salX < clock.getX() + 28 && salY > clock.getY() - 26 && salY < clock.getY() + 20) {
                    hasClock = true;
                    ups[items] = clock.getG();
                    items++;
                }
                //Exiting the Shop
                if (salX > 650) {
                    shopLoop.stop();
                    shopPlaying = false;
                    inShop = false;
                    //Increases the level.
                    level++;
                    //The amount of garbage will increase by 4 every level.
                    garbage = new Trash[level * 4];
                    //Garbage is placed in random positions.
                    for (int i = 0; i < garbage.length; i++)
                        garbage[i] = new Trash(things[random(1, 4) - 1], random(40, 760), random(260, 460));
                    //Clocks are placed in random positions
                    for (int i = 0; i < clocks.length; i++)
                        clocks[i] = new Trash(new Image("res/clock2.png"), 1000, random(260, 460));
                    coins = new Trash[random(1,3)];
                    for(int i=0;i<coins.length;i++)
                    {
                        coins[i]=new Trash(new Image("res/coinframe1.png"),1000,random(260,460));
                    }
                    //Running time is reset back to 0.
                    runningTime = 0;
                    //Inventory is reset back to 0;
                    inventory = 0;
                    //Player is spawned at the center of the screen.
                    salX = 410;
                    salY = 400;
                }
            } else {
                //Increases the level.
                level++;
                //The amount of garbage will increase by 4 every level.
                garbage = new Trash[level * 4];
                //Garbage is placed in random positions.
                for (int i = 0; i < garbage.length; i++)
                    garbage[i] = new Trash(things[random(1, 4) - 1], random(40, 760), random(260, 460));
                //Clocks are placed in random positions
                for (int i = 0; i < clocks.length; i++)
                    clocks[i] = new Trash(new Image("res/clock2.png"), 1000, random(260, 460));
                coins = new Trash[random(1,3)];
                for(int i=0;i<coins.length;i++)
                {
                    coins[i]=new Trash(new Image("res/coinframe1.png"),1000,random(260,460));
                }
                //Running time is reset back to 0.
                runningTime = 0;
                //Inventory is reset back to 0;
                inventory = 0;
                //Player is spawned at the center of the screen.
                salX = 410;
                salY = 400;
            }
        } else {
            if (!mainPlaying) {
                mainPlaying = true;
                mainLoop.loop();
            }
            //This keeps track of the running time in milliseconds.
            runningTime += delta;



            speed = 4 - inventory + boot;

            if (hasBackpack) {
                speed = 4 - (inventory / 2) + boot;
                maxInv = 6;
            }

            if (hasClock) {
                extraTime = 10000;
                totalTime=40000;
            }

            //Clocks spawning
            for (int i = 0; i < clocks.length; i++) {
                if (clocks[i].getX() == 1000 && runningTime > 10000 + (i * 10000)) {
                    clocks[i].setX(random(40, 760));
                }
            }
            //Coins spawning
            for (int i = 0; i < coins.length; i++) {
                if (coins[i].getX() == 1000 && runningTime > 5000 + (i * 10000)) {
                    coins[i].setX(random(40, 760));
                }
            }
       /*WASD controls the character. The speed is affected by the inventory,
         the more garbage is in the inventory, the slower the character is.
         The character also changes states based on what direction it is going.
       */
            if (runningTime < totalTime) {
                if (input.isKeyDown(Input.KEY_D) && salX < 800 - 16) {
                    salX += speed;
                    sal = right;
                }
                if (input.isKeyDown(Input.KEY_A) && salX > 0) {
                    salX -= speed;
                    sal = left;
                }
                if (input.isKeyDown(Input.KEY_W) && salY > 220) {
                    salY -= speed;
                    sal = back;
                }
                if (input.isKeyDown(Input.KEY_S) && salY < 500 - 32) {
                    salY += speed;
                    sal = front;
                }
                if (runningTime < totalTime&& hasNet) {
                    if (input.isKeyDown(Input.KEY_D) && salX < 800 - 16) {
                        salX += speed;
                        sal = rightnet;
                    }
                    if (input.isKeyDown(Input.KEY_A) && salX > 0) {
                        salX -= speed;
                        sal = leftnet;
                    }
                    if (input.isKeyDown(Input.KEY_W) && salY > 220) {
                        salY -= speed;
                        sal = backnet;
                    }
                    if (input.isKeyDown(Input.KEY_S) && salY < 500 - 32) {
                        salY += speed;
                        sal = frontnet;
                    }

                }
       /*This is the collision detection between the character, the trash can, and the garbage.
         If the player collides with garbage, the garbage is taken off the screen by setting its x to 1000,
         and the inventory is increased by 1. If the player collides with the trash can,
         the inventory is reset back to 0.
       */
                for (int i = 0; i < garbage.length; i++) {
                    if (inventory < maxInv) {
                        if(hasNet&&salX > garbage[i].getX() - 15 && salX < garbage[i].getX() + 33 && salY > garbage[i].getY() - 26 && salY < garbage[i].getY() + 20)
                        {
                            garbage[i].setX(1000);
                            inventory++;
                        }
                        else
                        if (salX > garbage[i].getX() - 10 && salX < garbage[i].getX() + 28 && salY > garbage[i].getY() - 26 && salY < garbage[i].getY() + 20) {
                            garbage[i].setX(1000);
                            inventory++;
                        }
                    }
                }
                //Collision detection for powerUps. If a clock is collected, 5 seconds will be added to the time.
                for (int i = 0; i < clocks.length; i++) {
                    if (salX > clocks[i].getX() - 10 && salX < clocks[i].getX() + 28 && salY > clocks[i].getY() - 26 && salY < clocks[i].getY() + 20) {
                        clocks[i].setX(900);
                        runningTime -= 5000;
                    }
                }
                for (int i = 0; i < coins.length; i++) {
                    if (salX > coins[i].getX() - 10 && salX < coins[i].getX() + 28 && salY > coins[i].getY() - 26 && salY < coins[i].getY() + 20) {
                        coins[i].setX(900);
                        currency++;
                    }
                }
                //Collision for coins
                //If the player collides with the trash can, the inventory is reset back to 0.
                if (salX > trashCan.getX() - 14 && salX < trashCan.getX() + 30 && salY > trashCan.getY() - 5 && salY < trashCan.getY() + 30) {
                    score += inventory;
                    inventory = 0;
                }
            }
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
        return 4;
    }
}



