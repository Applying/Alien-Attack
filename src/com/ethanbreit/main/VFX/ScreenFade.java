package com.ethanbreit.main.VFX;

import com.ethanbreit.main.Screen;

import java.awt.*;

/**
 * Created by garthbreit on 2016-02-10.
 */
public class ScreenFade extends VFX{
    private Color color;
    private int intensity = 0;
    private boolean finished = false;
    private boolean disposeOnFinish;

    public ScreenFade(Color color, boolean disposeOnFinish){
        this.color = color;
        ScreenOverlay.setShouldRender(true);
        ScreenOverlay.setMode(ScreenOverlay.MODE_RENDER_COLOUR);
        this.disposeOnFinish = disposeOnFinish;
    }


    @Override
    public void update() {
        intensity++;
        if(intensity>253){
            intensity=253;
            finished = true;

            if(disposeOnFinish==true){
                try{
                    this.finalize();
                }catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
        ScreenOverlay.setColour(new Color(color.getRed(),color.getGreen(), color.getBlue(), intensity));
    }


    @Override
    public void render() {
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
