package com.ymateu.civitas;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config =
                new Lwjgl3ApplicationConfiguration();

        config.setTitle("Civitas");
        config.setWindowedMode(1200, 600);
        config.setForegroundFPS(60);

        new Lwjgl3Application(new CivitasApp(), config);
    }
}