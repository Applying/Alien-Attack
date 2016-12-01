package com.ethanbreit.main.particles;

import com.ethanbreit.main.Screen;

import java.awt.*;

/**
 * Created by garthbreit on 2016-02-08.
 */
public abstract class Particle {
    protected int colour = 0xffffff;
    protected int x,y;
    public Particle(int x, int y){this.x = x; this.y = y;}
    public Particle(int x, int y, int color){this.x = x; this.y = y;}

    public abstract void init();
    public abstract void render();
    public abstract void update();

}
