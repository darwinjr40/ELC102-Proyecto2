package my.game.Controllers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ControladorFood extends InputAdapter {
    private Controlador controlador;

    public ControladorFood(Controlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public boolean keyDown(int keycode) {
        boolean b = true;
        if ( keycode == Input.Keys.A){
            if (!controlador.moverDerecha)
                controlador.moverIzquierda = true;
        } else if (keycode == Input.Keys.D){
            if (!controlador.moverIzquierda)
                controlador.moverDerecha = true;
        } else {
            b = false;
        }
        return b;
    }
    @Override
    public boolean keyUp(int keycode) {
        boolean b = true;
        if (keycode == Input.Keys.A){
            controlador.moverIzquierda = false;
        } else if (keycode == Input.Keys.D){
            controlador.moverDerecha = false;
        } else {
            b = false;
        }
        return b;
    }



    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Controlador getControlador() {
        return controlador;
    }
}
