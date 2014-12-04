/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Fabien
 */
public class Building {
    Texture sprite;
    int id;
    int x;
    int y;
    
    public Building(int type, Texture sprite,int x, int y){
        id=type;
        this.sprite=sprite;
        this.x=x;
        this.y=y;
    }
}
