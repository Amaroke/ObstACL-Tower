package com.acl.listeners;

import com.acl.enums.UserData;
import com.badlogic.gdx.physics.box2d.*;

public class CollisionListener implements ContactListener {
    private boolean playerCollidesWithStairs = false;
    private boolean playerCollidesWithChest = false;
    private boolean playerCollidesWithTrap = false;
    private boolean weaponCollidesWithBreakableObject = false;
    private boolean playerCollidesWithWall = false;
    private boolean weaponCollidesWithMonster = false;
    private boolean weaponCollidesWithWall = false;
    private Body weaponCollided;
    private boolean playerCollidesWithMonster = false;
    private Body monsterCollided = null;
    private Body chestCollided = null;
    private Body trapCollided = null;
    private Body breakableObjectCollided = null;

    @Override
    public void beginContact(Contact contact) {
        Object A = contact.getFixtureA().getBody().getUserData();
        Object B = contact.getFixtureB().getBody().getUserData();

        if ((A == UserData.PLAYER && B == UserData.STAIR) || (A == UserData.STAIR && B == UserData.PLAYER)){
            this.playerCollidesWithStairs = true;
        }

        if (A == UserData.PLAYER && B == UserData.CHEST){
            this.playerCollidesWithChest = true;
            this.chestCollided = contact.getFixtureB().getBody();
        }

        if (A == UserData.CHEST && B == UserData.PLAYER){
            this.playerCollidesWithChest = true;
            this.chestCollided = contact.getFixtureA().getBody();
        }

        if (A == UserData.PLAYER && B == UserData.TRAP){
            this.playerCollidesWithTrap = true;
            this.trapCollided = contact.getFixtureB().getBody();
        }

        if(A == UserData.TRAP && B == UserData.PLAYER){
            this.playerCollidesWithTrap = true;
            this.trapCollided = contact.getFixtureA().getBody();
        }

        if (A == UserData.WEAPON && B == UserData.BREAKABLEOBJ){
           this.weaponCollidesWithBreakableObject = true;
            this.weaponCollided = contact.getFixtureA().getBody();
            this.breakableObjectCollided = contact.getFixtureB().getBody();
        }

        if(A == UserData.BREAKABLEOBJ && B == UserData.WEAPON){
            this.weaponCollidesWithBreakableObject = true;
            this.weaponCollided = contact.getFixtureB().getBody();
            this.breakableObjectCollided = contact.getFixtureA().getBody();
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
        boolean value = playerCollidesWithChest;
        playerCollidesWithChest = false;
        return value;
    }

    public void setPlayerCollidesWithChest(boolean playerCollidesWithChest) {
        this.playerCollidesWithChest = playerCollidesWithChest;
    }

    public boolean isPlayerCollidesWithTrap() {
        boolean value = playerCollidesWithTrap;
        playerCollidesWithTrap = false;
        return value;
    }

    public void setPlayerCollidesWithTrap(boolean playerCollidesWithTrap) {
        this.playerCollidesWithTrap = playerCollidesWithTrap;
    }

    public boolean isWeaponCollidesWithBreakableObject() {
        boolean value = weaponCollidesWithBreakableObject;
        weaponCollidesWithBreakableObject = false;
        return value;
    }

    public void setWeaponCollidesWithBreakableObject(boolean weaponCollidesWithBreakableObject) {
        this.weaponCollidesWithBreakableObject = weaponCollidesWithBreakableObject;
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

    public Body getChestCollided() {
        Body value = chestCollided;
        chestCollided = null;
        return value;
    }

    public Body getTrapCollided() {
        Body value = trapCollided;
        trapCollided = null;
        return value;
    }

    public Body getBreakableObjectCollided(){
        Body value = breakableObjectCollided;
        breakableObjectCollided = null;
        return value;
    }
}