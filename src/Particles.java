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

    public Particles(Color color, double size, double x, double y, double lifetime, double x2, double y2){
        this.color=color;
        this.x=x;
        this.y=y;
        this.size=Math.random()*size+3;
        //setDestination();
        life=System.currentTimeMillis();
        this.lifetime=Math.random()*lifetime+8;
        this.x2=x2;
        this.y2=y2;
        findSlope();
    }

    public void graphic(Graphics g){
        Graphics2D asteroids = (Graphics2D)g;
        asteroids.setColor(color);
        move();
        asteroids.fillRect((int)Math.round(x),(int)Math.round(y),(int)Math.round(size),(int)Math.round(size));

    }

    public double getSize() {
        return size;
    }

    public void move(){
        x+=deltax;
        y+=deltay;

    }
    public void setDestination(){
        x2=(int)(Math.random()*GameEngine.DEFAULT_WINDOWSIZEX);
        y2=(int)(Math.random()*GameEngine.DEFAULT_WINDOWSIZEY);
        findSlope();
    }
    public void findSlope(){
        double slope = (y-y2)/(x-x2);
        deltax=x2<x?-1.0:1.0;
        deltay=deltax*slope;
        double distance = Math.sqrt(Math.pow(deltax,2)+Math.pow(deltay,2));
        deltax=(deltax/distance)*(Math.random()*4);
        deltay=(deltay/distance)*(Math.random()*4);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isalive(){
        if(Math.abs((life/100)-(System.currentTimeMillis()/100))>lifetime){
            return false;
        }   else {
            return true;
        }
    }

}
