package com.ethanbreit.main.Listiner;

import com.ethanbreit.main.Core;
import com.ethanbreit.main.level.Title;
import com.ethanbreit.main.level.levelrest;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by garthbreit on 2016-02-05.
 */
public class KeyListen implements KeyListener{

    private boolean[] keysPressed = new boolean[400];

    public Integer[] update(){
        ArrayList<Integer> output = new ArrayList();
        for(int i = 0; i<keysPressed.length; i++){
            if(keysPressed[i]==true){
                output.add(i);
            }
        }
        Integer[] out = output.toArray(new Integer[output.size()]);

        return out;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed[e.getKeyCode()] = true;
        if(Core.play.isWaitingForRespawn()&&e.getKeyCode()==KeyEvent.VK_SPACE){
            Core.play.respawn();
        }
        if(levelrest.isWaitForContinue()&&e.getKeyCode()==KeyEvent.VK_SPACE){
            levelrest.finish();
        }
        if(Title.isWaitingForStart()&&e.getKeyCode()==KeyEvent.VK_SPACE){
            Title.finish();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed[e.getKeyCode()] = false;
    }
}
