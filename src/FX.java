import java.awt.*;
import java.util.ArrayList;

/**
 * Created by DiegoGonzalez on 6/26/14.
 */
public class FX {
    ArrayList <Particles> particles = new ArrayList<Particles>();
    public FX()
    {
    }
    public void create(Particles a)
    {
        particles.add(a);
    }
    public void update(){
        for(int x=0;x<particles.size();x++)
        {
            particles.get(x).update();
            if(!particles.get(x).isalive())
            {
                particles.remove(x);
                x--;
            }

        }
    }
    public void draw(Graphics g)
    {
        for(int x=0;x<particles.size();x++)
        {
            particles.get(x).draw(g);
        }
    }
    public void reset()
    {
     particles.clear();
    }
}
