/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import framework.GameObject;
import framework.ObjectId;
import framework.Textures;
import game_2.Game;
import game_2.Handler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class Enemy extends GameObject {

    //Final variable
    private final int width = 80;
    private final int height = 75;
    private final double MAX_SPEED = 10;
    private int velX2 = 1;
    public int e_health;
    public int e_mana;
    //gravity
    private final double gravity = 0.5f;

    Textures tex = Game.getInstance();

    private Handler handler;

    public Enemy(double x, double y, Handler handler, ObjectId id, int health, int mana) {
        super(x, y, id, health, mana);
        this.handler = handler;
        this.e_health = health;
        this.e_mana = mana;
    }

    public void tick(LinkedList<GameObject> object) {
        y += velY;
        
        x += velX2;
        //Apply gravity when JUMP or FALL
        if (falling || jumping) {
            velY += gravity;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }
        Collision(object);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawImage(tex.enemy_sheet, (int) x, (int) y, null);

        //HEALTH BAR 
        g.setColor(Color.white);
        g.drawRect((int) x, (int) y - 30, width, 15);
        g.setColor(Color.GRAY);
        g.fillRect((int) x, (int) y - 30, width, 15);
        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y - 30, (int) (0.8 * this.getHEALTH()), 15);
        g.setColor(Color.white);
        g.setFont(new Font("default", Font.BOLD, 12));
        g.drawString( this.getHEALTH() + "/100", (int) x + width / 4 - 3, (int) y - 17);

    }

    int count = 0;

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (null != tempObject.getId()) {
                switch (tempObject.getId()) {
                    case Block:
                        //TOP COLLISION
                        if (getBoundsTop().intersects(tempObject.getBounds())) {
                            y = tempObject.getY() + height / 2;
                            velY = 0;
                        }   // BOTTOM COLLISION + JUMPING
                        if (getBounds().intersects(tempObject.getBounds())) {
                            y = tempObject.getY() - height;
                            velY = 0;
                            jumping = false;
                            falling = false;
                        } else {
                            falling = true;
                        }   ///RIGHT Collision
                        if (getBoundsRight().intersects(tempObject.getBounds())) {
                            x = tempObject.getX() - width;
                            velX2 = -1;
                        }   ///LEFT Collision
                        if (getBoundsLeft().intersects(tempObject.getBounds())) {
                            x = tempObject.getX() + width / 2;
                            velX2 = +1;

                        }
                        break;
                    /**
                     * HIT PLAYER
                     */
                    case Player:
                        if (getBoundsRight().intersects(tempObject.getBoundsLeft())) {
                            tempObject.setX((getX() + width));
                        }
                        if (getBoundsLeft().intersects(tempObject.getBoundsRight())) {
                            tempObject.setX((getX() - width));
                        }
                        if (getBoundsTop().intersects(tempObject.getBounds())) {
                            tempObject.setVelY(0);
                            tempObject.setY((getY() - height * 3));
                        }
                        break;

                    default:
                        break;
                }
            }
        }
    }

    @Override
    public Rectangle getBoundsAll() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x + 5, (int) ((int) y + (height - 5)), (int) width - 10, 5);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) x + 5, (int) y, (int) width - 10, (int) 5);
    }

    @Override
    public Rectangle getBoundsRight() {
        return new Rectangle((int) ((int) x + width - 5), (int) y + 10, (int) 5, (int) height - 20);
    }

    @Override
    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x + 1, (int) y + 10, (int) 5, (int) height - 20);
    }

}
