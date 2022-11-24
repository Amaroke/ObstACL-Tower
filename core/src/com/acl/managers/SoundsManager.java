package com.acl.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundsManager {

    private static Music background_sound;
    private final Sound chest;
    private final Sound damage;
    private final Sound fireball;
    private final Sound trapdoor_opening;
    private final Sound sword;
    private final Sound loose;
    private final Sound win;
    private final Sound barrel;

    public SoundsManager() {
        this.trapdoor_opening = Gdx.audio.newSound(Gdx.files.internal("trapdoor_opening.ogg"));
        this.sword = Gdx.audio.newSound(Gdx.files.internal("sword.ogg"));
        this.loose = Gdx.audio.newSound(Gdx.files.internal("loose.ogg"));
        this.win = Gdx.audio.newSound(Gdx.files.internal("win.ogg"));
        this.chest = Gdx.audio.newSound(Gdx.files.internal("chest.ogg"));
        this.damage = Gdx.audio.newSound(Gdx.files.internal("damage.ogg"));
        this.fireball = Gdx.audio.newSound(Gdx.files.internal("fireball.ogg"));
        this.barrel = Gdx.audio.newSound(Gdx.files.internal("barrel.ogg"));
        background_sound = Gdx.audio.newMusic(Gdx.files.internal("abc.ogg"));

    }

    public void soundTrapdoor_opening() {
        this.trapdoor_opening.play();
    }

    public void soundChest() {
        this.chest.play();
    }

    public void soundSword() {
        this.sword.play();
    }

    public void soundFireball() {
        this.fireball.play();
    }

    public void soundLoose() {
        this.loose.play();
    }

    public void soundDamage() {
        this.damage.play();
    }

    public void soundWin() {
        this.win.play();
    }

    public void soundBarrel() {
        this.barrel.play();
    }


    public void soundBackground() {
        background_sound.setLooping(true);
        background_sound.play();
        background_sound.setVolume(0.20f);
    }


}

