package com.game.main;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.game.main.Game.STATE;

import javax.swing.*;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private HUD hud;
    private Random r = new Random();
    private Random endPhraseSelector = new Random();
    private int musicVolume = 100;
    private int pitchValue = 1;
    private String endPhrases[] = {"Practice makes Best!", "Nah dude, keep trying", "Just dodge...", "How could you even die?", "This is ez", "U dumb?", "This is literally made by a teen"};
    String endPhraseSelected = endPhrases[endPhraseSelector.nextInt(6)];
    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
        if(game.gameState == STATE.Menu){
            for(int i = 0; i < 10; i++){
                handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH-150), r.nextInt(Game.HEIGHT-200), ID.MenuBigParticle, handler));
            }
        }
    }

    public void tick() {

    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        //Play Button
        if(game.gameState == STATE.Menu) {
            if (mouseOver(mx, my, 210, 125, 200, 64)) {
                game.gameState = STATE.Game;
                hud.setLevel(1);
                hud.setScore(0);
                handler.addObject(new Player(100, 100, ID.Player, handler));
                handler.clearEnemys();
                for (int i = 0; i < 5; i++) {
                    handler.addObject(new Enemy(Game.WIDTH - 50, r.nextInt(Game.HEIGHT - 74), ID.BasicEnemy, handler));
                }
                AudioPlayer.getMusic("menu_music").pause();
                AudioPlayer.getMusic("game_music").loop(pitchValue, musicVolume);
                AudioPlayer.getSound("click_sound").play();
            }
            //Quit Button
            if (mouseOver(mx, my, 210, 325, 200, 64)) {
                System.exit(1);
            }
            //Help Button
            if (mouseOver(mx, my, 10, Game.HEIGHT-90, 20, 20)) {
                game.gameState = STATE.Help;
                AudioPlayer.getSound("click_sound").play();
            }
            
        }
        //Help Back Button
        if (game.gameState == STATE.Help) {
            if (mouseOver(mx, my, 50, 375, 100, 34)) {
                game.gameState = STATE.Menu;
                AudioPlayer.getSound("click_sound").play();
                return;
            }
        }
        //GameOver Back Button
        if(game.gameState == STATE.GameOver){
            if(mouseOver(mx, my, 50, 320, 100, 32)){
                game.gameState = STATE.Menu;
                AudioPlayer.getSound("death_sound").stop();
                AudioPlayer.getSound("click_sound").play();
                return;
            }
        }
        //Respawn
        if(game.gameState == STATE.GameOver){
            if(mouseOver(mx, my, 200, 325, 150, 34)){
                game.gameState = STATE.Game;
                AudioPlayer.getSound("death_sound").stop();
                handler.addObject(new Player(100, 100, ID.Player, handler));
                handler.clearEnemys();
                hud.setLevel(1);
                hud.setScore(0);
                for (int i = 0; i < 5; i++) {
                    handler.addObject(new Enemy(Game.WIDTH - 50, r.nextInt(Game.HEIGHT - 74), ID.BasicEnemy, handler));
                }
                AudioPlayer.getMusic("menu_music").pause();
                AudioPlayer.getMusic("game_music").loop(pitchValue, musicVolume);
                AudioPlayer.getSound("click_sound").play();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
        if (mx > x && mx < x + width) {
            if (my > y && my < y + height) {
                return true;
            } else return false;
        } else return false;
    }

    public void render(Graphics g) {
        if (game.gameState == STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 40);
            Font fnt3 = new Font("arial", 1, 20);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Mini-Dodge", 180, 70);

            g.setFont(fnt2);
            g.setColor(Color.WHITE);
            g.drawString("Play", 265, 170);
            g.drawRect(210, 125, 200, 64);
            	
            
            g.setFont(fnt3);
            g.drawRect(10, Game.HEIGHT-90, 20, 20);
            g.drawString("?", 15, Game.HEIGHT-72);
            g.setFont(fnt2);
            g.drawRect(210, 325, 200, 64);
            g.drawString("Quit", 265, 370);
        } else if (game.gameState == STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 15);
            Font fnt3 = new Font("arial", 1, 20);
            Font fnt4 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Help", 240, 70);

            g.setFont(fnt3);
            g.drawString("Use WASD keys to move and dodge enemies", 25, 140);
            g.drawString("Use UP and DOWN arrows in level 5 :)", 25, 200);
            g.drawString("Press ESC to pause", 25, 260);
            g.setFont(fnt2);
            g.drawString("Programmer: SisasCamel", 400, 400);
            g.drawString("MusicFX: Adhesive Wombat", 400, 420);
            g.setFont(fnt4);
            g.drawRect(50, 375, 100, 34);
            g.drawString("Back", 65, 402);
        }  else if (game.gameState == STATE.GameOver) {
        	AudioPlayer.getMusic("game_music").stop();
            AudioPlayer.getMusic("menu_music").play();
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 20);
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString("Game Over", 180, 70);
            
            g.setFont(fnt3);
            g.drawString(endPhraseSelected, 50, 140);
            g.drawString("Score: " + hud.getScore(), 50, 240);
            g.setFont(fnt2);
            g.drawRect(50, 325, 100, 34);
            g.drawString("Menu", 60, 352);
            g.drawRect(200, 325, 150, 34);
            g.drawString("Respawn", 210, 352);
        }
    }
}

