package com.ethanbreit.main.Animation;

/**
 * Created by garthbreit on 2016-02-08.
 */
public class BasicEnemyDeathAnim extends Animation{
    private int fps = 1;
    private int dif = 0;
    private long lastmili = 0;

    private int frame = 1;

    @Override
    public void animate(int fps, int x, int y) {
        this.fps = fps;
        dif = (60/this.fps)*1000;
        lastmili = System.currentTimeMillis();
    }

    @Override
    public void update() {
        if(lastmili+dif<=System.currentTimeMillis()){
            frame++;
            lastmili= System.currentTimeMillis();
        }
    }

    @Override
    public void render() {
        switch (frame){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4 :
                break;

        }
    }
}
