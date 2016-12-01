package com.ethanbreit.main.VFX;

import java.awt.*;

/**
 * Created by garthbreit on 2016-02-10.
 */
public class ReverseScreenFade extends VFX{
    private Color color;
    private int intensity = 255;
    private boolean finished = false;
    private boolean disposeOnFinish;

    public ReverseScreenFade(Color color, boolean disposeOnFinish){
        this.color = color;
        ScreenOverlay.setShouldRender(true);
        ScreenOverlay.setMode(ScreenOverlay.MODE_RENDER_COLOUR);
        this.disposeOnFinish = disposeOnFinish;
    }


    @Override
    public void update() {
        intensity--;
        if(intensity<5){
            intensity=5;
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
