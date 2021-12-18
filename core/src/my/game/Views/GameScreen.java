package my.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import my.game.Models.Food;
import my.game.MyGame;

public class GameScreen implements Screen {
    private MyGame game;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
//    private ShapeRenderer shapeRenderer;
    public Texture texture;
    public Image image;

    private Stage stage;
    private BitmapFont bitmapFont;

    private Food food;

    public GameScreen(MyGame game){
        this.game = game;

        this.food = new Food(0, 0, 50, 50, 5.0f, 5.0f);
        this.food.setColor(1, 1, 1, 0.5f);//para hacer invisible

        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 700);
        this.spriteBatch = new SpriteBatch();
//        this.shapeRenderer = new ShapeRenderer();
        this.texture = new Texture(Gdx.files.internal("heart25.png"));
        this.image = new Image(texture);
        image.setPosition(Gdx.graphics.getWidth() *0.92f, Gdx.graphics.getHeight() * 0.9f);
        this.bitmapFont = new BitmapFont();
        this.stage = new Stage();
        stage.addActor(image);
        stage.addActor(food);
    }

    @Override
    public void show() {
//        stage.ad
//        stage.add
        Gdx.input.setInputProcessor(this.food.getEntrada());
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.1f, 0.3f, 0.3f, 1);
        this.camera.update();
        this.spriteBatch.begin();
            this.stage.act();
            this.stage.draw();
            this.bitmapFont.draw(spriteBatch, "vidas ", Gdx.graphics.getWidth()*0.915f, Gdx.graphics.getHeight() *0.95f);
        this.spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        this.spriteBatch.dispose();
    }
}
