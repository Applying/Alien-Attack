package com.ethanbreit.main.level;

import com.ethanbreit.main.Animation.Animation;
import com.ethanbreit.main.Entities.mob.Mob;
import com.ethanbreit.main.VFX.VFX;
import com.ethanbreit.main.projectiles.Projectile;
import com.ethanbreit.main.particles.*;
import com.sun.javafx.tools.ant.Callback;

import java.util.ArrayList;

/**
 * Created by garthbreit on 2016-02-07.
 */
public class Level {

    private static boolean running = false;

    ArrayList<Mob> mobs = new ArrayList<>();
    ArrayList<Projectile> projectiles = new ArrayList<>();
    ArrayList<Particle> particles = new ArrayList<>();
    ArrayList<Animation> animation = new ArrayList<>();
    ArrayList<VFX> vfx = new ArrayList<>();

    public static boolean isRunning() {
        return running;
    }

    public static void setRunning(boolean running) {
        Level.running = running;
    }


    public Mob getMob(int index){
        return mobs.get(index);
    }

    public void HighLevRender(){
        for(int i = 0; i<vfx.size(); i++){
            vfx.get(i).render();
        }
    }

    public void reset(){
        mobs.clear();
        projectiles.clear();
        particles.clear();
        animation.clear();
        vfx.clear();
    }

    public void Render(){
        for(int i = 0; i<animation.size(); i++){
            animation.get(i).render();
        }
        for(int i = 0; i<particles.size(); i++){
            try {
                particles.get(i).render();
            }catch(Exception e){

            }
        }
        for(int i = 0; i<mobs.size(); i++){
            mobs.get(i).Render();
        }
        for(int i = 0; i<projectiles.size(); i++){
            try {
                projectiles.get(i).render();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
        }


    }
    public void Add(Mob mob){
        mobs.add(mob);
        mob.init(mobs.size());
    }
    public int AddVfx(VFX vfx2){
        vfx.add(vfx2);
        return vfx.size();
    }
    public void removeVfx(Object obj){
        vfx.remove(obj);
    }
    public void animAdd(Animation anim){
        animation.add(anim);
    }

    public void projAdd(Projectile proj){
        projectiles.add(proj);
    }

    public void partAdd(Particle par){
        particles.add(par);
        par.init();
    }

    public void update(){
        int mobsleft = 0;
        try {
            for (int i = 0; i < animation.size(); i++) {
                animation.get(i).update();
            }
            if(isRunning()) {
                for (int i = 0; i < mobs.size(); i++) {
                    mobs.get(i).update();
                    if (mobs.get(i).isDead) {
                        mobsleft++;
                    }
                }
            }
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).update();
            }
            for (int i = 0; i < particles.size(); i++) {
                particles.get(i).update();
            }
            for (int i = 0; i < vfx.size(); i++) {
                vfx.get(i).update();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if(mobsleft==mobs.size()&&mobs.size()!=0){
            reset();

            levelrest.start();
        }
    }
}
