
public class Main {
    static GameEngine game;
    public static void main(String[] args) {
        game = new GameEngine();
        final Game run = new Game();
        game.add(run);
        game.setVisible(true);
        run.start();

        Runnable r1 = new Runnable() {
            public void run() {
                try {
                    while (true) {
                        run.repaint();
                        run.update();
                        Thread.sleep(100);
                    }
                } catch (InterruptedException iex) {}
            }
        };
        Runnable r2 = new Runnable() {
            public void run() {
                try {
                    while (true) {
                        //run.update();
                        Thread.sleep(0);
                    }
                } catch (InterruptedException iex) {}
            }
        };
        Thread thr1 = new Thread(r1);
        Thread thr2 = new Thread(r2);
        thr1.start();
        thr2.start();




    }
}
