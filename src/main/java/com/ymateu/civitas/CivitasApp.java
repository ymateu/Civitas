package com.ymateu.civitas;

import com.badlogic.gdx.Game;
import com.ymateu.civitas.UI.*;
import com.ymateu.civitas.UI.manager.UIAssetManager;
import com.ymateu.civitas.UI.preferences.GamePreferences;

public class CivitasApp extends Game {

    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private ConfirmExitScreen confirmExitScreen;
    private GameScreen gameScreen;

    private GamePreferences gamePreferences;
    private UIAssetManager uiAssets;

    public static final int MENU = 0;
    public static final int PREFERENCES = 1;
    public static final int APPLICATION = 2;
    public static final int CONFIRM_EXIT = 3;
    public static final int GAME = 4;

    public GamePreferences getPreferences() {
        return gamePreferences;
    }

    public UIAssetManager getUIAssets() {
        return uiAssets;
    }

    @Override
    public void create() {
        gamePreferences = new GamePreferences();

        uiAssets = UIAssetManager.getInstance();
        uiAssets.load();

        loadingScreen = new LoadingScreen(this);

        setScreen(loadingScreen);
    }

    public void changeScreen(int screen) {
        switch (screen) {

            case MENU:
                if (menuScreen == null) {
                    menuScreen = new MenuScreen(this);
                }
                setScreen(menuScreen);
                break;

            case PREFERENCES:
                if (preferencesScreen == null) {
                    preferencesScreen = new PreferencesScreen(this);
                }
                setScreen(preferencesScreen);
                break;

            case APPLICATION:
                if (mainScreen == null) {
                    mainScreen = new MainScreen(this);
                }
                setScreen(mainScreen);
                break;

            case CONFIRM_EXIT:
                if (confirmExitScreen == null) {
                    confirmExitScreen = new ConfirmExitScreen(this);
                }
                setScreen(confirmExitScreen);
                break;

            case GAME:
                if (gameScreen == null) {
                    gameScreen = new GameScreen(this);
                }
                setScreen(gameScreen);
                break;
        }
    }

    @Override
    public void dispose() {
        super.dispose();

        if (loadingScreen != null) {
            loadingScreen.dispose();
        }

        if (preferencesScreen != null) {
            preferencesScreen.dispose();
        }

        if (menuScreen != null) {
            menuScreen.dispose();
        }

        if (mainScreen != null) {
            mainScreen.dispose();
        }

        if (confirmExitScreen != null) {
            confirmExitScreen.dispose();
        }

        if (gameScreen != null) {
            gameScreen.dispose();
        }

        if (uiAssets != null) {
            uiAssets.dispose();
        }
    }
}