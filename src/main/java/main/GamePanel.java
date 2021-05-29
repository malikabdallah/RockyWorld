package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import gamestate.*;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    //dimensions
    public static final int WIDTH=320;
    public static final int HEIGHT=240;
    public static final int SCALE=2;

    //game
    private Thread thread;
    private boolean running;
    private int fps=60;
    private long targetTime=1000/fps;

    //image
    private BufferedImage image;
    private Graphics2D g;

    //manager
    private GameStateManager gsm;

    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        this.setFocusable(true);
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        gsm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gsm.keyReleased(e.getKeyCode());
    }

    private void init(){
        image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_BGR);
        g=(Graphics2D) image.getGraphics();
        running=true;
        gsm=new GameStateManager();

    }
    @Override
    public void run() {
            init();

            long start;
            long elapses;
            long wait;

            //gameloop
            while (running){
                start=System.nanoTime();
                update();
                draw();
                drawToScreen();
                elapses=System.nanoTime()-start;
                wait=targetTime-elapses/1000000;
                try {
                    Thread.sleep(wait);
                }catch (Exception e){

                }
            }
    }

    private void update(){
        gsm.update();

    }
    private void draw(){
        gsm.draw(g);

    }
    private void drawToScreen(){
        Graphics g2=getGraphics();
        g2.drawImage(image,0,0,WIDTH*SCALE,HEIGHT*SCALE,null);
        g2.dispose();
    }

    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread=new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }
}
