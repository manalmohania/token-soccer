package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

class Audio {
    private Music backgroundMusic; // obtained from https://www.youtube.com/watch?v=UPgHA8EW6Go
    private Sound goalMusic;

    Audio() {
        this.backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("audio/background.mp3"));
        this.goalMusic = Gdx.audio.newSound(Gdx.files.internal("audio/goal-sound.mp3"));
    }

    void playBackgroundMusic() {
        this.backgroundMusic.play();
    }

    void playGoalMusic() {
        this.goalMusic.loop();
    }

    void stopMusic() {
        if (backgroundMusic.isPlaying()) {
            backgroundMusic.stop();
        }
        goalMusic.stop();
        backgroundMusic.dispose();
        goalMusic.dispose();
    }
}
