package com.ethanbreit.main;
import com.ethanbreit.main.Sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.nio.Buffer;
import java.util.Arrays;

/**
 * Created by garthbreit on 2016-02-04.
 */
public class Screen {

    private static final int WIDTH = Core.width;
    private static final int HEIGHT = Core.height;

    public static int[] backgroundPix;
    public static int[] boundingPix = new int[WIDTH*HEIGHT];
    private static int[] finalPix = new int[WIDTH*HEIGHT];



    private int[] readBackground() {
        BufferedImage img;
        try {
            img = ImageIO.read(getClass().getResource("/res/ethanbreit/backgrounds/backgroundlev1_variation.png"));
            BufferedImage copy = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = copy.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, copy.getWidth(), copy.getHeight());
            g2d.drawImage(img, 0, 0, null);
            g2d.dispose();
            return ((DataBufferInt)copy.getRaster().getDataBuffer()).getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void init(){
        backgroundPix  = readBackground();
        System.arraycopy(backgroundPix, 0, finalPix, 0, WIDTH*HEIGHT);
    }
    public static void preRender(){
        for(int i = 0; i<finalPix.length; i++){
            finalPix[i] = backgroundPix[i];
        }

        for(int i = 0; i<boundingPix.length; i++){
            boundingPix[i] = 0xff00ff;
        }
    }

    public static int[] render(Core cl){



        return finalPix;
    }

    public static void Render64(Dimension dim, Sprite sprite, int id){
        int x = (int)dim.getWidth();
        int y = (int)dim.getHeight();
        for(int height = 0; height<64; height++){
            for(int width = 0; width<64; width++){
                if (!(sprite.imgPix[width + height * 64] == 0xff00ff)) {
                    try {
                        if ((x + width) + (y + height) * WIDTH > finalPix.length) {
                            finalPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 64];
                            boundingPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = id;
                        } else if ((x + width) + (y + height) * WIDTH < 0) {
                            finalPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 64];
                            boundingPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = id;
                        } else {
                            finalPix[(x + width) + (y + height) * WIDTH] = sprite.imgPix[width + height * 64];
                            boundingPix[(x + width) + (y + height) * WIDTH] = id;
                        }
                    }catch(Exception e){

                    }
                }
            }
        }
    }





    public static void HUDRender(Dimension dim, Sprite sprite){
        //int x = (int)dim.getWidth();
        //int y = (int)dim.getHeight();
        for(int height = 0; height<Core.height; height++){
            for(int width = 0; width<Core.width; width++){
                if (!(sprite.imgPix[width + height * Core.width] == 0xff00ff)) {
                    try {
                            finalPix[(width) + (height) * Core.width] = sprite.imgPix[width + height * Core.width];
                    }catch(Exception e){

                    }
                }
            }
        }

    }

    public static void RenderParticle(Dimension dim, int color){
        int x = (int)dim.getWidth();
        int y = (int)dim.getHeight();
        try {
            if (x + y * WIDTH > finalPix.length) {
                finalPix[x + (y - HEIGHT) * WIDTH] = color;
            } else if (x + y* WIDTH < 0) {
                finalPix[x + (y + HEIGHT) * WIDTH] = color;
            } else {
                finalPix[x+y*WIDTH] = color;

            }
        }catch(Exception e){

        }
   }

    public static void RenderPlayer64(Dimension dim, Sprite sprite){
        int x = (int)dim.getWidth();
        int y = (int)dim.getHeight();
        for(int height = 0; height<64; height++){
            for(int width = 0; width<64; width++){
                if (!(sprite.imgPix[width + height * 64] == 0xff00ff)) {
                    try {
                        if ((x + width) + (y + height) * WIDTH > finalPix.length) {
                            finalPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 64];
                            boundingPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = 0xffffff;
                        } else if ((x + width) + (y + height) * WIDTH < 0) {
                            finalPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 64];
                            boundingPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = 0xffffff;
                        } else {
                            finalPix[(x + width) + (y + height) * WIDTH] = sprite.imgPix[width + height * 64];
                            boundingPix[(x + width) + (y + height) * WIDTH] = 0xffffff;

                        }
                    }catch(Exception e){

                    }
                }
            }
        }
    }



    public static void Render32(Dimension dim, Sprite sprite){
        int x = (int)dim.getWidth();
        int y = (int)dim.getHeight();
        for(int height = 0; height<32; height++){
            for(int width = 0; width<32; width++){
                if (!(sprite.imgPix[width + height * 32] == 0xff00ff)) {
                    try {
                        if ((x + width) + (y + height) * WIDTH > finalPix.length) {
                            finalPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 32];
                            boundingPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = 0x000000;

                        } else if ((x + width) + (y + height) * WIDTH < 0) {
                            finalPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 32];
                            boundingPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = 0x000000;
                        } else {
                            finalPix[(x + width) + (y + height) * WIDTH] = sprite.imgPix[width + height * 32];
                            boundingPix[(x + width) + (y + height) * WIDTH] = 0x000000;
                        }
                    }catch(Exception e){

                    }
                }
            }
        }
    }

    public static void Render16(Dimension dim, Sprite sprite, int id){
        int x = (int)dim.getWidth();
        int y = (int)dim.getHeight();
        for(int height = 0; height<16; height++){
            for(int width = 0; width<16; width++){
                if (!(sprite.imgPix[width + height * 16] == 0xff00ff)) {
                    try {
                        if ((x + width) + (y + height) * WIDTH > finalPix.length) {
                            finalPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 16];
                            boundingPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = id;
                        } else if ((x + width) + (y + height) * WIDTH < 0) {
                            finalPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 16];
                            boundingPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = id;
                        } else {
                            finalPix[(x + width) + (y + height) * WIDTH] = sprite.imgPix[width + height * 16];
                            boundingPix[(x + width) + (y + height) * WIDTH] = id;
                        }
                    }catch(Exception e){

                    }
                }
            }
        }
    }

    public static void RenderProj32(Dimension dim, Sprite sprite){
        int x = (int)dim.getWidth();
        int y = (int)dim.getHeight();
        for(int height = 0; height<32; height++){
            for(int width = 0; width<32; width++){
                if (!(sprite.imgPix[width + height * 32] == 0xff00ff)) {
                    try {
                        if ((x + width) + (y + height) * WIDTH > finalPix.length) {
                            finalPix[(x + width) + (height + (y - HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 32];

                        } else if ((x + width) + (y + height) * WIDTH < 0) {
                            finalPix[(x + width) + (height + (y + HEIGHT)) * WIDTH] = sprite.imgPix[width + height * 32];
                        } else {
                            finalPix[(x + width) + (y + height) * WIDTH] = sprite.imgPix[width + height * 32];
                        }
                    }catch(Exception e){

                    }
                }
            }
        }
    }

}
