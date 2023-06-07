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

public class RecordsScreen implements Screen, InputProcessor {
    private final MainGame mainGame;
    private OrthographicCamera camera;
    private Texture bg;
    private Texture exitMenuBtn, exitMenuBtnDown;
    private Music buttonSound;
    private int width, height;
    private float ppuX, ppuY;
    private boolean isExitMenuDown;
    private float cameraWidth  = 600F;
    private float cameraHeight  = 800F;

    public RecordsScreen(MainGame mainGame) {
        this.mainGame = mainGame;
        show();
    }

    //геттеры и сеттеры
    public MainGame getMainGame() {
        return mainGame;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public Texture getBg() {
        return bg;
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }

    public Texture getExitMenuBtn() {
        return exitMenuBtn;
    }

    public void setExitMenuBtn(Texture exitMenuBtn) {
        this.exitMenuBtn = exitMenuBtn;
    }

    public Texture getExitMenuBtnDown() {
        return exitMenuBtnDown;
    }

    public void setExitMenuBtnDown(Texture exitMenuBtnDown) {
        this.exitMenuBtnDown = exitMenuBtnDown;
    }

    public Music getButtonSound() {
        return buttonSound;
    }

    public void setButtonSound(Music buttonSound) {
        this.buttonSound = buttonSound;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getPpuX() {
        return ppuX;
    }

    public void setPpuX(float ppuX) {
        this.ppuX = ppuX;
    }

    public float getPpuY() {
        return ppuY;
    }

    public void setPpuY(float ppuY) {
        this.ppuY = ppuY;
    }

    public boolean isExitMenuDown() {
        return isExitMenuDown;
    }

    public void setExitMenuDown(boolean exitMenuDown) {
        isExitMenuDown = exitMenuDown;
    }

    public float getCameraWidth() {
        return cameraWidth;
    }

    public void setCameraWidth(float cameraWidth) {
        this.cameraWidth = cameraWidth;
    }

    public float getCameraHeight() {
        return cameraHeight;
    }

    public void setCameraHeight(float cameraHeight) {
        this.cameraHeight = cameraHeight;
    }

    //загрузка текстур и фоновой музыки
    private void loadAssets(){
        bg = new Texture(Gdx.files.internal("sky.png"));
        exitMenuBtn = new Texture("button5.png");
        exitMenuBtnDown = new Texture("button6.png");
        buttonSound = Gdx.audio.newMusic(Gdx.files.internal("button_sound.wav"));
    }

    //прорисовка фона
    public void showBG() {
        mainGame.batch.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    //прорисовка текста
    public void showText(){
        mainGame.font4.draw(mainGame.batch, "Records", 177, 780);
        mainGame.font1.draw(mainGame.batch, "Records", 180, 780);
        mainGame.font3.draw(mainGame.batch, "<-", 20, 80);

    }

    //вывод кнопок поверх фона
    public void showButton() {
        if (!isExitMenuDown) {
            mainGame.batch.draw(exitMenuBtn, 0, 10);
        } else {
            mainGame.batch.draw(exitMenuBtnDown, 0, 10);
        }
    }

    //создание экрана
    @Override
    public void show() {
        ppuX = (float)width / cameraWidth;
        ppuY = (float)height / cameraHeight ;
        isExitMenuDown = false;
        mainGame.batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 600, 800);
        loadAssets();
        Gdx.input.setInputProcessor(this);
    }

    //устанавка позиции камеры на сцене
    public void SetCamera(float x, float y){
        this.camera.position.set(x, y,0);
        this.camera.update();
    }

    //устанавка размера экрана в пикселях и рассчет количества пикселей для оси X и Y
    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / cameraWidth;
        ppuY = (float)height / cameraHeight ;
    }

    //отрисовка текстур
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SetCamera(cameraWidth/2, cameraHeight  / 2f);

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
        exitMenuBtn.dispose();
        exitMenuBtnDown.dispose();
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
        if((height-screenY)/ppuY >= 10 && (height-screenY)/ppuY <= 120 && screenX/ppuX>=0 && screenX/ppuX<=120) {
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