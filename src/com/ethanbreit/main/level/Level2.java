package com.ethanbreit.main.level;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Entities.mob.BasicMob;

/**
 * Created by garthbreit on 2016-02-09.
 */
public class Level2 extends LevelTemp{

    public void init(){
        Core.lev.Add(new BasicMob(Core.width/2,Core.height/5));
        Core.lev.Add(new BasicMob(Core.width/3+200,Core.height-80));
        Core.lev.Add(new BasicMob(90,Core.height/2));
        Core.lev.Add(new BasicMob(Core.width-200,Core.height/8));


    }

}