package com.acl.game;

import com.acl.ObstACLTower;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        Graphics.DisplayMode displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode();
        int displayWidthBorder = displayMode.width / 10;
        int displayHeightBorder = displayMode.height / 7;
        config.setWindowedMode(displayMode.width-displayWidthBorder, displayMode.height-displayHeightBorder);
        config.setWindowPosition(displayWidthBorder/2,displayHeightBorder/2);
        config.setResizable(false);
        config.setForegroundFPS(60);
        config.setTitle("ObstACL Tower");
        new Lwjgl3Application(new ObstACLTower(), config);
    }
}
