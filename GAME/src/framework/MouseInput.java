/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import game_2.Handler;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Dell
 */
public class MouseInput implements MouseListener {

    Handler handler;

    public MouseInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        
        if(mx > 50 && mx < 150){
            if(my > 25 && my < 75){
                ///CHANGE GAME STATE TO SHOP
                //
            }
        }
        
        System.out.println("x : " + mx + ", y : " + my);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
