/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.Locale;

/**
 *
 * @author Tim
 */
public class OptionScreen implements Screen{
    
       private MyGame game;
       private Skin skin;
       private Texture backgroundTexture;
       private Sprite backgroundSprite;
       private SpriteBatch spriteBatch;
       private TextButton FpsButton;
       private TextButton quitButton;
       private TextButton languageButton;
       private Table table;
       private Stage stage;
       private Music music;
       private Music enter;
       private boolean FpsShowing;
       private Preferences prefs;
       private FileHandle baseFileHandle;
       private I18NBundle strings;
       private String language;
       
 
// /!\ la déclaration de l'input processor DOIT impérativement être faite lors de l'appel de show() ! Sinon, ça marche une première fois mais si on retourne sur le menu,
// l'input processor ne sera pas actualisé => BUGS EN MASSE

        public OptionScreen(final MyGame game)
        {
                //on garde une trace de game
                this.game = game;
                prefs = Gdx.app.getPreferences("userconf.prefs");
                baseFileHandle = Gdx.files.internal("strings");
                language = prefs.getString("language","");
                strings = I18NBundle.createBundle(baseFileHandle, new Locale(language));
                FpsShowing = prefs.getBoolean("fps", true);
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
                FpsButton = this.displayFpsChoice();
                languageButton=new TextButton(strings.get("otherlanguage"),skin);
                quitButton=new TextButton(strings.get("back"),skin);
                
                this.addAllToTable();
                
                // ATTENTION METTRE LES LISTENER APRES CETTE METHODE SINON CA FAIT DE LA MERDE
                stage.addActor(table);
                
                this.addAllButtonListener();

        }
        private TextButton displayFpsChoice()
        {
            if(FpsShowing)
                {
                    return (new TextButton(strings.get("hideFPS"),skin));
                }
                else
                {
                    return (new TextButton(strings.get("showFPS"),skin));
                }
        }
        private void addAllToTable()
        {
            table.add(FpsButton).width(200).height(40);
            table.row();
            table.add(languageButton).width(200).height(40).padTop(5);
            table.row();
            table.add(quitButton).width(200).height(40).padTop(5);
            table.row();
        }
        private void addAllButtonListener()
        {
            this.addFpsButtonListener();
            this.addLanguageButtonListener();
            this.addQuitButtonListener();
        }
        private void addFpsButtonListener()
        {
            FpsButton.addListener(new MenuScreenClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        FpsShowing = !(FpsShowing);       
                        prefs.putBoolean("fps", FpsShowing);
                        prefs.flush();
                        if(FpsShowing)
                        {
                            FpsButton=new TextButton("Hide FPS",skin);
                        }
                        else
                        {
                            FpsButton=new TextButton("Show FPS",skin);
                        }
                        refreshAllScreens();
                    }
                });
        }
        private void addLanguageButtonListener()
        {
            languageButton.addListener(new MenuScreenClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        if(language.equals(""))
                        {
                            language = "fr";
                        }
                        else
                        {
                            language = "";
                        }
                        prefs.putString("language", language);
                        prefs.flush();
                        refreshAllScreens();
                    }
                });
        }
        private void addQuitButtonListener()
        {
            quitButton.addListener(new MenuScreenClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        game.setScreen(game.menuscreen);
                    }
                });
        }
        private void refreshAllScreens()
        {
            //The game has already prepared the others screens; creating them anew will refresh them.
            game.creditscreen = new CreditScreen(game);
            game.menuscreen = new MenuScreen(game);
            game.gamescreen = new GameScreen(game);
            game.optionscreen = new OptionScreen(game);
            game.setScreen(game.optionscreen);
        }
        public void renderBG()
        {
            spriteBatch.begin();
            backgroundSprite.draw(spriteBatch);
            spriteBatch.end();
        }
        @Override
        public void render(float delta) 
        {
            renderBG();
            stage.act(delta);
            stage.draw();
        }
       @Override
        public void resize(int width, int height) 
        {
            
        }
        @Override
        public void show() 
        {
             // called when this screen is set as the screen with game.setScreen();
            Gdx.input.setInputProcessor(stage); // définition de l'input processor à faire ici (voir commentaire l.33)
        }
       @Override
        public void hide() 
        {

        }
       @Override
        public void pause() 
        {
        }
       @Override
        public void resume() 
        {
        }
       @Override
        public void dispose()
        {

        }
 }