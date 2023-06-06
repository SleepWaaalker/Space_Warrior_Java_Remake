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
    public Texture playerSkin;
    private final MainGame mainGame;
    public OrthographicCamera camera;
    private final int skinsCount = 16;
    public final Texture[] skinsTexture = new Texture[skinsCount];
    private final int[] skinsX = new int[]{70, 210, 350, 490, 70, 210, 340, 490, 70, 210, 350, 490, 70, 210, 350, 490};
    private final int[] skinsY = new int[]{600, 590, 595, 610, 440, 440, 450, 440, 270, 280, 275, 290, 110, 130, 130, 120};
    private Texture selectSkin, buySkin, dontBuySkin;
    private Texture exitMenuBtn, exitMenuBtnDown;
    private Texture bg;
    private Music buttonSound;
    private int width, height;
    public float ppuX, ppuY;
    private final int btnCount = 16;
    private final int[] btnX = new int[]{20, 160, 300, 440, 20, 160, 300, 440, 20, 160, 300, 440, 20, 160, 300, 440};
    private final int[] btnY = new int[]{570, 570, 570, 570, 410, 410, 410, 410, 250, 250, 250, 250, 90, 90, 90, 90};
    private final boolean[] buyBtn = new boolean[btnCount];
    private final boolean[] selectBtn = new boolean[btnCount];
    public int selectedSkin;
    private boolean isExitMenuDown;
    boolean isBtnSelected = false;
    float CAMERA_WIDTH = 600F;
    float CAMERA_HEIGHT = 800F;
    public int shopCoin = 100;
    String coinPrint;

    public SkinsScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        playerSkin = skinsTexture[0];
    }

    //загрузка фоновой музыки
    private void loadMusic() {
        buttonSound = Gdx.audio.newMusic(Gdx.files.internal("button_sound.wav"));
    }

    //загрузка текстур
    private void loadTextures() {
        bg = new Texture(Gdx.files.internal("sky.png"));
        selectSkin = new Texture("frame.png");
        dontBuySkin = new Texture("frame2.png");
        buySkin = new Texture("frame3.png");
        skinsTexture[0] = new Texture("alien1.png");
        skinsTexture[1] = new Texture("bluecargoship.png");
        skinsTexture[2] = new Texture("blueship2.png");
        skinsTexture[3] = new Texture("destroyer.png");
        skinsTexture[4] = new Texture("F5S4.png");
        skinsTexture[5] = new Texture("greenship2.png");
        skinsTexture[6] = new Texture("RD2.png");
        skinsTexture[7] = new Texture("orangeship2.png");
        skinsTexture[8] = new Texture("shuttlenoweps.png");
        skinsTexture[9] = new Texture("wship1.png");
        skinsTexture[10] = new Texture("alien2.png");
        skinsTexture[11] = new Texture("blue1.png");
        skinsTexture[12] = new Texture("blueshuttlenoweps.png");
        skinsTexture[13] = new Texture("F5S2.png");
        skinsTexture[14] = new Texture("orangeship3.png");
        skinsTexture[15] = new Texture("redship4.png");
        exitMenuBtn = new Texture("button5.png");
        exitMenuBtnDown = new Texture("button6.png");
    }

    //прорисовка фона
    public void showBG() {
        mainGame.batch.draw(bg, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    //прорисовка текста
    public void showText() {
        mainGame.font4.draw(mainGame.batch, "Shop", 222, 790);
        mainGame.font1.draw(mainGame.batch, "Shop", 220, 790);
        mainGame.font3.draw(mainGame.batch, "<-", 20, 80);
    }

    //прорисовка количества монет
    public void shopCoin() {
        coinPrint = String.valueOf(shopCoin);
        mainGame.font3.draw(mainGame.batch, "coin:", 300, 60);
        mainGame.font3.draw(mainGame.batch, coinPrint, 500, 60);
        mainGame.font5.draw(mainGame.batch, "all for 10 coins", 200, 90);

    }

    //прорисовка скинов для покупки в магазине
    public void drawSkins() {
        for (int i = 0; i < skinsCount; i++) {
            mainGame.batch.draw(skinsTexture[i], skinsX[i], skinsY[i]);
        }
    }

    //прорисовка кнопки выхода
    public void exitButton() {
        if (!isExitMenuDown) {
            mainGame.batch.draw(exitMenuBtn, 0, 10);
        } else {
            mainGame.batch.draw(exitMenuBtnDown, 0, 10);
        }
    }

    //прорисовка рамок показывающих куплен ли скин
    public void buyingSkin() {
        buyBtn[0] = true;
        for (int i = 0; i < btnCount; i++) {
            if (!buyBtn[i]) {
                mainGame.batch.draw(dontBuySkin, btnX[i], btnY[i]);
            } else {
                mainGame.batch.draw(buySkin, btnX[i], btnY[i]);
            }
        }
    }

    //прорисовка рамок показывающих выбран ли скин
    public void selectSkin() {
        for (int i = 0; i < btnCount; i++) {
            if (selectBtn[i]) {
                mainGame.batch.draw(selectSkin, btnX[i], btnY[i]);
            }
        }
    }

    @Override
    public void show() {
        ppuX = (float) width / CAMERA_WIDTH;
        ppuY = (float) height / CAMERA_HEIGHT;
        isExitMenuDown = false;
        mainGame.batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
        loadTextures();
        loadMusic();
        Gdx.input.setInputProcessor(this);
    }

    public void SetCamera(float x, float y) {
        this.camera.position.set(x, y, 0);
        this.camera.update();
    }

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float) width / CAMERA_WIDTH;
        ppuY = (float) height / CAMERA_HEIGHT;
    }

    //рендер текстур
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SetCamera(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2f);
        mainGame.batch.begin();
        mainGame.batch.setProjectionMatrix(camera.combined);
        showBG();
        shopCoin();
        drawSkins();
        exitButton();
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
        exitMenuBtn.dispose();
        exitMenuBtnDown.dispose();
        bg.dispose();
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
        //обработка нажатий в пределах рамок
        for (int i = 0; i < btnCount; i++) {
            if ((height - screenY) / ppuY >= btnY[i] && (height - screenY) / ppuY <= btnY[i] + 150 && screenX / ppuX >= btnX[i] && screenX / ppuX <= btnX[i] + 140) {
                if ((shopCoin >= 10) && (!buyBtn[i])) {
                    buttonSound.play();
                    shopCoin = shopCoin - 10;
                    buyBtn[i] = true;
                } else if ((buyBtn[i]) && (!selectBtn[i]) && (selectedSkin != i)) {
                    buttonSound.play();
                    if (selectedSkin != -1) {
                        selectBtn[selectedSkin] = false;
                    }
                    selectedSkin = i;
                    selectBtn[i] = true;
                }
            }
        }

        //обработка нажатий кнопки выхода в меню
        if ((height - screenY) / ppuY >= 10 && (height - screenY) / ppuY <= 93 && screenX / ppuX >= 0 && screenX / ppuX <= 100) {
            buttonSound.play();
            isExitMenuDown = true;
        }
        return true;
    }

    //при нажатии происходит действие
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Desktop))
            return false;

        for (int i = 0; i < btnCount; i++) {
            if (selectBtn[i]) {
                playerSkin = skinsTexture[i];
                isBtnSelected = true;
                break;
            } else {
                playerSkin = skinsTexture[0];
            }
        }
        if (!isBtnSelected) {
            playerSkin = skinsTexture[0];
        }
        //при нажатии происходит переход в меню
        if (isExitMenuDown) {
            dispose();
            mainGame.setScreen(mainGame.mainMenuScreen);
        }

        isExitMenuDown = false;
        return true;
    }

    public Texture getPlayerSkin() {
        return playerSkin;
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