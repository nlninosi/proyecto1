/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Controlador.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Nicolas
 */
public class view extends JFrame {
    // la vista actua como un JFrame
    //la vista esta compuesta por un board y posee un controlador y un panel
    private Controlador controlador;
    private static Board board;
    private static JPanel mpanel;
    public view(Controlador controlador) {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            this.controlador=controlador;
            board=new Board(controlador);
            board.stoptimer();
            mpanel = new JPanel(new FlowLayout());
            // el mpanel esta compuesto de varios botones
            JButton oneplayer = new JButton("1 jugador");
            JButton onplayer = new JButton("");
            JButton twoplayer = new JButton("2 jugadores");
            JButton volver = new JButton("volver al menu");
            //cada boton posee un ActionListener qeu agrega o quita los demas botones ademar de iniciar/acabar el juego
            //cada boton tambien cambia la AI de cada jugador dependiendo de la opcion
            volver.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
                    mpanel.remove(volver);
                    mpanel.add(oneplayer);
                    mpanel.add(twoplayer);
                    board.setActivo(false);
                    controlador.getplayer2().setAi(false);
                    board.stoptimer();
                    mpanel.revalidate();
                    mpanel.repaint();
		}
            });
            onplayer.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    mpanel.remove(oneplayer);
                    mpanel.remove(twoplayer);
                    board.starttimer();
                    mpanel.add(volver);
                    mpanel.revalidate();
                    mpanel.repaint();
                }
            });
            oneplayer.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    mpanel.remove(twoplayer);
                    mpanel.remove(oneplayer);
                    board.setActivo(true);
                    controlador.getplayer2().setAi(true);
                    board.starttimer();
                    mpanel.add(volver);
                    mpanel.revalidate();
                    mpanel.repaint();
                }
            });
            twoplayer.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    mpanel.remove(oneplayer);
                    mpanel.remove(twoplayer);
                    board.setActivo(true);
                    board.starttimer();
                    mpanel.add(volver);
                    mpanel.revalidate();
                    mpanel.repaint();
                }
            });
            mpanel.add(oneplayer);
            mpanel.add(twoplayer);
            this.setLayout(new BorderLayout());
            this.add(board,BorderLayout.CENTER);
            this.add(mpanel,BorderLayout.SOUTH);
            addKeyListener(new EventosTeclado());
            setFocusable(true);
            pack();
            setVisible(true);
            
	}
    private class EventosTeclado extends KeyAdapter {
        // la vista posee un Key adapter que afecta a los jugadores cambiando sus posiciones y/o velocidades dependiendo de la tecla
        @Override
        public void keyReleased(KeyEvent k) {
            int key = k.getKeyCode();
            
        }
        @Override
        public void keyPressed(KeyEvent k) {
           int key = k.getKeyCode();
           if (key == KeyEvent.VK_LEFT) {
               int a=controlador.getplayer1().getX()-5;
               controlador.getplayer1().setX(a);
               controlador.getplayer1().setVX(-5);
           }
           if (key == KeyEvent.VK_RIGHT) {
               int a=controlador.getplayer1().getX()+5;
               controlador.getplayer1().setX(a);
               controlador.getplayer1().setVX(5);
           }
           if ((key == KeyEvent.VK_UP)&&(controlador.getplayer1().getY()==330)) {
               controlador.getplayer1().setY(329);
               controlador.getplayer1().setVY(-15);
           }
           if ((key == KeyEvent.VK_DOWN)&&(controlador.getplayer1().getpie().getX()==controlador.getplayer1().getX()+10)){
               controlador.getplayer1().getpie().setVX(7);
           }
           if(controlador.getplayer2().getAi()==false){
               if (key == KeyEvent.VK_D) {
                    int a=controlador.getplayer2().getX()+5;
                    controlador.getplayer2().setX(a);
                    controlador.getplayer2().setVX(5);
               }
               if (key == KeyEvent.VK_A) {
                    int a=controlador.getplayer2().getX()-5;
                    controlador.getplayer2().setX(a);
                    controlador.getplayer2().setVX(-5);
               }
               if ((key == KeyEvent.VK_W)&&(controlador.getplayer2().getY()==330)) {
                    controlador.getplayer2().setY(329);
                    controlador.getplayer2().setVY(-15);
               }
               if ((key == KeyEvent.VK_S)&&(controlador.getplayer2().getpie().getX()==controlador.getplayer2().getX()+10)) {
                    controlador.getplayer2().getpie().setVX(-7);
               }
           }
        }
    }
    
}
