package my.game.Controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import my.game.Models.ModelBlock;
import my.game.Models.ModelSnake;
import my.game.Models.ModelStage;
import my.game.Models.estructura.*;
import my.game.Models.ModelFood;
import my.game.Views.GameScreen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class ControllerGame  extends Thread implements InputProcessor{
    private GameScreen gameScreen;
    private ModelStage modelStage;
    private MatrizSparce m;
    private ModelSnake snakeHead, snakeTail;
    private ModelFood food;
    private ModelBlock block;
    private ModelAnimation modelAnimation;
    private long time, score;
    private int delay;
    private byte t, lives, l, size;
    private boolean continuar,sw;

    public ControllerGame(GameScreen game){
        this.gameScreen = game;
        this.modelStage = game.getModelStage();
        this.m  = modelStage.getM();
        this.snakeHead = modelStage.getSnakeHead();
        this.snakeTail = modelStage.getSnakeTail();
        this.food = modelStage.getFood();
        this.block = modelStage.getBlock();
        this.modelAnimation = modelStage.getModelAnimation();
        this.time = System.currentTimeMillis();
        this.initSnake();
        this.loadLevel(1);
        this.init();
    }

    private void init(){
        this.score = 0;
        this.l = 1;
        this.lives = 2;
        this.delay = 200; // milisegundos
        this.food.generateFood(m);
    }

    public void initSnake(){
        m.clear();
        int ca = 0,
                cb = 5,
                fil = 10;
        for ( int i = ca; i < cb+1; i++)
            m.set(fil,i, (byte)(10+i));
        this.snakeTail.setFilCol(fil, ca);
        snakeTail.setDir(Direction.RIGHT);
        this.snakeHead.setFilCol(fil, cb);
        snakeHead.setDir(Direction.RIGHT);
        this.size = (byte)(cb -ca +1);
        this.t = (byte)(10+size-1);
        this.sw = true;
    }

    @Override
    public void run() {
        while (true){
            if ((System.currentTimeMillis()-time)% delay == 0){
                if (continuar) {
                    SoundPlayer.playMusic(Asset.GAME_SOUND);
                    this.moveSnake();
                }
                while ((System.currentTimeMillis()-time) % delay == 0){}
            }

        }
    }
    public void moveSnake() {
//        System.out.println(m.toString());
        this.checkMovimiento();
//        System.out.println(snakeTail.toString() );
//        System.out.println(snakeHead.toString() );
    }

    /*
     Colisiono con su cuerpo
      */
    private void checkMovimiento() {
        byte e = this.m.getEleSig(snakeHead.getFil(), snakeHead.getCol(), snakeHead.getDir());
        if( this.isColision(e))  { //choco con su cuerpo o con una pared
            lives--;
            this.checkLives();
        }else if (e == food.getValue()) {//se comio
            this.score++;   this.size++;
            this.checkLevel();
            this.food.generateFood(m);
            if (l == 2 && delay >= 50) {
                delay -= 4;
            }
            SoundPlayer.playSound(Asset.EAT_FOOD_SOUND, false);
        } else {
            this.setDirectionTail();//toca mover la cola
            this.setDirectionHead();//toca mover cabeza
        }

//        if (!gameOver && !isColision(e)  )
    }




    /*
    movemos la cabeza(segun la direccion en la que se encuentre)
     */
    public void setDirectionHead() {
        int f = snakeHead.getFil();
        int c = snakeHead.getCol();
        snakeHead.setNextPos(m.getNfil(), m.getNcol()); //mueve la pos de la cabeza

//        ||(Math.abs(snakeTail.getFil()-f) != 1)
//        ||(Math.abs(snakeTail.getCol()-c) != 1)
//        System.out.println(Math.abs(snakeHead.getCol()-c));
//        if ((Math.abs(snakeHead.getCol()-c) != 1) && (snakeHead.getCol()!=c)) {
//            lives--;
//            this.checkLives();
//        }
        t = (sw)? (byte)(t+1) : (byte)(t-1);
        if (t == 10 || t == 30) { sw=!sw;}
        this.m.set(snakeHead.getFil(), snakeHead.getCol(), t);//marcar la pos de la cabeza en la matriz
    }

    public void setDirectionTail() {
        Direction dir = this.getNextDirection();
        if (dir != snakeTail.getDir()) {snakeTail.setDir(dir);}
        this.m.set(snakeTail.getFil(), snakeTail.getCol(), (byte)0); //apagar
        this.snakeTail.setNextPos(m.getNfil(), m.getNcol());//actualiza la pos de la cola
    }





    public boolean isColision(byte e) {
        return this.esSnake(e) || this.block.getValue()==e;
    }

    private void checkLives(){
        if(lives == 0){
            this.snakeHead.setDir(Direction.NONE);
            SoundPlayer.stopMusic(Asset.GAME_SOUND);
            SoundPlayer.playMusic(Asset.GAME_OVER_SOUND, false);
            if (snakeHead.getDir() == Direction.NONE) {
                    initAnimation();
                    this.pausar();
                    this.initSnake();
                    this.loadLevel(1);
                    this.init();
                    this.gameScreen.getGame().setScreenMenu();

            }
        }else {
            this.initSnake(); //cargar snake de nuevo
            this.loadLevel(l);
            SoundPlayer.playSound(Asset.CRASH_SOUND, false);
        }
        food.generateFood(m);
    }

    private void initAnimation() {
        int ms = 600;
        modelAnimation.init( size*ms, ms);
        long timeIni = System.currentTimeMillis();
        modelAnimation.setFilCol(snakeHead.getFil(), snakeHead.getCol());
        while ((System.currentTimeMillis()-timeIni) <= (size*ms)){
            long act = System.currentTimeMillis();
            int ele = m.get(modelAnimation.getFil(), modelAnimation.getCol());
            m.set(modelAnimation.getFil(), modelAnimation.getCol(), (byte) 0);
            SoundPlayer.playSound(Asset.CRASH_SOUND, false);
            while ((System.currentTimeMillis()-act) <= ms){}
            this.gameOver(ele);
        }
    }

    public void gameOver(int ele){
        int f = modelAnimation.getFil();
        int c = modelAnimation.getCol();
        if ((Math.abs(m.getRIGHT(f,c) - ele))==1 ){
            c = (c+1) % m.getNcol();
        }else if ((Math.abs(m.getLEFT(f, c)- ele))==1 ){
            c = (c-1+m.getNcol()) % m.getNcol();
        }else if ((Math.abs(m.getDOWN(f, c) - ele))==1 ){
            f =(f+1) % m.getNfil();
        }else if ((Math.abs(m.getUP(f, c) - ele))==1 ){
            f =(f-1+m.getNfil()) % m.getNfil();
        }
        modelAnimation.setFilCol(f, c);
    }

    public void checkLevel(){
        if (l == 1 && score >= 3){
            l++;
            this.initSnake();
            this.loadLevel(l);
        } else {
            this.setDirectionHead();//toca mover cabeza
        }
    }
    public void loadLevel(int l){
        FileReader fr;
        BufferedReader br;
        try {
            String nivel = "android\\assets\\blocks\\" + ((l == 1)? ("level1.txt") : ("level3.txt"));
                fr = new FileReader(nivel);
                br = new BufferedReader(fr);
                String linea;
                int i = 0;
                while((linea = br.readLine()) != null){
                    for (int j = 0; j < linea.length(); j++){
                        String ele = linea.charAt(j)+"";
                        this.cargarBloque(i, j, Integer.valueOf(ele));
                    }
                    i++;
                }
        }catch (Exception e){
            System.out.println("Error al leer el archivo");
        }
    }

    private void cargarBloque(int i, int j, int ele) {
        if (ele != 0){
            m.set(i, j, (byte)ele);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
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

    /*
   Retorna la nueva direccion de la cola
    */
    private Direction getNextDirection(){
        Direction op = gerDirOpuesta(snakeTail.getDir());
        byte ele = m.get(snakeTail.getFil(), snakeTail.getCol());
        Direction[] directions = Direction.values();
        int i = 0, n = directions.length;
        Direction dir = snakeTail.getDir();
        while (i < n && dir == snakeTail.getDir()){
            byte e = m.getEleSig(snakeTail.getFil(), snakeTail.getCol(), directions[i]);
            if ( (dir != op) && (esSnake(e)) && (Math.abs(ele-e) == 1) ){
                    dir = directions[i];
            }
            i++;
        }
        return dir;
    }

    /*
    Retornamos la direccion opuesta
     */
    private Direction gerDirOpuesta(Direction dir){
        Direction op = dir;
        switch (dir){
            case DOWN: op = Direction.UP; break;
            case UP: op = Direction.DOWN; break;
            case LEFT: op = Direction.RIGHT; break;
            case RIGHT: op = Direction.LEFT; break;
        }
        return op;
    }

    /*
        Verifica en la matriz si es parte de la serpiente
     */
    private boolean esSnake(byte x){
        return x >= 10 && x <= 30;
    }

    public byte getLives() {
        return lives;
    }
    public String getLivesStr() {
        return "x "+getLives();
    }

    public String getSizeStr() {
        return "SIZE\n   "+ size;
    }
    public  void pausar(){
        continuar = false;
    }

    public  void continuando(){
        continuar = true;
    }

    public long getCantFood() {
        return score;
    }

    public String getCantFoodStr(){
        return "SCORE\n      "+ (getCantFood()*10);
    }
}
