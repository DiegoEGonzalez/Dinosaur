import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class GameEngine extends JFrame {
    public static int DEFAULT_WINDOWSIZEY=600;
    public static int DEFAULT_WINDOWSIZEX=1000;

    private static double windowSize=800;
    private static double zoomSize=1.0;
    private static double to=1.0;
    private static double offsetX=0.0;
    private static double offsetY=0.0;
    private static double targetX=0.0;
    private static double targetY=0.0;

    public GameEngine(){
        setTitle("Dino Dash");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(GameEngine.DEFAULT_WINDOWSIZEX+400, GameEngine.DEFAULT_WINDOWSIZEY+100);
        //setResizable(false);
        setLocationRelativeTo(null);
    }


    public int random(int x){
        Random movement = new Random();
        int y = (movement.nextInt(x));
        return (y);
    }

    public void move(ArrayList<Double> position, ArrayList<Double> destination, int number, int size, double speed){
        if(position.get(number)<destination.get(number)-speed){
            position.set(number, (position.get(number) + speed));
        } else if (position.get(number)>destination.get(number)+speed){
            position.set(number, (position.get(number)-speed));
        } else {
            destination.set(number, (double)random(size));
        }
    }
    public void move(ArrayList<Double> a, ArrayList<Double> b,int number){
        a.set(number,a.get(number)+b.get(number));

    }

    public static boolean collision(int x, int y, int h, int w, int x2, int y2, int h2, int w2){


        Shape Main = new Rectangle2D.Float(x,y,w,h);
        Shape Enemy = new Rectangle2D.Float(x2,y2,w2,h2);

        Area mainArea = new Area(Main);
        mainArea.intersect(new Area(Enemy));

        return !mainArea.isEmpty();




        /**
        int ax1=x;
        int ax2=x+w;
        int ay1=y;
        int ay2=y+h;
        int bx1=x2;
        int bx2=x2+w2;
        int by1=y2;
        int by2=y2+h2;

        if((ax1<bx2&&ax1>bx1||ax2<bx2&&ax2>bx1)&&(ay1<by2&&ay1>by1||ay2<by2&&ay2>by1)){
           return false;
        }
        return true;


        **/
    }

    public static double getOffsetX() {
        return offsetX;
    }
    public static double getOffsetY() {
        return offsetY;
    }

    public static void setOffsetX(double x) {
        GameEngine.offsetX = 200-10*zoomSize-x;
    }
    public static void setOffsetY(double y) {
        GameEngine.offsetY = 200-10*zoomSize-y;
    }


    public static void update(double x, double y){
        if(zoomSize<to)
            zoomSize+=.1;
        setOffsetY(y);
        setOffsetX(x);
    }


}
