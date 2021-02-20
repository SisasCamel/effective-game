package com.game.main;

import java.awt.*;
import java.util.Random;

public class BigEnemy extends GameObject {

    private Handler handler;
    Random speedUp = new Random();


    public BigEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        velX = -3;
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 50, 50);
    }

    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.WIDTH - 50);
        y = Game.clamp(y, 0, Game.HEIGHT);
        if (x <= 0) {
            velX = 3;
        } else if (x >= Game.WIDTH - 50) {
            velY = 0;
            velX = -3;
        }
        if (velY < 1) {
            y = Game.clamp(y, 0, Game.HEIGHT - 74);
            if (y <= 0) {
                velY = speedUp.nextInt(3);
            } else if (y >= Game.HEIGHT - 74) {
                velY = -speedUp.nextInt(3);
            }
        }

    }

    public void render(Graphics g) {
        Color orange = Color.decode("#FF8E00");
        g.setColor(orange);
        g.fillRect((int)x, (int)y, 50, 50);
    }

}