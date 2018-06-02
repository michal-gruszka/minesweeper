package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MinesweeperGame extends Game {

    @Override
    public void create() {
        setScreen(new MainMenuScreen());
    }
}
