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
            pauseTexture, playTexture;
    private Music buttonSound;
    private boolean isExitMenuDown, isPaused;
    private final MainGame mainGame;
    private Rectangle player;
    private OrthographicCamera camera;
    private float cameraWidth = 600F;
    private float cameraHeight = 800F;
    private int width, height;
    private float ppuX, ppuY;
    private int score = 0;
    private int coin = 0;
    private int lives = 3;
    String scorePrint, coinPrint;

    public GameScreen(MainGame mainGame) {
        this.mainGame = mainGame;
    }

    //геттеры и сеттеры

    public Texture getPlayerTexture() {
        return playerTexture;
    }

    public void setPlayerTexture(Texture playerSkin) {
        this.playerTexture  = playerSkin;
    }

    public Texture getBg() {
        return bg;
    }

    public void setBg(Texture bg) {
        this.bg = bg;
    }

    public Texture getGamePlayerSkin() {
        return gamePlayerSkin;
    }

    public void setGamePlayerSkin(Texture gamePlayerSkin) {
        this.gamePlayerSkin = gamePlayerSkin;
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

    public Texture getPauseTexture() {
        return pauseTexture;
    }

    public void setPauseTexture(Texture pauseTexture) {
        this.pauseTexture = pauseTexture;
    }

    public Texture getPlayTexture() {
        return playTexture;
    }

    public void setPlayTexture(Texture playTexture) {
        this.playTexture = playTexture;
    }

    public Music getButtonSound() {
        return buttonSound;
    }

    public void setButtonSound(Music buttonSound) {
        this.buttonSound = buttonSound;
    }

    public boolean isExitMenuDown() {
        return isExitMenuDown;
    }

    public void setExitMenuDown(boolean exitMenuDown) {
        isExitMenuDown = exitMenuDown;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public MainGame getMainGame() {
        return mainGame;
    }

    public Rectangle getPlayer() {
        return player;
    }

    public void setPlayer(Rectangle player) {
        this.player = player;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getScorePrint() {
        return scorePrint;
    }

    public void setScorePrint(String scorePrint) {
        this.scorePrint = scorePrint;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getCoinPrint() {
        return coinPrint;
    }

    public void setCoinPrint(String coinPrint) {
        this.coinPrint = coinPrint;
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

    //загрузка текстур
    private void loadTextures(){
        bg = new Texture(Gdx.files.internal("sky.png"));
        exitMenuBtn = new Texture("button3.png");
        exitMenuBtnDown = new Texture("button4.png");
        if (playerTexture  == null){
            playerTexture  = new Texture(Gdx.files.internal("alien1.png"));
        }
        gamePlayerSkin = playerTexture;
        pauseTexture = new Texture(Gdx.files.internal("pause.png"));
        playTexture = new Texture(Gdx.files.internal("play.png"));
    }

    //прорисовка счета и монет
    public void drawScore(){
        scorePrint = String.valueOf(score);
        mainGame.font6.draw(mainGame.batch, "score:", 220, 780);
        mainGame.font6.draw(mainGame.batch, scorePrint, 350, 780);
    }

    public void drawCoinInGame(){
        coinPrint = String.valueOf(coin);
        mainGame.font6.draw(mainGame.batch, "coin:", 20, 780);
        mainGame.font6.draw(mainGame.batch, coinPrint, 120, 780);
    }

    //прорисовка текста
    public void showText(){
        mainGame.font4.draw(mainGame.batch, "Pause", 222, 790);
        mainGame.font1.draw(mainGame.batch, "Pause", 220, 790);
        mainGame.font3.draw(mainGame.batch, "Menu", 25, 80);
    }

    //загрузка звуков кнопок
    private void loadMusic() {
        buttonSound = Gdx.audio.newMusic(Gdx.files.internal("button_sound.wav"));
    }

    //прорисовка фона
    public void showBG() {
        mainGame.batch.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    //прорисовка кнопки выхода в меню
    public void showButton() {
        if (!isExitMenuDown) {
            mainGame.batch.draw(exitMenuBtn, 0, 10);
        } else {
            mainGame.batch.draw(exitMenuBtnDown, 0, 10);
        }
    }

    //при проигрыше все полученные значения передаются на нужные экраны
    public void defeat() {
        if (lives <= 0) {
            //На данный момент экран поражения вызывается после
            // трех нажатий на кнопку паузы на игровом экране
            mainGame.defeatScreen.setScore(score);
            mainGame.defeatScreen.setCoin(coin);
            mainGame.skinsScreen.setShopCoin(coin);
            dispose();
            isPaused = false;
            mainGame.setScreen(mainGame.defeatScreen);
        }
    }

    //создание экрана
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

    //отрисовка текстур
    @Override
    public void render(float delta) {
        if(!isPaused){
            Gdx.gl.glClearColor(0, 0, 0.2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            SetCamera(cameraWidth / 2, cameraHeight / 2f);
            camera.update();
            mainGame.batch.setProjectionMatrix(camera.combined);
            mainGame.batch.begin();
            showBG();
            mainGame.batch.draw(pauseTexture, 560, 750);
            mainGame.batch.draw(gamePlayerSkin, player.x, player.y);
            drawScore();
            drawCoinInGame();
            mainGame.batch.end();
        }else {
            //отрисовка экрана паузы
            mainGame.batch.begin();
            mainGame.batch.draw(bg,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            showBG();
            mainGame.batch.draw(playTexture, 560, 750);
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
        pauseTexture.dispose();
        playTexture.dispose();
        //после очистки все значения становятся изначальными
        lives = 3;
        score = 0;
        coin = 0;
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
            //временное решение для проверки работоспособности переносов
            //в последствии используются в других местах
            score += 10;
            lives -= 1;
            coin += 5;
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
        defeat();
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
