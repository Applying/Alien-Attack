package com.ethanbreit.main.projectiles;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Entities.mob.BasicMob;
import com.ethanbreit.main.Screen;
import com.ethanbreit.main.Sprites.Sprite;
import com.ethanbreit.main.level.Level;

import java.awt.*;

/**
 * Created by garthbreit on 2016-02-07.
 */
public class ProjectileNormal extends Projectile{

    private int dir;


    private static final int WIDTH = Core.width;
    private static final int HEIGHT = Core.height;

    private int x,y;
    private int speed = 8;

    private int life = 2000;
    private long lastMil = 0;
    private boolean notDead = true;

    public ProjectileNormal(int x, int y, int dir) {
        super(x, y, dir, 1);
        this.dir =dir;
        this.x = x;
        this.y = y;
        lastMil = System.currentTimeMillis();
    }

    public void update(){
        if(notDead) {




            if (lastMil + life <= System.currentTimeMillis()) {
                notDead = false;
            }
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
            switch (dir) {

                case 1:
                    y += -speed;
                    break;
                case 2:
                    y += -speed;
                    x += speed;
                    break;
                case 3:
                    x += speed;
                    break;
                case 4:
                    y += speed;
                    x += speed;
                    break;
                case 5:
                    y += speed;
                    break;
                case 6:
                    y += speed;
                    x += -speed;
                    break;
                case 7:
                    x += -speed;
                    break;
                case 8:
                    x += -speed;
                    y += -speed;

                    break;
            }
            try {
                for(int x2 = 0; x2<32; x2++){
                    for(int y2 = 0; y2<32; y2++){
                        if(notDead) {
                            if (Screen.boundingPix[(x + x2) + (y + y2) * Core.width] != 0xff00ff && Screen.boundingPix[(x + x2) + (y + y2) * Core.width] != 0xffffff) {
                                Core.lev.getMob(Screen.boundingPix[(x + x2) + (y + y2) * Core.width] - 1).takeDamage(1);
                                notDead = false;
                                break;
                            } else if (Screen.boundingPix[x + (y + y2) * Core.width] == 0xffffff) {
                                //Core.play.damage(1);
                            }
                        }else{
                            break;
                        }
                    }
                }



            }catch (Exception e){

            }
        }
    }

    public void render(){
        if(notDead) {
            switch (dir) {

                case 1:
                    Screen.RenderProj32(new Dimension(x, y), Sprite.normalproj1);
                    break;
                case 2:
                    Screen.RenderProj32(new Dimension(x, y), Sprite.normalproj3);
                    break;
                case 3:
                    Screen.RenderProj32(new Dimension(x, y), Sprite.normalproj2);
                    break;
                case 4:
                    Screen.RenderProj32(new Dimension(x, y), Sprite.normalproj4);
                    break;
                case 5:
                    Screen.RenderProj32(new Dimension(x, y), Sprite.normalproj1);
                    break;
                case 6:
                    Screen.RenderProj32(new Dimension(x, y), Sprite.normalproj3);
                    break;
                case 7:
                    Screen.RenderProj32(new Dimension(x, y), Sprite.normalproj2);
                    break;
                case 8:
                    Screen.RenderProj32(new Dimension(x, y), Sprite.normalproj4);
                    break;
            }
        }
    }
}
