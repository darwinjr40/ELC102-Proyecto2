package my.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import my.game.Controllers.ControllerGame;
import my.game.MyGame;

public class MenuScreen implements Screen {
    private MyGame game;
    private Stage stage;
    private Skin skin;
    private Image image;
    private TextButton textButton;
    private int width, height;


    public MenuScreen(final MyGame game){
        this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.game = game;
        this.initImage();
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.initButton();
        this.initStage();
    }

    private void initImage() {
        this.image = new Image(new Texture("snakeP.png"));
        image.setPosition( (width/2) -(image.getWidth()/2), (height/2)*0.7f);
    }

    public void initStage(){
        this.stage = new Stage();
        stage.addActor(textButton);
        stage.addActor(image);
    }

    public void initButton(){
        this.textButton = new TextButton("Jugar", skin);
        textButton.setSize(width*0.3f, height*0.15f);
        textButton.setPosition(((width/2)-(textButton.getWidth()/2)) , ((height/2)*0.6f));
        textButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.gameScreen);
                game.gameScreen.getControllerGame().continuando();
            }
        });
    }

    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
//        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1);
        ScreenUtils.clear(Color.DARK_GRAY);
        stage.act();
        stage.draw();
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
        stage.dispose();
    }
}
