package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

public class Field extends ClickListener {

    private int x;
    private int y;
    private Content content;
    private Status status;
    private Minefield minefield;
    private boolean mouseHover;
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
        this.mouseHover = false;
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
        this.status = status;
    }

    public Array<ClickListener> getListeners() {
        return listeners;
    }

    public boolean isMouseHover() {
        return mouseHover;
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
                System.out.println(String.format("Button's x, y: %d, %d. Passed x, y: %f, %f", getX(), getY(), x, y));
                System.out.println(event.getButton());
                minefield.reveal(getX(), getY());
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                mouseHover = true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                mouseHover = false;
            }
        };
    }

    private ClickListener rightClickListener() {
        return new ClickListener(Input.Buttons.RIGHT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println(String.format("RIGHT CLICK---Button's x, y: %d, %d. Passed x, y: %f, %f", getX(), getY(), x, y));
            }
        };
    }
}
