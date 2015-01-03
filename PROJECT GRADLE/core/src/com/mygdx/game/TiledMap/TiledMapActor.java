/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public TiledMapActor(TiledMap tiledMap, TiledMapTileLayer tiledLayer, TiledMapTileLayer.Cell cell, TiledMapStage stage) {
        this.tiledMap = tiledMap;
        this.tiledLayer = tiledLayer;
        this.cell = cell;
        this.stage=stage;
        shape = new ShapeRenderer();
         projectionMatrixSet = false;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //batch.end();
        if(!projectionMatrixSet){
           shape.setProjectionMatrix(batch.getProjectionMatrix());
       }
       if(this.cell==null){
            if(this==stage.activetile){
                shape.begin(ShapeType.Line);
                
                shape.setColor(Color.WHITE);
                shape.rect(this.getX(), this.getY(), 32, 32);
                shape.end();
                
                
            }else{
                shape.begin(ShapeType.Line);
                shape.setColor(Color.RED);
                shape.rect(this.getX(), this.getY(), 32, 32);
                shape.end();
            }
       }
       //batch.begin();
        
        
    }
    
    
    
}