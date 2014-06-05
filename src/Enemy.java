import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

public abstract class Enemy {
    double x;
    double y;
    int h;
    int w;


    double speed;
    BufferedImage img = null;
    double scale = 1;

    public Enemy(String location){
        try{
            img = ImageIO.read(new File(location));
            h=img.getHeight();
            w=img.getWidth();
        } catch( Exception e){

        }

    }

    public void update(){

    }

    public void draw(Graphics g){
        BufferedImage shown = img;
        AffineTransform alter = new AffineTransform();
        alter.scale(scale,scale);
        //alter.rotate(angle,w/2,h/2);

        //AffineTransformOp op = new AffineTransformOp(alter,AffineTransformOp.TYPE_BILINEAR);
        //shown = op.filter(img,null);
        Graphics2D hi = (Graphics2D)g;
        hi.setTransform(alter);
        hi.drawImage(shown,(int)Math.round(x/scale),(int)Math.round(y),null);
    }

}
