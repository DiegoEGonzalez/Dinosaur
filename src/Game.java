import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Game extends JPanel{

    //boolean variables to represent keyboard keys, in order to know if they're still pressed down or not.
    private boolean A = false;
    private boolean D = false;
    private boolean W = false;
    private boolean S = false;

    //declaring the main elements !!! IMPORTANT !!!
    test dino;
    Stage1 floor;
    Grid world;

    //variables for the menu
    int x=420;
    int dx=-2;


    public Game(){

        world=new Grid();//creates a Grid named world
        floor=new Stage1(world);//creates a World named floor giving it the grid
        dino = new test(world);//creates a Dinosaur named dino giving it the grid


        //********** JPANEL SPECIFICS ***********
        setLayout(null);//designates that the JPanel has no layout and allows us to align elements within it using (x,y)
        setSize(GameEngine.DEFAULT_WINDOWSIZEX, GameEngine.DEFAULT_WINDOWSIZEY);//set's the size of our JPanel
        setBackground(new Color(200,10,0));//sets the background color to...
        setDoubleBuffered(true);//supposed to draw the background as an image and then to the screen in order to improve results, doesn't really
        setVisible(true);// makes the JPanel visible

        //********** KEY EVENTS ******************

        //--------------- A ---------------------
        Action left = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Left.");
                dino.move(-1);
                A = true;

            }
        };
        getInputMap().put(KeyStroke.getKeyStroke("A"),
                "left");
        getActionMap().put("left",
                left);

        Action leftReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                A = false;
                if (!D)
                    dino.move(0);
            }
        };
        getInputMap().put(KeyStroke.getKeyStroke("released A"),
                "leftReleased");
        getActionMap().put("leftReleased",
                leftReleased);

        // ---------------- D ---------------------
        Action right = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                D = true;
                System.out.println("Right.");
                dino.move(1);
            }
        };

        getInputMap().put(KeyStroke.getKeyStroke("D"),
                "right");
        getActionMap().put("right",
                right);
        Action rightReleased = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                D = false;
                if (!A)
                    dino.move(0);

            }
        };
        getInputMap().put(KeyStroke.getKeyStroke("released D"),
                "rightReleased");
        getActionMap().put("rightReleased",
                rightReleased);

        // --------------- W -------------------

        Action up = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                dino.jump();
            }
        };
        getInputMap().put(KeyStroke.getKeyStroke("W"),
                "up");
        getActionMap().put("up",
                up);

    }

    public void update(){
        //updates important elements ( called in Main )
        floor.update();
        dino.update();
    }
    public void start(){

    }
    public void gameover(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);//clears the display
        drawGame(g);
    }

    public void drawMenu(Graphics g){

        g.setColor(Color.WHITE);
        g.drawString("Dino Apocalypse", 450, 200);
        if(x<400)
            dx=2;
        if(x>440)
            dx=-2;
        x+=dx;


        g.drawString("PRESS SPACE TO START !!! ",x,300);
        g.drawString("by: Diego Gonzalez, Mike Roome, Ben Sentiff and Christian Illes",300,550);
    }
    public void drawGame(Graphics g){
        floor.draw(g); //draws the world

        // Make seeGrid true in order to see the grid in the game.
        boolean seeGrid = false;
        if(seeGrid){
            g.setColor(Color.LIGHT_GRAY);
            for(int y=0;y<GameEngine.DEFAULT_WINDOWSIZEY/10;y+=1){
                for(int x=20;x<(GameEngine.DEFAULT_WINDOWSIZEX/10+20);x+=1){
                    if(world.getFore()[y][x]==0)
                        g.setColor(Color.GREEN);// green if there's nothing in the background layer
                    else
                        g.setColor(Color.BLUE);// red if there's something there
                    g.drawRect((x-20)*10,y*10,10,10);// draws the grid square
                }
            }
        }

        dino.draw(g);//draws the player

        g.setColor(Color.WHITE);
        g.drawString("DINO RUNNER GAME DEVELOPMENT VERSION",20,20);
        g.drawString("DINOSAUR STATUS: "+dino.isAlive(),700,20); // prints true if alive, otherwise false
        g.drawString("by: Diego Gonzalez, Mike Roome, Christian Illes & Ben Sentiff",20,40); // the crew. ;)
        g.dispose();
    }
}
