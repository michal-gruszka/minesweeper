package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class PlayScreen extends ScreenAdapter {

    private static final int COLUMNS = 16;
    private static final int ROWS = 16;
    private static final int FIELD_SIZE = MinesweeperGame.HEIGHT / ROWS;
    private static final int BUTTON_WIDTH = 119;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BUTTON_MARGIN = 20;
    private static final int BUTTON_XPOS = MinesweeperGame.WIDTH - BUTTON_WIDTH - BUTTON_MARGIN;
    private static final int RESTART_BUTTON_YPOS = BUTTON_HEIGHT + BUTTON_MARGIN;
    private static final int BOMB_COUNTER_XPOS = 520;
    private static final int BOMB_COUNTER_YPOS = 440;

    // texture names
    private static final String ATLAS = "play_screen.atlas";
    private static final String FIELD_COVERED = "field_covered";
    private static final String FIELD_COVERED_HOVER = "field_covered_hover";
    private static final String FIELD_FLAGGED = "field_flagged";
    private static final String FIELD_FLAGGED_HOVER = "field_flagged_hover";
    private static final String FIELD_QUESTION_MARK = "field_question_mark";
    private static final String FIELD_QUESTION_MARK_HOVER = "field_question_mark_hover";
    private static final String FIELD_NONE = "field_none";
    private static final String FIELD_ONE = "field_one";
    private static final String FIELD_TWO = "field_two";
    private static final String FIELD_THREE = "field_three";
    private static final String FIELD_FOUR = "field_four";
    private static final String FIELD_FIVE = "field_five";
    private static final String FIELD_SIX = "field_six";
    private static final String FIELD_SEVEN = "field_seven";
    private static final String FIELD_EIGHT = "field_eight";
    private static final String FIELD_BOMB = "field_bomb";
    private static final String RESTART_BUTTON = "restart_button_silver";
    private static final String RESTART_BUTTON_HOVER = "restart_button_gold";

    private MinesweeperGame game;
    private SpriteBatch batch;
    private TextureAtlas atlas;
    private Skin skin;
    private Stage stage;
    private Minefield minefield;
    private BitmapFont font;

    public PlayScreen(MinesweeperGame game) {
        this.game = game;
        this.batch = game.getSpriteBatch();
        this.atlas = new TextureAtlas(Gdx.files.internal(ATLAS));
        this.skin = new Skin(atlas);
        this.minefield = new Minefield(COLUMNS, ROWS, 40);
        this.stage = new Stage(new ScreenViewport());
        initializeStage();
        this.font = new BitmapFont();
        font.setColor(0, 0, 0, 1);
    }

    private void initializeStage() {
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                Actor actor = createFieldActor(minefield.getField(x, y));
                stage.addActor(actor);
            }
        }
        stage.addActor(createRestartButton(skin));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderBackground(game.getShapeRenderer());
        stage.act();
        stage.draw();
        renderFields();
        renderBombCounter();
    }

    private void renderFields() {
        batch.begin();
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS; y++) {
                Sprite sprite = createFieldSprite(minefield.getField(x, y));
                sprite.setPosition(x * FIELD_SIZE, y * FIELD_SIZE);
                sprite.draw(batch);
            }
        }
        batch.end();
    }

    private void renderBombCounter() {
        String bombCounter = String.format("bombs: %d / %d", minefield.getFlags(), minefield.getBombs());
        batch.begin();
        font.draw(batch, bombCounter, BOMB_COUNTER_XPOS, BOMB_COUNTER_YPOS);
        batch.end();
    }

    // TODO: this code is copied from MainMenuScreen, create common superclass for screens or something
    private void renderBackground(ShapeRenderer renderer) {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.rect(
                0,
                0,
                MinesweeperGame.WIDTH,
                MinesweeperGame.HEIGHT,
                Color.DARK_GRAY,
                Color.DARK_GRAY,
                Color.LIGHT_GRAY,
                Color.LIGHT_GRAY
        );
        renderer.end();
    }

    private Actor createFieldActor(Field field) {
        Actor actor = new Actor();
        actor.setBounds(FIELD_SIZE * field.getX(), FIELD_SIZE * field.getY(), FIELD_SIZE, FIELD_SIZE);
        for (EventListener el: field.getListeners())
            actor.addListener(el);

        return actor;
    }

    private Sprite createFieldSprite(Field field) {
        String textureName = "";

        if (field.getStatus() == Field.Status.REVEALED) {
            switch (field.getContent()) {
                case EMPTY:
                    textureName = FIELD_NONE;
                    break;
                case ONE:
                    textureName = FIELD_ONE;
                    break;
                case TWO:
                    textureName = FIELD_TWO;
                    break;
                case THREE:
                    textureName = FIELD_THREE;
                    break;
                case FOUR:
                    textureName = FIELD_FOUR;
                    break;
                case FIVE:
                    textureName = FIELD_FIVE;
                    break;
                case SIX:
                    textureName = FIELD_SIX;
                    break;
                case SEVEN:
                    textureName = FIELD_SEVEN;
                    break;
                case EIGHT:
                    textureName = FIELD_EIGHT;
                    break;
                case BOMB:
                    textureName = FIELD_BOMB;
                    break;
            }
        } else if (field.getStatus() == Field.Status.COVERED) {
            textureName = field.isMouseOver() ? FIELD_COVERED_HOVER : FIELD_COVERED;

        } else if (field.getStatus() == Field.Status.FLAG) {
            textureName = field.isMouseOver() ? FIELD_FLAGGED_HOVER : FIELD_FLAGGED;

        } else if (field.getStatus() == Field.Status.QUESTION_MARK) {
            textureName = field.isMouseOver() ? FIELD_QUESTION_MARK_HOVER : FIELD_QUESTION_MARK;
        }

        if (textureName == "") throw new RuntimeException("Unexpected field status and/or condition");

        return new Sprite(atlas.findRegion(textureName));
    }

    private ImageButton createRestartButton(Skin skin) {

        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(RESTART_BUTTON);
        buttonStyle.over = skin.getDrawable(RESTART_BUTTON_HOVER);

        ImageButton button = new ImageButton(buttonStyle);
        button.setPosition(BUTTON_XPOS, RESTART_BUTTON_YPOS);
        button.addListener(restartButtonListener());

        return button;
    }

    private ClickListener restartButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(new PlayScreen(game));
            }
        };
    }

    @Override
    public void dispose() {
        super.dispose();
        this.atlas.dispose();
        this.skin.dispose();
        this.stage.dispose();
    }
}
