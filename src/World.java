
import java.awt.*;
import java.util.ArrayList;

public abstract class World {
    public static int speed = 1;
    public static double gravity = 2;
    ArrayList<Terrain> bottom = new ArrayList<Terrain>();
    Grid world=null;

    public World(Grid world){
        this.world=world;
        for(int x=0;x<=100;x+=10){
            spawnTerrain();
        }
    }
    public void update(){
        for(int times=0;times<bottom.size();times++){
            bottom.get(times).update();
            if(bottom.get(times).x+bottom.get(times).w<20){
                bottom.get(times).remove();
                bottom.remove(times);
                times--;
            }
        }
        if(bottom.size()>0&&bottom.get(bottom.size()-1).x<110){
            spawnTerrain();
        }
    }

    public void spawnTerrain(){
        int choices = 6;
        switch((int)(Math.random()*choices)){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
                if(bottom.size()>0)
                    bottom.add(new Ground(world,bottom.get(bottom.size()-1).x+10));
                else
                    bottom.add(new Ground(world,20));
                break;
            case 5:
                if(bottom.size()>0){
                    bottom.add(new Lava(world,bottom.get(bottom.size()-1).x+10));
                    if(!(bottom.get(bottom.size()-2) instanceof Lava))
                        bottom.add(new Lava(world,bottom.get(bottom.size()-1).x+10));
                }else{
                    bottom.add(new Lava(world,20));
                }
                break;
            default:
                bottom.add(new Ground(world,bottom.size()*10+20));
                if(bottom.size()>1)
                    bottom.get(bottom.size()-1).x=bottom.get(bottom.size()-2).x+10;
                break;
        }

    }

    public void draw(Graphics g){
        for(int x=0;x<bottom.size();x++){
            bottom.get(x).draw(g);
        }
    }
}
