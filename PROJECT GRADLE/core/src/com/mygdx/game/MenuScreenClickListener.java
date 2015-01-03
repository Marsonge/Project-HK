/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
