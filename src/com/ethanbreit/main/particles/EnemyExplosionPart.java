package com.ethanbreit.main.particles;

import com.ethanbreit.main.Core;

/**
 * Created by garthbreit on 2016-02-08.
 */
public class EnemyExplosionPart extends Particle {
    private double rad = 1;
    private double radmulti = 1.2;
    private long lastMili = 0;
    private boolean dead = false;
    private int length = 500;
    private int color = 0xCF1020;


    public EnemyExplosionPart(int x, int y){
        super(x,y);

    }
    public EnemyExplosionPart(int x, int y, int color){
        super(x,y); this.color = color;
    }
    public EnemyExplosionPart(int x, int y, int color, double r){
        super(x,y); this.color = color;
        radmulti = r;
    }
    public EnemyExplosionPart(int x, int y, int color, double r, int length){
        super(x,y); this.color = color;
        radmulti = r;
        this.length = length;
    }

    @Override
    public void init() {
        lastMili = System.currentTimeMillis();
        DrawCircle(x+32, y+32, 16);
    }
    void DrawCircle(int x0, int y0, int radius)
    {
        int x = radius;
        int y = 0;
        int decisionOver2 = 1 - x;   // Decision criterion divided by 2 evaluated at x=r, y=0

        while( y <= x )
        {
            Core.lev.partAdd(new ExplosionParticle( x + x0,  y + y0,color)); // Octant 1
            Core.lev.partAdd(new ExplosionParticle( y + x0,  x + y0,color)); // Octant 2
            Core.lev.partAdd(new ExplosionParticle(-x + x0,  y + y0,color)); // Octant 4
            Core.lev.partAdd(new ExplosionParticle(-y + x0,  x + y0,color)); // Octant 3
            Core.lev.partAdd(new ExplosionParticle(-x + x0, -y + y0,color)); // Octant 5
            Core.lev.partAdd(new ExplosionParticle(-y + x0, -x + y0,color)); // Octant 6
            Core.lev.partAdd(new ExplosionParticle( x + x0, -y + y0,color)); // Octant 7
            Core.lev.partAdd(new ExplosionParticle( y + x0, -x + y0,color)); // Octant 8
            y++;
            if (decisionOver2<=0)
            {
                decisionOver2 += 2 * y + 1;   // Change in decision criterion for y -> y+1
            }
            else
            {
                x--;
                decisionOver2 += 2 * (y - x) + 1;   // Change for y -> y+1, x -> x-1
            }
        }
    }

    @Override
    public void render() {

    }

    @Override
    public void update() {
        if(!dead) {
            rad+=radmulti;
            DrawCircle(x + 32, y + 32, (int)Math.round(rad));
            if (lastMili + length <= System.currentTimeMillis()) {
                dead = true;
            }
        }
    }
}
