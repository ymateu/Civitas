package com.ymateu.civitas;

import com.badlogic.gdx.Game;
import com.ymateu.civitas.UI.*;
import com.ymateu.civitas.UI.preferences.GamePreferences;

public class CivitasApp extends Game{

    private LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private ConfirmExitScreen confirmExitScreen;
    private GamePreferences gamePreferences = new GamePreferences();

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int CONFIRM_EXIT = 3;

    public GamePreferences getPreferences() {
        return this.gamePreferences;
    }

    @Override
    public void create () {
        loadingScreen = new LoadingScreen(this);

        setScreen(loadingScreen);
    }

    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if(mainScreen == null) mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case CONFIRM_EXIT:
                if(mainScreen == null) confirmExitScreen = new ConfirmExitScreen(this);
                this.setScreen(confirmExitScreen);
        }
    }
}