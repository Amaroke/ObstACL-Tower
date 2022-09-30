package com.acl.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class FloorManager {
    private final char[][] table;
    private final int width;
    private final int height;

    public FloorManager(String floor) {
        FileHandle file = Gdx.files.internal(floor);
        String[] tabString = file.readString().split("\\n");

        width = 3;
        height = 3;

        // We retrieve the content of the floor, in the form of a character matrix
        table = new char[width][height];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                table[j][i] = tabString[i].charAt(j);
            }
        }
    }

    public void createLevel() {
        // TODO : Déplacer le truc du constructeur ici ?
    }

    public void saveLevel() {
        // TODO : À implémenter
    }

    public char[][] getTable() {
        return table;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
