/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Nicolas
 */
public class Player extends Objeto  {
    private boolean ai;
    private Pie pie;
    public Player(int x, int y,int xp,int yp) {
        super(x, y);
        //cada jugador se compone de un pie, y posee una variable que indique si esta bajo contro humano 
        this.ai=false;
        this.pie= new Pie(xp,yp);
    }
    public boolean getAi(){
        return ai;
    }
    public Pie getpie(){
        return pie;
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
        pie.yObjeto=a+50;
    }
    @Override
    public void setX(int a){
        xObjeto=a;
        pie.xObjeto=a+10;
    }
    @Override
    public void setVY(int a){
        yvelocidad=a;
    }
    @Override
    public void setVX(int a){
        xvelocidad=a;
    }
    public void setAi(boolean a){
        ai=a;
    }
    public void ai(boolean ai,int x,int y){
        //el metodo ai hace que el jugador "persiga" un determinado punto.
        if(ai==true){
                if (this.xObjeto<x) {
                    int a=this.getX()+5;
                    this.setX(a);
                    this.setVX(5);
               }
               if (this.xObjeto>x) {
                    int a=this.getX()-5;
                    this.setX(a);
                    this.setVX(-5);
               }
               if ((this.xObjeto>y)&&(this.getY()==330)) {
                    this.setY(329);
                    this.setVY(-15);
               }
               if ((this.getpie().getX()==this.getX()+10)) {
                    this.getpie().setVX(-7);
               }
        }
    }
    
}
