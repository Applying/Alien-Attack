package com.ethanbreit.main.level;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Sprites.Sprite;
import com.ethanbreit.main.VFX.ReverseScreenFade;
import com.ethanbreit.main.VFX.ScreenOverlay;

import java.awt.*;

/**
 * Created by garthbreit on 2016-07-05.
 */
public class Title {
    private static boolean waitingForStart;
    public static void start(){
        ScreenOverlay.setBufferedImage(Sprite.TitleCard.getImage());
        ScreenOverlay.setMode(ScreenOverlay.MODE_RENDER_IMAGE);
        ScreenOverlay.setShouldRender(true);
        new Level1().init();
        waitingForStart = true;
    }

    public static void finish(){
        waitingForStart=false;
        new Thread() {

            public void run() {
                ReverseScreenFade sf = new ReverseScreenFade(new Color(127, 127, 127), true);
                int id = Core.lev.AddVfx(sf);

                while(sf.isFinished()==false){
                    try {
                        Thread.sleep(1);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                Core.lev.removeVfx(sf);
                ScreenOverlay.setMode(ScreenOverlay.MODE_RENDER_IMAGE);
                ScreenOverlay.setBufferedImage(Sprite.BEGIN.getImage());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ScreenOverlay.setShouldRender(false);
                Level.setRunning(true);
            }
        }.start();
    }

    public static boolean isWaitingForStart() {
        return waitingForStart;
    }

    public static void setWaitingForStart(boolean waitingForStart) {
        Title.waitingForStart = waitingForStart;
    }
}
