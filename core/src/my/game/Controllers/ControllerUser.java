package my.game.Controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import my.game.Models.ModelSnake;
import my.game.Models.ModelStage;
import my.game.Models.estructura.Direction;
import my.game.Models.estructura.MatrizSparce;

public class ControllerUser extends Thread implements InputProcessor {

    private ModelSnake snakeHead, snakeTail;
    private MatrizSparce m;

    public ControllerUser(ModelStage stage) {
        snakeHead = stage.getSnakeHead();
        snakeTail = stage.getSnakeTail();
        m = stage.getM();
    }

    @Override
    public void run() {
        while (true){
//            keyDown()
//            if ((System.currentTimeMillis()-time)% delay == 0){
//                if (continuar) {
//                    SoundPlayer.playMusic(Asset.GAME_SOUND);
//                    this.moveSnake();
//                }
//                while ((System.currentTimeMillis()-time) % delay == 0){}
//            }
        }
    }

    @Override
    public boolean keyDown(int k) {
        boolean b = true;
        Direction d = snakeHead.getDir();
        if ((k == Input.Keys.D) && (d == Direction.DOWN || d == Direction.UP) ){
            d = Direction.RIGHT;
        } else if ((k == Input.Keys.A) && (d == Direction.DOWN || d == Direction.UP) ){
            d = Direction.LEFT;
        } else if ((k == Input.Keys.W) && (d == Direction.LEFT || d == Direction.RIGHT) ){
            d = Direction.DOWN;
        } else if ((k == Input.Keys.S) && (d == Direction.LEFT || d == Direction.RIGHT) ){
            d = Direction.UP;
        } else {
            b = false;
        }
        if (b && d != snakeHead.getDir())
            snakeHead.setDir(d);
        return b;
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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
