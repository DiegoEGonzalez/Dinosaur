public class Water extends Terrain {
    public Water(Grid world,int x){
        super(world,"src/Water.png",x,50);
        collidable=false;
    }
}
