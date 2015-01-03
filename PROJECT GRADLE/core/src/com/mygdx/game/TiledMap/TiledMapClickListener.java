/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.TiledMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *
 * @author Fabien
 */
public class TiledMapClickListener extends ClickListener {

    private TiledMapActor actor;
    private int x;
    private int y;
    private Cell newcell;
    
    public TiledMapClickListener(TiledMapActor actor) {
        this.actor = actor;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println(actor.cell + " has been clicked.");
        
        if(actor.cell==null){
            x= (int)actor.getX();
            y = (int)actor.getY();
            System.out.println("x="+x+" y="+y);
            newcell = new Cell();
            newcell.setTile(new StaticTiledMapTile(new TextureRegion(new Texture("jolof.png"),32,32)));
            actor.tiledLayer.setCell((int)(x/actor.getWidth()), (int)(y/actor.getHeight()), newcell);
            actor.cell = newcell;
            
        }
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        actor.stage.activetile=actor;
        x= (int)actor.getX();
            y = (int)actor.getY();
            System.out.println("x="+x+" y="+y);
        
        
    }
    
    
    

    
}