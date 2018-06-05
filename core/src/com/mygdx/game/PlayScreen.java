package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayScreen extends ScreenAdapter {

    private MinesweeperGame game;

    public PlayScreen(MinesweeperGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderBackground(game.getShapeRenderer());
    }

    // TODO: this code is copied from MainMenuScreen, create common superclass for screens or something
    private void renderBackground(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(
                0,
                0,
                MinesweeperGame.WIDTH,
                MinesweeperGame.HEIGHT,
                Color.DARK_GRAY,
                Color.DARK_GRAY,
                Color.LIGHT_GRAY,
                Color.LIGHT_GRAY
        );
        renderer.end();
    }
}
