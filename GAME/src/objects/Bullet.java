package objects;

import framework.GameObject;
import framework.ObjectId;
import game_2.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Bullet extends GameObject {

    private int b_height = 16;
    private int b_width = 16;

    private Handler handler;
    
    private int BULLET_DMG = 25;

    public Bullet(double x, double y, Handler handler, ObjectId id, int velX, int health, int mana) {
        super(x, y, id, health, mana);
        this.velX = velX;
        this.handler = handler;
    }

    @Override
    public void tick(LinkedList<GameObject> object) {
        x += velX * 2;

        Collision(object);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, b_width, b_height);

    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Enemy) {
                if (getBoundsAll().intersects(tempObject.getBoundsAll())) {
                    if (tempObject.getHEALTH() <= 25) {
                        handler.removeObject(tempObject);
                        handler.setScore(handler.getScore() + 10);
                    } else {
                        tempObject.setHEALTH(tempObject.getHEALTH() - BULLET_DMG);
                    }
                    handler.removeObject(this);
                }
            } else if (tempObject.getId() == ObjectId.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }

        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, b_width, b_height);
    }

}
