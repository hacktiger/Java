/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_2;

import framework.GameObject;
import framework.ObjectId;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Block;
import objects.Enemy;
import objects.Flag;
import objects.ManaSupply;
import objects.Player;

/**
 *
 * @author Dell
 */
public class Handler {

    public LinkedList<GameObject> object = new LinkedList<>();

    private GameObject tempObject;
    private Camera cam;
    private Player player;

    // Levels 
    private BufferedImage level_01 = null;
    private BufferedImage level_02 = null;
    ////// STATS
    public int ADD_HP = 0;
    public int ADD_MP = 0;
    public int HP = 100 + ADD_HP;
    public int MP = 100 + ADD_MP;
    public int MAX_HP = 100 + ADD_HP;
    public int MAX_MP = 100 + ADD_MP;
    public int score = 0;

    public Handler(Camera cam) {
        this.cam = cam;

        ///Load the level
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            level_01 = loader.loadImage("/img/levels/level_01.png");
            level_02 = loader.loadImage("/img/levels/level_02.png");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);

            tempObject.tick(object);
        }

    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void loadImageLevel(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        //
        for (int xx = 0; xx < height; xx++) {
            for (int yy = 0; yy < width; yy++) {
                int pixel = image.getRGB(xx, yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                //Add object based on color
                if (red == 255 && green == 255 && blue == 255) {
                    // BLOCK : x , y , id , health , mana
                    addObject(new Block(xx * 32, yy * 32, 0, ObjectId.Block, 0, 0));
                }
                if (red == 0 && green == 0 && blue == 255) {
                    // PLAYER : x , y , Handler, Camera id , health , mana
                    addObject(new Player(xx * 32, yy * 32, this, cam, ObjectId.Player, getHP(), getMP()));
                }
                if (red == 255 && green == 242 && blue == 0) {
                    // FLAG : x , y , id , health , mana
                    addObject(new Flag(xx * 32, yy * 32, ObjectId.Flag, 0, 0));
                }
                if (red == 0 && green == 255 && blue == 0) {
                    // ENEMY : x , y , handler, id , health , mana
                    addObject(new Enemy(xx * 32, yy * 32, this, ObjectId.Enemy, 100, 0));
                }
                if (red == 255 && green == 0 && blue == 100) {
                    // MANA POOL : x , y , handler, id , health , mana
                    addObject(new ManaSupply(xx * 32, yy * 32 + 27, ObjectId.Mana, 0, 0));
                }
            }
        }
    }

    public void switchLevel() {
        clearLevel();
        cam.setX(0);

        switch (Game.LEVEL) {
            case 1:
                loadImageLevel(level_01);
                break;
            case 2:
                loadImageLevel(level_02);
                break;
            case 3:
                loadImageLevel(level_01);
                break;
            default:
                break;
        }
        Game.LEVEL++;
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void clearLevel() {
        object.clear();
    }

    public void setHP(int hp) {
        this.HP = hp;
    }

    public int getHP() {
        return HP;
    }

    public void setMP(int mp) {
        this.MP = mp;
    }

    public int getMP() {
        return MP;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addHP(int add) {
        this.ADD_HP += add;
    }

}
