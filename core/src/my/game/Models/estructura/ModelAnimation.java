package my.game.Models.estructura;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

public class ModelAnimation {
    private float width, height;
    private int fil, col, value;
    private Animation animation;
    private float tiempo;
    private TextureRegion[] regionsMovimiento;
    private Texture imagen;
    private TextureRegion frameActual;
    private boolean bandera;
    private byte c;
    private long timeIni, duration ;
    public ModelAnimation(int fil, int col, int width, int height) {
        setFilCol(fil, col);
        setSize(width, height);
        imagen = new Texture(Gdx.files.internal("images/explosion.png"));
        regionsMovimiento = new TextureRegion[4];
        regionsMovimiento[3] = new TextureRegion(imagen, 1, 32, 95, 193);
        regionsMovimiento[2] = new TextureRegion(imagen, 90, 32, 110, 193);
        regionsMovimiento[1] = new TextureRegion(imagen, 221, 32, 185, 193);
        regionsMovimiento[0] = new TextureRegion(imagen, 413, 32, 185, 193);
        animation = new Animation(0.5f/4f, regionsMovimiento);
        this.tiempo = 0f;
        bandera = false;
        c = 0;
    }

    public void draw(final Batch batch){
        if (bandera){
            if ((System.currentTimeMillis()-timeIni) <= duration){
//                System.out.println("paso");
                tiempo += Gdx.graphics.getDeltaTime(); //tiempo que paso desde el ultimo render;
                frameActual = (TextureRegion) this.animation.getKeyFrame(tiempo,true);
                batch.draw(frameActual, col*width, fil*height, width, height);
            }else{
                bandera = false;
            }
        }
    }

    public void init(int duration, float timeEscena){
        setBandera(true);
        this.timeIni = System.currentTimeMillis();
        this.duration = duration;
        timeEscena = timeEscena *0.001f;
        if (animation.getFrameDuration() != timeEscena){
            animation.setFrameDuration(timeEscena/4f);
        }
    }

    public boolean isBandera() {
        return bandera;
    }

    public void setBandera(boolean bandera) {
        this.bandera = bandera;
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



    @Override
    public String toString() {
        return "ModelSnake{" +
                "fil=" + fil +
                ", col=" + col +
                '}';
    }
}
