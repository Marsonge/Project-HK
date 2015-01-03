/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.mygdx.game.TiledMap.TiledMapStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;


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
    Stage stage;
    Music ambiance;
    
    public GameScreen(MyGame game){
        this.game=game;
        
    }


    @Override
    public void render(float f) {
        //Update l'affichage
        
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //On affiche la carte
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
        
        //On écrit le nombre de fps
        batch.begin();
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        batch.end();
        //On vérifie les input et on redéssine les acteurs (les cases)
        stage.act(f);
        stage.draw();
        
    }

    @Override
    public void resize(int i, int i1) {
        // FONCTIONNE PAAAAAAAAAS
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {
        //On charge tout lorsque la fenetre devient la fenetre active
        ambiance = Gdx.audio.newMusic(Gdx.files.internal("sound/dayambiance.mp3"));
        
        ambiance.setLooping(true);
        ambiance.play();
        ambiance.setVolume(0.1f);
        
        map = new TmxMapLoader().load("map.tmx");
        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(w,h);
        camera.setToOrtho(false);
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new TiledMapStage(map);
        Gdx.input.setInputProcessor(stage);
        
    }

    @Override
    public void hide() {
        ambiance.dispose();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        map.dispose();
        ambiance.dispose();
    }

    
    
}
