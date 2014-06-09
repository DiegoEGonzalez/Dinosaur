
public class Meteor extends Enemy {
    public Meteor(Grid world,int x, int y){
        super(world,"src/Meteor.png",x,y);
        deltaX=0;
        deltaY=2;
    }
}
