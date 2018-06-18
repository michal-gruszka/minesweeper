package com.mygdx.game;

import com.badlogic.gdx.utils.Array;

import java.util.LinkedList;
import java.util.Queue;

import static com.mygdx.game.Field.Status.*;
import static com.mygdx.game.Field.Content.*;


public class Minefield {

    private Field[][] grid;
    private int width;
    private int height;
    private int bombs;
    private int flags;
    private int revealed;

    public Minefield(int width, int height, int bombs) {
        this.width = width;
        this.height = height;
        this.bombs = bombs;
        this.flags = 0;
        this.revealed = 0;
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

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getRevealed() {
        return revealed;
    }

    public void setRevealed(int revealed) {
        this.revealed = revealed;
    }

    public Field getField(int x, int y) {
        return grid[x][y];
    }

    public void reveal(int x, int y) {

        Field field = getField(x, y);

        if (field.getStatus() == COVERED) {
            switch (field.getContent()) {
                case BOMB:
                    revealBombs();
                    break;
                case EMPTY:
                    revealAroundEmpty(field);
                    break;
                default:
                    field.setStatus(REVEALED);
            }
        }
    }

    private void revealBombs() {

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++)

                if (getField(x, y).getContent() == BOMB)
                    getField(x, y).setStatus(REVEALED);
    }

    private void revealAroundEmpty(Field field) {

        Queue<Field> queue = new LinkedList<>();
        queue.add(field);

        while (queue.peek() != null) {
            Field center = queue.poll();
            center.setStatus(REVEALED);

            for (Field f : getFieldsAround(center)) {

                if (f.getStatus() != REVEALED) {
                    if (f.getContent() == EMPTY)
                        queue.add(f);

                    f.setStatus(REVEALED);
                }
            }
        }
    }

    private Array<Field> getFieldsAround(Field center) {

        int x = center.getX();
        int y = center.getY();
        Array<Field> fields = new Array<>();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) continue;

                if (coordinatesValid(x + i, y + j))
                    fields.add(getField(x + i, y + j));
            }
        }
        return fields;
    }

    private boolean coordinatesValid(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}