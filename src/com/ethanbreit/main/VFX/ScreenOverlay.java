package com.ethanbreit.main.VFX;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by garthbreit on 2016-07-04.
 */
public class ScreenOverlay {
    private static boolean shouldRender = false;
    private static int mode;
    public static final int MODE_RENDER_COLOUR = 1;
    public static final int MODE_RENDER_IMAGE = 2;
    private static Color colour;
    private static BufferedImage bufferedImage;


    public static Color getColour() {
        return colour;
    }

    public static void setColour(Color colour) {
        ScreenOverlay.colour = colour;
    }

    public static BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public static void setBufferedImage(BufferedImage bufferedImage) {
        ScreenOverlay.bufferedImage = bufferedImage;
    }

    public static int getMode() {
        return mode;
    }

    public static void setMode(int mode) {
        ScreenOverlay.mode = mode;
    }

    public static boolean isShouldRender() {
        return shouldRender;
    }

    public static void setShouldRender(boolean shouldRender) {
        ScreenOverlay.shouldRender = shouldRender;
    }
}
