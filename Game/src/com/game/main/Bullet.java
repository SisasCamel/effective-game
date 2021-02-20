package com.game.main;

import java.awt.*;
import java.lang.ref.PhantomReference;
import java.util.Random;

import com.game.main.Game.STATE;

public class Bullet extends GameObject{

    Handler handler;
    private GameObject Bullet;
    private GameObject Player;
    private Random r = new Random();
    public Bullet(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        velX = 10;
        velY = 1;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 20);
    }

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.PongBoss||tempObject.getId() == ID.PongBossMov ) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    velX *= -1;
                    velY += 1;
                }
            }
            if(tempObject.getId() == ID.Player){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 10;
                }
            }
        }
    }
        public void tick(){
        GameObject playerpos = handler.object.get(0);
        x += velX;
        y += velY;
        if(y<= 0){
            velY *= -1;
        }
        if (y >= Game.HEIGHT - 74) {
            velY *= -1;
        }
        if(x >= Game.WIDTH) {
        	 HUD.HEALTH = 0;
        	 if(HUD.HEALTH == 0){
        		 HUD.HEALTH = 100;
        		 Game.gameState = STATE.GameOver;
        		 AudioPlayer.getSound("death_sound").play();
        		 handler.clearEnemys();
        		 handler.removeObject(Player);
        		 for(int i = 0; i < 10; i++){
        			 handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT), ID.MenuBigParticle, handler));
        		 }
        	 }
        }
        if(x <= 0) {
        	handler.clearEnemys();
        }
        collision();

    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect((int)x, (int)y, 32, 20);
    }

}
