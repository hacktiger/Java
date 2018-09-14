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
public class Rock extends GameObject {

    private int r_height = 16;
    private int r_width = 16;
    
    private Handler handler;

    public Rock(double x, double y, Handler handler, ObjectId id, int HEALTH, int MANA, int velY, int velX) {
        super(x, y, id, HEALTH, MANA);
        this.handler = handler;
        this.velY = velY;
        this.velX = velX;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        y += velY;
        x += velX;
        
        Collision(object);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, r_width, r_height);
    }
    
    
    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Enemy) {
                if (getBoundsAll().intersects(tempObject.getBoundsAll())) {
                    handler.removeObject(tempObject);
                    handler.removeObject(this);
                }
            }else if(tempObject.getId() == ObjectId.Block){
                if(getBounds().intersects(tempObject.getBounds())){
                    handler.removeObject(this);
                }
            }
            
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, r_width, r_height);
    }

}
