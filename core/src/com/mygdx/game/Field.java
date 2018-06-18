package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class Field {

    private int x;
    private int y;
    private Content content;
    private Status status;
    private Minefield minefield;
    private Array<ClickListener> listeners;

    public enum Content {
        EMPTY,
        BOMB,
        ONE,
        TWO,
        THREE,
        FOUR,
        FIVE,
        SIX,
        SEVEN,
        EIGHT
    }

    public enum Status {
        COVERED,
        REVEALED,
        FLAG,
        QUESTION_MARK
    }

    public Field(Minefield minefield, int x, int y, Content content, Status status) {
        this.minefield = minefield;
        this.x = x;
        this.y = y;
        this.content = content;
        this.status = status;
        initializeListeners();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Content getContent() {
        return content;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        updateFlagsCounter(status);
        updateRevealedCounter(status);
        this.status = status;
    }

    private void updateFlagsCounter(Status status) {
        int flags = this.minefield.getFlags();

        if (this.status != Status.FLAG && status == Status.FLAG) {
            this.minefield.setFlags(++flags);

        } else if (this.status == Status.FLAG && status != Status.FLAG) {
            this.minefield.setFlags(--flags);
        }
    }

    private void updateRevealedCounter(Status status) {
        int revealed = this.minefield.getRevealed();

        if (this.status != Status.REVEALED && status == Status.REVEALED) {
            this.minefield.setRevealed(++revealed);
        }
    }

    public Array<ClickListener> getListeners() {
        return listeners;
    }

    public boolean isMouseOver() {
        return listeners.get(0).isOver();
    }

    private void initializeListeners() {
        this.listeners = new Array<ClickListener>();
        listeners.add(leftClickListener());
        listeners.add(rightClickListener());
    }

    private ClickListener leftClickListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (status != Status.FLAG)
                    minefield.reveal(getX(), getY());
            }
        };
    }

    private ClickListener rightClickListener() {
        return new ClickListener(Input.Buttons.RIGHT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toggleStatus();
            }
        };
    }

    private void toggleStatus() {
        switch(status) {
            case COVERED:
                setStatus(Status.FLAG);
                break;
            case FLAG:
                setStatus(Status.QUESTION_MARK);
                break;
            case QUESTION_MARK:
                setStatus(Status.COVERED);
                break;
        }
    }
}
