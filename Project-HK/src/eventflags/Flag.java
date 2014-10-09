package eventflags;
import java.io.Serializable;

public class Flag implements Serializable{
  private final int id;
  private final String desc;
  private int triggered;

  public Flag(int idc, String descr, int trueorfalse)
    {id = idc;
    desc = descr;
    triggered = trueorfalse;}

    public int getTriggered() {
        return triggered;
    }
    
    public void setTriggered(int triggered) {
        this.triggered = triggered;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }
  
}
