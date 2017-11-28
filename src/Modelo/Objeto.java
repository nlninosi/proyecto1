/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Controlador.*;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Nicolas
 */
public abstract class Objeto {
    protected int yvelocidad;
    protected int xvelocidad;
    protected int velocidad;
    protected int tiempo;
    protected int yObjeto;
    protected int xObjeto;
    
    public Objeto(int x, int y){
        //cada objeto empieza con velocidades en cero, y solo requiere de la pocision inicial
        this.yObjeto=y;
        this.xObjeto=x;
        this.yvelocidad=0;
        this.xvelocidad=0;
        this.tiempo=0;
    }
    //Formulas de caida libre, que son iguales para cada objeto
    public static int vy(int v,int t){
        // formula de la velocidad en Y en caidad libre
        double vv=v;
        vv+=(t*0.1);
        v=intValue(vv);
        return v;
    }
    public static int y(int y, int v,int t){
        // formula de la pocision en Y en caidad libre 
        double yy=y;
        yy+=(v+((t*t)*0.1)/2);
        y=intValue(yy);
        return y;
    }
    public abstract int getY();
    public abstract int getX();
    public abstract int getVY();
    public abstract int getVX();
    public abstract int getTime();
    public abstract void setTime(int a);
    public abstract void setY(int a);
    public abstract void setX(int a);
    public abstract void setVY(int a);
    public abstract void setVX(int a);
}
