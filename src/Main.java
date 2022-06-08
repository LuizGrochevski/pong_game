

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable{
    
    private static final long serialVersionUID = 1L;
    public static int WIDTH = 200;
    public static int HEIGHT = 120;
    public static int SCALE = 3;

public BufferedImage layer = new BufferedImage(WIDTH*SCALE, HEIGHT*SCALE, BufferedImage.TYPE_INT_RGB);  

    public Player player;

    public Main() {
        this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));

        player = new Player();
    }

    public static void main(String[] args){
        Main game = new Main();
        JFrame frame = new JFrame("Pong");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.pack();
        frame.setVisible(true);

        new Thread().start();
    }

    public void tick(){
         
    }

    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = layer.getGraphics();
        player.render(g);


        g = bs.getDrawGraphics();
        g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);

        bs.show();
    }
    
    public void run() {
        while(true){
            tick();
            render();
            try{
                Thread.sleep(1000/60);
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

}
