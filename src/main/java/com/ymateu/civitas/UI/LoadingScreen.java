package com.ymateu.civitas.UI;

import com.badlogic.gdx.Screen;
import com.ymateu.civitas.CivitasApp;

public class LoadingScreen implements Screen {

    private CivitasApp app;

    public LoadingScreen(CivitasApp civitasApp) {
        app = civitasApp;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        app.changeScreen(CivitasApp.MENU);
    }

    @Override
    public void resize(int width, int height) {
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
    }

}
