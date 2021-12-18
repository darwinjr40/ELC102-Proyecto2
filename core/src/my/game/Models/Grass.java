package my.game.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

//class Cesped
public class Grass extends Actor {
    private Texture imageGrass;
    private Rectangle grass;
    private boolean move;
    float xs, ys;

    public Grass(float x, float y, float xs, float ys) {
        super();
        setPosition(x, y);
        //setSize(width, height);
        this.imageGrass = new Texture(" ");
        this.grass = new Rectangle(x, y, getWidth(), getHeight());
        this.move = move;
        this.xs = xs;
        this.ys = ys;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        act(0.1f);
        batch.draw(imageGrass, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //acciones
    }

    public Texture getImageGrass() {
        return imageGrass;
    }

    public void setImageGrass(Texture imageGrass) {
        this.imageGrass = imageGrass;
    }

    public Rectangle getGrass() {
        return grass;
    }

    public void setGrass(Rectangle grass) {
        this.grass = grass;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
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
}
