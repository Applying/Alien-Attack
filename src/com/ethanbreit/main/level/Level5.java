package com.ethanbreit.main.level;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Entities.mob.BasicMob;

/**
 * Created by garthbreit on 2016-02-09.
 */
public class Level5 extends LevelTemp{

    public void init(){
        Core.lev.Add(new BasicMob(Core.width/2,Core.height/4));
        Core.lev.Add(new BasicMob(Core.width/3+200,Core.height/2));
        Core.lev.Add(new BasicMob(Core.width/6,Core.height/2));
        Core.lev.Add(new BasicMob(Core.width/8+200,Core.height/8));


        Core.lev.Add(new BasicMob(Core.width/7,Core.height/6));
        Core.lev.Add(new BasicMob(Core.width/5+200,Core.height/7));
        Core.lev.Add(new BasicMob(Core.width/6,Core.height/3));
        Core.lev.Add(new BasicMob(Core.width/4+200,Core.height/8));

        Core.lev.Add(new BasicMob(Core.width/7+125,Core.height/2+74));
        Core.lev.Add(new BasicMob(Core.width/5+20,Core.height/3));
        Core.lev.Add(new BasicMob(Core.width/9,Core.height/8));
        Core.lev.Add(new BasicMob(Core.width/2+200,Core.height/8+3));
    }

}