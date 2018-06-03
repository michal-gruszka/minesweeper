package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MinesweeperGame extends Game {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    @Override
    public void create() {
        setScreen(new MainMenuScreen());
    }
}
