package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MinesweeperGame extends Game {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    @Override
    public void create() {
        setScreen(new MainMenuScreen(this));
        this.shapeRenderer = new ShapeRenderer();
        this.spriteBatch = new SpriteBatch();
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public SpriteBatch getSpriteBatch() {
        return spriteBatch;
    }
}
