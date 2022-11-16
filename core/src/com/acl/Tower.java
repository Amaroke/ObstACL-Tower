package com.acl;

import com.acl.datas.elements.*;
import com.acl.datas.elements.monsters.Guardian;
import com.acl.datas.elements.monsters.Slime;
import com.acl.datas.elements.monsters.Monster;
import com.acl.datas.elements.weapons.FireBall;
import com.acl.datas.elements.weapons.Sword;
import com.acl.datas.elements.weapons.Weapon;
import com.acl.enums.WeaponType;
import com.acl.listeners.CollisionListener;
import com.acl.listeners.KeyboardListener;
import com.acl.managers.FloorManager;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

public class Tower {
    private World world;
    private Player player;
    private float height;
    private ArrayList<Element> elements;
    private ArrayList<Monster> monsters;
    private ArrayList<Weapon> weapons;
    private boolean victory;
    private boolean defeat;
    private boolean gamePaused;
    private boolean allEnemiesAreDead;
    private int score = 0;

    private Stair stair;

    private CollisionListener collisionListener;

    public Tower() {
        createTower();
    }

    public void createTower() {
        world = new World(new Vector2(0, 0), true);

        this.weapons = new ArrayList<>();
        this.elements = new ArrayList<>();
        this.monsters = new ArrayList<>();

        this.gamePaused = false;
        this.allEnemiesAreDead = false;

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
    }

    public void createWeapon() {
        Weapon weapon = new FireBall(getPlayer().getBody().getPosition(), getPlayer().getDirection());
        if (getPlayer().getWeaponType() == WeaponType.SWORD) {
            weapon = new Sword(getPlayer().getBody().getPosition(), getPlayer().getDirection());
        }
        weapon.configureBodyDef();
        weapon.createBody(getWorld());
        weapon.setFixture();
        weapons.add(weapon);
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
        switch (letter) {
            case 'P' -> {
                player = new Player(position);
                player.configureBodyDef();
                player.createBody(getWorld());
                player.setFixture();
            }
            case 'W' -> element = new Wall(position);
            case 'S' ->  {
                this.stair = new Stair(position);
                element = this.stair;
            }
            case 'C' -> element = new Chest(position);
            case 'T' -> element = new Trap(position);
            case 'B' -> element = new BreakableObject(position);
            case 'M' -> {
                Monster slime = new Slime(position);
                element = slime;
                addMonster(slime);
            }
            case 'G' -> {
                Monster guardian = new Guardian(position);
                element = guardian;
                addMonster(guardian);
            }
        }
        if (element != null) {
            // We place the elements
            element.configureBodyDef();
            element.createBody(getWorld());
            element.setFixture();
            this.addElement(element);
        }
    }

    public void update() {
        if (!victory && !defeat) {


            // On met à jour la position des éléments dans le monde
            for (Element e : this.elements) {
                e.setPosition(e.getBody().getPosition());
            }

            for (Weapon w : this.weapons) {
                w.update();
                w.setPosition(w.getBody().getPosition());
                if(w.toDestroy()) {
                    getWeapons().remove(w);
                    deleteElem(w);
                    break;
                }
            }

            if (this.getCollisionListener().isWeaponCollidesWithMonster()) {
                Monster m = getMonsterFromBody(getCollisionListener().getMonsterCollided());
                Weapon w = getWeaponFromBody(getCollisionListener().getWeaponCollided());
                getWeapons().remove(w);
                deleteElem(w);
                // TODO Adapt to damage
                m.receiveDamage(5);
                checkMonsterHealth(m);
            }

            if (this.getCollisionListener().isWeaponCollidesWithWall()) {
                Weapon w = getWeaponFromBody(getCollisionListener().getWeaponCollided());
                getWeapons().remove(w);
                deleteElem(w);
            }

            if (this.monsters.size() == 0) {
                this.allEnemiesAreDead = true;
                //Loading the stair
                this.stair.setLocked(false);
            }

            if (this.player.getHp() <= 0) {
                this.endOfTheGameLost();
            }

            if (this.getCollisionListener().isPlayerCollidesWithStairs() && allEnemiesAreDead) {
                this.endOfTheStageWon();
            }

            if (this.getCollisionListener().isPlayerCollidesWithChest()) {
                Chest c = (Chest) getElementFromBody(getCollisionListener().getChestCollided());
                this.getPlayer().incrementScore(c.giveLoot());
                deleteElem(c);
            }

            if (this.getCollisionListener().isPlayerCollidesWithTrap()) {
                Trap t = (Trap) getElementFromBody(getCollisionListener().getTrapCollided());
                player.receiveDamage(t.getDealDamage());
            }

            if (this.getCollisionListener().isWeaponCollidesWithBreakableObject()) {
                BreakableObject bo = (BreakableObject) getElementFromBody(getCollisionListener().getBreakableObjectCollided());
                this.getPlayer().incrementScore(bo.giveLoot());
                deleteElem(bo);
            }

            if (this.getCollisionListener().isPlayerCollidesWithMonster()) {
                Monster m = getMonsterFromBody(getCollisionListener().getMonsterCollided());
                player.receiveDamage(m.getDmg());
                getCollisionListener().setMonsterCollided(null);
                getCollisionListener().setPlayerCollidesWithMonster(false);
            }
            if (this.player.getHp() == 0) {
                this.endOfTheGameLost();
            }

            if (this.getCollisionListener().isGuardianCollidesWithWall()) {
                Guardian guardian = (Guardian) getElementFromBody(getCollisionListener().getGuardianCollided());
                guardian.chaneDirection();
            }

            // We move all the monsters
            for (Monster m : this.monsters) {
                m.Move();
            }
        }
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public void endOfTheGameLost() {
        // When the game is lost.
        defeat = true;
        gamePaused = true;
        setScore(0);
    }

    private void checkMonsterHealth(Monster m) {
        if (m.getHp() <= 0) {
            getMonsters().remove(m);
            deleteElem(m);
        }
    }

    public void endOfTheStageWon() {
        // When the game is won.
        victory = true;
        gamePaused = true;
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

    public Monster getMonsterFromBody(Body b) {
        for (Monster m : this.getMonsters()) {
            if (b == m.getBody()) {
                return m;
            }
        }
        return null;
    }

    public Weapon getWeaponFromBody(Body b) {
        for (Weapon w : this.getWeapons()) {
            if (b == w.getBody()) {
                return w;
            }
        }
        return null;
    }

    public void deleteElem(Element e) {
        getElements().remove(e);
        this.getWorld().destroyBody(e.getBody());
    }

    public Element getElementFromBody(Body b) {
        for (Element e : this.elements) {
            if (b == e.getBody()) {
                return e;
            }
        }
        return null;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }
}





