package com.game.main;

import java.awt.*;
import java.util.Random;

public class Enemy extends GameObject {

    private Handler handler;


    public Enemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        velX = -6;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.WIDTH - 50);
        if (x <= 0) {
                y += 32;
                velX = 6;
            } else if (x >= Game.WIDTH - 50) {
                velY = 0;
            y += 32;
            velX = -6;
        }
        y = Game.clamp(y, 0, Game.HEIGHT - 74);
        if (y <= 0) {
            velY = 6;
        }
        else if (y >= Game.HEIGHT - 74) {
            velY = -6;
        }
    }

    public void render(Graphics g){
        g.setColor(Color.black);
        g.fillRect((int)x,(int) y, 32, 32);
    }

}
