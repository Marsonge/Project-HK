/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuScreen implements Screen {


       MyGame game;
       Skin skin;
       Texture backgroundTexture;
       Sprite backgroundSprite;
       SpriteBatch spriteBatch;
       TextButton gameButton;
       TextButton optionButton;
       TextButton creditButton;
       TextButton quitButton;
       Table table;
       Stage stage;
 


        public MenuScreen(final MyGame game){
                //on garde une trace de game
                this.game = game;
                
                //choix du background
                backgroundTexture = new Texture("menuBackground.jpg");
                backgroundSprite =new Sprite(backgroundTexture);
                spriteBatch = new SpriteBatch();
                
                //changer de skin ici
                skin = new Skin( Gdx.files.internal( "ui/uiskin.json" ));
                
                //definition du table et du stage
                stage=new Stage();
                Gdx.input.setInputProcessor(stage);
                table=new Table();
                table.setSize(800,480);
                
                //definition des elements
                gameButton=new TextButton("Play",skin);
                optionButton=new TextButton("Options",skin);
                creditButton=new TextButton("Credit",skin);
                quitButton=new TextButton("Quit",skin);
                
                
                //ajout des elements au table
                table.add(gameButton).width(200).height(40);
                table.row();
                
                table.add(optionButton).width(200).height(40).padTop(5);
                table.row();
                
                table.add(creditButton).width(200).height(40).padTop(5);
                table.row();
                
                table.add(quitButton).width(200).height(40).padTop(5);
                table.row();
                
                // ATTENTION METTRE LES LISTENER APRES CETTE METHODE SINON CA FAIT DE LA MERDE
                stage.addActor(table);
                
                //ajout des listeners
                gameButton.addListener(new ClickListener(){
                    @Override
                        public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(game.gamescreen);
                    }
                });
                
                optionButton.addListener(new ClickListener(){
                    @Override
                        public void clicked(InputEvent event, float x, float y) {
                        //game.setScreen(game.optionscreen);
                    }
                });
                
                creditButton.addListener(new ClickListener(){
                    @Override
                        public void clicked(InputEvent event, float x, float y) {
                        //game.setScreen(game.creditscreen);
                    }
                });
                
                 quitButton.addListener(new ClickListener(){
                    @Override
                        public void clicked(InputEvent event, float x, float y) {
                        System.exit(0);
                    }
                });
                
        }


        public void renderBG()
        {
            spriteBatch.begin();
                backgroundSprite.draw(spriteBatch);
            spriteBatch.end();
        }
        
        @Override
        public void render(float delta) {
            renderBG();
            stage.act(delta);
            stage.draw();
        }





       @Override
        public void resize(int width, int height) {
            
        }


       @Override
        public void show() {
             // called when this screen is set as the screen with game.setScreen();
        }


       @Override
        public void hide() {
             // called when current screen changes from this to a different screen
        }


       @Override
        public void pause() {
        }


       @Override
        public void resume() {
        }


       @Override
        public void dispose() {
                // never called automatically
        }
 }