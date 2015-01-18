package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Fabien
 */
public class MenuScreenClickListener extends ClickListener{
    
    static final Music click= Gdx.audio.newMusic(Gdx.files.internal("sound/clickmenu.mp3"));
    
    
    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        click.play();
    }
}
