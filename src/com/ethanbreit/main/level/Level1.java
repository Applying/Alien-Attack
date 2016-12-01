package com.ethanbreit.main.level;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Entities.mob.BasicMob;
import com.ethanbreit.main.projectiles.ProjectileNormal;

/**
 * Created by garthbreit on 2016-02-07.
 */
public class Level1 extends LevelTemp{

    public void init(){
        Core.lev.Add(new BasicMob(Core.width/6,Core.height/6));
        Core.lev.Add(new BasicMob(Core.width/3,Core.height/8));
        Core.lev.Add(new BasicMob(Core.width/6,Core.height/2));
        Core.lev.Add(new BasicMob(Core.width/2,Core.height/8));


    }

}
