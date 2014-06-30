import java.awt.*;

public class Lava extends Terrain {
    FX sfx=null;
    public Lava(Grid world,int x,FX sfx){
    super(world,"src/Lava2.png",x,70);
        collidable=false;
        this.sfx=sfx;
        id=3;
    }

    @Override
    public void update() {
        super.update();
        if(Math.random()<.3){
            sfx.create(new Particles(new Color(255,251,30),Math.random()*2+3,this.x*10+10+Math.random()*(this.w-2)*10,this.y*10+23d,Math.random()*3+8,Math.random()*.4-.2,Math.random()*-.8));
        }
    }
}
