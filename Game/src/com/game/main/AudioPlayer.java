package com.game.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Sound;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		
		try {
			soundMap.put("click_sound", new Sound("res/click_sound.ogg"));
			soundMap.put("death_sound", new Sound("res/DeathSound.ogg"));
			musicMap.put("menu_music", new Music("res/AdhesiveWombat-Oui.ogg"));
			musicMap.put("game_music", new Music("res/AdhesiveWombat-8-bit_Adventure.ogg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}
