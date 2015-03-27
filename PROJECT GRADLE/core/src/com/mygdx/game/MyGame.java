package com.mygdx.game;

import com.badlogic.gdx.Game;


public class MyGame extends Game {
    GameScreen gamescreen;
    MenuScreen menuscreen;
    OptionScreen optionscreen;
    CreditScreen creditscreen;
    GameOverScreen gameoverscreen;
    DayFinishedScreen dayfinishedscreen;
    VictoryScreen victoryscreen;
    TutorialScreen tutorialscreen;
    
    @Override
    public void create () {
        gamescreen = new GameScreen(this);
        menuscreen = new MenuScreen(this);
        optionscreen = new OptionScreen(this);
        creditscreen = new CreditScreen(this);
        gameoverscreen = new GameOverScreen(this);
        dayfinishedscreen = new DayFinishedScreen(this);
        victoryscreen = new VictoryScreen(this);
        tutorialscreen = new TutorialScreen(this);
        setScreen(menuscreen);
    }
}