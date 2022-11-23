package com.acl;

import com.acl.datas.elements.*;
import com.acl.datas.elements.monsters.Guardian;
import com.acl.datas.elements.monsters.Lich;
import com.acl.datas.elements.monsters.Monster;
import com.acl.datas.elements.monsters.Slime;
import com.acl.datas.elements.weapons.FireBall;
import com.acl.datas.elements.weapons.Sword;
import com.acl.datas.elements.weapons.Weapon;
import com.acl.enums.Direction;
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
    private int score;
    private Stair stair;
    private int nbLevel;
    private CollisionListener collisionListener;

    private int weaponCooldown = 0;
    private int pauseTime = 100;

    public Tower() {
        createTower(2, 0);
    }

    public void createTower(int nbLevel, int score) {
        world = new World(new Vector2(0, 0), true);

        this.weapons = new ArrayList<>();
        this.elements = new ArrayList<>();
        this.monsters = new ArrayList<>();

        this.gamePaused = false;
        this.allEnemiesAreDead = false;
        this.victory = false;
        this.defeat = false;

        this.height = 9 * 16;

        this.score = score;
        this.setGamePaused(false);

        this.setNbLevel(nbLevel);

        FloorManager floorManager = new FloorManager("floor" + this.getNbLevel() + ".txt");
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
            case 'S' -> {
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
            case 'L' -> {
                Monster lich = new Lich(position);
                element = lich;
                addMonster(lich);
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

    public void update(KeyboardListener keyboardListener) {
        if (gamePaused) {
            if (pauseTime == 0) {
                this.pauseTime = 100;
                this.getWorld().dispose();
                if (this.isVictory()) {
                    this.createTower(this.getNbLevel() == 3 ? 1 : this.getNbLevel() + 1, this.getScore());
                } else {
                    this.createTower(1, 0);
                }
            }
            pauseTime--;
        }

        weaponCooldown--;

        if (!gamePaused) {
            if (keyboardListener.getUseWeapon()) {
                if (weaponCooldown <= 0) {
                    this.weaponCooldown = 30; //TODO A INCLURE DANS LES CONSTANTES
                    createWeapon();
                }
            }

            if (keyboardListener.getSwapWeapon()) {
                player.swapWeapon();
            }

            // We move player
            getPlayer().setMotion(keyboardListener.getMotion());
            getPlayer().setDirection(keyboardListener.getDirection() != null ? keyboardListener.getDirection() : Direction.NORTH);
        }


        if (!victory && !defeat) {
            if (!gamePaused) {
                // On met à jour la position des éléments dans le monde
                for (Element e : this.elements) {
                    e.setPosition(e.getBody().getPosition());
                }


                for (Weapon w : this.weapons) {
                    w.update();
                    w.setPosition(w.getBody().getPosition());
                    if (w.toDestroy()) {
                        getWeapons().remove(w);
                        deleteElem(w);
                        break;
                    }
                }

                if (this.getCollisionListener().isWeaponCollidesWithMonster()) {
                    Monster m = getMonsterFromBody(getCollisionListener().getMonsterCollidedWithWeapon());
                    Weapon w = getWeaponFromBody(getCollisionListener().getWeaponCollidedWithMonster());
                    getWeapons().remove(w);
                    deleteElem(w);
                    // TODO Adapt to damage
                    m.receiveDamage(5);
                    checkMonsterHealth(m);
                }

                for(Body weapon : this.getCollisionListener().getWeaponsCollideWithWall()) {
                    Weapon w = getWeaponFromBody(weapon);
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
                    this.score += c.giveLoot();
                    deleteElem(c);
                }

                if (this.getCollisionListener().isPlayerCollidesWithTrap()) {
                    Trap t = (Trap) getElementFromBody(getCollisionListener().getTrapCollided());
                    player.receiveDamage(t.getDealDamage());
                }

                if (this.getCollisionListener().isWeaponCollidesWithBreakableObject()) {
                    BreakableObject bo = (BreakableObject) getElementFromBody(getCollisionListener().getBreakableObjectCollidedWithWeapon());
                    this.score += bo.giveLoot();
                    deleteElem(bo);
                }

                if (this.getCollisionListener().isPlayerCollidesWithMonster()) {
                    Monster m = getMonsterFromBody(getCollisionListener().getMonsterCollidedWithPlayer());
                    player.receiveDamage(m.getDmg());
                }

                if (this.player.getHp() == 0) {
                    this.endOfTheGameLost();
                }

                for(Body monster : this.getCollisionListener().getMonstersCollideWithWall()) {
                    Monster m = getMonsterFromBody(monster);
                    m.changeDirection();
                }

                this.getCollisionListener().resetCollideWithWall();

                // We move all the monsters
                for (Monster m : this.monsters) {
                    m.Move();
                }
            }
        }
        if (gamePaused) {
            this.player.setMotion(new Vector2(0, 0));
            for (Monster m : this.monsters) {
                m.getBody().setLinearVelocity(0, 0);
            }
            for (Weapon w : this.weapons) {
                w.getBody().setLinearVelocity(0, 0);
            }
            for (Element e : this.elements) {
                e.getBody().setLinearVelocity(0, 0);
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

    public void endOfTheStageWon() {
        // When the game is won.
        victory = true;
        gamePaused = true;
    }

    private void checkMonsterHealth(Monster m) {
        if (m.getHp() <= 0) {
            getMonsters().remove(m);
            deleteElem(m);
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

    public int getNbLevel() {
        return nbLevel;
    }

    public void setNbLevel(int nbLevel) {
        this.nbLevel = nbLevel;
    }
}





