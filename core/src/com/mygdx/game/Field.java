package com.mygdx.game;

public class Field {

    private int x;
    private int y;
    private Content content;
    private Status status;

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
}
