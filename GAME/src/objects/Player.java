/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import framework.Animation;
import framework.GameObject;
import framework.ObjectId;
import framework.Textures;
import game_2.Camera;
import game_2.Game;
import game_2.Handler;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class Player extends GameObject {

    //Final variable
    private final int width = 80;
    private final int height = 75;
    private final double MAX_SPEED = 10;
    //gravity
    private final double gravity = 0.5f;

    private Handler handler;

    private Camera cam;

    Textures tex = Game.getInstance();

    private Animation playerWalkRight;
    private Animation playerWalkLeft;

    public Player(double x, double y, Handler handler, Camera cam, ObjectId id, int health, int mana) {
        super(x, y, id, health, mana);
        this.handler = handler;
        this.cam = cam;

        //Animation
        playerWalkRight = new Animation(7, tex.player_right[0], tex.player_right[1], tex.player_right[0], tex.player_right[1]);
        playerWalkLeft = new Animation(7, tex.player_left[0], tex.player_left[1], tex.player_left[0], tex.player_left[1]);

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        System.out.println(handler.MAX_HP);
        if (handler.getMP() > handler.MAX_MP) {
            handler.setMP(handler.MAX_MP);
        }
        if (handler.getHP() > handler.MAX_HP) {
            handler.setHP(handler.MAX_HP);
        }

        /////// apply vel
        x += velX;
        y += velY;

        if (velX < 0) {
            facing = -1;
        } else if (velX > 0) {
            facing = 1;
        }
        //Apply gravity when JUMP or FALL
        if (falling || jumping) {
            velY += gravity;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }
        Collision(object);

        playerWalkRight.runAnimation();
        playerWalkLeft.runAnimation();
    }

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
                        }   ///LEFT Collision
                        if (getBoundsLeft().intersects(tempObject.getBounds())) {
                            x = tempObject.getX() + width / 2;

                        }
                        break;
                    case Flag:
                        //Switch level
                        if (getBounds().intersects(tempObject.getBounds())) {
                            //if hits Flag -> switch level in Handler
                            handler.switchLevel();
                        }
                        /**
                         * ENEMY COLLÍION
                         */
                        break;
                    case Enemy:
                        if (getBoundsAll().intersects(tempObject.getBoundsAll())) {
                            handler.setHP(handler.getHP() - 1);

                        }
                        break;

                    case Mana:
                        if (getBounds().intersects(tempObject.getBounds())) {
                            handler.setMP(handler.getMP() + 1);
                        }
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (jumping) {
            if (facing == 1) {
                g.drawImage(tex.player_right[0], (int) x, (int) y, null);
            } else {
                g.drawImage(tex.player_left[0], (int) x, (int) y, null);
            }

        } else {
            if (velX != 0) {
                if (facing == 1) {
                    playerWalkRight.drawAnimation(g, (int) x, (int) y);
                } else {
                    playerWalkLeft.drawAnimation(g, (int) x, (int) y);
                }
            } else {
                g.drawImage(tex.player_idle, (int) x, (int) y, null);
            }

        }

        //HEALTH BAR 
        g.setColor(Color.white);
        g.drawRect((int) x, (int) y - 30, width, 15);

        g.setColor(Color.GRAY);
        g.fillRect((int) x, (int) y - 30, width, 15);

        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y - 30, (int) (0.8 * handler.getHP()), 15);

        g.setColor(Color.white);
        g.setFont(new Font("default", Font.BOLD, 12));
        g.drawString(handler.getHP() + "/100", (int) x + width / 4 - 3, (int) y - 17);

        //MANA BAR
        g.setColor(Color.white);
        g.drawRect((int) x, (int) y - 14, width, 15);
        g.setColor(Color.GRAY);
        g.fillRect((int) x, (int) y - 14, width, 15);
        g.setColor(Color.BLUE);
        g.fillRect((int) x, (int) y - 14, (int) (0.8 * handler.getMP()), 15);
        g.setColor(Color.white);
        g.drawString(handler.getMP() + "/100", (int) x + width / 4 - 3, (int) y - 3);

        /// SHOP
        /*        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 14));
        g.drawString("SHOP", (int) x - 320, (int) y - 245);
        g.drawRect((int) x - 350, (int) y - 275, 100, 50);*/
        //
        g.drawString("Score : " + handler.getScore(), (int) x - 350, (int) y - 275);
        
        
        
        //COLLISION BOX
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBoundsAll());

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
