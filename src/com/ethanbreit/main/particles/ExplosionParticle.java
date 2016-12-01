package com.ethanbreit.main.particles;

import com.ethanbreit.main.Screen;

import java.awt.*;

/**
 * Created by garthbreit on 2016-02-08.
 */
public class ExplosionParticle extends Particle{
    private long lastMili = 0;
    private boolean dead = false;
    private int length = 500;
    private int color = 0xCF1020;
    public ExplosionParticle(int x, int y){
        super(x,y);
    }
    public ExplosionParticle(int x, int y, int color){
        super(x,y);
        this.color = color;
    }

    @Override
    public void init(){
        lastMili = System.currentTimeMillis();
    }

    @Override
    public void render() {
        if(!dead) {
            Screen.RenderParticle(new Dimension(x, y), color);
        }
    }

    @Override
    public void update() {
        if(!dead) {

            if (lastMili + length <= System.currentTimeMillis()) {
                dead = true;
            }
        }
    }
}
