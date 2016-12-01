package com.ethanbreit.main;

import com.ethanbreit.main.Listiner.KeyListen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by garthbreit on 2016-12-01.
 */
public class Exit {
    private long time;
    private boolean keyHeld;
    private boolean first = true;

    public void update(boolean held){
        keyHeld = held;
        if(first&&keyHeld)
        {
            first = false;
            time = System.currentTimeMillis();

        }

        if(!keyHeld){
            first = true;
        }

        if(keyHeld&&(time+2000<=System.currentTimeMillis())){
            System.exit(0);
        }

    }

}
