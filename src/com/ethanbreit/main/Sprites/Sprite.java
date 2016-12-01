package com.ethanbreit.main.Sprites;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by garthbreit on 2016-02-06.
 */
public class Sprite {

    public static Sprite player1 = new Sprite("/res/ethanbreit/sprites/player/player1.png");
    public static Sprite player2 = new Sprite("/res/ethanbreit/sprites/player/player2.png");
    public static Sprite player3 = new Sprite("/res/ethanbreit/sprites/player/player3.png");
    public static Sprite player4 = new Sprite("/res/ethanbreit/sprites/player/player4.png");
    public static Sprite player5 = new Sprite("/res/ethanbreit/sprites/player/player5.png");
    public static Sprite player6 = new Sprite("/res/ethanbreit/sprites/player/player6.png");
    public static Sprite player7 = new Sprite("/res/ethanbreit/sprites/player/player7.png");
    public static Sprite player8 = new Sprite("/res/ethanbreit/sprites/player/player8.png");
    public static Sprite playerDead = new Sprite("/res/ethanbreit/sprites/player/Nuclear-Explosion-64x64.jpg");

    public static Sprite enemybasic = new Sprite("/res/ethanbreit/sprites/enemy/enemybasic.png");

    public static Sprite normalproj1 = new Sprite("/res/ethanbreit/projectiles/normal/normal1.png");
    public static Sprite normalproj2 = new Sprite("/res/ethanbreit/projectiles/normal/normal2.png");
    public static Sprite normalproj3 = new Sprite("/res/ethanbreit/projectiles/normal/normal3.png");
    public static Sprite normalproj4 = new Sprite("/res/ethanbreit/projectiles/normal/normal4.png");

    public static Sprite GameOver = new Sprite("/res/ethanbreit/backgrounds/Untitled-1.png");
    public static Sprite progress = new Sprite("/res/ethanbreit/backgrounds/progress.png");
    public static Sprite BEGIN = new Sprite("/res/ethanbreit/backgrounds/begin.png", null);
    public static Sprite TitleCard = new Sprite("/res/ethanbreit/backgrounds/title.png");

    public static Sprite HUD1 = new Sprite("/res/ethanbreit/HUD/1.png");
    public static Sprite HUD2 = new Sprite("/res/ethanbreit/HUD/2.png");
    public static Sprite HUD3 = new Sprite("/res/ethanbreit/HUD/3.png");
    public static Sprite HUD4 = new Sprite("/res/ethanbreit/HUD/4.png");
    public static Sprite HUD5 = new Sprite("/res/ethanbreit/HUD/5.png");
    public static Sprite HUD6 = new Sprite("/res/ethanbreit/HUD/6.png");



    protected BufferedImage img;
    public int[] imgPix;

    public BufferedImage getImage(){return img;}

    protected Sprite(String url){
        try {
            img = ImageIO.read(getClass().getResource(url));
        }catch(Exception e){e.printStackTrace();}

        BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = copy.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        imgPix = ((DataBufferInt)copy.getRaster().getDataBuffer()).getData();

    }
    protected Sprite(String url, Object raw){
        try {
            img = ImageIO.read(getClass().getResource(url));
        }catch(Exception e){e.printStackTrace();}

        BufferedImage copy = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = copy.createGraphics();
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();
        imgPix = ((DataBufferInt)copy.getRaster().getDataBuffer()).getData();
    }


}
