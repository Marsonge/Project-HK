/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.TiledMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 *
 * @author Fabien
 */
public class TiledMapStage extends Stage {

    private TiledMap tiledMap;
    TiledMapActor activetile;
    SpriteBatch batch;

    public TiledMapStage(TiledMap tiledMap) {
        this.tiledMap = tiledMap;
        batch=new SpriteBatch();
        for (MapLayer layer : tiledMap.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            createActorsForLayer(tiledLayer);
        }
    }

    private void createActorsForLayer(TiledMapTileLayer tiledLayer) {
        for (int x = 0; x < tiledLayer.getWidth(); x++) {
            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell, this);
                actor.setBounds(x * tiledLayer.getTileWidth(), y * tiledLayer.getTileHeight(), tiledLayer.getTileWidth(),
                        tiledLayer.getTileHeight());
                addActor(actor);
                EventListener eventListener = new TiledMapClickListener(actor);
                actor.addListener(eventListener);
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
        if(activetile!=null)
            activetile.draw(batch, 1.0f);
        
    }
    
    
}
