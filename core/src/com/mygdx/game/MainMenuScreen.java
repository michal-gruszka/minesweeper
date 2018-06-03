package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainMenuScreen extends ScreenAdapter {

    MinesweeperGame game;

    public MainMenuScreen(MinesweeperGame game) {
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        game.getShapeRenderer().rect(
                0,
                0,
                MinesweeperGame.WIDTH,
                MinesweeperGame.HEIGHT,
                Color.DARK_GRAY,
                Color.DARK_GRAY,
                Color.LIGHT_GRAY,
                Color.LIGHT_GRAY
                );
        game.getShapeRenderer().end();
    }
}
