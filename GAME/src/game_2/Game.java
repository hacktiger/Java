/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_2;

import framework.GameObject;
import framework.KeyInput;
import framework.MouseInput;
import framework.ObjectId;
import framework.Textures;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import objects.Player;

/**
 *
 * @author Dell
 */
public class Game extends Canvas implements Runnable {

    public static final int W = 800;
    public static final int H = 600;

    private boolean running = false;
    private Thread thread;

    //
    public BufferedImage level_tut = null;
    public BufferedImage enemy = null;
    //Object
    Handler handler;
    Player player;
    Camera cam;
    static Textures tex;
    //helper
    public static int LEVEL = 1;

    private void init() {
        tex = new Textures(this);

        ///Load the level
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            level_tut = loader.loadImage("/img/levels/level_tutorial.png");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }

        /// init
        cam = new Camera(0, 0);
        handler = new Handler(cam);
        //

        handler.loadImageLevel(level_tut);

        //KEY LISTENER
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput(handler));
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * RUN
     */
    @Override
    public void run() {
        init();
        this.requestFocus();
        long lastTime = System.nanoTime();
        final double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int updates = 0;
        int FPS = 0;
        long timer = System.currentTimeMillis();

        while (running) {
            // game loop
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            FPS++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println(updates + " Ticks, FPS : " + FPS);
                updates = 0;
                FPS = 0;
            }
        }

    }

    private boolean alive = true;

    private void tick() {

        //System.out.println(java.lang.Thread.activeCount());
        ///Tick the handler for player
        handler.tick();

        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                GameObject tempObject = handler.object.get(i);
                cam.tick(tempObject);
                //////// if health == 0;
                if (handler.getHP() <= 0) {
                    alive = true;
                }
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        Graphics g2d = (Graphics2D) g;

        if (!alive) {
            /////////// something if dead
        } else {
            //////////////////////////////
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            g2d.translate((int) cam.getX(), (int) cam.getY()); //begin of cam

            handler.render(g);

            g2d.translate(-(int) cam.getX(), -(int) cam.getY()); //end of cam

            ///////////////////////////////
            g.dispose();
            bs.show();
        }
    }

    public static Textures getInstance() {
        return tex;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Window window = new Window(W, H, "PlatForm Game", new Game());
    }

}
