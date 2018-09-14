/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game_2;

import framework.GameObject;

/**
 *
 * @author Dell
 */
public class Camera {
    private double x,y;
    
    public Camera(double x,double y){
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    void tick(GameObject player) {
        //tweaning algorithm
        
        x = -player.getX() + Game.W/2;
        // follow even jump
        y = -player.getY() + Game.H/2;
    }
    
}
