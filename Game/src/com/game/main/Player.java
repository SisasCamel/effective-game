package com.game.main;

import java.awt.*;

public class Player extends GameObject{

    Handler handler;
    public Player(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32, 32);
    }

    private void collision(){
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.SmartEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                }
            }
            if(tempObject.getId() == ID.BigBoy){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 5;
                }
            }
            if(tempObject.getId() == ID.PongBoss){
                if(getBounds().intersects(tempObject.getBounds())){
                    HUD.HEALTH -= 0.5;
                }
            }
        }
    }

    public void tick(){
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.WIDTH-50);
        y = Game.clamp(y, 0, Game.HEIGHT-74);

        collision();
    }


    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect((int)x, (int)y, 32, 32);
    }


}
