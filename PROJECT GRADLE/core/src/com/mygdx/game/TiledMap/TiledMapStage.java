package com.mygdx.game.TiledMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
    ShapeRenderer shape;

    public TiledMapStage(TiledMap tiledMap) {
        //On le lie à la TiledMap
        this.tiledMap = tiledMap;
        
        //Le ShapeRenderer pour déssiner les cadres
        shape = new ShapeRenderer();
        
        //Pour chaque layer on crée des actors qui correspondent
        for (MapLayer layer : tiledMap.getLayers()) {
            TiledMapTileLayer tiledLayer = (TiledMapTileLayer)layer;
            createActorsForLayer(tiledLayer);
        }
    }

    //Merci internet
    private void createActorsForLayer(TiledMapTileLayer tiledLayer) {
        for (int x = 0; x < tiledLayer.getWidth(); x++) {
            for (int y = 0; y < tiledLayer.getHeight(); y++) {
                TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
                TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell, this);
                actor.setBounds(x * tiledLayer.getTileWidth(), y * tiledLayer.getTileHeight(), tiledLayer.getTileWidth(),
                        tiledLayer.getTileHeight());
                addActor(actor);
                //On ajoute les event aux acteurs
                EventListener eventListener = new TiledMapClickListener(actor);
                actor.addListener(eventListener);
            }
        }
    }

    @Override
    public void draw() {
        //On dessine tous les actor du stage comme normalement grâce à super.draw()
        super.draw();
        
        //On dessine un cadre différent pour le tile sur lequel la souris est.
        if(activetile!=null && activetile.isEmpty()){
            shape.begin(ShapeType.Line);
            shape.setColor(Color.RED);
            shape.rect(activetile.getX(), activetile.getY(), 32, 32);
            shape.end();
        }
    }
    
    
}
