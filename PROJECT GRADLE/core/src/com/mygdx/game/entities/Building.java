/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

/**
 *
 * @author Fabien
 */
public class Building extends Rectangle{
    public Texture sprite;
    public int id;
    
    public Building(int type, Texture sprite, int x, int y){
        super();
        this.x=x;
        this.y=y;
        id=type;
        this.sprite=sprite;
        width=64;
        height=64;
        
    }
}