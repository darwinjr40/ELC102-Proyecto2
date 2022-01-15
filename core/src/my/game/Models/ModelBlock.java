package my.game.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ModelBlock {
    private Sprite sprite;
    private float width, height;
    private int fil, col, value;



    public ModelBlock(int fil, int col, int width, int height, String internalPath, int srcX, int srcY, int srcWidth, int srcHeight) {
        setSize(width, height);
        setFilCol(fil, col);
        this.sprite = new Sprite(new Texture(internalPath), srcX, srcY, srcWidth,srcHeight);
        sprite.setSize(width, height);
    }

    public ModelBlock(int width, int height, Sprite sprite) {
        setSize(width, height);
        this.sprite = sprite;
        sprite.setSize(width, height);
        this.value = 1;
    }

    public ModelBlock(int width, int height, String internalPath) {
        setSize(width, height);
        this.sprite = new Sprite(new Texture(internalPath));
        sprite.setSize(width, height);
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

    public int getValue() {
        return value;
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

    public void draw(int f, int c, Batch batch) {
        setFilCol(f, c);
        sprite.setPosition(col*getWidth(), fil*getHeight());
        sprite.draw(batch);
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public String toString() {
        return "ModelSnake{" +
                "fil=" + fil +
                ", col=" + col +
                '}';
    }
}
