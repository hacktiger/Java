/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import game_2.Handler;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import objects.Bullet;
import objects.Rock;
import objects.Shop;
import objects.Water;

/**
 *
 * @author Dell
 */
public class KeyInput extends KeyAdapter {

    Handler handler;

    private boolean isShooting = false;
    private boolean isShopping = false;
    private boolean usingQ = false;
    private boolean usingW = false;
    private boolean usingR = false;

    private final int screen_h = 600;
    private final int screen_w = 800;

    private HashMap<String, Long> cooldowns = new HashMap<>();

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        //////// go through all objects
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Player) {
                if (key == KeyEvent.VK_RIGHT && !isShopping) {
                    tempObject.setVelX(5);
                }
                if (key == KeyEvent.VK_LEFT && !isShopping) {
                    tempObject.setVelX(-5);
                }
                if (key == KeyEvent.VK_UP && !tempObject.isJumping() && !isShopping) {
                    tempObject.setJumping(true);
                    tempObject.setVelY(-15);
                }
                if (key == KeyEvent.VK_Z && !isShopping) {
                    tempObject.setX(192.0);
                    tempObject.setY(309.0);

                }
                if (key == KeyEvent.VK_SPACE && !isShooting && handler.getMP() >= 1) {
                    //
                    handler.addObject(new Bullet(tempObject.getX() + 35, tempObject.getY() + 35, handler, ObjectId.Bullet, tempObject.getFacing() * 5, 0, 0));
                    isShooting = true;
                    handler.setMP(handler.getMP() - 1);

                }

                /////// USING Q
                if (key == KeyEvent.VK_Q && handler.getMP() >= 10 && !usingQ) {
                    for (int k = 0; k < 10; k++) {
                        handler.addObject(new Rock(tempObject.getX() - 300 + Math.random() * 600, tempObject.getY() - screen_h / 2, handler, ObjectId.Rock, 0, 0, (int) (5 + Math.random() * 5), (int) (Math.random() * 4)));
                    }
                    handler.setMP(handler.getMP() - 10);
                    usingQ = true;
                    cooldowns.put("Q", System.currentTimeMillis());
                }
                /// USING W
                if (key == KeyEvent.VK_W && !usingW && !tempObject.isJumping() && handler.getMP() >= 30) {

                }
                //Using R
                if (key == KeyEvent.VK_R && !usingR) {
                    usingR = true;
                }

                // SHOP
                if (key == KeyEvent.VK_P) {
                    if (!isShopping) {
                        handler.addObject(new Shop(tempObject.getX() - 200, tempObject.getY() - 200, handler, ObjectId.Shop, 0, 0));
                        isShopping = true;
                    } else {
                        for (int z = 0; z < handler.object.size(); z++) {
                            GameObject tempObject_shop = handler.object.get(z);
                            if (tempObject_shop.getId() == ObjectId.Shop) {
                                handler.removeObject(tempObject_shop);
                            }
                            isShopping = false;
                        }
                    }
                }
                
                if(isShopping){
                    if(key == KeyEvent.VK_B){
                        handler.addHP(10);
                    }
                }
            }
        }

        if (key == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }

    private boolean something = true;
    private boolean somethingW = true;

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getId() == ObjectId.Player) {
                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(0);
                }
                if (key == KeyEvent.VK_SPACE) {
                    // so that you cant hold down shoot button
                    isShooting = false;
                }
                ////// Q
                if (key == KeyEvent.VK_Q && something) {
                    /////// SET SKILL COOLDOWN FOR Q
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (usingQ) {
                                something = false;
                                if (System.currentTimeMillis() - cooldowns.get("Q") > 5000 /* 5000 = 5s cooldown */) {
                                    usingQ = false;
                                    something = true;
                                    cooldowns.remove("Q");
                                }
                            }
                        }
                    });
                    thread.start();
                    //////////// END SKILL COOLDOWN
                }
                ///// W
                if (key == KeyEvent.VK_W && somethingW) {
                }
                ///// R
                //Using R
                if (key == KeyEvent.VK_R) {
                    usingR = false;
                }
                // SHOP
            }
        }
    }
}
