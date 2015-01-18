package com.mygdx.game.TiledMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Fabien
 */
public class TiledMapActor extends Actor {

    private TiledMap tiledMap;

    TiledMapTileLayer tiledLayer;

    TiledMapTileLayer.Cell cell;
    
    TiledMapStage stage;
    
    ShapeRenderer shape;
    
    static private boolean projectionMatrixSet;
    /**
     * 
     * @param tiledMap La TileMap auquel est lié l'acteur
     * @param tiledLayer Le niveau (layer) auquel correspond l'acteur
     * @param cell La cell à laquelle il sera lié
     * @param stage  Le stage sur lequel il est placé
     */
    public TiledMapActor(TiledMap tiledMap, TiledMapTileLayer tiledLayer, TiledMapTileLayer.Cell cell, TiledMapStage stage) {
        this.tiledMap = tiledMap;
        this.tiledLayer = tiledLayer;
        this.cell = cell;
        this.stage=stage;
        //On récupère le ShapeRenderer du TiledMapStage
        shape = stage.shape;
        projectionMatrixSet = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //Si la case est vide, on dessine un cadre blanc autour (pour faire le quadrillage)
        if(this.isEmpty()){
            if(!projectionMatrixSet){
               shape.setProjectionMatrix(batch.getProjectionMatrix());
            }
       
            
                shape.begin(ShapeType.Line);
                
                shape.setColor(Color.WHITE);
                shape.rect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
                shape.end();

       }
       //batch.begin();
        
        
    }
    /**
     * 
     * @return true si la case est vide, false sinon.
     */
    public boolean isEmpty(){
        return cell==null;
    }
    
    
    
}