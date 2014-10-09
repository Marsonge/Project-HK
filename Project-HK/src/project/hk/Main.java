/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.hk;
import characters.CharConteneur;
import eventflags.Conteneur;
import characters.Character;
import eventflags.FlagSetter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Tim
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    private void newGame() throws IOException
    {
        Conteneur c = new Conteneur();
        c.charger("flagpool");
        FlagSetter flgst = new FlagSetter();
    }
    public static void main(String[] args) throws IOException {
        Character c = new Character(0,"Doc 'Von' Marsong",0,20,0,0,1);
        CharConteneur co = new CharConteneur();
        co.add(c);
        co.sauvegarder("charpool");
    }
    
}
