import java.awt.*;

public class Water extends Terrain {
    FX sfx = null;
    public Water(Grid world,int x, FX a){
        super(world,"src/Water2.png",x,70);
        sfx=a;
        collidable=false;
        id=2;
    }

    @Override
    public void update() {
        super.update();
        if(Math.random()<.3){
            sfx.create(new Particles(new Color(0,190,255),Math.random()*2+2,this.x*10+this.w*10/2,this.y*10+h*10-10,Math.random()*(6+Math.random()*2)+8,Math.random()*.8-.4,Math.random()*-2));
        }
    }
}
