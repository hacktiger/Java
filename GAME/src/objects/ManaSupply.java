/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import framework.GameObject;
import framework.ObjectId;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class ManaSupply extends GameObject {
    
    private int mana_width = 75;
    private int mana_height = 5;

    public ManaSupply(double x, double y, ObjectId id, int HEALTH, int MANA) {
        super(x, y, id, HEALTH, MANA);
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int) x, (int) y, mana_width, mana_height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, mana_width, mana_height);
    }

}
