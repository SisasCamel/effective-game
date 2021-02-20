package com.game.main;

import java.awt.*;
import java.util.Random;

public class PongBoss extends GameObject {

    private Handler handler;
    private GameObject bullet;
    private int timer = 30;
    private int timer2 = 100;


    public PongBoss(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ID.Bullet){
                bullet = handler.object.get(i);
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 20, 215);
    }


    public void tick() {
        x += velX;
        y += velY;
        y = Game.clamp(y, 0, Game.HEIGHT - 250);
        if(getId() == ID.PongBoss) {
            if (bullet.getY() >= (Game.HEIGHT / 2)) {
                velY = 4 ;
            } else if (bullet.getY() <= (Game.HEIGHT / 2)) {
                velY = -4;
            }
        }
    }

    public void render(Graphics g){
        g.setColor(Color.darkGray);
        g.fillRect((int)x,(int) y, 20, 215);
    }

}
