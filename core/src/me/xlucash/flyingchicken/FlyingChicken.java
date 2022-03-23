package me.xlucash.flyingchicken;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

public class FlyingChicken extends ApplicationAdapter {
	SpriteBatch batch;

	Texture background;
	Texture gameOver;
	Texture tapImage;
	Texture[] chickens;
	Texture topTube;
	Texture bottomTube;
	Rectangle[] topTubeRectangles;
	Rectangle[] bottomTubeRectangles;
	Circle chickenCircle;
	BitmapFont font;
	Random randomGen;

	int flapState = 0;
	float chickenY = 0;
	float velocity = 0;
	float gravity = 1.5f;

	int gameState = 0;

	int score = 0;
	int scoringTube = 0;

	float gap = 550;
	float maxTubeOffset;
	float tubeVelocity = 4;
	int numberOfTubes = 4;
	float[] tubeX = new float[numberOfTubes];
	float[] tubeOffset = new float[numberOfTubes];
	float distanceBetweenTubes;
}
