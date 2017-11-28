/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.*;

/**
 *
 * @author Nicolas
 */
public class Pie extends Objeto{
    //cada pie solo requiere de la posicion inicial del objeto
    public Pie(int x, int y) {
        super(x, y);
    } 
    @Override
    public int getY(){
        return yObjeto;
    }
    @Override
    public int getX(){
        return xObjeto;
    }
    @Override
    public int getVY(){
        return yvelocidad;
    }
    @Override
    public int getVX(){
        return xvelocidad;
    }
    @Override
    public int getTime(){
        return tiempo;
    }
    @Override
    public void setTime(int a){
        tiempo=a;
    }
    @Override
    public void setY(int a){
        yObjeto=a;
    }
    @Override
    public void setX(int a){
        xObjeto=a;
    }
    @Override
    public void setVY(int a){
        yvelocidad=a;
    }
    @Override
    public void setVX(int a){
        xvelocidad=a;
    }
}
