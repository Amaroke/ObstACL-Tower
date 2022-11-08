package com.acl.listeners;

import com.acl.enums.UserData;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class CollisionListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {

        // En cas de contact entre le joueur et un mur
        if (contact.getFixtureA().getBody().getUserData() == UserData.PLAYER &&
                contact.getFixtureB().getBody().getUserData() == UserData.WALL) {
            System.out.println("contact wall 1");
        } else if (contact.getFixtureB().getBody().getUserData() == UserData.WALL &&
                contact.getFixtureA().getBody().getUserData() == UserData.PLAYER) {
            System.out.println("contact wall 2");
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