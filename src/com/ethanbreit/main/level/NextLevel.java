package com.ethanbreit.main.level;

import com.ethanbreit.main.Core;

import java.util.ArrayList;

/**
 * Created by garthbreit on 2016-02-09.
 */
public class NextLevel {

    private static ArrayList<LevelTemp> thing = new ArrayList();
    private static int currentlevel = 0;

    public static void init(){
        thing.add(new Level1());
        thing.add(new Level2());
        thing.add(new Level3());
        thing.add(new Level4());
        thing.add(new Level5());
        thing.add(new Level6());

    }

    public static void Restart(){
        Core.play.removeVelocity();
        Core.play.setXY((Core.width/2)-16, (Core.height/2)-16);
        Core.lev.reset();

        thing.get(currentlevel).init();
    }

    public static void nextlevel(){
        Core.play.removeVelocity();
        Core.play.setXY((Core.width/2)-16, (Core.height/2)-16);
        currentlevel++;
        thing.get(currentlevel).init();
    }

}
