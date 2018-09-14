/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public abstract class GameObject {

    protected int height, width;
    protected double x, y;
    protected double velX = 0, velY = 0;
    protected ObjectId id;
    protected boolean falling = true;
    protected boolean jumping = false;
    protected int facing = 1;
    protected int HEALTH;
    protected int MANA;

    public GameObject(double x, double y, ObjectId id, int HEALTH, int MANA) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.HEALTH = HEALTH;
        this.MANA = MANA;
    }

    public abstract void tick(LinkedList<GameObject> object);

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public Rectangle2D getBoundsLeft() {
        return new Rectangle((int) x + 1, (int) y + 10, (int) 5, (int) height - 20);

    }

    public Rectangle2D getBoundsRight() {
        return new Rectangle((int) ((int) x + width - 5), (int) y + 10, (int) 5, (int) height - 20);
    }

    public Rectangle2D getBoundsAll() {
        return new Rectangle((int) x, (int) y, 80, 75);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public ObjectId getId() {
        return id;
    }

    //////
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    ///
    public int getFacing() {
        return facing;
    }

    public int getHEALTH() {
        return HEALTH;
    }

    public int getMANA() {
        return MANA;
    }

    public void setHEALTH(int HEALTH) {
        this.HEALTH = HEALTH;
    }

    public void setMANA(int MANA) {
        this.MANA = MANA;
    }

    

}
