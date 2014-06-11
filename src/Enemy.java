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
    Player dino=null;
    boolean alive =false;

    public Enemy(Grid world,Player dino, String location,int x, int y) {
        this.x=x;
        this.y=y;
        this.world=world;
        this.dino=dino;
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
        //removes old position from grid
        removeFromWorld();
        //applies movement
        x+=deltaX;
        y+=deltaY;
        x-=World.speed;
        //re-adds it to the grid
        addToWorld();
        //checks if it's offscreen
        if(y>(GameEngine.DEFAULT_WINDOWSIZEY)/10+10||x<20)
            alive=false;
        //checks if it hit the dinosaur
        dino.hit();
    }
    public void draw(Graphics g){
        g.drawImage(img,x*10-200,y*10-200,null);
    }

    public boolean isAlive(){
        return alive;
    }
}
