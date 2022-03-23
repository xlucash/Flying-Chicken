package me.xlucash.flyingchicken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class GameGraphics extends GameStates {
    @Override
    public void create () {
        batch = new SpriteBatch();

        background = new Texture("bg.png");
        gameOver = new Texture("gameover.png");
        tapImage = new Texture("startscreen.png");


        chickenCircle = new Circle();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);

        chickens = new Texture[2];
        chickens[0] = new Texture("chicken.png");
        chickens[1] = new Texture("chicken2.png");


        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        maxTubeOffset = Gdx.graphics.getHeight()/2-gap/2-100;
        randomGen = new Random();

        distanceBetweenTubes = Gdx.graphics.getWidth()*3/4;

        topTubeRectangles = new Rectangle[numberOfTubes];
        bottomTubeRectangles = new Rectangle[numberOfTubes];
    }
}
