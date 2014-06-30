import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Particles {
    private double x;
    private double y;
    private double deltax;
    private double deltay;
    private double x2;
    private double y2;
    private double size;
    private double velocity=.5;
    private Color color;
    private long life;
    private double lifetime;
    boolean alive;

    public Particles(Color color, double size, double x, double y, double lifetime, double deltax, double deltay){
        this.color=color;
        this.x=x;
        this.y=y;
        this.size=size;
        life=System.currentTimeMillis();
        this.lifetime=lifetime;
        this.deltax=deltax;
        this.deltay=deltay;
        alive=true;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect((int)Math.round(x)-200,(int)Math.round(y)-200,(int)Math.round(size),(int)Math.round(size));
    }

    public void update(){
        x+=deltax- World.speed*10;
        y+=deltay;
        if((double)Math.abs((life-System.currentTimeMillis())/100)>lifetime)
            alive=false;
    }
    public void findSlope(){
        double slope = (y-y2)/(x-x2);
        deltax=x2<x?-1.0:1.0;
        deltay=deltax*slope;
        double distance = Math.sqrt(Math.pow(deltax,2)+Math.pow(deltay,2));
        deltax=(deltax/distance)*(Math.random()*4);
        deltay=(deltay/distance)*(Math.random()*4);
    }
    public boolean isalive(){
       return alive;
    }

}
