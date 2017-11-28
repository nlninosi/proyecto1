 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Controlador.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Math.*;
import javax.swing.*;
import static oracle.jrockit.jfr.events.Bits.intValue;
/**
 *
 * @author Nicolas
 */
public class Controlador extends JPanel implements ActionListener{
    //El controlador se compone de 2 jugadores y un balon
    private Player player1;
    private Player player2;
    private Balon balon;
    private Timer timer;
    //tambien posee un timer y 2 varibles que indican el marcador
    private int score1;
    private int score2;
    public Controlador(){
        this.timer = new Timer(100, this);
        this.timer.start();
        this.balon=new Balon(490,20); 
        
        this.player1=new Player (150,350,160,400);
        this.player2=new Player (800,350,810,400);
        
        this.score1=0;
        this.score2=0;
        
    }
    public void setTimer(Timer timer){
        this.timer=timer;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //El timer "actualiza" cada cierto tiempo la informacion de los 2 jugadores y del balon
        int y1 =player1.getY();
        int x1 =player1.getX();
        int vy1=player1.getVY();
        int vx1=player1.getVX();
        int t1= player1.getTime();
        
        int y2 =player2.getY();
        int x2 =player2.getX();
        int vy2=player2.getVY();
        int vx2=player2.getVX();
        int t2= player2.getTime();
        
        int by =balon.getY();
        int bx =balon.getX();
        int bvy=balon.getVY();
        int bvx=balon.getVX();
        int bt= balon.getTime();
        //Cada jugador es representado en diferentes rectangulos que dada una colision reaccionan diferente   
        Rectangle P1=new Rectangle(player1.getX(), player1.getY(), 40, 70);
        Rectangle P1oeste=new Rectangle(player1.getX(), player1.getY()+15, 10, 55);
        Rectangle P1este=new Rectangle(player1.getX()+30, player1.getY()+15, 10, 55);
        Rectangle P1norte=new Rectangle(player1.getX()+10, player1.getY(), 20, 15);
        Rectangle P1noreste=new Rectangle(player1.getX()+25, player1.getY(), 15, 15);
        Rectangle P1noroeste=new Rectangle(player1.getX(), player1.getY(), 15, 15);
        Rectangle P1sureste=new Rectangle(player1.getX()+25, player1.getY()+55, 15, 15);
        
        Rectangle P2=new Rectangle(player2.getX(), player2.getY(), 40, 70);
        Rectangle P2oeste=new Rectangle(player2.getX(), player2.getY()+15, 10, 55);
        Rectangle P2este=new Rectangle(player2.getX()+30, player2.getY()+15, 10, 55);
        Rectangle P2norte=new Rectangle(player2.getX()+10, player2.getY(), 20, 15);
        Rectangle P2noreste=new Rectangle(player2.getX()+25, player2.getY(), 15, 15);
        Rectangle P2noroeste=new Rectangle(player2.getX(), player2.getY(), 15, 15);
        Rectangle P2suroeste=new Rectangle(player2.getX(), player2.getY()+55, 15, 15);
        
        //cada pie y cada balon se pueden representar en un simple rectangulo
        Rectangle P1pie=new Rectangle(player1.getpie().getX(),player1.getpie().getY(),20,20);
        Rectangle P2pie=new Rectangle(player2.getpie().getX(),player2.getpie().getY(),20,20);
        
        Rectangle ball=new Rectangle(balon.getX(),balon.getY(), 30, 30);
        
        
        //las porterias y sus respectivos palos son representadas por un rectangulo
        Rectangle cancha1=new Rectangle(0,250,100,150);
        Rectangle cancha2=new Rectangle(900,250,100,150);
        Rectangle palo1=new Rectangle(0,240,100,15);
        Rectangle palo2=new Rectangle(900,240,100,15);
        Rectangle expalo1=new Rectangle(90,240,10,15);
        Rectangle expalo2=new Rectangle(900,240,10,15);
        
        
        //se establecen los "limites" de los jugadores y del balon
        if(balon.getX()>970){
            bx=970;
            bvx=-bvx;
            balon.setVX(bvx);
            balon.setX(bx);
        }else if(balon.getX()<0){
            bvx=-bvx;
            balon.setVX(bvx);
            balon.setX(0);
        }else if(balon.getY()<=0){
            by=0;
            bvy=-bvy;
            balon.setVY(bvy);
            balon.setY(by);
        }else if(balon.getX()>970){
            bx=970;
            bvx=-bvx;
            balon.setVX(bvx);
            balon.setX(bx);
        }
        if(player1.getX()<0){
            player1.setX(0);
        }
        if(player1.getX()>960){
            player1.setX(960);
        }
        if(player2.getX()<0){
            player2.setX(0);
        }
        if(player2.getX()>960){
            player2.setX(960);
        }
        //conforme un balon toque el piso su velocidad disminuira para representar la friccion
        //cada jugador pierde velocidad independientemente de si tiene contacto con el piso
        if((balon.getY()==(370))&&(balon.getVX()>0)){
            bvx--;
            balon.setVX(bvx);
        }else if((balon.getY()==(370))&&(balon.getVX()<0)){
            bvx++;
            balon.setVX(bvx);
        }
        if((player1.getVX()>0)){
            vx1--;
            player1.setVX(vx1);
        }else if((player1.getVX()<0)){
            vx1++;
            player1.setVX(vx1);
        }
        if((player2.getVX()>0)){
            vx2--;
            player2.setVX(vx2);
        }else if((player2.getVX()<0)){
            vx2++;
            player2.setVX(vx2);
        }
        //colisiones balon/player1
        //dependiendo de cual rectangulo colision con el balon, este reaccionara de manera distinta
        if(ball.intersects(P1norte)&&(ball.intersects(P1noroeste))&&(ball.intersects(P1noreste))){
            // en este caso la pelota simplemente rebota contra la parte superior 
            by=y1-31;
            bt=0;
            bvy=-bvy;
            balon.setY(by);
            balon.setTime(bt);
            balon.setVY(bvy);
        }else if(ball.intersects(P1norte)&&(ball.intersects(P1noroeste))){
            //en los siguientes casos se da un tiro parabolico
            // dado un angulo y la rapidez de la pelota esta recciona de manera distinta cada vez
            double rad= Math.toRadians(60);
            double rapidez=sqrt((bvy*bvy)+(bvx*bvx));
            double xx=-rapidez*cos(rad);
            double yy=rapidez*sin(rad);
            int a=0;
            if(vy1<0){
                a=vy1;
            }
            by=y1-31;
            bvy=-intValue(yy)+a;
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setY(by);
            balon.setTime(0);
        }else if(ball.intersects(P1norte)&&(ball.intersects(P1noreste))){
            double rad= Math.toRadians(60);
            double rapidez=sqrt((bvy*bvy)+(bvx*bvx));
            double xx=rapidez*cos(rad);
            double yy=rapidez*sin(rad);
            int a=0;
            if(vy1<0){
                a=vy1;
            }
            by=y1-31;
            bvy=-intValue(yy)+a;
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setY(by);
            balon.setTime(0);
        }else if(ball.intersects(P1noreste)){
            double rad= Math.toRadians(45);
            double rapidez=sqrt((bvy*bvy)+(bvx*bvx));
            double xx=rapidez*cos(rad);
            double yy=rapidez*sin(rad);
            int a=0;
            if(vy1<0){
                a=vy1;
            }
            by=y1-31;
            bvy=-intValue(yy)+a;
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setY(by);
            balon.setTime(0);
        }else if(ball.intersects(P1noroeste)){
            double rad= Math.toRadians(45);
            double rapidez=sqrt((bvy*bvy)+(bvx*bvx));
            double xx=-rapidez*cos(rad);
            double yy=rapidez*sin(rad);
            int a=0;
            if(vy1<0){
                a=vy1;
            }
            by=y1-31;
            bvy=-intValue(yy)+a;
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setY(by);
            balon.setTime(0);
        }else if(ball.intersects(P1oeste)){
            bvx=-10;
            bx+=bvx;
            balon.setX(bx);
            balon.setVX(bvx);
        }else if((ball.intersects(P1este))){
            bvx=10;
            bx+=bvx;
            balon.setX(bx);
            balon.setVX(bvx);
        }
        
        //colisiones PLAYER2/balon
        if(ball.intersects(P2norte)&&(ball.intersects(P2noroeste))&&(ball.intersects(P2noreste))){
            by=y2-31;
            bt=0;
            bvy=-bvy;
            balon.setY(by);
            balon.setTime(bt);
            balon.setVY(bvy);
        }else if(ball.intersects(P2norte)&&(ball.intersects(P2noroeste))){
            double rad= Math.toRadians(60);
            double rapidez=sqrt((bvy*bvy)+(bvx*bvx));
            double xx=-rapidez*cos(rad);
            double yy=rapidez*sin(rad);
            int a=0;
            if(vy1<0){
                a=vy2;
            }
            by=y2-31;
            bvy=-intValue(yy)+a;
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setY(by);
            balon.setTime(0);
        }else if(ball.intersects(P2norte)&&(ball.intersects(P2noreste))){
            double rad= Math.toRadians(60);
            double rapidez=sqrt((bvy*bvy)+(bvx*bvx));
            double xx=rapidez*cos(rad);
            double yy=rapidez*sin(rad);
            int a=0;
            if(vy1<0){
                a=vy2;
            }
            by=y2-31;
            bvy=-intValue(yy)+a;
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setY(by);
            balon.setTime(0);
        }else if(ball.intersects(P2noreste)){
            double rad= Math.toRadians(45);
            double rapidez=sqrt((bvy*bvy)+(bvx*bvx));
            double xx=rapidez*cos(rad);
            double yy=rapidez*sin(rad);
            int a=0;
            if(vy1<0){
                a=vy2;
            }
            by=y2-31;
            bvy=-intValue(yy)+a;
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setY(by);
            balon.setTime(0);
        }else if(ball.intersects(P2noroeste)){
            double rad= Math.toRadians(45);
            double rapidez=sqrt((bvy*bvy)+(bvx*bvx));
            double xx=-rapidez*cos(rad);
            double yy=rapidez*sin(rad);
            int a=0;
            if(vy1<0){
                a=vy2;
            }
            by=y2-31;
            bvy=-intValue(yy)+a;
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setY(by);
            balon.setTime(0);
        }else if(ball.intersects(P2oeste)){
            bvx=-10;
            bx+=bvx;
            balon.setX(bx);
            balon.setVX(bvx);
        }else if((ball.intersects(P2este))){
            bvx=10;
            bx+=bvx;
            balon.setX(bx);
            balon.setVX(bvx);
        }
        //fisica balon
        //el balon esta sujeto a la caida libre si no toca el suelo para representar la gravedad
        if((balon.getY()<(370))&&(balon.getVY()>0)){
            bvy=balon.vy(bvy, bt);
            by=balon.y(by, bvy, bt);
            bt++;
            bx+=bvx;
            balon.setX(bx);
            balon.setTime(bt);
            balon.setVY(bvy);
            balon.setY(by);
        }else if((balon.getY()<(370))&&(balon.getVY()<=0)){
            bvy=balon.vy(bvy, bt);
            by=balon.y(by, bvy, bt);
            bt++;
            bx+=bvx;
            balon.setX(bx);
            balon.setTime(bt);
            balon.setVY(bvy);
            balon.setY(by);
        }else if(balon.getY()>370){
            bx+=bvx;
            balon.setX(bx);
            balon.setTime(0);
            balon.setY(370);
        }else if(balon.getY()==370){
            // si el balon toca el piso, este rebota pero pierde velocidad a cada rebote
            bvy-=(2*bvy);
            by=balon.y(by,bvy,bt);
            bx+=bvx;
            balon.setX(bx);
            balon.setVY(bvy);
            balon.setY(by);
        }
        //fisica de los jugadores
        // cada jugador esta sujeto a la caida libre si no toca el suelo para representar la gravedad
        if((player1.getY()<=(330))&&(player1.getVY()<0)){
            vy1=player1.vy(vy1, t1);
            y1=player1.y(y1, vy1, t1);
            t1++;
            player1.setTime(t1);
            player1.setVY(vy1);
            player1.setY(y1);
        }else if((player1.getY()<(330))&&(player1.getVY()>=0)){
            vy1=player1.vy(vy1, t1);
            y1=player1.y(y1, vy1, t1);
            t1++;
            player1.setTime(t1);
            player1.setVY(vy1);
            player1.setY(y1);
        }else if(player1.getY()>(330)){
            // si la pocision es menor al suelo, simplemente la posicion vuelve al nivel del suelo y se acaba la caida 
            player1.setTime(0);
            player1.setVY(0);
            player1.setY(330);
        }
        if((player2.getY()<=(330))&&(player2.getVY()<0)){
            vy2=player2.vy(vy2, t2);
            y2=player2.y(y2, vy2, t2);
            t2++;
            player2.setTime(t2);
            player2.setVY(vy2);
            player2.setY(y2);
        }else if((player2.getY()<(330))&&(player2.getVY()>=0)){
            vy2=player2.vy(vy2, t2);
            y2=player2.y(y2, vy2, t2);
            t2++;
            player2.setTime(t2);
            player2.setVY(vy2);
            player2.setY(y2);
        }else if(player2.getY()>(330)){
            player2.setTime(0);
            player2.setVY(0);
            player2.setY(330);
        }
        //fisica de los pies
        //si un pie no esta en la posicion inicial la velocidad de este cambiara gradualmente para que este vuelva a ella 
        if((player1.getpie().getX()>=player1.getX()+10)&&(player1.getpie().getVX()>0)){
            int a=player1.getpie().getVX();
            a--;
            int b=player1.getpie().getX();
            b+=a;
            player1.getpie().setVX(a);
            player1.getpie().setX(b);
        }else if((player1.getpie().getX()>player1.getX()+10)&&(player1.getpie().getVX()<=0)){
            int a=player1.getpie().getVX();
            a--;
            int b=player1.getpie().getX();
            b+=a;
            player1.getpie().setVX(a);
            player1.getpie().setX(b);
        }else if((player1.getpie().getX()<player1.getX()+10)){
            player1.getpie().setVX(0);
            player1.getpie().setX(player1.getX()+10);
        }
        
        if((player2.getpie().getX()<=player2.getX()+10)&&(player2.getpie().getVX()<0)){
            int a=player2.getpie().getVX();
            a++;
            int b=player2.getpie().getX();
            b+=a;
            player2.getpie().setVX(a);
            player2.getpie().setX(b);
        }else if((player2.getpie().getX()<player2.getX()+10)&&(player2.getpie().getVX()>=0)){
            int a=player2.getpie().getVX();
            a++;
            int b=player2.getpie().getX();
            b+=a;
            player2.getpie().setVX(a);
            player2.getpie().setX(b);
        }else if((player2.getpie().getX()>player2.getX()+10)){
            player2.getpie().setVX(0);
            player2.getpie().setX(player2.getX()+10);
        }
        
        //colisiones pie/balon
        //si el balon colisiona con un pie iniciara un tiro parabolico independiente de la velocidad del balon 
        if(ball.intersects(P1pie)){
            double rad= Math.toRadians(45);
            double xx=30*cos(rad);
            double yy=30*sin(rad);
            bvy=-intValue(yy);
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setTime(0);
        }
        if(ball.intersects(P2pie)){
            double rad= Math.toRadians(45);
            double xx=-30*cos(rad);
            double yy=30*sin(rad);
            bvy=-intValue(yy);
            bvx=intValue(xx);
            bt=0;
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setTime(0);
        }
        // colision palo/balon
        // dada un colision con el palo la pelota simplemente rebotara
        if(ball.intersects(expalo1)){
            bvy=-bvy;
            bvx=-bvx;
            by=240;
            bx=100;
            balon.setY(by);
            balon.setX(bx);
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setTime(0);
        }else if(ball.intersects(expalo2)){
            bvy=-bvy;
            bvx=-bvx;
            by=210;
            bx=870;
            balon.setY(by);
            balon.setX(bx);
            balon.setVY(bvy);
            balon.setVX(bvx);
            balon.setTime(0);
        }else if(ball.intersects(palo2)){
            bvy=-bvy;
            bx+=bvx;
            by=210;
            balon.setY(by);
            balon.setX(bx);
            balon.setVY(bvy);
            balon.setTime(0);
        }else if(ball.intersects(palo1)){
            bvy=-bvy;
            bx+=bvx;
            by=210;
            balon.setY(by);
            balon.setX(bx);
            balon.setVY(bvy);
            balon.setTime(0);
        }else if(ball.intersects(cancha1)){
            // si se detecta una colision con la porteria se aumentara el marcador dependienddo de cual sea 
            // ademas todos los objetos volveran a sus pocisiones iniciales
            balon.setY(20);
            balon.setX(490);
            balon.setVX(0);
            balon.setVY(0);
            balon.setTime(0);
            player1.setY(350);
            player1.setX(150);
            player1.setVX(0);
            player1.setVY(0);
            player1.setTime(0);
            player2.setY(350);
            player2.setX(800);
            player2.setVX(0);
            player2.setVY(0);
            player2.setTime(0);
            score2+=1;
        }else if(ball.intersects(cancha2)){
            balon.setY(20);
            balon.setX(490);
            balon.setVX(0);
            balon.setVY(0);
            balon.setTime(0);
            player1.setY(350);
            player1.setX(150);
            player1.setVX(0);
            player1.setVY(0);
            player1.setTime(0);
            player2.setY(350);
            player2.setX(800);
            player2.setVX(0);
            player2.setVY(0);
            player2.setTime(0);
            score1+=1;
        }
        if(player2.getAi()==true){
            player2.ai(player2.getAi(), bx, by);
        }
    }
    public Balon getbalon(){
        return balon;
    }
    public Player getplayer1(){
        return player1;
    }
    public Player getplayer2(){
        return player2;
    }
    public int getscore1(){
        return score1;
    }
    public int getscore2(){
        return score2;
    }
    public void setscore1(int a){
        score1=a;
    }
    public void setscore2(int a){
        score2=a;
    }
    public void ganador(){
        if(score1>score2){
            JOptionPane.showMessageDialog(this, "Gana el jugador 1");
            JOptionPane.showMessageDialog(this, "volver al menu");
        }else if(score1>score2){
            JOptionPane.showMessageDialog(this, "Gana el jugador 2");
            JOptionPane.showMessageDialog(this, "volver al menu");
        }else if(score1==score2){
            JOptionPane.showMessageDialog(this, "Empate");
            JOptionPane.showMessageDialog(this, "volver al menu");
        }
    }
}
