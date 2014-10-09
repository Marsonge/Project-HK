/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eventflags;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author lucie la belle gosse
 * @param <Flag>
 */
public class Conteneur<Flag extends java.io.Serializable> {
    private int indiceCourant;
    private java.util.List<Flag> lesValeurs;
    public Conteneur(){
        lesValeurs = new java.util.ArrayList <>();
    }
    public Conteneur(java.util.List<Flag> l){
        lesValeurs = l;
    }
    public void charger(java.lang.String nomFic) throws IOException
    {
        ObjectInputStream input = new ObjectInputStream(new FileInputStream(nomFic));
        boolean fin = true;
        while (fin)
        {
            Object o = null;
            try {
                o = input.readObject();
            }
            catch (IOException | ClassNotFoundException e) {
                fin = false;
            }
            if (fin)
            {
                lesValeurs.add((Flag) o);
            }
        }
    }
    public void dernier()
    {
        indiceCourant = lesValeurs.size()-1;
    }
    public boolean estVide()
    {
        return (lesValeurs.isEmpty());
    }
    public int nbElements()
    {
        return lesValeurs.size();
    }
    public void add(Flag f)
    {
        lesValeurs.add(f);
    }
    public Flag obtenir(int a)
    {
        return (Flag) lesValeurs.get(a);
    }
    public List valeurs()
    {
        return lesValeurs;
    }
    public void precedent() throws ConteneurException
    {
        if (indiceCourant>0)
        {
            indiceCourant -= 1;
        }
        else
        {
            throw new ConteneurException("L'indice est au plus bas !");
        }
    }
    public void suivant() throws ConteneurException
    {
        if (1 - lesValeurs.size()==indiceCourant)
        {
            throw new ConteneurException("L'indice est au maximum !");
        }
        else
        {
            indiceCourant +=1;
        }
    }
    public void vider()
    {
        lesValeurs.clear();
    }
    public void premier()
    {
        indiceCourant = 0;
    }
    public void sauvegarder(java.lang.String nomFic) throws IOException
    {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomFic));
        boolean error = false;
        int i = 0;
        Object o = null;
        while (!error && i < lesValeurs.size())
        {
            o = (Flag) lesValeurs.get(i);
            try {
                out.writeObject(o);
            }
            catch (IOException e) {
                error = true;
            }
            i +=1;
        }
    }
}
