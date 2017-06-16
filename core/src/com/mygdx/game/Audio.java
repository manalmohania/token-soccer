package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by allen on 16/06/17.
 */
public class Audio {
    private Sound backgroundMusic;
    private Sound goalMusic;

    Audio() {
        this.backgroundMusic = Gdx.audio.newSound(Gdx.files.internal("meme.mp3"));
        this.goalMusic = Gdx.audio.newSound(Gdx.files.internal("meme2.mp3"));
    }

    public void playBackgroundMusic() {
        this.backgroundMusic.play();
    }

    public void playGoalMusic() {
        this.goalMusic.play();
    }
}
