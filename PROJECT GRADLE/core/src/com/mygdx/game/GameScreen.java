package com.mygdx.game;

import com.mygdx.game.TiledMap.TiledMapStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;
import static com.badlogic.gdx.utils.TimeUtils.millis;
import static com.badlogic.gdx.utils.TimeUtils.timeSinceMillis;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import java.util.Locale;


/**
 *
 * @author Fabien
 */
public class GameScreen implements Screen {
    private final TiledMap map = new TmxMapLoader().load("map.tmx");
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private MyGame game;
    private SpriteBatch batch;
    private BitmapFont font;
    private Stage stage;
    private Music ambiance;
    enum State{ // Choix possibles pour la variable state
        PAUSE,
        RUN
    };
    private State state = State.RUN; // initialisée à RUN pour que le jeu se lance bien
    private final Sprite spritePause = new Sprite(new Texture("pause.png"));
    
    private double food;
    private int day;
    private double foodPerSecond;
    private int defense;
    private Timer ressourceRender;
    private Timer clock;
    private int currentHour;
    private final Preferences prefs = Gdx.app.getPreferences("userconf.prefs");
    private final Preferences scoring = Gdx.app.getPreferences("userscore.prefs");
    private boolean FpsShowing;
    private final FileHandle baseFileHandle = Gdx.files.internal("strings");
    private I18NBundle strings;
    private Stage buttonStage;
    private String language;
    private final Task clockTask = new Task(){
            @Override
            public void run() {
                if(currentHour >= 24){
                    currentHour = 0;
                    if(defense>=300)
                    {
                        if(day==9)
                        {
                           scoring.putInteger("day", 0);
                           scoring.putInteger("victory",scoring.getInteger("victory",0)+1);
                           scoring.flush();
                           game.victoryscreen = new VictoryScreen(game);
                           game.setScreen(game.victoryscreen);
                        }
                        else
                        {
                            day += 1;
                            scoring.putInteger("day",day);
                            scoring.flush();
                            game.dayfinishedscreen = new DayFinishedScreen(game);
                            game.setScreen(game.dayfinishedscreen);
                        }
                    }   
                    else
                    {
                        scoring.putInteger("day", 0);
                        scoring.flush();
                        game.setScreen(game.gameoverscreen);
                    }
                }
                currentHour = currentHour +1;

            }
        };
    private final Task resourceTask = new Task(){
                    @Override
                    public void run() {
                        addFood();
                    }
                };
    private TextButton quitButton;
    public GameScreen(final MyGame game){
        this.game=game;
        language = prefs.getString("language","");
        strings = I18NBundle.createBundle(baseFileHandle, new Locale(language));
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        spritePause.setX(w/2 - spritePause.getWidth()/2);
        spritePause.setY(h/2 - spritePause.getHeight()/2);
        FpsShowing = prefs.getBoolean("fps", true);
        day = scoring.getInteger("day",0);        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera(w,h);
        camera.setToOrtho(false);
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new TiledMapStage(map, this);
        food = 10;
        foodPerSecond = 0.1;
        defense = 5;
        currentHour = 5 + day;
        batch.setProjectionMatrix(camera.combined);
        ressourceRender = new Timer();
        ressourceRender.schedule(resourceTask, 0, 1);
        ressourceRender.instance().stop();
        clock = new Timer();
        clock.schedule(clockTask,0,10);
        clock.instance().stop();
    }

    @Override
    public void render(float f) {
        //Update l'affichage quand le jeu est en train de tourner
        switch (state) {
            case RUN:
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                //On affiche la carte
                camera.update();
                tiledMapRenderer.setView(camera);
                tiledMapRenderer.render();
                
                //On écrit le nombre de fps
                batch.begin();
                if(FpsShowing){
                    font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
                }
                font.draw(batch, strings.get("clock") + currentHour+"H", Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-10);
                font.draw(batch, strings.get("food") + (int)food, 20, Gdx.graphics.getHeight()-10);
                font.draw(batch, strings.get("defense") + defense, 170, Gdx.graphics.getHeight()-10);
                batch.end();
                //On vérifie les input et on redéssine les acteurs (les cases)
                stage.act(f);
                stage.draw();
                
                if (Gdx.input.isKeyJustPressed(Keys.SPACE)) { // Appui sur la touche espace pour mettre en pause
                    this.pause();
                }
                break;
            case PAUSE: // Sinon, quand le jeu est en pause on affiche l'icone pause
                batch.begin();
                    spritePause.draw(batch);
                batch.end();
                buttonStage.act();
                buttonStage.draw();
                if (Gdx.input.isKeyJustPressed(Keys.SPACE)) { // Second appui pour retourner en jeu
                    this.resume();
                }
                break;
        }

    }
    private void addQuitButtonListener()
        {
            quitButton.addListener(new MenuScreenClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        refreshAllScreens();
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

    @Override
    public void resize(int i, int i1) {
        // FONCTIONNE PAAAAAAAAAS
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
    }

    @Override
    public void show() {
        //On charge la musique et on change l'Input Processor
        ambiance = Gdx.audio.newMusic(Gdx.files.internal("sound/dayambiance.mp3"));
        
        ambiance.setLooping(true);
        ambiance.play();
        ambiance.setVolume(0.2f);
        clock.instance().start();
        ressourceRender.instance().start();
        
        // Déplacé les déclarations de variables dans le constructeur
        
        Gdx.input.setInputProcessor(stage);
        
    }

    @Override
    public void hide() {
        ambiance.dispose();
    }

    @Override
    public void pause() {
        // quand on met le jeu en pause (avec barre espace) on actualise le statut et on coupe la musique
        this.state = State.PAUSE;
        ambiance.pause();
        clock.instance().stop();
        ressourceRender.instance().stop();
        buttonStage = new Stage();
        Table table=new Table();
        table.setSize(800,200);
        quitButton=new TextButton(strings.get("menu"),new Skin( Gdx.files.internal( "ui/uiskin.json" )));
        this.addQuitButtonListener();
        table.add(quitButton).width(200).height(40).padTop(5);
        table.row();
        buttonStage.addActor(table);
        Gdx.input.setInputProcessor(buttonStage);
    }

    @Override
    public void resume() {
        // on fait l'inverse quand on retourne au jeu
        this.state = State.RUN;
        ambiance.play();
        clock.instance().start();
        ressourceRender.instance().start();
        Gdx.input.setInputProcessor(stage);
    }
    

    @Override
    public void dispose() {
        map.dispose();
        ambiance.dispose();
    }

    public double getFood(){
        return food;
    }
    
    public double getFoodPerSecond(){
        return foodPerSecond;
    }
    
    public int getDefense(){
        return defense;
    }
    
    public void addFoodPerSecond(double quantity){
        foodPerSecond = foodPerSecond + quantity;
    }
    
    public void addFood(){
        food = food + foodPerSecond;
    }
    
    public void addDefense(int quantity){
        defense = defense + quantity;
    }
    
    public void removeFoodPerSecond(double quantity){
        foodPerSecond = foodPerSecond - quantity;
    }
    
    public void removeFood(double quantity){
        food = food - quantity;
    }
    
    public void removeDefense(int quantity){
        defense = defense - quantity;
    }
    
}
