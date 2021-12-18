package my.game.Controllers;

public class Controlador {
    boolean moverIzquierda;
    boolean moverDerecha;
    boolean moverArriba;
    boolean moverAbajo;
    int ObjetivoX;

    public boolean isMoverIzquierda() {
        return moverIzquierda;
    }

    public void setMoverIzquierda(boolean moverIzquierda) {
        this.moverIzquierda = moverIzquierda;
    }

    public boolean isMoverDerecha() {
        return moverDerecha;
    }

    public void setMoverDerecha(boolean moverDerecha) {
        this.moverDerecha = moverDerecha;
    }

    public boolean isMoverArriba() {
        return moverArriba;
    }

    public void setMoverArriba(boolean moverArriba) {
        this.moverArriba = moverArriba;
    }

    public boolean isMoverAbajo() {
        return moverAbajo;
    }

    public void setMoverAbajo(boolean moverAbajo) {
        this.moverAbajo = moverAbajo;
    }

    public int getObjetivoX() {
        return ObjetivoX;
    }

    public void setObjetivoX(int objetivoX) {
        ObjetivoX = objetivoX;
    }
}
