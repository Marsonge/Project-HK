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
import java.util.Random;
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
        CharConteneur co = new CharConteneur();
        co.charger("charpool");
        ArrayList<Integer> chosen = new ArrayList<>();
        CharConteneur current = new CharConteneur();
        current.add(randomFromPool(chosen, co)); // We select 4 random character
        current.add(randomFromPool(chosen, co));
        current.add(randomFromPool(chosen, co));
        current.add(randomFromPool(chosen, co));
        
    }
    private Character randomFromPool(ArrayList<Integer> chosen,CharConteneur co)
    {
        Character ch;
        int i;
        Random rand = new Random();
        do
        {
            i = rand.nextInt(co.nbElements()+1);
            
        } while(this.belongs(i,chosen));
        ch = (Character) co.obtenir(i);
        chosen.add(i);
        return ch;
    }
    private boolean belongs(int i,ArrayList<Integer>  chosen)
    {
        int j = 0;
        while(j<chosen.size())
        {
             if (chosen.get(j) == i)
             {
                return true;
             }
        }
        return false;    
    
    }
    public static void main(String[] args) throws IOException {
        
    }
    
}
