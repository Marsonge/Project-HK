/*
 * Do what you want with our game: mod it, tweak it, whatever.
 * Just distribute it for free, with no malicious intent.
 * Remember to share the word, too.
 */
package characters;

import java.io.Serializable;

/**
 *
 * @author Tim
 */
public class Character implements Serializable{
  private final int id;
  private final String name;
  private int build;
  private int medic;
  private int shooting;
  private int scout;
  private int status; //0 is dead, 1 is alive, 2 is sick

  public Character(int idc, String nam, int builder, int medicer, int shootinger, int scouter, int statuser)
    {id = idc;
    name = nam;
    build = builder;
    medic = medicer;
    shooting = shootinger;
    scout = scouter;
    status = statuser;
    }
}