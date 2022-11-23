package com.acl.listeners;

import com.acl.enums.UserData;
import com.badlogic.gdx.physics.box2d.*;

import java.util.ArrayList;

public class CollisionListener implements ContactListener {

    private boolean playerCollidesWithStairs = false;
    private boolean playerCollidesWithChest = false;
    private Body chestCollided = null;
    private boolean playerCollidesWithTrap = false;
    private Body trapCollided = null;

    private boolean weaponCollidesWithBreakableObject = false;
    private Body weaponCollidedWithBreakableObject = null;
    private Body breakableObjectCollidedWithWeapon = null;

    private boolean weaponCollidesWithMonster = false;
    private Body weaponCollidedWithMonster = null;
    private Body monsterCollidedWithWeapon = null;

    private boolean playerCollidesWithMonster = false;
    private Body monsterCollidedWithPlayer = null;

    private ArrayList<Body> weaponsCollideWithWall = new ArrayList<>();
    private ArrayList<Body> monstersCollideWithWall = new ArrayList<>();

    @Override
    public void beginContact(Contact contact) {
        Object A = contact.getFixtureA().getBody().getUserData();
        Object B = contact.getFixtureB().getBody().getUserData();

        if ((A == UserData.PLAYER && B == UserData.STAIR) || (A == UserData.STAIR && B == UserData.PLAYER)) {
            this.playerCollidesWithStairs = true;
        }

        if ((A == UserData.PLAYER && B == UserData.CHEST) || (A == UserData.CHEST && B == UserData.PLAYER)) {
            this.playerCollidesWithChest = true;
            this.chestCollided = contact.getFixtureA().getUserData() == UserData.CHEST ?
                    contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
        }

        if (A == UserData.PLAYER && B == UserData.TRAP || (A == UserData.TRAP && B == UserData.PLAYER)) {
            this.playerCollidesWithTrap = true;
            this.trapCollided = contact.getFixtureA().getUserData() == UserData.TRAP ?
                    contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
        }

        if ((A == UserData.WEAPON && B == UserData.BREAKABLE) || (A == UserData.BREAKABLE && B == UserData.WEAPON)) {
            this.weaponCollidesWithBreakableObject = true;
            this.weaponCollidedWithBreakableObject = contact.getFixtureA().getUserData() == UserData.WEAPON ?
                    contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
            this.breakableObjectCollidedWithWeapon = contact.getFixtureA().getUserData() == UserData.BREAKABLE ?
                    contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
        }

        if ((A == UserData.WEAPON && B == UserData.MONSTER) || (A == UserData.MONSTER && B == UserData.WEAPON)) {
            this.weaponCollidesWithMonster = true;
            this.weaponCollidedWithMonster = contact.getFixtureA().getUserData() == UserData.WEAPON ?
                    contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
            this.monsterCollidedWithWeapon = contact.getFixtureA().getUserData() == UserData.MONSTER ?
                    contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
        }

        if ((A == UserData.PLAYER && B == UserData.MONSTER) || (A == UserData.MONSTER && B == UserData.PLAYER)) {
            this.playerCollidesWithMonster = true;
            this.monsterCollidedWithPlayer = contact.getFixtureA().getUserData() == UserData.MONSTER ?
                    contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
        }

        if ((A == UserData.WEAPON && B == UserData.WALL) || (A == UserData.WALL && B == UserData.WEAPON)) {
            this.weaponsCollideWithWall.add((A == UserData.WEAPON) ? contact.getFixtureA().getBody() : contact.getFixtureB().getBody());
        }

        if ((A == UserData.MONSTER && B == UserData.WALL) || (A == UserData.WALL && B == UserData.MONSTER)) {
            this.monstersCollideWithWall.add((A == UserData.MONSTER) ? contact.getFixtureA().getBody() : contact.getFixtureB().getBody());
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

    public boolean isPlayerCollidesWithStairs() {
        boolean value = playerCollidesWithStairs;
        playerCollidesWithStairs = false;
        return value;
    }

    public boolean isPlayerCollidesWithChest() {
        boolean value = playerCollidesWithChest;
        playerCollidesWithChest = false;
        return value;
    }

    public Body getChestCollided() {
        return chestCollided;
    }

    public boolean isPlayerCollidesWithTrap() {
        boolean value = playerCollidesWithTrap;
        playerCollidesWithTrap = false;
        return value;
    }

    public Body getTrapCollided() {
        return trapCollided;
    }

    public boolean isWeaponCollidesWithBreakableObject() {
        boolean value = weaponCollidesWithBreakableObject;
        weaponCollidesWithBreakableObject = false;
        return value;
    }

    public Body getWeaponCollidedWithBreakableObject() {
        return weaponCollidedWithBreakableObject;
    }

    public Body getBreakableObjectCollidedWithWeapon() {
        return breakableObjectCollidedWithWeapon;
    }

    public boolean isWeaponCollidesWithMonster() {
        boolean value = weaponCollidesWithMonster;
        weaponCollidesWithMonster = false;
        return value;
    }

    public Body getWeaponCollidedWithMonster() {
        return weaponCollidedWithMonster;
    }

    public Body getMonsterCollidedWithWeapon() {
        return monsterCollidedWithWeapon;
    }

    public boolean isPlayerCollidesWithMonster() {
        boolean value = playerCollidesWithMonster;
        playerCollidesWithMonster = false;
        return value;
    }

    public Body getMonsterCollidedWithPlayer() {
        return monsterCollidedWithPlayer;
    }

    public ArrayList<Body> getWeaponsCollideWithWall() {
        return weaponsCollideWithWall;
    }

    public ArrayList<Body> getMonstersCollideWithWall() {
        return monstersCollideWithWall;
    }

    public void resetCollideWithWall() {
        weaponsCollideWithWall = new ArrayList<>();
        monstersCollideWithWall = new ArrayList<>();
    }
}