package my.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.ScreenUtils;
import my.game.Controllers.ControllerGame;
import my.game.Controllers.ControllerUser;
import my.game.Models.ModelStage;
import my.game.MyGame;

public class GameScreen implements Screen {
    private int width, height;
    private MyGame game;
    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;
    private Image image;
    private ModelStage modelStage;
    private Stage stage;
    private BitmapFont bitmapFont;
    private ControllerGame controllerGame;
    private ControllerUser controllerUser;



    public GameScreen(MyGame game){
        this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.game = game;
        this.modelStage = new ModelStage();
        this.camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 700);
        this.spriteBatch = new SpriteBatch();
        this.image = new Image(new Texture("heart25.png"));
        image.setPosition( width*0.9f, height * 0.93f);//0.92
        this.bitmapFont = new BitmapFont();
        this.bitmapFont.getData().setScale(1.2f);
        this.controllerUser = new ControllerUser(modelStage);
        controllerUser.start();
        this.controllerGame = new ControllerGame(this);
        controllerGame.start();
        this.stage = new Stage();
        stage.addActor(modelStage);
        stage.addActor(image);
    }



    @Override
    public void show() {
//        stage.addListener(this.controllerUser);
//        Gdx.input.setInputProcessor(this.stage);
        Gdx.input.setInputProcessor(this.controllerUser);
    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear(0.1f, 0.3f, 0.3f, 1);
        ScreenUtils.clear(0, 0, 0, 0.1f);
        this.camera.update();
        this.spriteBatch.begin();
            this.stage.act();
            this.stage.draw();
            this.bitmapFont.draw(spriteBatch, controllerGame.getCantFoodStr(), width*0.02f, height *0.97f);
            this.bitmapFont.draw(spriteBatch, controllerGame.getLivesStr(), width*0.93f, height *0.95f);
            this.bitmapFont.draw(spriteBatch, controllerGame.getSizeStr(), width*0.13f, height *0.97f);
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

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    public ModelStage getModelStage() {
        return modelStage;
    }

    public MyGame getGame() {
        return game;
    }

    public ControllerGame getControllerGame() {
        return controllerGame;
    }
}
