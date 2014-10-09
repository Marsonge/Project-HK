/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package characters;

import eventflags.*;
import java.io.IOException;

/**
 * This class is an easy way to add characters to its pools, shall you add some
 * @author Tim
 */
public class CharacAdder {
    public static void main(String[] args) throws IOException {
        CharConteneur c = new CharConteneur();
        c.charger("charpool"); // Charges current char pool
        Character f = new Character(0,"Doc 'Von' Marsong",0,20,0,0,1); // ID,name,build stat,medic,shooting,scouting,status
        c.add(f);// is_triggered = 0 means that it is not!
        Character u = new Character(c.nbElements(),"Jack 'One-Eye' Grim",20,0,0,0,1); // c.nbElements() is way more secure to have the correct ID.
        c.add(u); // adds the new character! Can add as many as you want
        c.sauvegarder("charpool"); //Please, don't delete charpool. Else, you go recode the adder! ;_;
        
    }
}
