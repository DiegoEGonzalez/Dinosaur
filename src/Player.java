import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Player {
    int x=0; // x-axis location
    int y=0; // y-axis location
    int h=0; // height
    int w=0; // width

    Grid world=null;

    int xDirection=0;
    boolean jump=false;
    int endJump;

    double speed=0;
    BufferedImage img = null;
    boolean alive = false;

    boolean up=false;
    boolean down=false;
    boolean right=false;
    boolean left=false;

    public Player(Grid world, String location, int x, int y){
        this.world=world;
        try{
            //Declares the img as the one in location
            img = ImageIO.read(new File(location));
            h=img.getHeight()/10;
            w=img.getWidth()/10;
        }catch (IOException e){
            e.printStackTrace();
        }
        this.x=x+20;
        this.y=y;
        alive = true;
    }

    public void update(){
        for(int times=0;times<4;times++){
        up=true;
        for(int x=this.x;x<this.x+w;x++){
            if(world.getBack()[y-1][x]!=0)
            up=false;
        }
        if(up&&jump&&y>endJump)
            y-=1;
        else
            jump=false;
        }

        ///// DOWN /////
        down=true;
        if(world.getBack().length<=y+h)
            down=false;
        if(down){
        for(int x=this.x;x<this.x+w;x++){
            if(world.getBack()[y+h][x]!=0)
            down=false;
        }
        }
        if(down)
            y+=1;

        for(int a=0;a<speed;a++){
        left=true;
        if(!(x>0))
        left=false;
        else
        for(int y=this.y;y<this.y+h;y++){
            if(world.getBack()[y][x-1]!=0)
            left=false;
        }

        if(left&&xDirection==-1)
            x-=1;



        right=true;
        if(!(x+w<(world.getBack().length+50)))
        right=false;
        else
        for(int y=this.y;y<this.y+h;y++){
            if(world.getBack()[y][x+w]!=0)
            right=false;
        }
        if(right&&xDirection==1)
            x+=1+World.speed;
        }
        if(left)
            x-=World.speed;

    }
    public void move(int xDirection){
        this.xDirection=xDirection;
    }

    public void jump(){
        if(world.getBack()[y+h][x]!=0){
        endJump=y-10;
        jump=true;
        }
    }
    public void draw(Graphics g){
        g.drawImage(img,x*10,y*10,null);
        g.setColor(Color.RED);
        g.drawRect(x*10,y*10,w*10,h*10);

        g.setColor(Color.BLACK);
        for(int x=this.x;x<this.x+w;x++){
            g.drawRect(x*10,(y-1)*10,10,10);
        }
        for(int x=this.x;x<this.x+w;x++){
            g.drawRect(x*10,(y+h)*10,10,10);
        }
        for(int y=this.y;y<this.y+h;y++){
            g.drawRect((x-1)*10,y*10,10,10);
        }
        for(int y=this.y;y<this.y+h;y++){
            g.drawRect((x+w)*10,y*10,10,10);
        }

    }
    public boolean isAlive(){
        return alive;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
