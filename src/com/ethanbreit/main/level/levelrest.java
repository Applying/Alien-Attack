package com.ethanbreit.main.level;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Sprites.Sprite;
import com.ethanbreit.main.VFX.ReverseScreenFade;
import com.ethanbreit.main.VFX.ScreenFade;
import com.ethanbreit.main.VFX.ScreenOverlay;

import java.awt.*;

/**
 * Created by garthbreit on 2016-07-04.
 */
public class levelrest {
    private static boolean waitForContinue = false;
    public static void start(){
        Level.setRunning(false);
        new Thread() {

            public void run() {
                ScreenFade sf = new ScreenFade(Color.white, true);
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
                ScreenOverlay.setBufferedImage(Sprite.progress.getImage());
                waitForContinue=true;
                NextLevel.nextlevel();
                Core.play.health = 6;
            }
        }.start();
    }

    public static void finish(){
        waitForContinue=false;
        new Thread() {

            public void run() {
                ReverseScreenFade sf = new ReverseScreenFade(Color.white, true);
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

    public static boolean isWaitForContinue() {
        return waitForContinue;
    }

}
