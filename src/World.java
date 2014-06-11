
import java.awt.*;
import java.util.ArrayList;

public abstract class World {
    public static int speed = 1; //*** DO NOT CHANGE *** ( if you want to make the game faster, go to Main and decrease sleep time )
    public static double gravity = 2;
    ArrayList<Terrain> bottom = new ArrayList<Terrain>();//list for the floor made of Terrain blocks
    ArrayList<Enemy> meteors = new ArrayList<Enemy>();//list of meteors, maybe all the enemies later on but right now only meteors
    Grid world=null;
    int choices = 5; //amount of cases

    public World(Grid world){
        this.world=world;//sets world as the Grid
        for(int x=0;x<=100;x+=10){
            spawnTerrain();// spawns Terrain to fill the floor
        }
        choices=14;

    }
    public void update(){
        //goes through all the objects in bottom
        for(int times=0;times<bottom.size();times++){
            bottom.get(times).update(); // updates the floor
            if(bottom.get(times).x+bottom.get(times).w<20){
                bottom.get(times).remove();// if a floor element is off screen, deletes itself
                bottom.remove(times);//deletes the floor element from bottom
                times--;// readjust the for loop for the deleted element
            }
        }
        if(bottom.size()>0&&bottom.get(bottom.size()-1).x<120){
            spawnTerrain();// if there's not enough elements to fill the screen, create more
        }

        for(int x=0;x<meteors.size();x++){ //goes through all the meteors
            meteors.get(x).update();//updates them
            if(!meteors.get(x).isAlive()){ //if they're dead, eliminates them.
                meteors.get(x).remove();
                meteors.remove(x);
                x--;
            }
        }
        if(Math.random()<.1)
            spawnMeteor();
    }

    public void spawnTerrain(){
        switch((int)(Math.random()*choices)){
            // 10/14 times, it will generate a ground block
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if(bottom.size()>0)
                    bottom.add(new Ground(world,bottom.get(bottom.size()-1).x+10));
                else
                    bottom.add(new Ground(world,20));
                break;
            // 1/14 times, it will generate a lava block
            case 10:
                if(bottom.size()>0){
                    if((bottom.get(bottom.size()-2) instanceof Water))
                        bottom.add(new Ground(world,bottom.get(bottom.size()-1).x+10));
                    bottom.add(new Lava(world,bottom.get(bottom.size()-1).x+10));
                    if(!(bottom.get(bottom.size()-2) instanceof Lava))
                        bottom.add(new Lava(world,bottom.get(bottom.size()-1).x+10));
                }else{
                    bottom.add(new Lava(world,20));
                }
                break;
            // 3/14 times, it will generate a water block
            case 11:
            case 12:
            case 13:
                if(bottom.size()>0){
                    if((bottom.get(bottom.size()-2) instanceof Lava))
                        bottom.add(new Ground(world,bottom.get(bottom.size()-1).x+10));
                    bottom.add(new Water(world,bottom.get(bottom.size()-1).x+10));
                    if(!(bottom.get(bottom.size()-2) instanceof Water)){
                        bottom.add(new Water(world,bottom.get(bottom.size()-1).x+10));
                    }
                }else{
                    bottom.add(new Water(world,20));
                }
                break;
            //if something goes wrong, just generate a ground block
            default:
                if(bottom.size()>0)
                    bottom.add(new Ground(world,bottom.get(bottom.size()-1).x+10));
                else
                    bottom.add(new Ground(world,20));
                break;
        }

    }

    public void spawnMeteor(){
        //spawns a meteor block ( check x and y, there is a need for a better way to spawn them )
        meteors.add(new Meteor(world,20+((int)(Math.random()*(GameEngine.DEFAULT_WINDOWSIZEX/10+10)))+2,(int)(Math.random()*20)));
    }

    public void draw(Graphics g){
        //draws the floor
        for(int x=0;x<bottom.size();x++){
            bottom.get(x).draw(g);
        }
        //draws the meteors
        for(int x=0;x<meteors.size();x++){
            meteors.get(x).draw(g);
        }

    }
}
