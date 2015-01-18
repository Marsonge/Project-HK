package com.mygdx.game.TiledMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 *Le click listener pour les actors de la TiledMap
 * 
 * @author Fabien
 */
public class TiledMapClickListener extends ClickListener {

    
    //Le son qui se jouera lors de la construction
    private static final Music buildingsound=Gdx.audio.newMusic(Gdx.files.internal("sound/building.mp3"));
    
    private TiledMapActor actor;
    //Les coordonnées de l'acteur
    private int x;
    private int y;
    
    /**
     * 
     * @param actor 
     *      L'actor auquel on lie l'event
     */
    public TiledMapClickListener(TiledMapActor actor) {
        //On garde l'acteur auquel on lie l'event (plus pratique)
        this.actor = actor;
    }

    /**
     * Lors du clic, vérifie si la case est vide et créé un batiment dedans si ce n'est pas le cas
     * @param event
     * @param x
     * @param y 
     */
    @Override
    public void clicked(InputEvent event, float x, float y) {
        Gdx.app.log("Cellule cliquée",actor.cell + " has been clicked.");
        
        if(actor.isEmpty()){
            //On reset le son si besoin puis on le joue
            if(buildingsound.isPlaying()) buildingsound.stop();
            
            buildingsound.play();
            x= (int)actor.getX();
            y = (int)actor.getY();
            System.out.println("x="+x+" y="+y);
            Cell newcell = new Cell();
            //On ajoute une Tile perso à la Cell (ici avec la texture jolof...)
            newcell.setTile(new StaticTiledMapTile(new TextureRegion(new Texture("jolof.png"),32,32)));
            actor.tiledLayer.setCell((int)(x/actor.getWidth()), (int)(y/actor.getHeight()), newcell);
            actor.cell = newcell;
            
        }
    }
    /**
     * Met à jour l'activetile du stage pour être celui dans lequel la souris entre
     * @param event
     * @param x
     * @param y
     * @param pointer
     * @param fromActor 
     */
    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        actor.stage.activetile=actor;
        x= (int)actor.getX();
        y = (int)actor.getY();
        Gdx.app.log("Coord mouseover","x="+x+" y="+y);
        
        
    }
    
    
    

    
}