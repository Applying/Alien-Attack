package com.ethanbreit.main.Entities.mob;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Screen;
import com.ethanbreit.main.Sprites.Sprite;
import com.ethanbreit.main.particles.EnemyExplosionPart;

import java.awt.*;

/**
 * Created by garthbreit on 2016-02-08.
 */
public class BasicMob extends Mob {
    private int health = 6;
    private long id;
    public BasicMob(int x, int y) {
        super(x, y);
    }

    @Override
    public void init(long id) {
        this.id = id;
    }

    @Override
    public void Render() {
        if(!(health<=0)) {
            Screen.Render64(new Dimension(x, y), Sprite.enemybasic, (int)id);
        }
    }

    @Override
    public void update() {
        if(!(health<=0)) {
            try {
                Dimension dim = Core.play.getXY();
                if ((dim.getHeight() < y) &&
                        (Screen.boundingPix[x + (y - 1) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[x + (y - 1) * Core.width] == (0xff00ff)) &&
                        (Screen.boundingPix[(x + 64) + (y - 1) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x + 64) + (y - 1) * Core.width] == (0xff00ff)) &&
                        (Screen.boundingPix[(x + 32) + (y - 1) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x + 32) + (y - 1) * Core.width] == (0xff00ff))) {
                    y--;
                }
                if (dim.getHeight() > y &&
                        (Screen.boundingPix[x + (y + 65) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[x + (y + 65) * Core.width] == (0xff00ff)) &&
                        (Screen.boundingPix[(x + 64) + (y + 65) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x + 64) + (y + 65) * Core.width] == (0xff00ff)) &&
                        (Screen.boundingPix[(x + 32) + (y + 65) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x + 32) + (y + 65) * Core.width] == (0xff00ff))) {
                    y++;
                }
                if (dim.getWidth() < x &&
                        (Screen.boundingPix[(x - 1) + (y) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x - 1) + (y) * Core.width] == (0xff00ff)) &&
                        (Screen.boundingPix[(x - 1) + (y + 64) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x - 1) + (y + 64) * Core.width] == (0xff00ff)) &&
                        (Screen.boundingPix[(x - 1) + (y + 32) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x - 1) + (y + 32) * Core.width] == (0xff00ff))) {
                    x--;
                }
                if (dim.getWidth() > x &&
                        (Screen.boundingPix[(x + 65) + (y) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x + 65) + (y) * Core.width] == (0xff00ff)) &&
                        (Screen.boundingPix[(x + 65) + (y + 64) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x + 65) + (y + 64) * Core.width] == (0xff00ff)) &&
                        (Screen.boundingPix[(x + 65) + (y + 32) * Core.width] == (0xffffff) ||
                                Screen.boundingPix[(x + 65) + (y + 32) * Core.width] == (0xff00ff))) {
                    x++;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            for(int x = 0; x<64; x++){
                for(int y = 0; y<64; y++){
                    if(health>0) {
                        try {
                            if (Screen.boundingPix[(x + this.x) + ((this.y + y) * Core.width)] == 0xffffff) {
                                Core.play.damage(3);
                                health = 0;
                                die();
                                break;


                            }
                        } catch (Exception e) {

                        }
                    }else{
                        break;
                    }
                }
            }

        }

    }

    @Override
    public void takeDamage(int amount) {
        health += -amount;

        if(health<=0) {
           die();
        }
    }
    public void die(){
        isDead = true;
        Core.lev.partAdd(new EnemyExplosionPart(x, y));
        Core.lev.partAdd(new EnemyExplosionPart(x, y+23, 0x333333,0.2));
        Core.lev.partAdd(new EnemyExplosionPart(x+12, y+29, 0x333333,0.6));
        Core.lev.partAdd(new EnemyExplosionPart(x-21, y-23, 0x333333,1.2));
    }
}
