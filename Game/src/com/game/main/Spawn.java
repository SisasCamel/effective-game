package com.game.main;

import com.sun.management.GarbageCollectionNotificationInfo;

import java.util.Random;

public class Spawn {

    private Handler handler;
    private HUD hud;
    private Enemy baseEnemy;
    private Random r = new Random();
    private int velY;
    private int velX;
    private int BaseEnemySpawnRate = 500;
    private int BigEnemySpawnRate = 1000;

    private int scoreKeep = 0;
    private int scoreKeepBig = 0;
    private int delayPongBoss = 100;

    public Spawn(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
        this.baseEnemy = baseEnemy;
        if(hud.getLevel()== 6) {
        	for(int i = 0; i< 5; i++){
        		handler.addObject(new Enemy(630, r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
        	}
        }
    }

    public void tick(){
        scoreKeep++;
        scoreKeepBig++;
        if(scoreKeep >= BaseEnemySpawnRate) {
        	handler.addObject(new Enemy(r.nextInt(Game.WIDTH - 50), 0, ID.BasicEnemy, handler));
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);
            if (scoreKeep >= BigEnemySpawnRate) {
                handler.addObject(new BigEnemy(r.nextInt(Game.WIDTH - 50), 0, ID.BigBoy, handler));
            }
            if (hud.getLevel() == 4 || hud.getLevel() == 8) {
                handler.addObject(new SmartEnemy(Game.WIDTH / 2, Game.HEIGHT / 2, ID.SmartEnemy, handler));
            }
            if (hud.getLevel() == 5) {
                handler.clearEnemys();
                handler.addObject(new Bullet(125, Game.HEIGHT / 2, ID.Bullet, handler));
                handler.addObject(new PongBoss(Game.WIDTH - 50, Game.HEIGHT / 2, ID.PongBossMov, handler));
                handler.addObject(new PongBoss(10, 100, ID.PongBoss, handler));
            }
            if(hud.getLevel() > 5) {
            	BaseEnemySpawnRate = 300;
            }
        }
    }
}
