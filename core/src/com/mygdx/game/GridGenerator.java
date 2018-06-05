package com.mygdx.game;

import java.util.Random;

public class GridGenerator {

    private Field[][] grid;
    private int width;
    private int height;
    private int bombs;

    public Field[][] generate(int width, int height, int bombs) {
        this.width = width;
        this.height = height;
        this.bombs = bombs;
        this.grid = new Field[width][height];

        placeRandomBombs();
        placeRemainingFields();
        return grid;
    }

    private void placeRandomBombs() {
        int bombsPlaced = 0;
        while (bombsPlaced < bombs) {
            int x = new Random().nextInt(width);
            int y = new Random().nextInt(height);
            if (grid[x][y] == null) {
                grid[x][y] = new Field(x, y, Field.Content.BOMB, Field.Status.COVERED);
                bombsPlaced++;
            }
        }
    }

    private void placeRemainingFields() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid[i][j] == null) {
                    int bombsAround = countBombsAround(grid, i, j);
                    grid[i][j] = new Field(i, j, intToFieldContent(bombsAround), Field.Status.COVERED);
                }
            }
        }
    }

    private int countBombsAround(Field[][] grid, int x, int y) {
        int bombsAround = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                if (coordinatesValid(x+i, y+j) && grid[x+i][y+j] != null && grid[x+i][y+j].getContent() == Field.Content.BOMB) {
                    bombsAround++;
                }
            }
        }
        return bombsAround;
    }

    private boolean coordinatesValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private Field.Content intToFieldContent(int value) {
        switch (value) {
            case 0:
                return Field.Content.EMPTY;
            case 1:
                return Field.Content.ONE;
            case 2:
                return Field.Content.TWO;
            case 3:
                return Field.Content.THREE;
            case 4:
                return Field.Content.FOUR;
            case 5:
                return Field.Content.FIVE;
            case 6:
                return Field.Content.SIX;
            case 7:
                return Field.Content.SEVEN;
            case 8:
                return Field.Content.EIGHT;
            default:
                throw new RuntimeException("argument expected to be in range 0-8, actual value: " + value);
        }
    }
}
