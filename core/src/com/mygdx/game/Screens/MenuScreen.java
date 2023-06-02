package com.mygdx.game.Screens;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MainGame;

public class MenuScreen implements Screen, InputProcessor{
    MainGame main;

    private int width, height;
    private Texture skyImage;
    private SpriteBatch spriteBatch;
    public OrthographicCamera camera;



    public Map<String, Texture> textures;
    public float ppuX;
    public float ppuY;
    float CAMERA_WIDTH = 600F;
    float CAMERA_HEIGHT = 800F;
    boolean downBtn;

    @Override
    public void show() {
        textures = new HashMap<String, Texture>();
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
        downBtn = false;
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        loadTextures();
        Gdx.input.setInputProcessor(this);
    }

   public void SetCamera(float x, float y){
        this.camera.position.set(x, y,0);
        this.camera.update();
    }

    //прорисовка фона
    public void showBG() {
        spriteBatch.draw(skyImage,0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    //загрузка текстур
    private void loadTextures(){

        skyImage = new Texture(Gdx.files.internal("sky.png"));
        textures.put("cover_button_start_up", new Texture(Gdx.files.internal("button_up.jpg")));
        textures.put("cover_button_start_down", new Texture(Gdx.files.internal("button_down.jpg")));
    }
    //вывод кнопок поверх фона
    public void showMenu() {
        if (downBtn)
            spriteBatch.draw(textures.get("cover_button_start_down"), 653, 183, 256, 128);
        else
            spriteBatch.draw(textures.get("cover_button_start_up"), 653, 183, 256, 128);
    }

    public void setSize (int w, int h) {
        this.width = w;
        this.height = h;
        ppuX = (float)width / CAMERA_WIDTH;
        ppuY = (float)height / CAMERA_HEIGHT;
    }




    @Override
    public void render(float delta) {
        //SetCamera(CAMERA_WIDTH/2, CAMERA_HEIGHT / 2f);
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        showBG();
        //showMenu();
        spriteBatch.end();
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

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        try{
            spriteBatch.dispose();
            skyImage.dispose();
            textures.clear();
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

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if((height-screenY)/ppuY >= 213 && (height-screenY)/ppuY <= 283 && screenX/ppuX>=660 && screenX/ppuX<=780)
            downBtn = true;

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (!Gdx.app.getType().equals(Application.ApplicationType.Desktop))
            return false;
        if(downBtn){
            dispose();
            main.setScreen(main.mainMenuScreen);
        }

        downBtn = false;
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    public boolean touchMoved(int x, int y) {
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

    public MenuScreen(MainGame main){
        this.main = main;
    }
}
