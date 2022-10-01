package com.acl.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FloorManager {
    private char[][] table;
    private int width;
    private int height;

    private final String floor;

    public FloorManager(String floor) {
        this.floor = floor;
        this.createLevel();
    }

    public void createLevel() {
        FileHandle file = Gdx.files.internal(floor);
        String[] tabString = file.readString().split("\\n");

        width = 5;
        height = 3;

        // We retrieve the content of the floor, in the form of a character matrix
        table = new char[width][height];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                table[i][j] = tabString[j].charAt(i);
            }
        }
    }

    public void saveLevel() {
        try {

            File file = new File("floor2.txt");

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < height; ++i) {
                for (int j = 0; j < width; ++j) {
                    bw.write(table[j][i]);
                }
                bw.write("\n");
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
