package my.game.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.ScreenUtils;
import my.game.MyGame;

public class MenuScreen implements Screen {
    private MyGame game;
    private Stage stage;
    private Skin skin;
    private Image image;
    private TextButton textButton;
    private Texture texture;

    public MenuScreen(final MyGame game){
        float mitadPantallaX = Gdx.graphics.getWidth() / 2;
        float mitadPantallaY = Gdx.graphics.getHeight() / 2;
        this.game = game;
        this.texture = new Texture("snake.png");
        this.image = new Image(texture);
        image.setPosition( mitadPantallaX -(image.getWidth()/2), mitadPantallaY*0.8f);
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.textButton = new TextButton("Jugar", skin);
        textButton.setSize(300, 100);
        textButton.setPosition(mitadPantallaX-(textButton.getWidth()/2) , mitadPantallaY*0.6f);
        textButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(game.gameScreen);
            }
        });
        this.stage = new Stage();
        stage.addActor(textButton);
        stage.addActor(image);
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1);
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
