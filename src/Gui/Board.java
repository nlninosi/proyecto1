/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controlador.*;
import Modelo.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static oracle.jrockit.jfr.events.Bits.intValue;

/**
 *
 * @author Nicolas
 */
public class Board extends JPanel implements ActionListener {
    // El board actua como un JPanel
    private Timer timer;
    Controlador controlador;
    private Balon b;
    private Player pl1;
    private Player pl2;
    private int im1;
    private int d1;
    private int i1;
    private int h1;
    private int im2;
    private int d2;
    private int i2;
    private int h2;
    private boolean activo;
    private int tiempo;
    private int t;
    private int t1;
    private int sc1;
    private int sc2;
    public Board(Controlador controlador){
        //cada Board posee un controlador y otras variables/objetos que dependen de el
        //tambien posee variables para medir el tiempo
        this.controlador=controlador;
        this.timer = new Timer(100, this);
        this.timer.start();
        this.tiempo=0;
        this.t=0;
        this.t1=0;
        this.activo=false;
        this.sc1=this.controlador.getscore1();
        this.sc2=this.controlador.getscore2();
        b=this.controlador.getbalon();
        pl1=this.controlador.getplayer1();
        pl2=this.controlador.getplayer2();
    }
    public void paintComponent(Graphics g){
            super.paintComponent(g);
            Image player = loadImage("player.png");
            Image fondo = loadImage("fondo.jpg");
            Image grass = loadImage("grass.jpg");
            Image net = loadImage("cancha.png");
            Image ball = loadImage("ball.png");
            Image score = loadImage("score.png");
            //Imagen de fondo
            g.drawImage(fondo, 0, 0, 1000, 500, this);
            //Porterias
            g.drawImage(net, 120, 240, 0, 400,0,0,300,592, this);
            g.drawImage(net, 880, 240, 1000, 400,0,0,300,592, this);
            //Marcador
            g.drawImage(score, 490, 30, 520, 50,670,50,770,100, this);
            g.drawImage(score, 430, 10, 480, 60,5+90*sc1,140,95+90*sc1,250, this);
            g.drawImage(score, 530, 10, 580, 60,5+90*sc2,340,95+90*sc2,450, this);
            //Tiempo
            g.drawImage(score, 0, 0, 50, 50,0+50*t1,0,40+50*t1,55, this);
            g.drawImage(score, 50, 0, 100, 50,0+50*t,0,40+50*t,55, this);
            //Jugadores
            //dadas unas variables se dibuja la imagen de los jugadores de acuerdo a su velocidad, o si patean el balon
            g.drawImage(player, pl1.getX(),pl1.getY(), pl1.getX()+40, pl1.getY()+70, ((30+(100*d1))+150*im1), 775-h1, ((30+(100*i1))+150*im1), 890-h1, this);
            g.drawImage(player, pl2.getX(),pl2.getY(), pl2.getX()+40, pl2.getY()+70, ((30+(100*d2))+150*im2), 775-h2, ((30+(100*i2))+150*im2), 890-h2, this);
            //Balon
            g.drawImage(ball, b.getX(),b.getY(), 30,30, this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // se detecta la velocidad y de acuerdo a esta se navega el sprite
        if(pl1.getVX()>0){
            d1=1;
            h1=0;
            i1=0;
        }else if(pl1.getVX()<0){
            d1=0;
            h1=0;
            i1=1;
        }else{
            d1=1;
            im1=2;
            h1=755;
            i1=0;
        }
        if(pl1.getpie().getX()!=pl1.getX()+10){
            // si el pie no esta en su posicion inicial se asume que el jugador esta pateando 
            d1=1;
            im1=1;
            h1=151;
            i1=0;
        }
        if(im1<4){
            im1++;
        }else{
            im1=0;
        }
        
        if(pl2.getVX()>0){
            d2=1;
            h2=0;
            i2=0;
        }else if(pl2.getVX()<0){
            d2=0;
            h2=0;
            i2=1;
        }else{
            d2=1;
            im2=2;
            h2=755;
            i2=0;
        }
        if(pl2.getpie().getX()!=pl2.getX()+10){
            d2=0;
            im2=1;
            h2=151;
            i2=1;
        }
        if(im2<4){
            im2++;
        }else{
            im2=0;
        }
        // estas variables miden el tiempo y navegan el sprite
        this.tiempo++;
        t=intValue(tiempo/20);
        if(intValue(tiempo/20)>=10){
            tiempo=0;
            t1+=1;
            this.t=0;
        }
        if(t1>=6){
            this.timer.stop();
            controlador.ganador();
        }
        if((sc1>=9)||(sc2>=9)){
            this.timer.stop();
            controlador.ganador();
        }
        this.sc1=this.controlador.getscore1();
        this.sc2=this.controlador.getscore2();
        pl1=controlador.getplayer1();
        pl2=controlador.getplayer2();
        
        repaint();
    }
    
    
    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
    public boolean getActivo(){
        return activo;
    }
    public void setActivo(boolean a){
        activo=a;
    }
    public void starttimer(){
        this.timer.start();
        this.activo=true;
    }
    //el metodo stoptimer "reinicia" el juego 
    public void stoptimer(){
        this.timer.stop();
        this.activo=false;
        controlador.getplayer1().setY(350);
        controlador.getplayer1().setX(150);
        controlador.getplayer1().setVX(0);
        controlador.getplayer1().setVY(0);
        controlador.getplayer1().setTime(0);
        controlador.getplayer2().setY(350);
        controlador.getplayer2().setX(800);
        controlador.getplayer2().setVX(0);
        controlador.getplayer2().setVY(0);
        controlador.getplayer2().setTime(0);
        controlador.getbalon().setY(20);
        controlador.getbalon().setX(490);
        controlador.getbalon().setVX(0);
        controlador.getbalon().setVY(0);
        controlador.getbalon().setTime(0);
        controlador.setscore1(0);
        controlador.setscore2(0);
        this.tiempo=0;
        this.t=0;
        this.t1=0;
    }
}
