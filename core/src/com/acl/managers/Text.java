package com.acl.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;


public class Text {

    public final SpriteBatch textBatch;
    public BitmapFont font;


    public Text() {
        font = new BitmapFont();

        //CAM
        OrthographicCamera textCamera = new OrthographicCamera();
        textCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        textBatch = new SpriteBatch();


        //GENERATE
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("mcfont.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        //PARAMS
        params.size = Gdx.graphics.getWidth() * 50 / 1024;
        params.color = new Color(255, 255, 0, 0.75f);
        params.borderColor = Color.BLACK;
        params.borderWidth = (float) (Gdx.graphics.getWidth() * 3) / 1024;
        params.characters = FreeTypeFontGenerator.DEFAULT_CHARS;

        //CAM
        textBatch.setProjectionMatrix(textCamera.combined);
        font = fontGenerator.generateFont(params);

    }

    public void displayScore(String message) {
        textBatch.begin();
        float posX = (float) (Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 6);
        float posY = (float) (Gdx.graphics.getHeight() - 10);
        font.draw(textBatch, message, posX, posY, message.length(), 1, false);
        textBatch.end();
    }

    public void displayHP(String message) {
        textBatch.begin();
        float posX = (float) Gdx.graphics.getWidth() / 6;
        float posY = (float) (Gdx.graphics.getHeight() - 10);
        font.draw(textBatch, message, posX, posY, message.length(), 1, false);
        textBatch.end();
    }

    public void displayDie(String message) {
        textBatch.begin();
        float posX = (float) (Gdx.graphics.getWidth() / 2 - 15);
        float posY = (float) (Gdx.graphics.getHeight() / 1.5 - 35);
        font.draw(textBatch, message, posX, posY, message.length(), 1, false);
        textBatch.end();
    }

    public void displayStageClear(String message) {
        textBatch.begin();
        float posX = (float) Gdx.graphics.getWidth() / 2 - 15;
        float posY = (float) (Gdx.graphics.getHeight() / 1.5 - 35);
        font.draw(textBatch, message, posX, posY, message.length(), 1, false);
        textBatch.end();
    }

    public void dispose() {
    }

}
