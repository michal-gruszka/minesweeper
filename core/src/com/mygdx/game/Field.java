package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Field extends ClickListener {

    private int x;
    private int y;
    private Content content;
    private Status status;
    private boolean mouseHover;

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

    public Field(int x, int y, Content content, Status status) {
        this.x = x;
        this.y = y;
        this.content = content;
        this.status = status;
        this.mouseHover = false;
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

    public boolean isMouseHover() {
        return mouseHover;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        System.out.println(String.format("Button's x, y: %d, %d. Passed x, y: %f, %f", this.x, this.y, x, y));
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        this.mouseHover = true;
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        this.mouseHover = false;
    }
}
