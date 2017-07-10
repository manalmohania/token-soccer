package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;

class UIElements {

    private int width = Gdx.graphics.getWidth();
    private BitmapFont font = new BitmapFont();

    Skin createSkin() {
        // Texture
        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(width/4, width/4, Pixmap.Format.RGB888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("background", new Texture(pixmap));

        // Font
        skin.add("default", font);

        NinePatch ninePatch = new NinePatch(new Texture("images/screen/button-background.png"));
        ninePatch.setColor(Color.GREEN);

        // Button Style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        textButtonStyle.fontColor = Color.ORANGE;
        textButtonStyle.up = new NinePatchDrawable(ninePatch);
        skin.add("default", textButtonStyle);

        pixmap.dispose();
        return skin;
    }

    void dispose(){
        font.dispose();
    }
}
