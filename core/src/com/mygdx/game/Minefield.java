package com.mygdx.game;

import static com.mygdx.game.Field.Status.*;

public class Minefield {

    private Field[][] grid;
    private int width;
    private int height;
    private int bombs;

    public Minefield(int width, int height, int bombs) {
        this.grid = grid;
        this.width = width;
        this.height = height;
        this.bombs = bombs;
        this.grid = new GridGenerator().generate(this);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getBombs() {
        return bombs;
    }

    public Field getField(int x, int y) {
        return grid[x][y];
    }

    public void reveal(int x, int y) {
        Field field = getField(x, y);

        if (field.getStatus() == COVERED) {
            switch (field.getContent()) {
                case BOMB:
                    // TODO: display all bombs, game over
                    break;
                case EMPTY:
                    // TODO: reveal other fields according to rules. Recursion? Queue?
                    break;
                default:
                    field.setStatus(REVEALED);
            }
        }
    }
}