package com.mygdx.game;

import com.badlogic.gdx.Game;


public class MyGame extends Game {
	
        GameScreen gamescreen;
        MenuScreen menuscreen;
        
	@Override
	public void create () {
            gamescreen = new GameScreen(this);
            menuscreen = new MenuScreen(this);
            
            setScreen(menuscreen);
	}
}