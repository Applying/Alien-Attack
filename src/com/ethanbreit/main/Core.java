package com.ethanbreit.main;


import com.ethanbreit.main.Listiner.KeyListen;
import com.ethanbreit.main.VFX.ScreenOverlay;
import com.ethanbreit.main.level.Level;
import com.ethanbreit.main.level.Level1;
import com.ethanbreit.main.level.NextLevel;
import com.ethanbreit.main.level.Title;
import com.ethanbreit.main.player.HUD;
import com.ethanbreit.main.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Core extends Canvas implements Runnable{
    public static int width = 500;
    public static int height = 375;
    private static JFrame frame;
    private Graphics2D graphics2D;

    private static long totalUp = 0;



    private KeyListen thing = new KeyListen();
    public static Player play;


    public static Level lev;
    private boolean running = true;
    private BufferedImage img;
    private int[] pixels;
    private Exit exit;
    private KeyListen key;


    Core(){
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
        play = new Player();
        key = new KeyListen();
        lev = new Level();
        exit = new Exit();
        Title.start();

    }


    public static void main(String[] args) {
        Core core = new Core();
        frame = new JFrame();

        GraphicsDevice gd =
                GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (gd.isFullScreenSupported()) {
            frame.setUndecorated(true);
            gd.setFullScreenWindow(frame);
        } else {
            System.err.println("Full screen not supported");

        }
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
            public void windowLostFocus(WindowEvent e) {
                frame.requestFocus();
            }

        });
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setBackground(Color.cyan);

        frame.add(core);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(core.key);

        Thread thread = new Thread(core);
        thread.start();


    }

    private void init(){
        Screen screen = new Screen();
        screen.init();
        NextLevel.init();
        System.gc();
    }

    private void update(){
        thing.update();
        play.update();
        boolean isEscHeld = false;
        double x = 0,y = 0;
        Integer[] keys = key.update();
        for(int i = 0; i<keys.length; i++){
            if(keys[i].equals(KeyEvent.VK_W))y+=-0.5;
            if(keys[i].equals(KeyEvent.VK_S))y+=0.5;

            if(keys[i].equals(KeyEvent.VK_A))x+=-0.5;
            if(keys[i].equals(KeyEvent.VK_D))x+=0.5;

            if(keys[i].equals(KeyEvent.VK_ESCAPE))isEscHeld = true;


            if(keys[i].equals(KeyEvent.VK_SPACE))play.shoot();


        }
        exit.update(isEscHeld);

        lev.update();
        HUD.update();
        //System.out.print(play.getXY());
        play.move(x,y);
    }

    public void run(){
        long oldTimeMili = System.currentTimeMillis();
        long oldTimeNano = System.nanoTime();

        long dif = 1000000000/60;
        int updates = 0;
        long secs = 0;

        init();

        Thread thread = new Thread(){public void run(){while (running){render();}}}; thread.start();
        while(running){

            if(oldTimeMili+1000<=System.currentTimeMillis()){
                oldTimeMili = System.currentTimeMillis();
                System.out.println(updates+", "+dif);
                secs++;


                if(totalUp!=secs*60){
                    dif = 1000000000/(120-updates);
                }else{
                    totalUp = 0;
                    secs = 0;
                    dif = 1000000000/60;
                }



                updates = 0;
            }if(oldTimeNano+dif<=System.nanoTime()){

                oldTimeNano = System.nanoTime();
                updates++;
                totalUp++;
                update();
            }
        }
    }

    public static long getTotalUp() {
        return totalUp;
    }


    //JAVA IS ABGR

    protected void render() {

        if (getBufferStrategy() == null) {

            createBufferStrategy(3);
            return;
        }
        //pre
        Screen.preRender();
        //norm
        lev.Render();
        play.render();
        //post
        HUD.Render();
        lev.HighLevRender();

        //final
        int[] thing = Screen.render(this);
        System.arraycopy(thing,0, pixels, 0, width*height);


        graphics2D = (Graphics2D) getBufferStrategy().getDrawGraphics();

        graphics2D.setColor(Color.BLUE);
        graphics2D.fillRect(0,0, (int)getSize().getWidth(),(int)getSize().getHeight());

        graphics2D.drawImage(img, 0, 0, (int)getSize().getWidth(),(int)getSize().getHeight(), null);

        if(ScreenOverlay.isShouldRender()){
            if(ScreenOverlay.getMode()==ScreenOverlay.MODE_RENDER_COLOUR){
                graphics2D.setColor(ScreenOverlay.getColour());
                graphics2D.fillRect(0,0, (int)getSize().getWidth(),(int)getSize().getHeight());
            }else if(ScreenOverlay.getMode()==ScreenOverlay.MODE_RENDER_IMAGE){
                //need to improve this feature

                graphics2D.drawImage(ScreenOverlay.getBufferedImage(), 0, 0, (int)getSize().getWidth(),(int)getSize().getHeight(), null);

            }
        }

        getBufferStrategy().show();
        graphics2D.dispose();
        graphics2D.finalize();
        graphics2D = null;
    }




}
