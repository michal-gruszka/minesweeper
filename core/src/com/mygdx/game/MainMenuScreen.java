package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends ScreenAdapter {

    private static final String ATLAS = "pack.atlas";
    private static final String LOGO = "logo";
    private static final int LOGO_XPOS = 30;
    private static final int LOGO_YPOS = 350;
    private static final String PLAY_BUTTON = "play_button_silver";
    private static final String PLAY_BUTTON_HOVER = "play_button_gold";
    private static final String HIGHSCORES_BUTTON = "highscores_button_silver";
    private static final String HIGHSCORES_BUTTON_HOVER = "highscores_button_gold";
    private static final String ABOUT_BUTTON = "about_button_silver";
    private static final String ABOUT_BUTTON_HOVER = "about_button_gold";
    private static final String EXIT_BUTTON = "exit_button_silver";
    private static final String EXIT_BUTTON_HOVER = "exit_button_gold";

    private static final int FIRST_MENU_ITEM_YPOS = (int) (MinesweeperGame.HEIGHT * 0.5);
    private static final int BUTTON_WIDTH = 190;
    private static final int BUTTON_HEIGHT = 40;
    private static final int BUTTON_SPACING = 20;
    private static final int BUTTON_XPOS = (MinesweeperGame.WIDTH - BUTTON_WIDTH) / 2;
    private static final int PLAY_BUTTON_YPOS = FIRST_MENU_ITEM_YPOS;
    private static final int HIGHSCORES_BUTTON_YPOS = FIRST_MENU_ITEM_YPOS - (BUTTON_HEIGHT + BUTTON_SPACING) * 1;
    private static final int ABOUT_BUTTON_YPOS = FIRST_MENU_ITEM_YPOS - (BUTTON_HEIGHT + BUTTON_SPACING) * 2;
    private static final int EXIT_BUTTON_YPOS = FIRST_MENU_ITEM_YPOS - (BUTTON_HEIGHT + BUTTON_SPACING) * 3;

    private MinesweeperGame game;
    private SpriteBatch batch;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin buttonSkin;
    private Sprite logo;

    public MainMenuScreen(MinesweeperGame game) {
        this.game = game;
        this.batch = game.getSpriteBatch();
        this.atlas = new TextureAtlas(Gdx.files.internal(ATLAS));
        this.buttonSkin = new Skin(atlas);
        this.logo = new Sprite(atlas.findRegion(LOGO));
        this.stage = new Stage(new ScreenViewport());

        logo.setPosition(LOGO_XPOS, LOGO_YPOS);
        stage.addActor(createPlayButton(buttonSkin));
        stage.addActor(createHighscoresButton(buttonSkin));
        stage.addActor(createAboutButton(buttonSkin));
        stage.addActor(createExitButton(buttonSkin));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderBackground(game.getShapeRenderer());
        batch.begin();
        logo.draw(batch);
        batch.end();
        stage.act();
        stage.draw();
    }

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

    private ImageButton createPlayButton(Skin skin) {

        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(PLAY_BUTTON);
        buttonStyle.over = skin.getDrawable(PLAY_BUTTON_HOVER);

        ImageButton button = new ImageButton(buttonStyle);
        button.setPosition(BUTTON_XPOS, PLAY_BUTTON_YPOS);
        button.addListener(playButtonListener());

        return button;
    }

    private ImageButton createHighscoresButton(Skin skin) {

        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(HIGHSCORES_BUTTON);
        buttonStyle.over = skin.getDrawable(HIGHSCORES_BUTTON_HOVER);

        ImageButton button = new ImageButton(buttonStyle);
        button.setPosition(BUTTON_XPOS, HIGHSCORES_BUTTON_YPOS);
        button.addListener(highscoresButtonListener());

        return button;
    }

    private ImageButton createAboutButton(Skin skin) {

        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(ABOUT_BUTTON);
        buttonStyle.over = skin.getDrawable(ABOUT_BUTTON_HOVER);

        ImageButton button = new ImageButton(buttonStyle);
        button.setPosition(BUTTON_XPOS, ABOUT_BUTTON_YPOS);
        button.addListener(aboutButtonListener());

        return button;
    }

    private ImageButton createExitButton(Skin skin) {

        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(EXIT_BUTTON);
        buttonStyle.over = skin.getDrawable(EXIT_BUTTON_HOVER);

        ImageButton button = new ImageButton(buttonStyle);
        button.setPosition(BUTTON_XPOS, EXIT_BUTTON_YPOS);
        button.addListener(exitButtonListener());

        return button;
    }

    private ClickListener playButtonListener() {
        return new ClickListener();
    }

    private ClickListener highscoresButtonListener() {
        return new ClickListener();
    }

    private ClickListener aboutButtonListener() {
        return new ClickListener();
    }

    private ClickListener exitButtonListener() {
        return new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Button clicked");
                Gdx.app.exit();
            }
        };
    }

    @Override
    public void dispose() {
        super.dispose();
        this.atlas.dispose();
        this.buttonSkin.dispose();
        this.stage.dispose();
    }
}
