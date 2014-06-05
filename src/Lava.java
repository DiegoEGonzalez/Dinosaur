
public class Lava extends Terrain {
    public Lava(Grid world,int x){
    super(world,"src/Lava.png",x,50);
        collidable=false;
    }
}
