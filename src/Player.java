import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Player {
    final boolean displayHitBox=false;

    int x=0; // x-axis location
    int y=0; // y-axis location
    int h=0; // height
    int w=0; // width

    int xDirection=0;
    boolean jump=false;
    int endJump;
    double speed=0;

    Grid world=null;
    BufferedImage img = null;
    boolean alive = false;

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
        this.y=y+20;
        alive = true;
    }
    public boolean up(){ //returns true if nothing above and there's an available space
        boolean up=true;
        for(int x=this.x;x<this.x+w;x++){
            if(world.getBack()[y-1][x]>0)
                up=false;
        }
        return up;
    }
    public boolean down(){
        boolean down=true;
        if(world.getBack().length<=y+h+1)
            down=false;
        if(down){
            for(int x=this.x;x<this.x+w;x++){
                if(world.getBack()[y+h][x]>0)
                    down=false;
            }
        }
        return down;
    } //returns true if nothing below and there's an available space
    public boolean left(){
        boolean left=true;
        if(!(x>0))
            left=false;
        else
            for(int y=this.y;y<this.y+h;y++){
                if(world.getBack()[y][x-1]>0)
                    left=false;

            }
        return left;
    } //returns true if nothing to the left and there's an available space
    public boolean right(){
        boolean right=true;
        if(!(x+w<(world.getBack().length+50)))
            right=false;
        else
            for(int y=this.y;y<this.y+h;y++){
                if(world.getBack()[y][x+w]>0)
                    right=false;

            }
        return right;
    } //returns true if nothing to the right and there's an available space

    public void hit(){
        //collision testing for body
        for(int y=this.y+3;y<this.y+h-3;y++){
            for(int x=this.x+2;x<this.x+w-2;x++){
                if(world.getFore()[y][x]!=0)
                    alive=false;

            }
        }
        //collision testing for left leg
        for(int y=this.y+h-3;y<this.y+h;y++){
            for(int x=this.x+2;x<this.x+3;x++){
                if(world.getFore()[y][x]!=0)
                    alive=false;
            }
        }
        //collision testing for right leg
        for(int y=this.y+h-3;y<this.y+h;y++){
            for(int x=this.x+7;x<this.x+8;x++){
                if(world.getFore()[y][x]!=0)
                    alive=false;

            }
        }
        //collision testing for head
        for(int y=this.y;y<this.y+h-5;y++){
            for(int x=this.x+7;x<this.x+w;x++){
                if(world.getFore()[y][x]!=0)
                    alive=false;

            }
        }
    }
    public void update(){
        if(alive){
        /////// UP ////////
        for(int times=0;times<4;times++){
        if(up()&&jump&&y>endJump)
            y-=1;
        else
            jump=false;
        }
        }
        ////// DOWN ///////
        for(int times=0;times<World.gravity;times++){
        if(down())
            y+=1;
        }


       if(alive){
        ////// LEFT ///////
        for(int a=0;a<speed;a++){
        if(left()&&xDirection==-1)
            x-=1;

        ////// RIGHT //////
        if(right()&&xDirection==1)
            x+=1+World.speed;

        }
           if(left())
               x-=World.speed;
    }



        //////////////////// CHECK IF IT'S ALIVE ////////////////
        if(x+w<20){
            alive=false;
        } else if(x>20+(GameEngine.DEFAULT_WINDOWSIZEX/10)){
           alive=false;
        } else if(y>20+(GameEngine.DEFAULT_WINDOWSIZEY/10)){
            alive=false;
        }

        hit();

    }
    public void move(int xDirection){
        this.xDirection=xDirection;
    }

    public void jump(){
        if(!down()){
        endJump=y-15;
        jump=true;
        }
    }
    public void draw(Graphics g){
        g.drawImage(img,(x-20)*10,(y-20)*10,null);


        if(displayHitBox){
        g.setColor(Color.RED);
        g.drawRect((x-20)*10,y*10,w*10,h*10);

        if(!up())
        g.setColor(Color.WHITE);
        else
        g.setColor(Color.BLACK);
        for(int x=this.x;x<this.x+w;x++){
            g.drawRect((x-20)*10,(y-1)*10,10,10);
        }
        if(!down())
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.BLACK);
        for(int x=this.x;x<this.x+w;x++){
            g.drawRect((x-20)*10,(y+h)*10,10,10);
        }
        if(!left())
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.BLACK);
        for(int y=this.y;y<this.y+h;y++){
            g.drawRect((x-21)*10,y*10,10,10);
        }
        if(!right())
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.BLACK);
        for(int y=this.y;y<this.y+h;y++){
            g.drawRect((x+w-20)*10,y*10,10,10);
        }
        }






    }
    public boolean isAlive(){
        return alive;
    }

    public void reset(){
        x=40;
        y=40;
        alive=true;
    }

}
