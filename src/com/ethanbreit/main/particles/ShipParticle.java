package com.ethanbreit.main.particles;

import com.ethanbreit.main.Screen;

import java.awt.*;

/**
 * Created by garthbreit on 2016-02-08.
 */
public class ShipParticle extends Particle{
    private long lastMili = 0;
    private boolean dead = false;
    private int length = 500;
    public ShipParticle(int x,int y){
        super(x,y);
    }

    @Override
    public void init(){
        lastMili = System.currentTimeMillis();
        colour = 0x1803A1;
    }

    @Override
    public void render() {
        if(!dead) {
            Screen.RenderParticle(new Dimension(x, y), colour);
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
