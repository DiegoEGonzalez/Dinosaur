import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Enemy{
    int x;
    int y;
    int h;
    int w;
    int deltaX=0;
    int deltaY=0;
    BufferedImage img = null;
    Grid world=null;
    boolean alive =false;

    public Enemy(Grid world, String location,int x, int y) {
        this.x=x;
        this.y=y;
        this.world=world;
        try{
            //Declares the img as the one in location
            img = ImageIO.read(new File(location));
            h=img.getHeight()/10;
            w=img.getWidth()/10;
        }catch (IOException e){
            e.printStackTrace();
        }
        world.enems.add(this);
        addToWorld();
        alive=true;

    }

    public void addToWorld(){
        world.getFore()[y][x]=2;
        for(int y=this.y;y<=this.y+h;y++){
            for(int x=this.x;x<this.x+w;x++){
                    world.getFore()[y][x]=world.enems.indexOf(this)+1;
            }
        }
    }

    public void removeFromWorld(){
        for(int y=this.y;y<=this.y+h;y++){
            for(int x=this.x;x<this.x+w;x++){
                world.getFore()[y][x]=0;
            }
        }
    }

    public void remove(){
        removeFromWorld();
        world.enems.remove(world.enems.indexOf(this));
    }
    public void update(){
        removeFromWorld();
        x+=deltaX;
        y+=deltaY;
        if(x>0)
        x-=World.speed;
        addToWorld();
        if(y>(GameEngine.DEFAULT_WINDOWSIZEY)/10+10)
            alive=false;
    }

    public void draw(Graphics g){
        g.drawImage(img,x*10-200,y*10-200,null);
        g.setColor(Color.GREEN);
        //g.drawRect(x*10-200,y*10-200,w*10,h*10);
    }

    public boolean isAlive(){
        return alive;
    }
}
