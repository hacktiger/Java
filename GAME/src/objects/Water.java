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
import game_2.Game;
import game_2.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class Water extends GameObject {

    Textures tex = Game.getInstance();

    private Handler handler;
    private Animation water_attack;
    
    private int WATER_DMG = 50;

    public Water(double x, double y, Handler handler, ObjectId id, int HEALTH, int MANA) {
        super(x, y, id, HEALTH, MANA);
        this.handler = handler;

        water_attack = new Animation(5, tex.water_atk[0], tex.water_atk[1], tex.water_atk[2], tex.water_atk[3]);

    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        water_attack.runAnimation();
        
        Collision(object);
    }

    @Override
    public void render(Graphics g) {
        water_attack.drawAnimation(g, (int) x, (int) y);

        //COLLISION BOX
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.red);
        g2d.draw(getBounds());

    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Enemy) {
                if (getBounds().intersects(tempObject.getBoundsAll())) {
                    if (tempObject.getHEALTH() <= 50) {
                        //handler.removeObject(tempObject);
                    } else {
                        tempObject.setHEALTH(tempObject.getHEALTH() - WATER_DMG);
                    }
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 70, 60);
    }

}
