/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Controlador.*;
import Modelo.*;
import javax.swing.JFrame;
/**
 *
 * @author Nicolas
 */
public class main  {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controlador c= new Controlador();
        view v=new view(c);
        v.setFocusable(true);
        v.setSize(1020,500);
    }
    
}
