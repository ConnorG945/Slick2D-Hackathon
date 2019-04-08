package com.company;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.awt.Font;

public class shop extends BasicGameState
{
    int salX = 410;
    int salY = 400;
    int boot = 0;
    Image shopBackground;
    Font awtFont;
    TrueTypeFont font;
    Image sal;
    Image front;
    Image back;
    Image right;
    Image left;
    public shop(int state)
    {
        //Kappa keepo
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        awtFont = new Font("Times New Roman", Font.PLAIN, 50);
        font =new TrueTypeFont(awtFont, false);
        right = new Image("res/right.png");
        left = new Image("res/left.png");
        front = new Image("res/front.png");
        back = new Image("res/back.png");
        //Initiates the character facing forward at the start of the game
        sal = front;
        shopBackground = new Image("res/Alola Athletic.png");


    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        font.drawString(330, 50, "Kappa", Color.white);
        shopBackground.draw();
        g.drawImage(sal, salX, salY);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input;
        input = gc.getInput();
        if (input.isKeyDown(Input.KEY_D) && salX < 800 - 16) {
            salX += 4;
            salX += 4 + boot;
            sal = right;
        }
        if (input.isKeyDown(Input.KEY_A) && salX > 0) {
            salX -= 4;
            salX -= 4 + boot;
            sal = left;
        }
        if (input.isKeyDown(Input.KEY_W) && salY > 220) {
            salY -= 4;
            salY -= 4 + boot;
            sal = back;
        }
        if (input.isKeyDown(Input.KEY_S) && salY < 500 - 32) {
            salY += 4;
            salY += 4 + boot;
            sal = front;
        }


        }

    public int getID() {
        return 4;
    }
}
