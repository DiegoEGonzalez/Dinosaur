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

    private boolean A = false;
    private boolean D = false;
    private boolean W = false;
    private boolean S = false;

    test dino;
    Stage1 floor;
    Grid space;
    public Game(){

        space=new Grid();
        floor=new Stage1(space);
        dino = new test(space);


        setLayout(null);
        setSize(GameEngine.DEFAULT_WINDOWSIZEX, GameEngine.DEFAULT_WINDOWSIZEY);
        setBackground(Color.BLUE);
        setDoubleBuffered(true);
        setVisible(true);

        /////////////////// Key Events ////////////////////////////////////////////////////////
        // ******** A *********
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

        // ********* D **********
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

        // ********* W ************

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
        floor.update();
        dino.update();
    }
    public void start(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        floor.draw(g);
        g.setColor(Color.LIGHT_GRAY);
        for(int y=0;y<GameEngine.DEFAULT_WINDOWSIZEY/10;y+=1){
            for(int x=0;x<GameEngine.DEFAULT_WINDOWSIZEX/10+50;x+=1){
                if(space.getBack()[y][x]==0)
                    g.setColor(Color.GREEN);
                else
                    g.setColor(Color.RED);
                g.drawRect(x*10,y*10,10,10);
            }
        }
        dino.draw(g);

        g.setColor(Color.BLACK);
        g.drawRect(200,0,GameEngine.DEFAULT_WINDOWSIZEX,GameEngine.DEFAULT_WINDOWSIZEY);

        g.setColor(Color.WHITE);
        g.drawString("DINO RUNNER GAME DEVELOPMENT VERSION",220,620);
        g.drawString("by: Diego Gonzalez, Mike Roome, Christian Illes & Ben Sentiff",220,640);
    }
}
