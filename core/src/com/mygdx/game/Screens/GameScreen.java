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
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MainGame;

public class GameScreen implements Screen, InputProcessor {
    private Texture playerTexture, bg, gamePlayerSkin, exitMenuBtn, exitMenuBtnDown,
            pauseTexture;
    private Music buttonSound;
    private boolean isExitMenuDown, isPaused;
    private final MainGame mainGame;
    private Rectangle player;
    private OrthographicCamera camera;
    float CAMERA_WIDTH = 600F;
    float CAMERA_HEIGHT = 800F;
    private int width, height;
    public float ppuX, ppuY;
    public int score = 0;
    String scorePrint;

    public GameScreen(MainGame mainGame, Texture playerSkin) {
        this.mainGame = mainGame;
        this.playerTexture  = playerSkin;
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
        if (playerTexture  == null){
            playerTexture  = new Texture(Gdx.files.internal("alien1.png"));
        }
        gamePlayerSkin = playerTexture;
        pauseTexture = new Texture(Gdx.files.internal("pause.png"));
    }

    public void drawScore(){
        scorePrint = String.valueOf(score);
        mainGame.font6.draw(mainGame.batch, "score:", 220, 780);
        mainGame.font6.draw(mainGame.batch, scorePrint, 350, 780);
    }
    public void setPlayerTexture(Texture playerSkin) {
        this.playerTexture  = playerSkin;
    }

    public void showText(){
        mainGame.font4.draw(mainGame.batch, "Pause", 222, 790);
        mainGame.font1.draw(mainGame.batch, "Pause", 220, 790);
        mainGame.font3.draw(mainGame.batch, "<-", 20, 80);
    }
    private void loadMusic() {
        buttonSound = Gdx.audio.newMusic(Gdx.files.internal("button_sound.wav"));
    }
    public void showBG() {
        mainGame.batch.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainGame.batch.draw(pauseTexture, 560, 750);
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
        player = new Rectangle();
        player.x = 600 / 2 - 64 / 2; // центрировать корабль по горизонтали
        player.y = 20; // нижний левый угол корабля на 20 пикселей выше нижнего края экрана
        player.width = 64;
        player.height = 64;
    }

    @Override
    public void render(float delta) {
        if(!isPaused){
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            camera.update();
            mainGame.batch.setProjectionMatrix(camera.combined);
            mainGame.batch.begin();
            showBG();
            mainGame.batch.draw(gamePlayerSkin, player.x, player.y);
            drawScore();
            mainGame.batch.end();
        }else {
            //отрисовка экрана паузы
            mainGame.batch.begin();
            mainGame.batch.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            showBG();
            showButton();
            showText();
            mainGame.batch.end();
            isPaused = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        setSize(width, height);
        this.width = width;
        this.height = height;
    }

    @Override
    public void pause() {
        isPaused = true;
    }

    @Override
    public void resume() {
        isPaused = true;
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
        if ((height - screenY) / ppuY >= 750 && (height - screenY) / ppuY <= 796 && screenX / ppuX >= 560 && screenX / ppuX <= 596) {
            isPaused = !isPaused;
        }
        if(isPaused){
            if((height-screenY)/ppuY >= 10 && (height-screenY)/ppuY <= 120 && screenX/ppuX>=0 && screenX/ppuX<=120) {
                buttonSound.play();
                isExitMenuDown = true;
            }
        }
        return true;

    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Desktop))
            return false;

        if(isPaused){
            if(isExitMenuDown){
                dispose();
                isPaused = false;
                mainGame.setScreen(mainGame.mainMenuScreen);
            }
            isExitMenuDown = false;
        }
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
