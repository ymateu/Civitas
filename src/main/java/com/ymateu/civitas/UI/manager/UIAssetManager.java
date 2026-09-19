package com.ymateu.civitas.UI.manager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.assets.AssetManager;

public class UIAssetManager {

    private static UIAssetManager instance;

    private final AssetManager assetManager;

    public static final Color BACKGROUND_COLOR =
            new Color(0.1f, 0.06f, 0.10f, 1f);

    public static final Color PANEL_COLOR =
            new Color(0.08f, 0.12f, 0.18f, 1f);

    public static final Color NAVBAR_COLOR =
            new Color(0.06f, 0.09f, 0.14f, 1f);

    public static final Color TEXT_COLOR =
            new Color(1f, 1f, 1f, 1f);

    private static final String SKIN =
            "skin/glassy-ui.json";

    private static final String ICON_3BAR =
            "icons/3bar.png";

    private static final String PROFILE_PRESIDENTE =
            "icons/profile/presidente.png";

    private static final String PROFILE_PREFEITO =
            "icons/profile/prefeito.png";

    private UIAssetManager() {
        assetManager = new AssetManager();
    }

    public static UIAssetManager getInstance() {
        if (instance == null) {
            instance = new UIAssetManager();
        }

        return instance;
    }

    public void load() {
        assetManager.load(SKIN, Skin.class);
        assetManager.load(ICON_3BAR, Texture.class);
        assetManager.load(PROFILE_PRESIDENTE, Texture.class);
        assetManager.load(PROFILE_PREFEITO, Texture.class);

        assetManager.finishLoading();
    }

    public Skin getSkin() {
        return assetManager.get(SKIN, Skin.class);
    }

    public Texture get3BarIcon() {
        return assetManager.get(
                ICON_3BAR,
                Texture.class
        );
    }

    public Texture getPresidenteIcon() {
        return assetManager.get(
                PROFILE_PRESIDENTE,
                Texture.class
        );
    }

    public Texture getPrefeitoIcon() {
        return assetManager.get(
                PROFILE_PREFEITO,
                Texture.class
        );
    }

    public Texture getTexture(String path) {
        if (!assetManager.isLoaded(path, Texture.class)) {
            assetManager.load(path, Texture.class);
            assetManager.finishLoading();
        }

        return assetManager.get(
                path,
                Texture.class
        );
    }

    public Color getBackgroundColor() {
        return new Color(BACKGROUND_COLOR);
    }

    public Color getPanelColor() {
        return new Color(PANEL_COLOR);
    }

    public Color getNavbarColor() {
        return new Color(NAVBAR_COLOR);
    }

    public Color getTextColor() {
        return new Color(TEXT_COLOR);
    }

    public void dispose() {
        assetManager.dispose();
        instance = null;
    }
}