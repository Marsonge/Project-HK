/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventflags;

import java.io.IOException;

/**
 * This class is an easy way to add flags to the flag pools, shall you add events and/or characters
 * @author Tim
 */
public class FlagAdder {
    public static void main(String[] args) throws IOException {
        Conteneur c = new Conteneur();
        c.charger("flagpool"); // Charges current flag pool
        Flag f = new Flag(0,"Doc is dead",0); // ID,desc,is_triggered
        c.add(f);// is_triggered = 0 means that it is not!
        Flag u = new Flag(c.nbElements(),"Jack is dead",0); // c.nbElements() is way more secure to have the correct ID.
        c.add(u); // adds the new flags! Can add as many as you want
        c.sauvegarder("flagpool"); //Please, don't delete flagpool. Else, you go recode the adder! ;_;
        
    }
}
