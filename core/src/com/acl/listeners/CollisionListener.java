package com.acl.listeners;

import com.acl.enums.UserData;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionListener implements ContactListener {


    boolean playerContactPiege = false;

    @Override
    public void beginContact(Contact contact) {
        Object A = contact.getFixtureA().getBody().getUserData();
        Object B = contact.getFixtureB().getBody().getUserData();

        // En cas de contact entre le joueur et un mur
        if (A == UserData.PLAYER && B == UserData.WALL) {
            System.out.println("contact wall 1");
        } else if(A == UserData.WALL && B == UserData.PLAYER) {
            System.out.println("contact wall 2");
        }

        // Collision between weapons and monsters
        if ((A == UserData.WEAPON && B == UserData.MONSTER) || (A == UserData.MONSTER && B == UserData.WEAPON)){
            System.out.println("contact monster");
        }

        System.out.println(A);
        System.out.println(B);
        if ((A == UserData.WEAPON && B == UserData.WALL) || (A == UserData.WALL && B == UserData.WEAPON)){
            System.out.println("contact wall weapon");
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
}