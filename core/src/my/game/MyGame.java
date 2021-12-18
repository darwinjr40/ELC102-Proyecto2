package my.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import my.game.Views.GameScreen;
import my.game.Views.MenuScreen;

public class MyGame extends Game {
	private AssetManager manager;
	public MenuScreen menuScreen;
	public GameScreen gameScreen;

	public void create () {
		this.manager = new AssetManager();
		this.menuScreen = new MenuScreen(this);
		this.gameScreen = new GameScreen(this);
		this.setScreen(menuScreen);
	}

	public AssetManager getManager(){
		return this.manager;
	}

	public void setScreenMenu(){
		this.setScreen(menuScreen);
	}
}
