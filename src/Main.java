
public class Main {

    static GameEngine game;

    public static void main(String[] args) {
        game = new GameEngine();//Creates a JFrame using GameEngine
        final Game run = new Game();//Creates the Game
        game.add(run);//adds the Game to the JFrame

        //*********** JFRAME SPECIFICS **************
        game.setVisible(true); //makes the JFrame visible

        run.start();//starts the Game

        Runnable r1 = new Runnable() {
            public void run() { //Sets an operation for a thread to do, currently used for the actual game application
                try {
                    while (true) {
                        run.repaint();//repaint the Game
                        run.update();//update the Game
                        Thread.sleep(35); //sleeps the thread, makes it wait, 35 milliseconds
                    }
                } catch (InterruptedException iex) {}
            }
        };
        Runnable r2 = new Runnable() {
            public void run() { //Sets an operation for a thread to do, currently unused.
                try {
                    while (true) {
                        Thread.sleep(0);
                    }
                } catch (InterruptedException iex) {}
            }
        };
        Thread thr1 = new Thread(r1); //Creates a thread with runnable r1
        Thread thr2 = new Thread(r2); //Creates a thread with runnable r2 (does nothing)
        thr1.start(); //starts the first thread
        thr2.start(); //starts the 2nd thread
    }
}
