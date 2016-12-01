package com.ethanbreit.main.player;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Screen;
import com.ethanbreit.main.Sprites.Sprite;
import com.ethanbreit.main.VFX.ReverseScreenFade;
import com.ethanbreit.main.VFX.ScreenFade;
import com.ethanbreit.main.VFX.ScreenOverlay;
import com.ethanbreit.main.Weapons.Weapon;
import com.ethanbreit.main.Weapons.normal;
import com.ethanbreit.main.level.Level;
import com.ethanbreit.main.level.NextLevel;
import com.ethanbreit.main.particles.EnemyExplosionPart;
import com.ethanbreit.main.particles.ShipParticle;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by garthbreit on 2016-02-05.
 */
public class Player {

    private static boolean waitingForRespawn = false;

    private static final int WIDTH = Core.width;
    private static final int HEIGHT = Core.height;
    public int health = 6;
    // I have an Idea

    private int x = (Core.width/2)-16;
    private int y = (Core.height/2)-16;

    private Weapon weapon = new normal();

    private double velocityX = 0;
    private double velocityY = 0;

    private int dir = 1;

    private final double friction = -0.3;

    public static boolean isWaitingForRespawn() {
        return waitingForRespawn;
    }

    public void damage(int amount){
        if(health>=0) {
            health += -amount;
            if (health <= 0) {
                Core.lev.partAdd(new EnemyExplosionPart(x, y));
                Core.lev.partAdd(new EnemyExplosionPart(x, y + 23, 0x333333, 0.2));
                Core.lev.partAdd(new EnemyExplosionPart(x + 12, y + 29, 0x333333, 0.6));
                Core.lev.partAdd(new EnemyExplosionPart(x - 21, y - 23, 0x333333, 1.2));
                die();
            }
        }
    }

    private void velocity(){
        if(velocityX>0){
            if(Math.round(velocityX)==0){
                velocityX=0;
            }else {
                velocityX += friction;
            }
        }else{
            if(Math.round(velocityX-0.1)==0){
                velocityX=0;
            }else {
                velocityX += -friction;
            }
        }

        if(velocityY>0){
            if(Math.round(velocityY)==0){
                velocityY=0;
            }else {
                velocityY += friction;
            }
        }else{
            if(Math.round(velocityY-0.1)==0){
                velocityY=0;
            }else {
                velocityY += -friction;
            }
        }

        if(velocityY>15){
            velocityY=15;
        }if(velocityY<-15){
            velocityY=-15;
        }if(velocityX>15){
            velocityX=15;
        }if(velocityX<-15){
            velocityX=-15;
        }

        y += Math.floor(velocityY);
        x += Math.floor(velocityX);
    }

    public void update(){
        if(!(health<=0)) {

            for(int i = 0; i<32; i++){
                if(dir==1||dir==5) {
                    Core.lev.partAdd(new ShipParticle(x + 16+i, y + 32));
                }else if(dir==3||dir==7){
                    Core.lev.partAdd(new ShipParticle(x + 32, y + 16+i));

                }else if(dir==2||dir==6){
                    Core.lev.partAdd(new ShipParticle(x + 16+i, y + 16+i));

                }else if(dir==8){
                    Core.lev.partAdd(new ShipParticle(x + 64-i, y + 32+i));

                }else if(dir==4){
                    Core.lev.partAdd(new ShipParticle(x + 40-i, y + 8+i));

                }
            }



            weapon.update();
            if (y > HEIGHT) {
                y = 0;
            }
            if (y < 0) {
                y = HEIGHT;
            }

            if (x > WIDTH) {
                x = 0;
            }
            if (x < 0) {
                x = WIDTH;
            }
            velocity();
        }

    }
    public void move(double x, double y){
        velocityX += x;
        velocityY += y;
        if(y<0&&x==0){
            dir = 1;
        }else if(y>0&&x==0){
            dir = 5;
        }else if(x<0&&y==0){
            dir = 7;
        }else if (x>0&&y==0){
            dir = 3;
        }


        if (y < 0 && x > 0) {
            dir = 2;
        } else if (y < 0 && x <0) {
            dir = 8;
        } else if (y > 0 && x<0) {
            dir = 6;
        } else if (y > 0 && x>0) {
            dir = 4;
        }





    }

    public void shoot(){
        if(Level.isRunning())
        weapon.fire(x+16,y+16,dir);
    }

    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Dimension getXY(){
        return new Dimension(x,y);
    }
    public void respawn(){
        waitingForRespawn = false;
        health = 6;
        Level.setRunning(false);
        NextLevel.Restart();
        ScreenOverlay.setShouldRender(false);
        new Thread() {

            public void run() {
                ReverseScreenFade sf = new ReverseScreenFade(Color.black, true);
                System.out.println("why u no work 1");
                int id = Core.lev.AddVfx(sf);

                while(sf.isFinished()==false){
                    try {
                        Thread.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                System.out.println("why u no work 2");
                Core.lev.removeVfx(sf);
                ScreenOverlay.setMode(ScreenOverlay.MODE_RENDER_IMAGE);
                ScreenOverlay.setBufferedImage(Sprite.BEGIN.getImage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ScreenOverlay.setShouldRender(false);
                Level.setRunning(true);
            }
        }.start();
    }
    private void die(){
        new Thread() {

            public void run() {
                ScreenFade sf = new ScreenFade(Color.black, true);

                int id = Core.lev.AddVfx(sf);

                while(sf.isFinished()==false){
                    try {
                        Thread.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                Core.lev.removeVfx(sf);
                ScreenOverlay.setMode(ScreenOverlay.MODE_RENDER_IMAGE);
                ScreenOverlay.setBufferedImage(Sprite.GameOver.getImage());
                waitingForRespawn = true;
            }
        }.start();
    }

    public void render(){
        if(!(health<=0)) {

            if (dir == 1) {
                Screen.RenderPlayer64(getXY(), Sprite.player1);
            } else if (dir == 2) {
                Screen.RenderPlayer64(getXY(), Sprite.player5);
            } else if (dir == 3) {
                Screen.RenderPlayer64(getXY(), Sprite.player2);
            } else if (dir == 4) {
                Screen.RenderPlayer64(getXY(), Sprite.player6);
            } else if (dir == 5) {
                Screen.RenderPlayer64(getXY(), Sprite.player3);
            } else if (dir == 6) {
                Screen.RenderPlayer64(getXY(), Sprite.player7);
            } else if (dir == 7) {
                Screen.RenderPlayer64(getXY(), Sprite.player4);
            } else if (dir == 8) {
                Screen.RenderPlayer64(getXY(), Sprite.player8);
            }
        }else{
            //Screen.RenderPlayer64(getXY(), Sprite.playerDead);
        }
    }

    public void removeVelocity(){
        velocityX = 0;
        velocityY = 0;
    }

}
