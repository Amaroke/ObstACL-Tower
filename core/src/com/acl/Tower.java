package com.acl;

import com.acl.datas.elements.*;
import com.acl.datas.elements.monsters.BaseMonster;
import com.acl.datas.elements.monsters.Monster;
import com.acl.listeners.CollisionListener;
import com.acl.managers.FloorManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Tower {

    private World world;
    private Player player;
    private float height;
    private ArrayList<Element> elements;
    private ArrayList<Monster> monsters;
    private boolean victory;
    private boolean defeat;

    private int trapDamage = 25;
    private boolean allEnemiesAreDead = false;
    private int score = 0;

    private int stageNumber;

    private CollisionListener collisionListener;

    public Tower() {
        createTower();
    }

    private void createTower() {
        world = new World(new Vector2(0, 0), true);

        this.elements = new ArrayList<>();
        this.monsters = new ArrayList<>();

        this.height = 9 * 16;

        FloorManager floorManager = new FloorManager("floor.txt");
        char[][] table = floorManager.getTable();
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[i].length; ++j) {
                createElement(table[i][j], i, j);
            }
        }

        setCollisionListener(new CollisionListener());
        this.getWorld().setContactListener(this.getCollisionListener());
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
        Vector2 position = new Vector2(i * 16, getHeight() - j * 16);
        switch(letter) {
            case 'P' -> {
                player = new Player(position);
                player.configureBodyDef();
                player.createBody(getWorld());
                player.setFixture(  );
            }
            case 'W' -> element = new Wall(position);
            case 'S' -> element = new Stair(position);
            case 'C' -> element = new Chest(position);
            case 'T' -> element = new Trap(position);
            case 'B' -> element = new BreakableObject(position);
            case 'M' -> {
                Monster monster = new BaseMonster(position);
                element = monster;
                addMonster(monster);
            }
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

    public void update() {
        if (!victory && !defeat) {
            // On met à jour la position des éléments dans le monde
            for (Element e : this.elements) {
                e.setPosition(e.getBody().getPosition());
            }

            if(this.player.getHp() == 0){
                this.endOfTheGameLost();
            }

            if(this.allEnemiesAreDead){
                this.endOfTheStageWon();
            }

            if(this.getCollisionListener().isPlayerCollidesWithStairs()){
                this.endOfTheStageWon();
            }

            if(this.getCollisionListener().isPlayerCollidesWithChest()){
                System.out.println("à voir");
            }

            if(this.getCollisionListener().isPlayerCollidesWithTrap()){
                this.getPlayer().setHp( this.getPlayer().getHp() - this.trapDamage);
            }

            if(this.getCollisionListener().isWeaponsCollidesWithBreakableObject()){
                System.out.println("Affichage du loot de la caisse");
            }
            //We move all the monsters
            for (Monster m : this.monsters) {
                m.Move();
            }
        }
    }

    public void endOfTheGameLost() {
        //When the game is lost, we reset the score.
        defeat = true;
        setScore(0);

        this.getWorld().dispose();
    }

    public void endOfTheStageWon() {
        //When the game is won, we go to the next stage.
        victory = true;
        this.getWorld().dispose();

        this.stageNumber++;
        createTower();
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

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void addMonster(Monster monster) {
        this.monsters.add(monster);
    }

    private float getHeight() {
        return this.height;
    }

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public boolean isDefeat() {
        return defeat;
    }

    public void setDefeat(boolean defeat) {
        this.defeat = defeat;
    }

    public int getScore() {
        return score;
    }

    public CollisionListener getCollisionListener() {
        return collisionListener;
    }

    public void setCollisionListener(CollisionListener collisionListener) {
        this.collisionListener = collisionListener;
    }

    public void setScore(int score) {
        this.score = score;
    }
}


