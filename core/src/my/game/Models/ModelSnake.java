package my.game.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import my.game.Models.estructura.Direction;

public class ModelSnake{
    private Sprite sprite;
    private int fil, col;
    private float width, height;
    private Direction dir;

    public ModelSnake(int fil, int col, int width, int height, String internalPath, int srcX, int srcY, int srcWidth, int srcHeight) {
        this.dir = Direction.RIGHT;
        this.setSize(width, height);
        this.setFilCol(fil, col);
        this.sprite = new Sprite(new Texture(internalPath), srcX, srcY, srcWidth,srcHeight);
//        sprite.setSize(width, height);   //        setRotation();
    }

    public ModelSnake(int width, int height, Sprite sprite) {
        this.sprite = sprite;
        sprite.setSize(width, height);
        this.dir = Direction.RIGHT;
        this.setSize(width, height);
    }

    public void setSize (int width, int height) {
        this.width = width;
        this.height = height;
    }
    public void setFilCol(int fil, int col){
        this.fil = fil;
        this.col = col;
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

    public void setNextPos(int NFil, int NCol){
        switch (dir){
            case UP :  this.setNextUP(NFil); break;
            case DOWN :  this.setNextDOWN(NFil); break;
            case LEFT :  this.setNextLEFT(NCol); break;
            case RIGHT : this.setNextRIGHT(NCol); break;
        }
        this.setRotation();//actualiza la rotacion de la cabeza
    }

    public void setNextDOWN(int NFil){
        fil = (fil+1) % NFil;
    }

    public void setNextUP(int NFil){
        fil = (fil-1+NFil) % NFil;
    }

    public void setNextLEFT(int NCol){
        col = (col-1+NCol) % NCol;
    }

    public void setNextRIGHT(int NCol){
        col = (col+1) % NCol;
    }

    //actualiza la rotacion de la cabeza
    public void setRotation(){
        switch (dir){
            case UP :  this.setRotationUP(); break;
            case DOWN :  this.setRotationDOWN(); break;
            case LEFT :  this.setRotationLEFT(); break;
            case RIGHT : this.setRotationRIGHT(); break;
        }
    }

    public void setRotationLEFT() {
        float x = (width/2);
        float y = (height/2);
        sprite.setOrigin(x, y);
        sprite.setRotation(180);
        sprite.setSize(width, height );
    }

    public void setRotationRIGHT() {
        float x = (width/2);
        float y = (height/2);
        sprite.setOrigin(x, y);
        sprite.setRotation(0);
        sprite.setSize(width, height );
    }

    public void setRotationUP() {
        float x = (width/2) - ((width-height)/2);
        float y = height/2;
        sprite.setOrigin(x, y);
        sprite.setRotation(-90);
        sprite.setSize(height, width);
    }

    public void setRotationDOWN (){
        float x = (width/2);
        float y = (height/2)+((width-height)/2);
        sprite.setOrigin(x, y);
        sprite.setRotation(90);
        sprite.setSize(height, width);
    }


    public Texture getImage() {
        return sprite.getTexture();
    }

    public Direction getDir() {
        return dir;
    }

    public void setDir(Direction dir) {
        this.dir = dir;
    }

    public void draw(Batch batch) {
        sprite.setPosition(col*width, fil*height);
        sprite.draw(batch);
    }
    public Sprite getSprite() {
        return sprite;
    }

    public boolean isPos(int f, int c){
        return fil == f && col ==c;
    }
    @Override
    public String toString() {
        return "ModelSnake{" +
                "fil=" + fil +
                ", col=" + col +
                ", dir=" + dir +
                '}';
    }
}
