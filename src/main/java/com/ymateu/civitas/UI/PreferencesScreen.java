package com.ymateu.civitas.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ymateu.civitas.CivitasApp;
import com.badlogic.gdx.scenes.scene2d.Event;

public class PreferencesScreen implements Screen {

    private CivitasApp app;
    private Stage stage;

    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;

    public PreferencesScreen(CivitasApp civitasApp) {
        app = civitasApp;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        final Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );
        final Slider volumeSlider = new Slider(0f, 1f, 0.1f,false, skin );

        final CheckBox musicCheckbox = new CheckBox(null, skin);
        final TextButton backButton = new TextButton("Voltar", skin, "small");

        volumeMusicSlider.setValue( app.getPreferences().getMusicVolume() );
        volumeMusicSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                app.getPreferences().setMusicVolume( volumeMusicSlider.getValue() );
                return false;
            }
        });

        volumeSlider.setValue( app.getPreferences().getSoundVolume() );
        volumeSlider.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                app.getPreferences().setSoundVolume( volumeSlider.getValue() );
                return false;
            }
        });

        musicCheckbox.setChecked( app.getPreferences().isMusicEnabled() );
        musicCheckbox.addListener( new EventListener() {
            @Override
            public boolean handle(Event event) {
                boolean enabled = musicCheckbox.isChecked();
                app.getPreferences().setMusicEnabled( enabled );
                return false;
            }
        });

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                app.changeScreen(CivitasApp.MENU);
            }
        });


        titleLabel = new Label( "Configurações", skin );
        volumeMusicLabel = new Label( "Musica", skin );
        volumeSoundLabel = new Label( "Volume", skin );
        musicOnOffLabel = new Label( "Desligar a Musica", skin );

        table.add(titleLabel);
        table.row();
        table.add(volumeMusicLabel);
        table.add(volumeMusicSlider);
        table.row();
        table.add(musicOnOffLabel);
        table.add(musicCheckbox);
        table.row();
        table.add(volumeSoundLabel);
        table.add(volumeSlider);
        table.row();
        table.add(backButton);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
    }

}
