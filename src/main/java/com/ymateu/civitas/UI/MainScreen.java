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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ymateu.civitas.CivitasApp;

public class MainScreen implements Screen {

    private CivitasApp app;
    private Stage stage;
    private Skin skin;

    private Texture backgroundTexture;
    private Texture profileBackgroundTexture;

    private Table content;
    private Table profilesTable;

    private TextField nameField;
    private TextButton playButton;

    private String selectedRole;

    public MainScreen(CivitasApp civitasApp) {
        app = civitasApp;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        if (stage.getActors().size > 0) {
            return;
        }

        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        backgroundTexture = criarTextura(
                new Color(0.04f, 0.06f, 0.10f, 1f)
        );

        profileBackgroundTexture = criarTextura(
                new Color(0.08f, 0.12f, 0.18f, 1f)
        );

        Table root = new Table();

        root.setFillParent(true);

        root.setBackground(
                new TextureRegionDrawable(
                        new TextureRegion(backgroundTexture)
                )
        );

        content = new Table();
        content.pad(30);

        Label title = new Label("Novo Jogo", skin);
        title.setFontScale(1.4f);

        Label subtitle = new Label(
                "Escolha seu cargo",
                skin
        );
        subtitle.setFontScale(0.8f);

        profilesTable = new Table();

        adicionarPerfil(
                "Presidente",
                "icons/profile/presidente.png"
        );

        adicionarPerfil(
                "Prefeito",
                "icons/profile/prefeito.png"
        );

        ScrollPane scrollPane = new ScrollPane(
                profilesTable,
                skin
        );

        scrollPane.setScrollingDisabled(false, true);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setOverscroll(false, false);

        nameField = new TextField("", skin);
        nameField.setMessageText("Digite seu nome");

        playButton = new TextButton("Jogar", skin);

        nameField.setVisible(false);
        playButton.setVisible(false);

        nameField.getStyle().font.getData().setScale(0.6f);
        playButton.getLabel().setFontScale(0.5f);

        playButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (selectedRole == null) {
                    return;
                }

                String nome = nameField.getText().trim();

                if (nome.isEmpty()) {
                    return;
                }

                System.out.println(
                        "Cargo: " + selectedRole
                );

                System.out.println(
                        "Nome: " + nome
                );
                app.changeScreen(CivitasApp.GAME);
            }
        });

        content.add(title)
                .padBottom(10);

        content.row();

        content.add(subtitle)
                .padBottom(30);

        content.row();

        content.add(scrollPane)
                .width(700)
                .height(280)
                .padBottom(30);

        content.row();

        content.add(nameField)
                .width(420)
                .height(55)
                .padBottom(15);

        content.row();

        content.add(playButton)
                .width(180)
                .height(55);

        root.add(content)
                .center();

        stage.addActor(root);
    }

    private void adicionarPerfil(
            String nome,
            String caminho
    ) {
        Texture texture = new Texture(
                Gdx.files.internal(caminho)
        );

        ImageButton button = new ImageButton(
                new TextureRegionDrawable(
                        new TextureRegion(texture)
                )
        );

        button.getImage().setScaling(
                Scaling.fit
        );

        Label label = new Label(
                nome,
                skin
        );

        label.setFontScale(0.7f);

        Table profile = new Table();

        profile.setBackground(
                new TextureRegionDrawable(
                        new TextureRegion(profileBackgroundTexture)
                )
        );

        profile.pad(15);

        profile.add(button)
                .width(180)
                .height(180);

        profile.row();

        profile.add(label)
                .padTop(10);

        profilesTable.add(profile)
                .width(220)
                .height(250)
                .pad(10);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(
                    ChangeEvent event,
                    Actor actor
            ) {
                selecionarPerfil(nome);
            }
        });
    }

    private void selecionarPerfil(String role) {
        selectedRole = role;

        nameField.setVisible(true);
        playButton.setVisible(true);

        nameField.setText("");
        nameField.setMessageText(
                "Digite seu nome"
        );

        stage.setKeyboardFocus(nameField);
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
        Gdx.gl.glClearColor(
                0.04f,
                0.06f,
                0.10f,
                1f
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
    public void resize(
            int width,
            int height
    ) {
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
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();

        backgroundTexture.dispose();
        profileBackgroundTexture.dispose();
    }
}