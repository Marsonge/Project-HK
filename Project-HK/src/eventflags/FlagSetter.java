/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventflags;

/**
 * Good ol' flagsetter class. Here to make raising/unraising flags a one-line issue.
 * You can check status with it, too
 * @author Tim
 */
public class FlagSetter {
    public FlagSetter(){};
    public void raiseFlag(Conteneur pool, int id)
    {
        Flag f = (Flag) pool.obtenir(id);
        f.setTriggered(1);
    }
    public void unraiseFlag(Conteneur pool, int id)
    {
        Flag f = (Flag) pool.obtenir(id);
        f.setTriggered(0);
    }
}
