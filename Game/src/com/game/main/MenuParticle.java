package com.game.main;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject {

    private Handler handler;
    Random speedUp = new Random();
    Random r = new Random();
    private Color col;


    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);

        this.handler = handler;
        velX =(r.nextInt(7 - -7) + -7);
        velY =(r.nextInt(7 - -7)+ - 7);
        if(velX == 0)velX = 1;
        if(velY == 0)velY = 1;
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 50, 50);
    }

    public void tick() {
        x += velX;
        y += velY;
        if(y<= 0 || y >= Game.HEIGHT-80)velY *= -1;
        if(x<= 0 || x>= Game.WIDTH-60)velX *= -1;

    }

    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int)x, (int)y, 50, 50);
    }

}