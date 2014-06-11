
public class Meteor extends Enemy {
    public Meteor(Grid world,Player dino,int x, int y){
        super(world,dino,"src/Meteor.png",x,y);
        deltaX=0;
        deltaY=2;
    }
}
