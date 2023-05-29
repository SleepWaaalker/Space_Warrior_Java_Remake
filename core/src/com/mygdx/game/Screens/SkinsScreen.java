package com.mygdx.game.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MainGame;

public class SkinsScreen implements Screen, InputProcessor {
    private MainGame mainGame;

    public SkinsScreen(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    public OrthographicCamera camera;
    private Texture skins1, skins2, skins3, skins4, skins5, skins6, skins7, skins8, skins9, skins10,
            skins11, skins12, skins13, skins14, skins15, skins16;
    private Texture selectSkin, buySkin, dontBuySkin;
    private Texture exitMenuBtn,exitMenuBtnDown;
    private Music buttonSound;
    private int width, height;
    public float ppuX, ppuY;
    private boolean isExitMenuDown;
    private boolean buy1Btn, buy2Btn, buy3Btn, buy4Btn, buy5Btn, buy6Btn,
            buy7Btn, buy8Btn, buy9Btn, buy10Btn, buy11Btn, buy12Btn,
            buy13Btn, buy14Btn, buy15Btn, buy16Btn;
    private boolean select1Btn, select2Btn, select3Btn, select4Btn, select5Btn, select6Btn,
            select7Btn, select8Btn, select9Btn, select10Btn, select11Btn, select12Btn,
            select13Btn, select14Btn, select15Btn, select16Btn;
    float CAMERA_WIDTH = 600F;
    float CAMERA_HEIGHT = 800F;
    public int shopCoin = 100;
    String coinPrint;


    //загрузка фоновой музыки
    private void loadMusic() {
        buttonSound = Gdx.audio.newMusic(Gdx.files.internal("button_sound.wav"));
    }

    //загрузка текстур
    private void loadTextures(){
        selectSkin = new Texture("frame.png");
        dontBuySkin = new Texture("frame2.png");
        buySkin = new Texture("frame3.png");
        skins1 = new Texture("alien1.png");//
        skins2 = new Texture("bluecargoship.png");//
        skins3 = new Texture("blueship2.png");//
        skins4 = new Texture("destroyer.png");//
        skins5 = new Texture("F5S4.png");//
        skins6 = new Texture("greenship2.png");//
        skins7 = new Texture("RD2.png");//
        skins8 = new Texture("orangeship2.png");//
        skins9 = new Texture("shuttlenoweps.png");//
        skins10 = new Texture("wship1.png");//
        skins11 = new Texture("alien2.png");//
        skins12 = new Texture("blue1.png");//
        skins13 = new Texture("blueshuttlenoweps.png");//
        skins14 = new Texture("F5S2.png");//
        skins15 = new Texture("orangeship3.png");//
        skins16 = new Texture("redship4.png");//
        exitMenuBtn = new Texture("button5.png");
        exitMenuBtnDown = new Texture("button6.png");
    }

    //прорисовка фона
    public void showBG() {
        mainGame.batch.draw(mainGame.bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    //прорисовка текста
    public void showText(){
        mainGame.font4.draw(mainGame.batch, "Shop", 222, 790);
        mainGame.font1.draw(mainGame.batch, "Shop", 220, 790);
        mainGame.font3.draw(mainGame.batch, "<-", 20, 80);
    }
    public void shopCoin(){
        coinPrint = String.valueOf(shopCoin);
        mainGame.font3.draw(mainGame.batch, "coin:", 300, 60);
        mainGame.font3.draw(mainGame.batch, coinPrint, 500, 60);
        mainGame.font5.draw(mainGame.batch, "all for 10 coins", 200, 90);

    }

    public void drawSkins(){
        mainGame.batch.draw(skins1, 70, 600);
        mainGame.batch.draw(skins2, 210, 590);
        mainGame.batch.draw(skins3, 350, 595);
        mainGame.batch.draw(skins4, 495, 610);
        mainGame.batch.draw(skins5, 70, 440);
        mainGame.batch.draw(skins6, 210, 440);
        mainGame.batch.draw(skins7, 340, 450);
        mainGame.batch.draw(skins8, 490, 440);
        mainGame.batch.draw(skins9, 70, 270);
        mainGame.batch.draw(skins10, 210, 280);
        mainGame.batch.draw(skins11, 350, 275);
        mainGame.batch.draw(skins12, 490, 290);
        mainGame.batch.draw(skins13, 70, 110);
        mainGame.batch.draw(skins14, 210, 130);
        mainGame.batch.draw(skins15, 350, 130);
        mainGame.batch.draw(skins16, 490, 120);
    }

    //вывод кнопок поверх фона
    public void showButton() {
        if (!isExitMenuDown) {
            mainGame.batch.draw(exitMenuBtn, 0, 10);
        } else {
            mainGame.batch.draw(exitMenuBtnDown, 0, 10);
        }
    }

    public void buyingSkin(){
        if (!buy1Btn) {
            mainGame.batch.draw(dontBuySkin, 20, 570);
        } else {
            mainGame.batch.draw(buySkin, 20, 570);
        }
        if (!buy2Btn) {
            mainGame.batch.draw(dontBuySkin, 160, 570);
        } else {
            mainGame.batch.draw(buySkin, 160, 570);
        }
        if (!buy3Btn) {
            mainGame.batch.draw(dontBuySkin, 300, 570);
        } else {
            mainGame.batch.draw(buySkin, 300, 570);
        }
        if (!buy4Btn) {
            mainGame.batch.draw(dontBuySkin, 440, 570);
        } else {
            mainGame.batch.draw(buySkin, 440, 570);
        }
        if (!buy5Btn) {
            mainGame.batch.draw(dontBuySkin, 20, 410);
        } else {
            mainGame.batch.draw(buySkin, 20, 410);
        }
        if (!buy6Btn) {
            mainGame.batch.draw(dontBuySkin, 160, 410);
        } else {
            mainGame.batch.draw(buySkin, 160, 410);
        }
        if (!buy7Btn) {
            mainGame.batch.draw(dontBuySkin, 300, 410);
        } else {
            mainGame.batch.draw(buySkin, 300, 410);
        }
        if (!buy8Btn) {
            mainGame.batch.draw(dontBuySkin, 440, 410);
        } else {
            mainGame.batch.draw(buySkin, 440, 410);
        }
        if (!buy9Btn) {
            mainGame.batch.draw(dontBuySkin, 20, 250);
        } else {
            mainGame.batch.draw(buySkin, 20, 250);
        }
        if (!buy10Btn) {
            mainGame.batch.draw(dontBuySkin, 160, 250);
        } else {
            mainGame.batch.draw(buySkin, 160, 250);
        }
        if (!buy11Btn) {
            mainGame.batch.draw(dontBuySkin, 300, 250);
        } else {
            mainGame.batch.draw(buySkin, 300, 250);
        }
        if (!buy12Btn) {
            mainGame.batch.draw(dontBuySkin, 440, 250);
        } else {
            mainGame.batch.draw(buySkin, 440, 250);
        }
        if (!buy13Btn) {
            mainGame.batch.draw(dontBuySkin, 20, 90);
        } else {
            mainGame.batch.draw(buySkin, 20, 90);
        }
        if (!buy14Btn)  {
            mainGame.batch.draw(dontBuySkin, 160, 90);
        } else {
            mainGame.batch.draw(buySkin, 160, 90);
        }
        if (!buy15Btn) {
            mainGame.batch.draw(dontBuySkin, 300, 90);
        } else {
            mainGame.batch.draw(buySkin, 300, 90);
        }
        if (!buy16Btn) {
            mainGame.batch.draw(dontBuySkin, 440, 90);
        } else {
            mainGame.batch.draw(buySkin, 440, 90);
        }
    }

    public void selectSkin(){
        if (select1Btn)
            mainGame.batch.draw(selectSkin, 20, 570);

        if (select2Btn)
            mainGame.batch.draw(selectSkin, 160, 570);

        if (select3Btn)
            mainGame.batch.draw(selectSkin, 300, 570);

        if (select4Btn)
            mainGame.batch.draw(selectSkin, 440, 570);

        if (select5Btn)
            mainGame.batch.draw(selectSkin, 20, 410);

        if (select6Btn)
            mainGame.batch.draw(selectSkin, 160, 410);

        if (select7Btn)
            mainGame.batch.draw(selectSkin, 300, 410);

        if (select8Btn)
            mainGame.batch.draw(selectSkin, 440, 410);

        if (select9Btn)
            mainGame.batch.draw(selectSkin, 20, 250);

        if (select10Btn)
            mainGame.batch.draw(selectSkin, 160, 250);

        if (select11Btn)
            mainGame.batch.draw(selectSkin, 300, 250);

        if (select12Btn)
            mainGame.batch.draw(selectSkin, 440, 250);

        if (select13Btn)
            mainGame.batch.draw(selectSkin, 20, 90);

        if (select14Btn)
            mainGame.batch.draw(selectSkin, 160, 90);

        if (select15Btn)
            mainGame.batch.draw(selectSkin, 300, 90);

        if (select16Btn)
            mainGame.batch.draw(selectSkin, 440, 90);
    }

    @Override
    public void show() {
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;

        isExitMenuDown = false;
        mainGame.batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
        loadTextures();
        loadMusic();
        Gdx.input.setInputProcessor(this);
    }

    public void SetCamera(float x, float y){
        this.camera.position.set(x, y,0);
        this.camera.update();
    }

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SetCamera(CAMERA_WIDTH/2, CAMERA_HEIGHT / 2f);
        mainGame.batch.begin();
        mainGame.batch.setProjectionMatrix(camera.combined);
        showBG();
        shopCoin();
        drawSkins();
        showButton();
        buyingSkin();
        selectSkin();
        showText();

        mainGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        setSize(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    //очистка экрана
    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        try{

            exitMenuBtn.dispose();
            exitMenuBtnDown.dispose();

        }
        catch(Exception e){
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    //границы кнопок и обработка нажатий
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if((height-screenY)/ppuY >= 570 && (height-screenY)/ppuY <= 720 && screenX/ppuX>=20 && screenX/ppuX<=170) {
            if ((shopCoin>=10)&&(!buy1Btn)){
                buttonSound.play();
                shopCoin = shopCoin-10;
                buy1Btn = true;
            }else if ((buy1Btn) && (!select1Btn)){
                buttonSound.play();
                select1Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 570 && (height-screenY)/ppuY <= 720 && screenX/ppuX>=160 && screenX/ppuX<=310) {
            if ((shopCoin>=10)&&(!buy2Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy2Btn = true;
            }else if ((buy2Btn) && (!select2Btn)){
                buttonSound.play();
                select2Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 570 && (height-screenY)/ppuY <= 720 && screenX/ppuX>=300 && screenX/ppuX<=450) {
            if ((shopCoin>=10)&&(!buy3Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy3Btn = true;
            }else if ((buy3Btn) && (!select3Btn)){
                buttonSound.play();
                select3Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 570 && (height-screenY)/ppuY <= 720 && screenX/ppuX>=440 && screenX/ppuX<=590) {
            if ((shopCoin>=10)&&(!buy4Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy4Btn = true;
            }else if ((buy4Btn) && (!select4Btn)){
                buttonSound.play();
                select4Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 410 && (height-screenY)/ppuY <= 560 && screenX/ppuX>=20 && screenX/ppuX<=170) {
            if ((shopCoin>=10)&&(!buy5Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy5Btn = true;
            }else if ((buy5Btn) && (!select5Btn)){
                buttonSound.play();
                select5Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 410 && (height-screenY)/ppuY <= 560 && screenX/ppuX>=160 && screenX/ppuX<=310) {
            if ((shopCoin>=10)&&(!buy6Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy6Btn = true;
            }else if ((buy6Btn) && (!select6Btn)){
                buttonSound.play();
                select6Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 410 && (height-screenY)/ppuY <= 560 && screenX/ppuX>=300 && screenX/ppuX<=450) {
            if ((shopCoin>=10)&&(!buy7Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy7Btn = true;
            }else if ((buy7Btn) && (!select7Btn)){
                buttonSound.play();
                select7Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 410 && (height-screenY)/ppuY <= 560 && screenX/ppuX>=440 && screenX/ppuX<=590) {
            if ((shopCoin>=10)&&(!buy8Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy8Btn = true;
            }else if ((buy8Btn) && (!select8Btn)){
                buttonSound.play();
                select8Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 250 && (height-screenY)/ppuY <= 400 && screenX/ppuX>=20 && screenX/ppuX<=170) {
            if ((shopCoin>=10)&&(!buy9Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy9Btn = true;
            }else if ((buy9Btn) && (!select9Btn)){
                buttonSound.play();
                select9Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 250 && (height-screenY)/ppuY <= 400 && screenX/ppuX>=160 && screenX/ppuX<=310) {
            if ((shopCoin>=10)&&(!buy10Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy10Btn = true;
            }else if ((buy10Btn) && (!select10Btn)){
                buttonSound.play();
                select10Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 250 && (height-screenY)/ppuY <= 400 && screenX/ppuX>=300 && screenX/ppuX<=450) {
            if ((shopCoin>=10)&&(!buy11Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy11Btn = true;
            }else if ((buy11Btn) && (!select11Btn)){
                buttonSound.play();
                select11Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 250 && (height-screenY)/ppuY <= 400 && screenX/ppuX>=440 && screenX/ppuX<=590) {
            if ((shopCoin>=10)&&(!buy12Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy12Btn = true;
            }else if ((buy12Btn) && (!select12Btn)){
                buttonSound.play();
                select12Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 90 && (height-screenY)/ppuY <= 240 && screenX/ppuX>=20 && screenX/ppuX<=170) {
            if ((shopCoin>=10)&&(!buy13Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy13Btn = true;
            }else if ((buy13Btn) && (!select13Btn)){
                buttonSound.play();
                select13Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 90 && (height-screenY)/ppuY <= 240 && screenX/ppuX>=160 && screenX/ppuX<=310) {
            if ((shopCoin>=10)&&(!buy14Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy14Btn = true;
            }else if ((buy14Btn) && (!select14Btn)){
                buttonSound.play();
                select14Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 90 && (height-screenY)/ppuY <= 240 && screenX/ppuX>=300 && screenX/ppuX<=450) {
            if ((shopCoin>=10)&&(!buy15Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy15Btn = true;
            }else if ((buy15Btn) && (!select15Btn)){
                buttonSound.play();
                select15Btn = true;
            }
        }
        if((height-screenY)/ppuY >= 90 && (height-screenY)/ppuY <= 240 && screenX/ppuX>=440 && screenX/ppuX<=590) {
            if ((shopCoin>=10)&&(!buy16Btn)){
                buttonSound.play();
                shopCoin -= 10;
                buy16Btn = true;
            }else if ((buy16Btn) && (!select16Btn)){
                buttonSound.play();
                select16Btn = true;
            }
        }


        if((height-screenY)/ppuY >= 10 && (height-screenY)/ppuY <= 93 && screenX/ppuX>=0 && screenX/ppuX<=100) {
            buttonSound.play();
            isExitMenuDown = true;
        }
        return true;
    }

    //при нажатии происходит переход на другой экран
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Desktop))
            return false;

        if(isExitMenuDown){
            dispose();
            mainGame.setScreen(mainGame.mainMenuScreen);
        }

        isExitMenuDown = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
