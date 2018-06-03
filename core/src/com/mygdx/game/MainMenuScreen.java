package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends ScreenAdapter {

    private static final String PLAY_BUTTON = "play_button_silver";
    private static final String PLAY_BUTTON_HOVER = "play_button_gold";
    private static final String HIGHSCORES_BUTTON = "highscores_button_silver";
    private static final String HIGHSCORES_BUTTON_HOVER = "highscores_button_gold";
    private static final String ABOUT_BUTTON = "about_button_silver";
    private static final String ABOUT_BUTTON_HOVER = "about_button_gold";
    private static final String EXIT_BUTTON = "exit_button_silver";
    private static final String EXIT_BUTTON_HOVER = "exit_button_gold";
    private static final int FIRST_MENU_ITEM_YPOS = (int) (MinesweeperGame.HEIGHT * 0.5);
    private static final int BUTTON_HEIGHT = 40;
    private static final int BUTTON_SPACING = 20;

    private static final int PLAY_BUTTON_YPOS = FIRST_MENU_ITEM_YPOS;
    private static final int HIGHSCORES_BUTTON_YPOS = FIRST_MENU_ITEM_YPOS - (BUTTON_HEIGHT + BUTTON_SPACING) * 1;
    private static final int ABOUT_BUTTON_YPOS = FIRST_MENU_ITEM_YPOS - (BUTTON_HEIGHT + BUTTON_SPACING) * 2;
    private static final int EXIT_BUTTON_YPOS = FIRST_MENU_ITEM_YPOS - (BUTTON_HEIGHT + BUTTON_SPACING) * 3;

    private MinesweeperGame game;
    private Stage stage;
    private TextureAtlas buttonAtlas;
    private Skin buttonSkin;

    public MainMenuScreen(MinesweeperGame game) {
        System.out.println(PLAY_BUTTON_YPOS);
        this.game = game;

        buttonAtlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));
        buttonSkin = new Skin(buttonAtlas);

        stage = new Stage(new ScreenViewport());
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

        ImageButton exitButton = new ImageButton(buttonStyle);

        exitButton.setPosition(
                (MinesweeperGame.WIDTH - exitButton.getWidth()) / 2,
                PLAY_BUTTON_YPOS
        );
        exitButton.addListener(playButtonListener());
        return exitButton;
    }

    private ImageButton createHighscoresButton(Skin skin) {

        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(HIGHSCORES_BUTTON);
        buttonStyle.over = skin.getDrawable(HIGHSCORES_BUTTON_HOVER);

        ImageButton exitButton = new ImageButton(buttonStyle);

        exitButton.setPosition(
                (MinesweeperGame.WIDTH - exitButton.getWidth()) / 2,
                HIGHSCORES_BUTTON_YPOS
        );
        exitButton.addListener(highscoresButtonListener());
        return exitButton;
    }

    private ImageButton createAboutButton(Skin skin) {

        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(ABOUT_BUTTON);
        buttonStyle.over = skin.getDrawable(ABOUT_BUTTON_HOVER);

        ImageButton exitButton = new ImageButton(buttonStyle);

        exitButton.setPosition(
                (MinesweeperGame.WIDTH - exitButton.getWidth()) / 2,
                ABOUT_BUTTON_YPOS
        );
        exitButton.addListener(aboutButtonListener());
        return exitButton;
    }

    private ImageButton createExitButton(Skin skin) {

        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(EXIT_BUTTON);
        buttonStyle.over = skin.getDrawable(EXIT_BUTTON_HOVER);

        ImageButton exitButton = new ImageButton(buttonStyle);

        exitButton.setPosition(
                (MinesweeperGame.WIDTH - exitButton.getWidth()) / 2,
                EXIT_BUTTON_YPOS
        );
        exitButton.addListener(exitButtonListener());
        return exitButton;
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
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                System.out.println("enter");
            }
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                System.out.println("exit");
            }
        };
    }
}
