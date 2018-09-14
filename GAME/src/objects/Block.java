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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author Dell
 */
public class Block extends GameObject {

    Textures tex = Game.getInstance();
    private int type;

    public Block(double x, double y, int type, ObjectId id, int health, int mana) {
        super(x, y, id, health, mana);
        this.type = type;
    }

    public void tick(LinkedList<GameObject> object) {
    }

    public void render(Graphics g) {
        
         g.setColor(Color.white); 
         g.drawRect((int)x,(int)y,32,32);
        
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
    
    
    


}
