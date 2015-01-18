package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

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
       Music music;
       Music enter;
 
// /!\ la déclaration de l'input processor DOIT impérativement être faite lors de l'appel de show() ! Sinon, ça marche une première fois mais si on retourne sur le menu,
 // l'input processor ne sera pas actualisé => BUGS EN MASSE

        public MenuScreen(final MyGame game){
                //on garde une trace de game
                this.game = game;
                
                //On lance la musique d'ambiance
                music=Gdx.audio.newMusic(Gdx.files.internal("sound/menu.mp3"));
                enter= Gdx.audio.newMusic(Gdx.files.internal("sound/enterbutton.mp3"));
                
                
                //choix du background
                backgroundTexture = new Texture("menuBackground.jpg");
                backgroundSprite =new Sprite(backgroundTexture);
                spriteBatch = new SpriteBatch();
                
                //changer de skin ici
                skin = new Skin( Gdx.files.internal( "ui/uiskin.json" ));
                
                //definition du table et du stage
                stage=new Stage();
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
                gameButton.addListener(new MenuScreenClickListener(){
                    @Override
                        public void clicked(InputEvent event, float x, float y) {
                        enter.play();
                        game.setScreen(game.gamescreen);
                    }
                });
                
                optionButton.addListener(new MenuScreenClickListener(){
                    @Override
                        public void clicked(InputEvent event, float x, float y) {
                        //game.setScreen(game.optionscreen);
                    }
                });
                
                creditButton.addListener(new MenuScreenClickListener(){
                    @Override
                        public void clicked(InputEvent event, float x, float y) {
                        //game.setScreen(game.creditscreen);
                    }
                });
                
                 quitButton.addListener(new MenuScreenClickListener(){
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
            Gdx.input.setInputProcessor(stage); // définition de l'input processor à faire ici (voir commentaire l.33)
            music.setLooping(true);
            music.play();
        }


       @Override
        public void hide() {
             // called when current screen changes from this to a different screen
            
            //On enlève cette musique d'ambiance (qui fait froid dans le dos) quand on change d'écran.
            music.dispose();
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
            music.dispose();
        }
 }