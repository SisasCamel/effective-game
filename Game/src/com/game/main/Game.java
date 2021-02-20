package com.game.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.Scanner;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 7560296564786416245L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = true;
    
    public static boolean paused = false;
    
    private Random EnemyrandPos;
    public Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;
    private Color back;
    private int frames;

    public enum STATE{
        Menu,
        Help,
        Game,
        Settings,
        SoundSettings,
        Language,
        GameOver
    }
    public static STATE gameState = STATE.Menu;

    public Game() throws InterruptedException {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);
        
        AudioPlayer.load();
        AudioPlayer.getMusic("menu_music").loop();
        
        new Window(WIDTH, HEIGHT, "Mini-Dodge", this);

        spawner = new Spawn(handler, hud);
        EnemyrandPos = new Random();

    }


    public synchronized void start(){
        thread = new Thread(this);
        thread.start();

    }
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    // game loop
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                frames++;

                if (System.currentTimeMillis() - timer > 1000) {
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
            //stop();
        }

    }

    private void tick(){
        if(gameState == STATE.Game){
        	if(!paused) {
        		handler.tick();
        		 hud.tick();
                 spawner.tick();
                 if(HUD.HEALTH <= 0){
                     HUD.HEALTH = 100;
                     gameState = STATE.GameOver;
                     AudioPlayer.getSound("death_sound").play();
                     handler.clearEnemys();
                     for(int i = 0; i < 10; i++){
                         handler.addObject(new MenuParticle(EnemyrandPos.nextInt(Game.WIDTH-50), EnemyrandPos.nextInt(Game.HEIGHT), ID.MenuBigParticle, handler));
                     }
                 }
            }
        }
        else if(gameState == STATE.Menu|| gameState == STATE.GameOver || gameState == STATE.Settings || gameState == STATE.Help || gameState == STATE.SoundSettings || gameState == STATE.Language){
        	handler.tick();
            menu.tick();
        }


    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(back);
        g.fillRect(0,0, WIDTH, HEIGHT);
        handler.render(g);
        
        if(paused) {
        	g.setColor(Color.orange);
        	g.drawString("PAUSED", 500, 40);
        }
        
        if(gameState == STATE.Game) {
            back = Color.BLUE;
            hud.render(g);
        }
        else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver || gameState == STATE.Settings || gameState == STATE.SoundSettings || gameState == STATE.Language){
            back = Color.BLACK;
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if(var >= max){
            return var = max;
        }
        else if(var <= min){
            return var = min;
        }
        else{
            return var;
        }
    }


    public static void main(String args[]) throws InterruptedException {
        new Game();
    }
}
