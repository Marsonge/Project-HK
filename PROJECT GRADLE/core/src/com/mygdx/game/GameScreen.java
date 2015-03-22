package com.mygdx.game;

import com.mygdx.game.TiledMap.TiledMapStage;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import static com.badlogic.gdx.utils.TimeUtils.millis;
import static com.badlogic.gdx.utils.TimeUtils.timeSinceMillis;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;


/**
 *
 * @author Fabien
 */
public class GameScreen implements Screen {
    TiledMap map;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    OrthographicCamera camera;
    MyGame game;
    SpriteBatch batch;
    private BitmapFont font;
    Stage stage;
    Music ambiance;
    enum State{ // Choix possibles pour la variable state
        PAUSE,
        RUN
    };
    private State state = State.RUN; // initialisée à RUN pour que le jeu se lance bien
    Sprite spritePause; // Sprite de l'icone pause
    
    private double food;
    private double foodPerSecond;
    private int defense;
    private Timer ressourceRender;
    private Timer clock;
    private int currentHour;
    
    public GameScreen(MyGame game){
        this.game=game;
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        spritePause=new Sprite(new Texture("pause.png")); // J'initialise le sprite ici avec l'image
        spritePause.setX(w/2 - spritePause.getWidth()/2);
        spritePause.setY(h/2 - spritePause.getHeight()/2);
        
        map = new TmxMapLoader().load("map.tmx");
        
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
        
        camera = new OrthographicCamera(w,h);
        camera.setToOrtho(false);
        batch = new SpriteBatch();
        font = new BitmapFont();
        stage = new TiledMapStage(map, this);
        food = 10;
        foodPerSecond = 0.1;
        defense = 5;
        currentHour = 5;
        
        batch.setProjectionMatrix(camera.combined);
        ressourceRender = new Timer();
        ressourceRender.schedule(new Task(){
                    @Override
                    public void run() {
                        addFood();
                    }
                }, 0, 1);
        ressourceRender.instance().stop();
        
        clock = new Timer();
        clock.schedule(new Task(){
            @Override
            public void run() {
                if(currentHour > 24){
                    currentHour = 5;
                }
                currentHour = currentHour +1;
            }
        },0,10);
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
                font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
                font.draw(batch, "CLOCK: " + currentHour+"H", Gdx.graphics.getWidth()-100, Gdx.graphics.getHeight()-10);
                font.draw(batch, "FOOD: " + (int)food, 20, Gdx.graphics.getHeight()-10);
                font.draw(batch, "DEFENSE: " + defense, 120, Gdx.graphics.getHeight()-10);
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
                if (Gdx.input.isKeyJustPressed(Keys.SPACE)) { // Second appui pour retourner en jeu
                    this.resume();
                }
                break;
        }

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
    }

    @Override
    public void resume() {
        // on fait l'inverse quand on retourne au jeu
        this.state = State.RUN;
        ambiance.play();
        clock.instance().start();
        ressourceRender.instance().start();
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
