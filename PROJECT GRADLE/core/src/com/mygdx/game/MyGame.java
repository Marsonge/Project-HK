package com.mygdx.game;

import com.badlogic.gdx.Game;


public class MyGame extends Game {
	
        GameScreen gamescreen;
        MenuScreen menuscreen;
        OptionScreen optionscreen;
        CreditScreen creditscreen;
	@Override
	public void create () {
            gamescreen = new GameScreen(this);
            menuscreen = new MenuScreen(this);
            optionscreen = new OptionScreen(this);
            creditscreen = new CreditScreen(this);
            setScreen(menuscreen);
	}
}