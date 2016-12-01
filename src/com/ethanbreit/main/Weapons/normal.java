package com.ethanbreit.main.Weapons;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.projectiles.ProjectileNormal;

/**
 * Created by garthbreit on 2016-02-07.
 */
public class normal extends Weapon{

    private int recharge = 200;
    private boolean canshoot = true;
    private long lastmili;

    public normal(){

    }
    public void update(){
        if (!canshoot){
            if(lastmili+recharge<=System.currentTimeMillis()){
                canshoot=true;
            }
        }
    }

    public void fire(int x,int y,int dir){
        if(canshoot) {
            Core.lev.projAdd(new ProjectileNormal(x, y, dir));
            canshoot=false;
            lastmili = System.currentTimeMillis();
        }
    }

}
