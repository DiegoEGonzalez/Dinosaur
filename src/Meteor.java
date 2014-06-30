import java.awt.*;

public class Meteor extends Enemy {
    FX sfx=null;
    public Meteor(Grid world,Player dino,int x, int y, FX sfx){
        super(world,dino,"src/Meteor.png",x,y);
        deltaX=0;
        deltaY=2;
        this.sfx=sfx;
        id=1;
    }

    @Override
    public void update() {
        super.update();
        //if(Math.random()<.8)
        for(int x=0;x<2;x++)
            sfx.create(new Particles(new Color(255,(int)(Math.random()*251),30),Math.random()*4+3,(this.x*10+Math.random()*h*10)+5,this.y*10,Math.random()*2.5,2+Math.random()*2,Math.random()*-1));

    }
}
