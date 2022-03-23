package me.xlucash.flyingchicken;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class GameStates extends FlyingChicken {
    public void startGame()
    {
        chickenY = Gdx.graphics.getHeight()/2F - chickens[0].getHeight()/2F;

        for(int i = 0; i < numberOfTubes; i++)
        {
            tubeOffset[i] = (randomGen.nextFloat() - 0.5f)*(Gdx.graphics.getHeight() - gap - 200);

            tubeX[i] = Gdx.graphics.getWidth()/2F-topTube.getWidth()/2F + Gdx.graphics.getWidth()+ i*distanceBetweenTubes;

            topTubeRectangles[i] = new Rectangle();

            bottomTubeRectangles[i] = new Rectangle();
        }
    }

    @Override
    public void render () {
        batch.begin();
        batch.draw(background,0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if(gameState == 1)
        {
            batch.draw(chickens[flapState], Gdx.graphics.getWidth()/2 - chickens[flapState].getWidth()/2, chickenY);

            if(tubeX[scoringTube] < Gdx.graphics.getWidth()/2)
            {
                score++;

                Gdx.app.log("Score",String.valueOf(score));

                if(scoringTube<numberOfTubes - 1)
                {
                    scoringTube++;
                } else {
                    scoringTube=0;
                }
            }

            if(Gdx.input.justTouched())
            {
                velocity = -30;

            }
            for(int i = 0; i < numberOfTubes; i++) {
                if (tubeX[i] < -topTube.getWidth()) {
                    tubeX[i] += numberOfTubes*distanceBetweenTubes;
                    tubeOffset[i] = (randomGen.nextFloat() - 0.5f)*(Gdx.graphics.getHeight() - gap - 200);
                } else {
                    tubeX[i] -= tubeVelocity;
                }
                batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
                batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);

                topTubeRectangles[i] = new Rectangle(
                        tubeX[i],
                        Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i],
                        topTube.getWidth(), topTube.getHeight());
                bottomTubeRectangles[i] = new Rectangle(
                        tubeX[i],
                        Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i],
                        bottomTube.getWidth(),bottomTube.getHeight());
            }

            if(chickenY>0) {

                velocity += gravity;
                chickenY -= velocity;

            } else {
                gameState = 2;
            }
        } else if (gameState == 0){
            batch.draw(
                    tapImage,
                    Gdx.graphics.getWidth()/2 - gameOver.getWidth()/2-250,
                    (Gdx.graphics.getHeight()/2 - gameOver.getHeight()/2)-gameOver.getHeight());
            if(Gdx.input.justTouched())
            {
                gameState = 1;
            }
        } else if (gameState == 2)
        {
            batch.draw(
                    gameOver,
                    Gdx.graphics.getWidth()/2 - gameOver.getWidth()/2,
                    Gdx.graphics.getHeight()/2 - gameOver.getHeight()/2);
            batch.draw(
                    tapImage,
                    Gdx.graphics.getWidth()/2 - gameOver.getWidth()/2-250,
                    (Gdx.graphics.getHeight()/2 - gameOver.getHeight()/2)-gameOver.getHeight());

            if(Gdx.input.justTouched())
            {
                gameState = 1;
                startGame();
                score = 0;
                scoringTube = 0;
                velocity = 0;
            }
        }

        if(flapState==0){
            flapState=1;
        } else flapState = 0;


        font.draw(batch, String.valueOf(score), Gdx.graphics.getWidth()/2 - 50, Gdx.graphics.getHeight()-100);
        batch.end();
        chickenCircle.set(
                Gdx.graphics.getWidth()/2,
                chickenY+chickens[flapState].getHeight()/2,
                chickens[flapState].getWidth()/2);

        for (int i = 0;i<numberOfTubes;i++)
        {
            if(Intersector.overlaps(chickenCircle,topTubeRectangles[i]) || Intersector.overlaps(chickenCircle, bottomTubeRectangles[i])){
                gameState = 2;

            }
        }
    }
}
