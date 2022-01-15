package my.game.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import my.game.Models.estructura.Asset;
import my.game.Models.estructura.MatrizSparce;

public class ModelFood {
    private Sprite sprite;
    private int fil, col, value;
    private float width, height;
    private String[] foodTypes = {
            "celeste", "chocolate", "chocolate1", "rojo", "green", "orange", "pink", "pink1",
            "red", "white", "white1", "yellow"};
    public ModelFood(int fil, int col, int width, int height, String internalPath, int srcX, int srcY, int srcWidth, int srcHeight) {
        setSize(width, height);
        setFilCol(fil, col);
        this.sprite = new Sprite(new Texture(internalPath), srcX, srcY, srcWidth,srcHeight);
        sprite.setSize(width, height);
        this.value = 9;
    }


    public ModelFood(int width, int height, Sprite sprite) {
        setSize(width, height);
        this.sprite = sprite;
        sprite.setSize(width, height);
        this.value = 9;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void setSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    public void setFilCol(int fil, int col){
        setFil(fil);
        setCol(col);
    }
    public int getFil() {
        return fil;
    }

    public void setFil(int fil) {
        this.fil = fil;
    }

    public int getValue() {
        return value;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void incFil(){
        fil++;
    }

    public void decFil(){
        fil--;
    }

    public void incCol(){
        col++;
    }

    public void decCol(){
        col--;
    }

    public Texture getImage() {
        return sprite.getTexture();
    }

    public void draw(Batch batch) {
        sprite.setPosition(col*getWidth(), fil*getHeight());
        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }

    //genera una nueva fil y col para la comida
    public void generateFood(MatrizSparce m){
        int f, c, i = MathUtils.random(foodTypes.length - 1);
        this.sprite = Asset.instance().getSprite(foodTypes[i]);
        sprite.setSize(width, height);
        do {
            f = MathUtils.random(0, m.getNfil()-1);
            c = MathUtils.random(0, m.getNcol()-1);
        }while(m.get(f,c) != 0);
        this.setFilCol(f, c);
        m.set(f,c, (byte)9);
    }

    @Override
    public String toString() {
        return "ModelSnake{" +
                "fil=" + fil +
                ", col=" + col +
                '}';
    }
}
