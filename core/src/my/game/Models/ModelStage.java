package my.game.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import my.game.Models.estructura.*;


public class ModelStage extends Actor {
    private int imageWidth, imageHeight;
    private MatrizSparce m;
    private Texture grass, SnakeBody ;
    private ModelSnake snakeHead, snakeTail;
    private ModelFood food;
    private ModelBlock block;
    private ModelAnimation modelAnimation;
    public ModelStage() {
        setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.m = new MatrizSparce(20, 20);
        this.ImageSize((int)getWidth() / m.getNcol(),  (int)getHeight() / m.getNfil());
        this.grass = new Texture(Gdx.files.internal("images/grass4.jpg"));
        this.snakeHead = new ModelSnake(imageWidth,  imageHeight, Asset.instance().getSprite("snake_head"));
        this.SnakeBody = new Texture("images/snakeBody.png");
        this.snakeTail = new ModelSnake(imageWidth,  imageHeight, Asset.instance().getSprite("snake_tail"));
        this.food = new ModelFood(imageWidth, imageHeight, Asset.instance().getSprite("green"));
        this.block = new ModelBlock(imageWidth, imageHeight, Asset.instance().getSprite("muro"));
        modelAnimation = new ModelAnimation(10, 0, imageWidth, imageHeight);
    }




    public void dibujar(Batch batch){
        //batch.draw(imagenes.get(0), (j) * imageWidth, (getHeight()-imageHeight)-((i) * imageHeight), imageWidth, imageHeight);
        for (int i = 0; i < m.getNfil(); i++)
            for (int j = 0; j < m.getNcol(); j++) {
                batch.draw(this.grass, j * imageWidth, i * imageHeight, imageWidth, imageHeight);//pasto
                byte e = m.get(i,j);
                if (this.esSnake(e) && !snakeHead.isPos(i, j) && !snakeTail.isPos(i, j))
                    batch.draw(SnakeBody, j * imageWidth, i * imageHeight, imageWidth, imageHeight);
                if (e == block.getValue()) block.draw(i, j, batch);
                if (snakeTail.isPos(i, j) && this.esSnake(e) ) this.snakeTail.draw(batch);
                if (snakeHead.isPos(i, j) && this.esSnake(e)) this.snakeHead.draw(batch);
                if (e == food.getValue())  this.food.draw(batch);
            }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        act(1.0f);
        this.dibujar(batch);
        modelAnimation.draw(batch);
    }

    public ModelAnimation getModelAnimation() {
        return modelAnimation;
    }

    public MatrizSparce getM() {
        return m;
    }

    public ModelSnake getSnakeHead() {
        return snakeHead;
    }

    public ModelSnake getSnakeTail() {
        return snakeTail;
    }

    public void setSnake(ModelSnake snake) {
        this.snakeHead = snake;
    }

    public ModelBlock getBlock() {
        return block;
    }

    public ModelFood getFood() {
        return food;
    }

    private boolean esSnake(byte x){
        return x >= 10 && x <= 30;
    }

    private void ImageSize(int imageWidth, int imageHeight) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }




}
