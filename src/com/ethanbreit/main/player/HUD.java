package com.ethanbreit.main.player;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.Screen;
import com.ethanbreit.main.Sprites.Sprite;

import java.awt.*;

/**
 * Created by garthbreit on 2016-07-05.
 */
public class HUD {
    private static int health = 6;
    public static void update(){
        health = Core.play.health;
    }

    public static void Render(){
        switch (health){
            case 1:
                Screen.HUDRender(new Dimension(Core.width, Core.height), Sprite.HUD1);
                break;
            case 2:
                Screen.HUDRender(new Dimension(Core.width, Core.height), Sprite.HUD2);
                break;
            case 3:
                Screen.HUDRender(new Dimension(Core.width, Core.height), Sprite.HUD3);
                break;
            case 4:
                Screen.HUDRender(new Dimension(Core.width, Core.height), Sprite.HUD4);
                break;
            case 5:
                Screen.HUDRender(new Dimension(Core.width, Core.height), Sprite.HUD5);
                break;
            case 6:
                Screen.HUDRender(new Dimension(Core.width, Core.height), Sprite.HUD6);
                break;
        }
    }
}
