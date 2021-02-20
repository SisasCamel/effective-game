package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.Key;

import com.game.main.Game.STATE;

public class KeyInput extends KeyAdapter {

    private Handler handler;
    private GameObject Bullet;
    private  GameObject Player;
    private boolean[] keyDown = new boolean[4];
    
    Game game;


    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
//Events for this ID
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_W) {tempObject.setVelY(-4); keyDown[0]=true;}
                if(key == KeyEvent.VK_S) tempObject.setVelY(4); keyDown[1]= true;
                if(key == KeyEvent.VK_D) tempObject.setVelX(4); keyDown[2]=true;
                if(key == KeyEvent.VK_A) tempObject.setVelX(-4); keyDown[3]=true;
            }
            if(tempObject.getId() == ID.PongBossMov){
                if(key == KeyEvent.VK_UP) tempObject.setVelY(-3);
                if(key == KeyEvent.VK_DOWN) tempObject.setVelY(3);
            }

        }
       if(key == KeyEvent.VK_ESCAPE) {
    	   if(game.gameState == STATE.Game) {
    		   if(Game.paused) {
    			    AudioPlayer.getMusic("game_music").resume();
    		   		Game.paused = false;
    	   		}else {
    	   			Game.paused = true;
    	   			AudioPlayer.getMusic("game_music").pause();
    	   		}
    	   }
       }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        int BulletObjNum = 6;
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
//Events for this ID
            if(tempObject.getId() == ID.Player){

                if(key == KeyEvent.VK_W) keyDown[0]=false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) keyDown[1]=false;//tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) keyDown[2]=false;//tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) keyDown[3]=false;//tempObject.setVelX(0);


                //vertical movement
                if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
                //horizontal movement
                if(!keyDown[2] || !keyDown[3]) tempObject.setVelX(0);
            }
            if(tempObject.getId() == ID.PongBossMov){
                if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
                if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
            }

        }
    }
}



