package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
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

public class MyGame extends Game {
	/*SpriteBatch batch;
	Texture img;
	Texture bg; 
        private OrthographicCamera camera;
        private Rectangle jolof;
        private Rectangle jolof1;
        private Building bat1;
        private List<Building> buildlist;
        private int newx;
        private int newy;*/
        GameScreen gamescreen;
        //OrthographicCamera camera;
	@Override
	public void create () {
            
            /*
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
            */
            gamescreen = new GameScreen(this);
            setScreen(gamescreen);
                              
                
                
	}

	
}
