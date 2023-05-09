package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.game.Screens.GameScreen;
import com.mygdx.game.Screens.MainMenuScreen;
import com.mygdx.game.Screens.RecordsScreen;
import com.mygdx.game.Screens.SkinsScreen;

public class MainGame extends Game{

	public GameScreen gameScreen;
	public SkinsScreen skinsScreen;
	public RecordsScreen recordsScreen;
	public MainMenuScreen mainMenuScreen;
	public SpriteBatch batch;
	public BitmapFont font1, font2, font3, font4;

	public void fonts(){
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		FreeTypeFontGenerator.FreeTypeFontParameter parameter4 = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter1.size = 52;
		parameter2.size = 20;
		parameter3.size = 43;
		parameter4.size = 53;
		font1 = generator.generateFont(parameter1);
		font2 = generator.generateFont(parameter1);
		font3 = generator.generateFont(parameter3);
		font4 = generator.generateFont(parameter4);
		font1.setColor(1, 0.1f, 0.1f, 1);
		font2.setColor(0.8f, 0.8f, 0.2f, 1);
		font3.setColor(0.8f, 0.8f, 0.2f, 1);
		font4.setColor(0.8f, 0.8f, 0.2f, 1);
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		fonts();
		gameScreen = new GameScreen(this);
		skinsScreen = new SkinsScreen(this);
		recordsScreen = new RecordsScreen(this);
		mainMenuScreen = new MainMenuScreen(this);
		setScreen(mainMenuScreen);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
