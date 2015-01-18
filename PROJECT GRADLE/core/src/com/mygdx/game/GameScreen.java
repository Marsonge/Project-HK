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
    enum State{ // Choix possibles pour la variable state
        PAUSE,
        RUN
    };
    private State state = State.RUN; // initialisée à RUN pour que le jeu se lance bien
    Sprite spritePause; // Sprite de l'icone pause
    
    
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
        stage = new TiledMapStage(map);
        
        batch.setProjectionMatrix(camera.combined);
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
    }

    @Override
    public void resume() {
        // on fait l'inverse quand on retourne au jeu
        this.state = State.RUN;
        ambiance.play();
    }
    

    @Override
    public void dispose() {
        map.dispose();
        ambiance.dispose();
    }

    
    
}
