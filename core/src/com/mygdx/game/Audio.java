package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Created by allen on 16/06/17.
 */
public class Audio {
    private Music backgroundMusic; // obtained from https://www.youtube.com/watch?v=UPgHA8EW6Go
    private Sound goalMusic;

    Audio() {
        this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/background.mp3"));
        this.goalMusic = Gdx.audio.newSound(Gdx.files.internal("audio/goal-sound.mp3"));
    }

    public void playBackgroundMusic() {
        this.backgroundMusic.play();
    }

    public void playGoalMusic() {
        this.goalMusic.loop();
    }

    public void stopMusic() {
        if (backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
        }
        goalMusic.stop();
        backgroundMusic.dispose();
        goalMusic.dispose();
    }
}
