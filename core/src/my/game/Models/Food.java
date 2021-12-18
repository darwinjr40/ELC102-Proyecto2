package my.game.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.*;
import my.game.Controllers.Controlador;
import my.game.Controllers.ControladorFood;
import org.jetbrains.annotations.NotNull;

public class Food extends Actor {
    private Texture imageFood;
    private Rectangle food;  //fondo
    float xs, ys;

    private Controlador controlador;
    private ControladorFood entrada;
    public float velocidad;
    public float aceleracion;

    public Food(float x, float y, float width, float height, float xs, float ys){
        super();
        setPosition(x, y);
        setSize(width, height);
        this.xs = xs;
        this.ys = ys;
        this.food = new Rectangle(x, y, width, height);
        this.imageFood = new Texture("manzana.png");

        this.controlador = new Controlador();
        this.entrada = new ControladorFood(controlador);
        velocidad = 0;
        aceleracion = 0;
    }

    public Texture getImageTabla() {
        return imageFood;
    }

    public void setImageTabla(Texture imageFood) {
        this.imageFood = imageFood;
    }

    public float getXs() {
        return xs;
    }

    public void setXs(float xs) {
        this.xs = xs;
    }

    public float getYs() {
        return ys;
    }

    public void setYs(float ys) {
        this.ys = ys;
    }

    public Rectangle getFood() {
        return food;
    }

    public void setFood(Rectangle food) {
        this.food = food;
    }

    public void acelerar() {
        if (aceleracion <= 240f) aceleracion += 60f;
    }

    public void marchaAtras() {
        if (aceleracion >= -240f) aceleracion -= 40f;
    }

    public void frenarCoche() {
        aceleracion = 0;
        if (Math.abs(velocidad) > 0.5f)
            velocidad *= 0.95f;
        else
            velocidad = 0;
    }

    @Override
    public void draw(@NotNull Batch batch, float parentAlpha) {
        act(1.0f);
        batch.setColor(getColor().r, getColor().g, getColor().b, getColor().a * parentAlpha);
        batch.draw(imageFood, getX(), getY(), getWidth(), getHeight());
        food.x = getX();
        food.y = getY();
        this.controladores();
    }

    public void update() {
        float posicion = getX();
        float tiempo = Gdx.graphics.getDeltaTime();
        System.out.println(tiempo);
        this.velocidad += aceleracion * tiempo;
        posicion += velocidad * tiempo + 0.5 * aceleracion * (tiempo * tiempo);
        setX(posicion);
    }

    public void controladores() {
        if (entrada.getControlador().isMoverDerecha()) {
            acelerar();
        } else if (entrada.getControlador().isMoverIzquierda()) {
            marchaAtras();
        } else {
            frenarCoche();
        }
        update();
    }

    public ControladorFood getEntrada() {
        return entrada;
    }
}

