package com.mygdx.game;

public class Grid {

    private Field[][] grid;
    private int width;
    private int height;
    private int bombs;

    public Grid(int width, int height, int bombs) {
        this.width = width;
        this.height = height;
        this.bombs = bombs;
        this.grid = new GridGenerator().generate(width, height, bombs);
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


}