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

    private static final String EXIT_BUTTON = "exit_button_silver";
    private static final String EXIT_BUTTON_HOVER = "exit_button_gold";

    private MinesweeperGame game;
    private Stage stage;
    private TextureAtlas buttonAtlas;
    private Skin buttonSkin;

    public MainMenuScreen(MinesweeperGame game) {
        this.game = game;

        buttonAtlas = new TextureAtlas(Gdx.files.internal("pack.atlas"));
        buttonSkin = new Skin(buttonAtlas);

        stage = new Stage(new ScreenViewport());
        stage.addActor(createExitButton(buttonSkin));
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.getShapeRenderer().begin(ShapeRenderer.ShapeType.Filled);
        game.getShapeRenderer().rect(
                0,
                0,
                MinesweeperGame.WIDTH,
                MinesweeperGame.HEIGHT,
                Color.DARK_GRAY,
                Color.DARK_GRAY,
                Color.LIGHT_GRAY,
                Color.LIGHT_GRAY
                );
        game.getShapeRenderer().end();
        stage.act();
        stage.draw();
    }

    private ImageButton createExitButton(Skin skin) {

        ImageButtonStyle buttonStyle = new ImageButtonStyle();
        buttonStyle.up = skin.getDrawable(EXIT_BUTTON);
        buttonStyle.over = skin.getDrawable(EXIT_BUTTON_HOVER);

        ImageButton exitButton = new ImageButton(buttonStyle);

        exitButton.setPosition(
                (MinesweeperGame.WIDTH - exitButton.getWidth()) / 2,
                (MinesweeperGame.HEIGHT - exitButton.getHeight()) / 2
        );
        exitButton.addListener(exitButtonListener());
        return exitButton;
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
