package com.ymateu.civitas.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ymateu.civitas.CivitasApp;
import com.ymateu.civitas.UI.manager.UIAssetManager;

public class GameScreen implements Screen {

    private final CivitasApp app;
    private final Stage stage;
    private final UIAssetManager uiAssets;
    private final Skin skin;

    private Table dropdown;
    private Texture navbarTexture;
    private Texture dropdownTexture;

    public GameScreen(CivitasApp civitasApp) {
        app = civitasApp;
        uiAssets = app.getUIAssets();
        skin = uiAssets.getSkin();

        stage = new Stage(new ScreenViewport());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        if (stage.getActors().size > 0) {
            return;
        }

        navbarTexture = criarTextura(uiAssets.getNavbarColor());
        dropdownTexture = criarTextura(new com.badlogic.gdx.graphics.Color(
                0.12f, 0.16f, 0.23f, 1f
        ));

        Table root = new Table();
        root.setFillParent(true);
        root.top();

        Table navbar = new Table();

        navbar.setBackground(
                new TextureRegionDrawable(
                        new TextureRegion(navbarTexture)
                )
        );

        Label logo = new Label("CIVITAS", skin);
        logo.setFontScale(1.1f);

        ImageButton menuButton = criarBotaoMenu();

        navbar.add(logo)
                .left()
                .expandX()
                .padLeft(20);

        navbar.add(menuButton)
                .width(60)
                .height(60)
                .padRight(10);

        root.add(navbar)
                .growX()
                .height(60);

        stage.addActor(root);

        criarDropdown();

        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dropdown.setVisible(!dropdown.isVisible());
            }
        });
    }

    private ImageButton criarBotaoMenu() {
        TextureRegionDrawable drawable = new TextureRegionDrawable(
                new TextureRegion(uiAssets.get3BarIcon())
        );

        ImageButton.ImageButtonStyle style =
                new ImageButton.ImageButtonStyle();

        style.imageUp = drawable;
        style.imageDown = drawable;
        style.imageChecked = drawable;

        ImageButton button = new ImageButton(style);

        button.getImage().setScaling(Scaling.fit);

        return button;
    }

    private void criarDropdown() {
        dropdown = new Table();

        dropdown.setBackground(
                new TextureRegionDrawable(
                        new TextureRegion(dropdownTexture)
                )
        );

        dropdown.setVisible(false);

        TextButton configuracoes = new TextButton(
                "Configurações",
                skin
        );

        configuracoes.getLabel().setFontScale(0.5f);

        configuracoes.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dropdown.setVisible(false);
                app.changeScreen(CivitasApp.PREFERENCES);
            }
        });

        dropdown.add(configuracoes)
                .width(160)
                .height(50)
                .pad(10);

        dropdown.setSize(180, 70);

        posicionarDropdown(
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight()
        );

        stage.addActor(dropdown);
    }

    private void posicionarDropdown(int width, int height) {
        dropdown.setPosition(
                width - dropdown.getWidth() - 10,
                height - 60 - dropdown.getHeight() - 10
        );
    }

    private Texture criarTextura(com.badlogic.gdx.graphics.Color color) {
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
        com.badlogic.gdx.graphics.Color color =
                uiAssets.getBackgroundColor();

        Gdx.gl.glClearColor(
                color.r,
                color.g,
                color.b,
                color.a
        );

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(delta, 1f / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        if (dropdown != null) {
            posicionarDropdown(width, height);
        }
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

        if (navbarTexture != null) {
            navbarTexture.dispose();
        }

        if (dropdownTexture != null) {
            dropdownTexture.dispose();
        }
    }
}