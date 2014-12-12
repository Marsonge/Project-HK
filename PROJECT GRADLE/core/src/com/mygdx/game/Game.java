package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.entities.Building;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture bg; 
        private OrthographicCamera camera;
        private Rectangle jolof;
        private Rectangle jolof1;
        private Building bat1;
        private List<Building> buildlist;
        private int newx;
        private int newy;
        
	@Override
	public void create () {
		batch = new SpriteBatch();
                buildlist= new ArrayList();
		img = new Texture("jolof.png");
                bg = new Texture("bg.png");
                camera = new OrthographicCamera();
                jolof = new Rectangle();
                jolof.x = 800 / 2 - 64 / 2;
                jolof.y = 20;
                jolof.width = 64;
                jolof.height = 64;
                jolof1 = new Rectangle();
                jolof1.x = 800 / 2 - 64 / 2;
                jolof1.y = 300;
                jolof1.width = 64;
                jolof1.height = 64;
                bat1=new Building(1,new Texture("jolof.png"),100,100);
                buildlist.add(bat1);
	}

	@Override
	public void render () {
                camera.setToOrtho(false, 800, 480);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                batch.setProjectionMatrix(camera.combined);
                batch.begin();
                batch.draw(bg,0,0);
                batch.draw(img, jolof.x, jolof.y);
                batch.draw(img, jolof1.x, jolof1.y);
                for(Building batiment : buildlist){
                    batch.draw(batiment.sprite, batiment.x, batiment.y);
                }
		batch.end();
                if(Gdx.input.isKeyPressed(Keys.LEFT)) jolof.x -= 200 * Gdx.graphics.getDeltaTime();
                if(Gdx.input.isKeyPressed(Keys.RIGHT)) jolof.x += 200 * Gdx.graphics.getDeltaTime();
                if(Gdx.input.isKeyPressed(Keys.DOWN)) jolof.y -= 200 * Gdx.graphics.getDeltaTime();
                if(Gdx.input.isKeyPressed(Keys.UP)) jolof.y += 200 * Gdx.graphics.getDeltaTime();
                if(Gdx.input.isButtonPressed(Buttons.LEFT)) {
                    newx = Gdx.input.getX()-64/2;
                    newy = Gdx.input.getY()+64/2;
                    buildlist.add(new Building(1,new Texture("jolof.png"),newx,480-newy));
                }//Quand on clique, on fait apparaitre un batiment avec l'image de jolof!
                if(Gdx.input.isButtonPressed(Buttons.RIGHT)) {
                    newx = Gdx.input.getX()-64/2;
                    newy = Gdx.input.getY()+64/2;
                    buildlist.add(new Building(1,new Texture("mamache.jpg"),newx,480-newy));
                }
                
                if(jolof.x < 0) jolof.x = 0;
                if(jolof.x > 800 - 64) jolof.x = 800 - 64;
                if(jolof.y < 0) jolof.y = 0;
                if(jolof.y > 480 - 64) jolof.y = 480 - 64;
	}
}
