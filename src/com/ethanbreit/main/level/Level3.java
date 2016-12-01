package com.ethanbreit.main.level;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Entities.mob.BasicMob;

/**
 * Created by garthbreit on 2016-02-09.
 */
public class Level3 extends LevelTemp{

    public void init(){
        Core.lev.Add(new BasicMob(Core.width/5,Core.height-90));
        Core.lev.Add(new BasicMob((Core.width/3)+200,Core.height/7));
        Core.lev.Add(new BasicMob(Core.width/6,Core.height/2));
        Core.lev.Add(new BasicMob(Core.width/8+200,Core.height/8));
        Core.lev.Add(new BasicMob(Core.width/6+600,Core.height/8));
        Core.lev.Add(new BasicMob(Core.width/5,Core.height/3));



    }

}