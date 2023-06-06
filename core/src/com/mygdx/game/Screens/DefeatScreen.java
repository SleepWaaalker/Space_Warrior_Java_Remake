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



//На данный момент экран поражения вызывается после трех нажатий на кнопку паузы на игровом экране
public class DefeatScreen implements Screen, InputProcessor {
    private Texture bg, exitMenuBtn, exitMenuBtnDown;
    private Music buttonSound;
    private boolean isExitMenuDown;
    private final MainGame mainGame;
    private OrthographicCamera camera;
    private float CAMERA_WIDTH = 600F;
    private float CAMERA_HEIGHT = 800F;
    private int width, height;
    private float ppuX, ppuY;
    private int score = 0;
    String scorePrint;

    public DefeatScreen(MainGame mainGame) {
        this.mainGame = mainGame;
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

    private void loadTextures(){
        bg = new Texture(Gdx.files.internal("sky.png"));
        exitMenuBtn = new Texture("button5.png");
        exitMenuBtnDown = new Texture("button6.png");
    }

    public void drawScore(){
        scorePrint = String.valueOf(score);
        mainGame.font4.draw(mainGame.batch, "you score:", 120, 650);
        mainGame.font4.draw(mainGame.batch, scorePrint, 300, 550);
    }

    public void showText(){
        mainGame.font7.draw(mainGame.batch, "You", 110, 790);
        mainGame.font7.draw(mainGame.batch, "lose", 310, 790);
        mainGame.font3.draw(mainGame.batch, "<-", 20, 80);
    }
    private void loadMusic() {
        buttonSound = Gdx.audio.newMusic(Gdx.files.internal("button_sound.wav"));
    }
    public void showBG() {
        mainGame.batch.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void showButton() {
        if (!isExitMenuDown) {
            mainGame.batch.draw(exitMenuBtn, 0, 10);
        } else {
            mainGame.batch.draw(exitMenuBtnDown, 0, 10);
        }
    }

    @Override
    public void show() {
        mainGame.batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
        loadTextures();
        loadMusic();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SetCamera(CAMERA_WIDTH / 2, CAMERA_HEIGHT / 2f);
        camera.update();
        mainGame.batch.setProjectionMatrix(camera.combined);
        mainGame.batch.begin();
        showBG();
        showButton();
        showText();
        drawScore();
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
    }

    @Override
    public void dispose() {
        mainGame.batch.dispose();
        bg.dispose();
        exitMenuBtnDown.dispose();
        exitMenuBtn.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if((height-screenY)/ppuY >= 10 && (height-screenY)/ppuY <= 120 && screenX/ppuX>=0 && screenX/ppuX<=120) {
            buttonSound.play();
            isExitMenuDown = true;
        }
        return true;
    }

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
