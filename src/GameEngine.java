import javax.swing.*;

public class GameEngine extends JFrame {
    public static int DEFAULT_WINDOWSIZEY=600; //width of the game *** DO NOT CHANGE *** NO! NO! NO! ( I WILL FREAK OUT SO F**CKING BAD! )
    public static int DEFAULT_WINDOWSIZEX=1000; // height of the game *** DO NOT CHANGE or read above ***

    public GameEngine(){
        //********* JFRAME SPECIFICS ******************
        setTitle("Dino Dash");//adds a title to the top of the JFRAME
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//determines that if the user presses the red x, the program will end.
        setSize(GameEngine.DEFAULT_WINDOWSIZEX, GameEngine.DEFAULT_WINDOWSIZEY);//sets the size of the JFRAME
        setResizable(false); //prevents user from resizing window
        setLocationRelativeTo(null);//centers the JFRAME to the middle of the screen
    }

}
