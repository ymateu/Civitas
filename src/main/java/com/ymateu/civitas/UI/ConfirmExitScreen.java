package com.ymateu.civitas.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ymateu.civitas.CivitasApp;
import com.ymateu.civitas.UI.manager.UIAssetManager;

public class ConfirmExitScreen implements Screen {

    private CivitasApp app;
    private Stage stage;

    private UIAssetManager uiAssets;

    private Texture backgroundTexture;
    private Texture panelTexture;

    public ConfirmExitScreen(CivitasApp civitasApp) {
        app = civitasApp;

        uiAssets = app.getUIAssets();

        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        if (stage.getActors().size > 0) {
            return;
        }

        Skin skin = uiAssets.getSkin();

        backgroundTexture = criarTextura(
                uiAssets.getBackgroundColor()
        );

        panelTexture = criarTextura(
                uiAssets.getPanelColor()
        );

        Table root = new Table();
        root.setFillParent(true);

        root.setBackground(
                new TextureRegionDrawable(
                        new TextureRegion(backgroundTexture)
                )
        );

        Table panel = new Table();

        panel.setBackground(
                new TextureRegionDrawable(
                        new TextureRegion(panelTexture)
                )
        );

        panel.pad(40);

        Label title = new Label(
                "Deseja mesmo sair?",
                skin
        );

        title.setFontScale(1.2f);

        Label subtitle = new Label(
                "Seu progresso será mantido.",
                skin
        );

        subtitle.setFontScale(0.55f);

        TextButton exit =
                new TextButton("Sair", skin);

        TextButton cancel =
                new TextButton("Voltar", skin);

        exit.getLabel().setFontScale(0.5f);
        cancel.getLabel().setFontScale(0.5f);

        exit.addListener(new ChangeListener() {
            @Override
            public void changed(
                    ChangeEvent event,
                    Actor actor
            ) {
                Gdx.app.exit();
            }
        });

        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(
                    ChangeEvent event,
                    Actor actor
            ) {
                app.changeScreen(CivitasApp.MENU);
            }
        });

        panel.add(title)
                .colspan(2)
                .padBottom(12);

        panel.row();

        panel.add(subtitle)
                .colspan(2)
                .padBottom(35);

        panel.row();

        panel.add(exit)
                .width(150)
                .height(55)
                .padRight(10);

        panel.add(cancel)
                .width(150)
                .height(55)
                .padLeft(10);

        root.add(panel)
                .width(420)
                .pad(30);

        stage.addActor(root);
    }

    private Texture criarTextura(Color color) {
        Pixmap pixmap = new Pixmap(
                1,
                1,
                Pixmap.Format.RGBA8888
        );

        pixmap.setColor(color);
        pixmap.fill();

        Texture texture = new Texture(pixmap);

        pixmap.dispose();

        return texture;
    }

    @Override
    public void render(float delta) {
        Color color =
                uiAssets.getBackgroundColor();

        Gdx.gl.glClearColor(
                color.r,
                color.g,
                color.b,
                color.a
        );

        Gdx.gl.glClear(
                GL20.GL_COLOR_BUFFER_BIT
        );

        stage.act(
                Math.min(delta, 1f / 30f)
        );

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(
                width,
                height,
                true
        );
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        if (Gdx.input.getInputProcessor() == stage) {
            Gdx.input.setInputProcessor(null);
        }
    }

    @Override
    public void dispose() {
        if (Gdx.input.getInputProcessor() == stage) {
            Gdx.input.setInputProcessor(null);
        }

        stage.dispose();

        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }

        if (panelTexture != null) {
            panelTexture.dispose();
        }
    }
}