package my.game.Models.estructura;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
//import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Asset {
    private static Asset instance = new Asset();
    private AssetManager assetManager = new AssetManager(new InternalFileHandleResolver());
    //images
    public static final String SNAKE_PACK = "images/snake.pack";
    //sounds
    public static final String GAME_SOUND = "sounds/8bit_bg1.mp3";
    public static final String GAME_OVER_SOUND = "sounds/gameover.mp3";
    public static final String GAME_OVER_SOUND1 = "sounds/gameover1.mp3";
    public static final String EAT_FOOD_SOUND = "sounds/eat_food.mp3";
    public static final String CRASH_SOUND = "sounds/crash.ogg";
    //fonts
    public static final String PIXEL_FONT = "fonts/pixel.ttf";
    private Asset() { }

    /*
    carga las imagenes en Asset
     */
    public void loadAsset() {
//        loadFont();
        loadSounds();
        loadImages();
        assetManager.finishLoading();
    }

    private void loadImages() {
        assetManager.load(SNAKE_PACK, TextureAtlas.class);
    }

    private void loadSounds() {
        assetManager.load(GAME_SOUND, Music.class);
        assetManager.load(GAME_OVER_SOUND, Music.class);
        assetManager.load(GAME_OVER_SOUND1, Sound.class);
        assetManager.load(EAT_FOOD_SOUND, Sound.class);
        assetManager.load(CRASH_SOUND, Sound.class);
    }

    private void loadFont() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
//        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
//        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
//        FreetypeFontLoader.FreeTypeFontLoaderParameter mySmallFont = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
//        mySmallFont.fontFileName = PIXEL_FONT;
//        mySmallFont.fontParameters.size = 16;
//        mySmallFont.fontParameters.color = Color.WHITE;
//        assetManager.load(PIXEL_FONT, BitmapFont.class, mySmallFont);
    }
    public static Asset instance() {
        return instance;
    }

    public <T> T get(String filename) {
        return assetManager.get(filename);
    }

    public Sprite getSprite(String name) {
        TextureAtlas atlas = this.get(Asset.SNAKE_PACK);
        return atlas.createSprite(name);
    }

}
