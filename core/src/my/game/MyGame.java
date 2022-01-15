package my.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import my.game.Models.estructura.Asset;
import my.game.Models.estructura.SoundPlayer;
import my.game.Views.GameScreen;
import my.game.Views.MenuScreen;

public class MyGame extends Game {
	public MenuScreen menuScreen;
	public GameScreen gameScreen;

	public void create () {
		Asset.instance().loadAsset();
		SoundPlayer.init();
		this.gameScreen = new GameScreen(this);
		this.menuScreen = new MenuScreen(this);
		this.setScreen(menuScreen);
	}


	public void setScreenMenu(){
		this.setScreen(menuScreen);
	}
}
