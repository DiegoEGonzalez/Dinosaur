import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Terrain{
  boolean collidable=true;
  int x;
  int y;
  int h;
  int w;
  BufferedImage img = null;
  Grid world=null;
  int id=0;

  public Terrain(Grid world, String location,int x, int y) {
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
      world.objs.add(this);
      addToWorld();

  }

  public void addToWorld(){
      world.getBack()[y][x]=collidable?id:id*-1;
      for(int y=this.y;y<=this.y+h;y++){
          for(int x=this.x;x<this.x+w;x++){
              if(collidable)
                  world.getBack()[y][x]=id; //if it's a positive index then it's collidable
              else
                  world.getBack()[y][x]=id*-1; //if it's a negative index # then it's not collidable
          }
      }

      world.getBackobjs()[y][x]=this;
      for(int y=this.y;y<=this.y+h;y++){
          for(int x=this.x;x<this.x+w;x++){
              if(collidable)
                  world.getBackobjs()[y][x]=this; //if it's a positive index then it's collidable
              else
                  world.getBackobjs()[y][x]=this; //if it's a negative index # then it's not collidable
          }
      }
  }

  public void removeFromWorld(){
      for(int y=this.y;y<=this.y+h;y++){
          for(int x=this.x;x<this.x+w;x++){
              world.getBack()[y][x]=0;
          }
      }
      for(int y=this.y;y<=this.y+h;y++){
          for(int x=this.x;x<this.x+w;x++){
              world.getBackobjs()[y][x]=null;
          }
      }
  }

  public void remove(){
      removeFromWorld();
      world.objs.remove(world.objs.indexOf(this));
  }
  public void update(){
      removeFromWorld();
        x-=World.speed;
      addToWorld();
  }

  public void draw(Graphics g){
      g.drawImage(img,(x-20)*10,(y-20)*10,null);
      g.setColor(Color.RED);
      //g.drawRect((x-20)*10,y*10,w*10,h*10);
  }
}
