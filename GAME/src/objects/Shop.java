/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import framework.GameObject;
import framework.ObjectId;
import game_2.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class Shop extends GameObject{

    public Shop(double x, double y, Handler handler, ObjectId id, int HEALTH, int MANA) {
        super(x, y, id, HEALTH, MANA);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.drawRect((int) x,(int)y,400,400);
        
        g.drawString("Add Health :                      $50 ", (int)x + 50, (int)y+50);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
    
}
