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

public class MainMenuScreen implements Screen, InputProcessor{

    private MainGame mainGame;
    public OrthographicCamera camera;

    private Texture bg;
    private Texture playBtn;
    private Texture playBtnDown;
    private Texture shopBtn;
    private Texture shopBtnDown;
    private Texture recordsBtn;
    private Texture recordsBtnDown;
    private Texture exitBtn;
    private Texture exitBtnDown;
    private Music gameMusic;
    private Music buttonSound;
    private int width, height;
    public float ppuX, ppuY;
    private boolean isPlayDown, isShopDown, isRecordsDown, isExitDown;
    float CAMERA_WIDTH = 600F;
    float CAMERA_HEIGHT = 800F;

    //
    public MainMenuScreen(MainGame main){
        this.mainGame = main;
    }

    //загрузка фоновой музыки
    private void loadMusic() {
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("theme.mp3"));
        buttonSound = Gdx.audio.newMusic(Gdx.files.internal("button_sound.wav"));
    }

    //загрузка текстур
    private void loadTextures(){
        bg = new Texture(Gdx.files.internal("sky.png"));
        playBtn = new Texture("button1.png");
        playBtnDown = new Texture("button2.png");

        shopBtn = new Texture("button1.png");
        shopBtnDown = new Texture("button2.png");

        recordsBtn = new Texture("button1.png");
        recordsBtnDown = new Texture("button2.png");

        exitBtn = new Texture("button3.png");
        exitBtnDown = new Texture("button4.png");
    }

    //прорисовка фона
    public void showBG() {
        mainGame.batch.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    //прорисовка текста
    public void showText(){
        mainGame.font4.draw(mainGame.batch, "Space", 53, 730);
        mainGame.font4.draw(mainGame.batch, "Warrior", 286, 730);
        mainGame.font1.draw(mainGame.batch, "Space Warrior", 52, 730);
        mainGame.font2.draw(mainGame.batch, "Start", 220, 585);
        mainGame.font2.draw(mainGame.batch, "Shop", 230, 440);
        mainGame.font2.draw(mainGame.batch, "Records", 175, 285);
        mainGame.font3.draw(mainGame.batch, "Exit", 240, 100);
    }

    //вывод кнопок поверх фона
    public void showButton() {
        if (!isPlayDown) {
            mainGame.batch.draw(playBtn, 120, 500);
        } else {
            mainGame.batch.draw(playBtnDown, 120, 500);
        }
        if (!isShopDown) {
            mainGame.batch.draw(shopBtn, 120, 355);
        } else {
            mainGame.batch.draw(shopBtnDown, 120, 355);
        }
        if (!isRecordsDown) {
            mainGame.batch.draw(recordsBtn, 120, 200);
        } else {
            mainGame.batch.draw(recordsBtnDown, 120, 200);
        }
        if (!isExitDown) {
            mainGame.batch.draw(exitBtn, 215, 30);
        } else {
            mainGame.batch.draw(exitBtnDown, 215, 30);
        }
    }

    @Override
    public void show() {
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        isPlayDown = false;
        isShopDown = false;
        isRecordsDown = false;
        isExitDown = false;
        mainGame.batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
        loadTextures();
        loadMusic();
        gameMusic.setLooping(true);
        gameMusic.play();
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
        showButton();
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
            bg.dispose();
            mainGame.font1.dispose();
            mainGame.font2.dispose();
            mainGame.font3.dispose();
            mainGame.font4.dispose();
            playBtn.dispose();
            playBtnDown.dispose();
            shopBtn.dispose();
            shopBtnDown.dispose();
            recordsBtn.dispose();
            recordsBtnDown.dispose();
            exitBtn.dispose();
            exitBtnDown.dispose();
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

    //границы кнопок и обработка нажатий (при нажатии меняется спрайт кнопки)
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if((height-screenY)/ppuY >= 500 && (height-screenY)/ppuY <= 600 && screenX/ppuX>=120 && screenX/ppuX<=495) {
            buttonSound.play();
            isPlayDown = true;
        }

        if((height-screenY)/ppuY >= 355 && (height-screenY)/ppuY <= 455 && screenX/ppuX>=120 && screenX/ppuX<=495) {
            buttonSound.play();
            isShopDown = true;
        }

        if((height-screenY)/ppuY >= 200 && (height-screenY)/ppuY <= 300 && screenX/ppuX>=120 && screenX/ppuX<=495) {
            buttonSound.play();
            isRecordsDown = true;
        }

        if((height-screenY)/ppuY >= 30 && (height-screenY)/ppuY <= 110 && screenX/ppuX>=215 && screenX/ppuX<=385)
            isExitDown = true;

        return true;
    }

    //при нажатии происходит переход на другой экран
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Desktop))
            return false;
        if(isPlayDown){
            dispose();
            mainGame.setScreen(mainGame.gameScreen);
        }
        if(isShopDown){
            dispose();
            mainGame.setScreen(mainGame.skinsScreen);
        }
        if(isRecordsDown){
            dispose();
            mainGame.setScreen(mainGame.recordsScreen);
        }
        if(isExitDown){
            dispose();
            Gdx.app.exit();
        }

        isPlayDown = false;
        isShopDown = false;
        isRecordsDown = false;
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
