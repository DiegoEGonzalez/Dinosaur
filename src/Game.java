import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.io.*;

public class Game extends JPanel{

    //boolean variables to represent keyboard keys, in order to know if they're still pressed down or not.
    private boolean A = false;
    private boolean D = false;

    //boolean variable to represent the start button
    private boolean start = false;
    private boolean gameover = false;

    Font fscore = getFont("PressStart2P.ttf",25);
    Font fmenu = getFont("triple_dot_digital-7.ttf",70);
    Font fgameover = getFont("triple_dot_digital-7.ttf",70);
    Font fscoreover = getFont("PressStart2P.ttf",25);
    Font fmenu2 = getFont("PressStart2P.ttf",25);
    Font fmenu3 = getFont("PressStart2P.ttf",12);


    //declaring the main elements !!! IMPORTANT !!!
    test dino;
    Stage1 floor;
    Grid world;

    //variables for the menu
    int x=225;
    int dx=-2;
    
    //variables for score
    private long startTime;
    private String endTime;
    private int score;


    public Game(){
    	 world=new Grid();//creates a Grid named world
         dino = new test(world);//creates a Dinosaur named dino giving it the grid
         floor=new Stage1(world,dino);//creates a World named floor giving it the grid
 
 
         //********** JPANEL SPECIFICS ***********
         setLayout(null);//designates that the JPanel has no layout and allows us to align elements within it using (x,y)
         setSize(GameEngine.DEFAULT_WINDOWSIZEX, GameEngine.DEFAULT_WINDOWSIZEY);//set's the size of our JPanel
         setBackground(new Color(200,10,0));//sets the background color to...
         setDoubleBuffered(true);//supposed to draw the background as an image and then to the screen in order to improve results, doesn't really
         setVisible(true);}//makes the Jpanel visible
         
         //********** KEY EVENTS ******************
         
         //--------------- A ---------------------
         Action left = new AbstractAction() {
             public void actionPerformed(ActionEvent e) {
                 System.out.println("Left.");
                 dino.move(-1);
                 A = true;
 
             }
         };
         {
         getInputMap().put(KeyStroke.getKeyStroke("A"),"left");
         getActionMap().put("left", left);
 
         Action leftReleased = new AbstractAction() {
             public void actionPerformed(ActionEvent e) {
                 A = false;
                 if (!D) dino.move(0);
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
 
 
          // ---------- SPACE ---------------
         Action space = new AbstractAction() {
             public void actionPerformed(ActionEvent e) {
 
                 if(!start){
                     start=true;

                    startTime=System.currentTimeMillis();
                    start();
                 }else if(!gameover){
                     dino.jump();
                 } else {
                     reset();
                    startTime=System.currentTimeMillis();
                     gameover=false;
                 }
 
             }
         };
         getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                 "space");
         getActionMap().put("space",
                 space);
                
        // ---------- ESCAPE ----------     
        
        // ----------- ESCAPE ------------
         Action esc = new AbstractAction() {
             public void actionPerformed(ActionEvent e) {
                 start = false;
                 reset();
                gameover = false;
             }
         };
         getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"),
                 "ESC");
         getActionMap().put("ESC",
                 esc);

}
 
     public void update(){
         //updates important elements ( called in Main )
         if(start&&!gameover){
         dino.update();
         floor.update();
         if(!dino.isAlive()){
             gameover=true;
            endTime = ((((System.currentTimeMillis()-startTime)/1000) + "." + ((System.currentTimeMillis()-startTime)%1000)));
         }
         }
     }
     public void start(){

    	startTime=System.currentTimeMillis();
     }
     public void reset(){
    	startTime=System.currentTimeMillis();
         dino.reset();
         floor.choices=5;
         floor.bottom.clear();
         floor.meteors.clear();
         world.clear(world.getBack());
         world.clear(world.getFore());
         world.objs.clear();
         world.enems.clear();
         for(int x=0;x<=100;x+=10){
             floor.spawnTerrain();// spawns Terrain to fill the floor
         }
         floor.choices=14;
 
     }
     public void paintComponent(Graphics g){
         super.paintComponent(g);//clears the display

 
         if(!start)
         drawMenu(g);
         else
         drawGame(g);

         if(gameover)
             drawEnd(g);



     }
     public Font getFont(String name, int size){
         Font font = null;
         try{
         InputStream is = getClass().getResourceAsStream(name);
         font = Font.createFont(Font.TRUETYPE_FONT,is);
         font = font.deriveFont(Font.PLAIN,size);
         } catch (FontFormatException e){
         System.out.println("Font format exception");
         } catch (IOException e){
         System.out.println("IO Exception from font");
         }
         return font;
     }
        
        
        		
 
     public void drawMenu(Graphics g){

         g.setColor(Color.WHITE);
         g.drawString("v 1.0",900,550);
         g.setFont(fmenu);
         g.drawString("DINO", 325, 145);
         g.drawString("APOCALYPSE", 75, 295);
         if(x<200)
             dx=2;
         if(x>225)
             dx=-2;
         x+=dx;
         
         g.setFont(fmenu2);
         g.drawString("PRESS SPACE TO START !!! ",x,400);
         g.setFont(fmenu3);
         g.drawString("by: Diego Gonzalez, Mike Roome, Christian Illes and Ben Sentiff",110,550);
    	      }
    public void drawGame(Graphics g){
        floor.draw(g); //draws the world
                if(dino.isAlive())
        	         {
                        g.setColor(Color.WHITE);
                	    g.setFont(fscore);
        	         	g.drawString(((((System.currentTimeMillis()-startTime)/1000) + "." + ((System.currentTimeMillis()-startTime)%1000))),445,100);
        	         }

    }
    public void drawEnd(Graphics g){
        g.setFont(fgameover);
        g.setColor(Color.WHITE);
        g.drawString("YOU ARE",230,180);
        g.drawString("EXTINCT",230,300);
        g.setFont(fscoreover);
        g.drawString("SCORE:" + endTime + " sec",335,390);
        g.drawString("PRESS SPACE TO RESTART" ,250, 440);
    }

}
