package framework;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import game_2.BufferedImageLoader;
import game_2.Game;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class Textures {

    private int P_HEIGHT = 75;
    private int P_WIDTH = 80;
    //
    public BufferedImage player_sheet;
    public BufferedImage missle_sheet;
    public BufferedImage missle;
    public BufferedImage enemy_sheet;
    public BufferedImage water_sheet;
    //
    public BufferedImage player_idle;
    public BufferedImage player_xayah;
    public BufferedImage water_atk_test;
    //
    public BufferedImage[] player_right = new BufferedImage[3];
    public BufferedImage[] player_left = new BufferedImage[3];
    public BufferedImage[] water_atk = new BufferedImage[12];

    //Spirtes
    private SpriteSheet ss;
    private SpriteSheet ene_ss;
    private SpriteSheet bullet_ss;
    private SpriteSheet water_ss;

    public Textures(Game game) {
        ///Load images
        BufferedImageLoader loader = new BufferedImageLoader();
        try {
            player_sheet = loader.loadImage("/img/SpriteSheet2.png");
            missle_sheet = loader.loadImage("/img/bullet.png");
            enemy_sheet = loader.loadImage("/img/Skeleton_front.png");
            water_sheet = loader.loadImage("/img/water_1.png");

        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        //////
        ss = new SpriteSheet(player_sheet);
        bullet_ss = new SpriteSheet(missle_sheet);
        ene_ss = new SpriteSheet(enemy_sheet);
        water_ss = new SpriteSheet(water_sheet);

        getTextures();
    }

    private void getTextures() {
        //// cot / hang / width / height
        player_idle = ss.grabImage(2, 1, P_WIDTH, 75);
        //
        player_xayah = ss.grabImage( 4, 1, P_WIDTH, 75);
        //
        player_left[0] = ss.grabImage(2, 3, P_WIDTH, 75);
        player_left[1] = ss.grabImage(3, 3, P_WIDTH, 75);
        //
        player_right[0] = ss.grabImage(2, 2, P_WIDTH, 75);
        player_right[1] = ss.grabImage(3, 2, P_WIDTH, 75);
        //
        //Bullet
        //
        missle = bullet_ss.grabImage(1, 1, 32, 32);
        //
        enemy_sheet = ene_ss.grabImage(1, 1, P_WIDTH, 74);
        /// WATER
        water_atk[0] = water_ss.grabImage(1, 1, 75, 65);
        water_atk[1] = water_ss.grabImage(2, 1, 75, 65);
        water_atk[2] = water_ss.grabImage(3, 1, 75, 65);
        water_atk[3] = water_ss.grabImage(4, 1, 75, 65);

    }
}
