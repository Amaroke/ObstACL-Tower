package com.acl;

import com.acl.datas.elements.*;
import com.acl.managers.FloorManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Tower {

    private World world;
    private Player player;
    private float height;

    private ArrayList<Element> elements;

    public Tower() {
        createTower();
    }

    private void createTower() {
        world = new World(new Vector2(0, 0), true);


        this.elements = new ArrayList<>(9);

        this.height = 9 * 16;

        FloorManager floorManager = new FloorManager("floor.txt");
        char[][] table = floorManager.getTable();
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[i].length; ++j) {
                createElement(table[i][j], i, j);
            }
        }

        //floorManager.saveLevel();
    }

    public Player getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }

    private void createElement(char letter, int i, int j) {
        Element element = null;
        switch(letter) {
            case 'P' -> {
                player = new Player(new Vector2(i * 16, getHeight() - j * 16));
                player.configureBodyDef();
                player.createBody(getWorld());
                player.setFixture();
            }
            case 'W' -> element = new Wall(new Vector2(i * 16, getHeight() - j * 16));
            case 'S' -> element = new Stair(new Vector2(i * 16, getHeight() - j * 16));
            case 'C' -> element = new Chest(new Vector2(i * 16, getHeight() - j * 16));
            case 'T' -> element = new Trap(new Vector2(i * 16, getHeight() - j * 16));
            case 'B' -> element = new BreakableObject(new Vector2(i * 16, getHeight() - j * 16));
        }
        if (element != null) {
            //We place the elements
            element.configureBodyDef();
            element.createBody(getWorld());
            element.setFixture();
            this.addElement(element);
            //System.out.println(letter + "" + element.getPosition().x + " " + element.getPosition().y);
        }
    }

    private void addElement(Element e) {
        this.elements.add(e);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    private float getHeight() {
        return this.height;
    }
}
