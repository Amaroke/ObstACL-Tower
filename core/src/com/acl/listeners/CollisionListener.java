package com.acl.listeners;

import com.acl.datas.elements.weapons.Weapon;
import com.acl.enums.UserData;
import com.badlogic.gdx.physics.box2d.*;

public class CollisionListener implements ContactListener {
    private boolean playerCollidesWithStairs = false;

    private boolean playerCollidesWithChest = false;

    private boolean playerCollidesWithTrap = false;

    private boolean weaponsCollidesWithBreakableObject = false;

    private boolean playerCollidesWithWall = false;

    private boolean weaponCollidesWithMonster = false;
    private boolean weaponCollidesWithWall = false;
    private Body weaponCollided;

    private boolean playerCollidesWithMonster = false;

    private Body monsterCollided = null;

    @Override
    public void beginContact(Contact contact) {
        Object A = contact.getFixtureA().getBody().getUserData();
        Object B = contact.getFixtureB().getBody().getUserData();

        if ((A == UserData.PLAYER && B == UserData.STAIR) || (A == UserData.STAIR && B == UserData.PLAYER)){
            this.playerCollidesWithStairs = true;
        }

        if ((A == UserData.PLAYER && B == UserData.CHEST) || (A == UserData.CHEST && B == UserData.PLAYER)){
            this.playerCollidesWithChest = true;
        }

        if ((A == UserData.PLAYER && B == UserData.TRAP) || (A == UserData.TRAP && B == UserData.PLAYER)){
            this.playerCollidesWithTrap = true;
        }

        if ((A == UserData. WEAPON && B == UserData.BREAKABLEOBJ) || (A == UserData.BREAKABLEOBJ && B == UserData.WEAPON)){
           this.weaponsCollidesWithBreakableObject = true;
        }

        if ((A == UserData.PLAYER && B == UserData.WALL) || (A == UserData.WALL && B == UserData.PLAYER)){
            this.playerCollidesWithWall = true;
        }

        if ((A == UserData.WEAPON && B == UserData.MONSTER) || (A == UserData.MONSTER && B == UserData.WEAPON)){
            this.weaponCollidesWithMonster = true;
        }

        if (A == UserData.PLAYER && B == UserData.MONSTER){
            this.playerCollidesWithMonster = true;
            this.monsterCollided = contact.getFixtureB().getBody();
        }

        if (A == UserData.MONSTER && B == UserData.PLAYER) {
            this.playerCollidesWithMonster = true;
            this.monsterCollided = contact.getFixtureB().getBody();
        }

        if ((A == UserData.WEAPON && B == UserData.WALL) || (A == UserData.WALL && B == UserData.WEAPON)){
            this.weaponCollidesWithWall = true;
            weaponCollided = (A == UserData.WEAPON) ? contact.getFixtureA().getBody() : contact.getFixtureB().getBody();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public boolean isWeaponCollidesWithWall() {
        boolean value = weaponCollidesWithWall;
        weaponCollidesWithWall = false;
        return value;
    }

    public boolean isPlayerCollidesWithStairs() {
        return playerCollidesWithStairs;
    }

    public void setPlayerCollidesWithStairs(boolean playerCollidesWithStairs) {
        this.playerCollidesWithStairs = playerCollidesWithStairs;
    }

    public boolean isPlayerCollidesWithChest() {
        return playerCollidesWithChest;
    }

    public void setPlayerCollidesWithChest(boolean playerCollidesWithChest) {
        this.playerCollidesWithChest = playerCollidesWithChest;
    }

    public boolean isPlayerCollidesWithTrap() {
        return playerCollidesWithTrap;
    }

    public void setPlayerCollidesWithTrap(boolean playerCollidesWithTrap) {
        this.playerCollidesWithTrap = playerCollidesWithTrap;
    }

    public boolean isWeaponsCollidesWithBreakableObject() {
        return weaponsCollidesWithBreakableObject;
    }

    public void setWeaponsCollidesWithBreakableObject(boolean weaponsCollidesWithBreakableObject) {
        this.weaponsCollidesWithBreakableObject = weaponsCollidesWithBreakableObject;
    }

    public boolean isPlayerCollidesWithMonster() {
        return playerCollidesWithMonster;
    }

    public Body getMonsterCollided() {
        return monsterCollided;
    }

    public void setPlayerCollidesWithMonster(boolean playerCollidesWithMonster) {
        this.playerCollidesWithMonster = playerCollidesWithMonster;
    }

    public void setMonsterCollided(Body monsterCollided) {
        this.monsterCollided = monsterCollided;
    }

    public Body getWeaponCollided() {
        Body value = weaponCollided;
        weaponCollided = null;
        return value;
    }
}