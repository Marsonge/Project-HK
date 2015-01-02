/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


/**
 *
 * @author Fabien
 */
public class GameScreen implements Screen{
    TiledMap map;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    MyGame game;
    SpriteBatch batch;
    private BitmapFont font;
    
    public GameScreen(MyGame game){
        // load tilemap
        this.game=game;
        
    }


    @Override
    public void render(float f) {
         Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    tiledMapRenderer.render();
    camera.update();
    tiledMapRenderer.setView(camera);
    batch.begin();
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
		batch.end();
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void show() {
        map = new TmxMapLoader().load("map.tmx");
        //atlas = new TileAtlas(map, Gdx.files.internal("maps"));
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        // handles rendering the tile map. 8x8 is the chunk size to preload
        camera=new OrthographicCamera(800,480);
        batch = new SpriteBatch();
        font = new BitmapFont();
        
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    
    
}
