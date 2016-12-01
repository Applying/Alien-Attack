package com.ethanbreit.main.projectiles;

import com.ethanbreit.main.Sprites.Sprite;

/**
 * Created by garthbreit on 2016-02-07.
 */
public abstract class Projectile {

    public Projectile(int x, int y, int dir, int speed){}

    public abstract void render();
    public abstract void update();

}
