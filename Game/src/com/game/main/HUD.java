package com.game.main;

import java.awt.*;

public class HUD {

    public static float HEALTH = 100;
    private int greenValue = 255;
    private GameObject player;
    private Handler handler;

    private int score = 0;
    private int level = 1;

    public void tick(){
        HEALTH = Game.clamp(HEALTH, 0, 100);

        score++;
    }

    public void render(Graphics g){
        g.setColor(Color.red);
        g.fillRect(15, 15, 200, 22);
        g.setColor(Color.getHSBColor( (2f * HEALTH) / 360, 1f, 0.5f));
        g.fillRect(15, 15, (int) (HEALTH*2), 22);
        g.setColor(Color.WHITE);
        g.drawRect(15, 15, 200, 22);

        g.drawString("Score: " + score, 10, 66);
        g.drawString("Level: " + level, 10, 84);
    }

    public void setScore(int score){
        this.score = score;
    }
    public int getScore(){
        return score;
    }

    public int getLevel(){
        return  level;
    }
    public void setLevel(int level){
        this.level = level;
    }
}
