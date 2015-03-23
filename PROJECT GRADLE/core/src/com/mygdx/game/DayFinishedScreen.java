package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;
import java.util.Locale;

public class DayFinishedScreen implements Screen {


       private MyGame game;
       private Skin skin;
       private Texture backgroundTexture;
       private Sprite backgroundSprite;
       private SpriteBatch spriteBatch;
       private TextButton continueButton;
       private Table table;
       private Stage stage;
       private Music music;
       private Music enter;
       private BitmapFont font;
       private Preferences prefs;
       private FileHandle baseFileHandle;
       private I18NBundle strings;
       private String language;
       private Preferences scoring;
       private int day;

 
// /!\ la déclaration de l'input processor DOIT impérativement être faite lors de l'appel de show() ! Sinon, ça marche une première fois mais si on retourne sur le menu,
// l'input processor ne sera pas actualisé => BUGS EN MASSE

        public DayFinishedScreen(final MyGame game){
                //on garde une trace de game
                this.game = game;
                scoring = Gdx.app.getPreferences("userscore.prefs");
                day = scoring.getInteger("day",1); 
                //choix du background
                prefs = Gdx.app.getPreferences("userconf.prefs");
                baseFileHandle = Gdx.files.internal("strings");
                language = prefs.getString("language","");
                strings = I18NBundle.createBundle(baseFileHandle, new Locale(language));
                backgroundTexture = new Texture("menuBackground.jpg");
                backgroundSprite =new Sprite(backgroundTexture);
                spriteBatch = new SpriteBatch();
                font = new BitmapFont();
                font.setColor(Color.WHITE);
                
                //changer de skin ici
                skin = new Skin( Gdx.files.internal( "ui/uiskin.json" ));
                
                //definition du table et du stage
                stage=new Stage();
                table=new Table();
                table.setSize(800,200);
                
                //definition des elements
                continueButton=new TextButton(strings.get("go"),skin);
                
                this.addAllToTable();
                // ATTENTION METTRE LES LISTENER APRES CETTE METHODE SINON CA FAIT DE LA MERDE
                stage.addActor(table);
                
                this.addAllButtonListener();
     
        }
        public void addAllToTable()
        {
            table.add(continueButton).width(200).height(40).padTop(5);
            table.row();
        }
        public void addAllButtonListener()
        {  
            this.addContinueButtonListener();
        }
        public void addContinueButtonListener()
        {
            continueButton.addListener(new MenuScreenClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        refreshAllScreens();
                        game.setScreen(game.gamescreen);
                    }
                    
                });
        }
        public void refreshAllScreens()
        {
            //The game has already prepared the others screens; creating them anew will refresh them.
            game.creditscreen = new CreditScreen(game);
            game.menuscreen = new MenuScreen(game);
            game.gamescreen = new GameScreen(game);
            game.optionscreen = new OptionScreen(game);
        }
        public void renderText()
        {
            spriteBatch.begin();
            if(day > 1)
            {
                font.draw(spriteBatch, strings.format("adaysurvived",day,strings.get("days")), 200, 400);
            }
            else
            {
                font.draw(spriteBatch, strings.format("adaysurvived",day,strings.get("day")), 200, 400);
            }
            font.draw(spriteBatch, strings.get("destroyed"), 200, 370);
            font.draw(spriteBatch, strings.get("quick"), 200, 350);
            spriteBatch.end();
        }
        
        @Override
        public void render(float delta) {
            
            stage.act(delta);
            stage.getBatch().begin();
            stage.getBatch().draw(backgroundSprite, 0, 0);
            stage.getBatch().end();
            stage.draw();
            renderText();
            
        }

       @Override
        public void resize(int width, int height) {
            
        }


       @Override
        public void show() {
             // called when this screen is set as the screen with game.setScreen();
            Gdx.input.setInputProcessor(stage); // définition de l'input processor à faire ici (voir commentaire l.33)
            stage.getRoot().getColor().a = 0;
            stage.getRoot().addAction(Actions.fadeIn(0.5f)); //The transition is weird, will try to fix
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