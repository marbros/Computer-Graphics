package dibujar.circulo;

/**
 *
 * @author Mario Giraldo R
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JFrame;


public class DibujarCirculo extends JPanel {

   @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      // size es el tamano de la ventana.
      Dimension size = getSize();
      // Insets son los bordes y los titulos de la ventana.
      Insets insets = getInsets();

      int w =  size.width - insets.left - insets.right; // Ancho del Frame  
      int h =  size.height - insets.top - insets.bottom;    //Alto del Frame
     
      int valx, valy, rad; // Ubicación y radio de cada circulo
      
      int cont= 1;
      int cont2= 1;
      
      while(cont!=1000){
          rad = (int)(Math.random()*100);
          valx = (int)(Math.random()*w);
          valy = (int)(Math.random()*h);
          int x,y,param;
          x = 0;
          y = rad;
          /*calcule el primer parámetro como 
            p1 = 3 -2r
            si p1 < 0 la siguiente posición es (x1+1, y1) de lo contrario, la sgte
            posicion es (x1+1, y1 -1)                             
          */                 
          param = 3-2*rad;
          if(cont2==1){
            g2d.setColor(Color.CYAN);
            cont2++;
          }else if(cont2==2){
            g2d.setColor(Color.MAGENTA);
            cont2=1;
          }

          /* Ecuación del termino de error para ubicar el siguiente punto de la
          * circunferencia segun parametro = 3-2*rad 
          */
          
          while(x < y) {
              if(param < 0) {
                  param = param + 4 * x +6;
              }else {
                  param = param + 4*(x-y)+10;
                  y = y-1;
              }
              x++;
              //x1, y1, x2, y2
              //Coordenadas Polares
              g2d.drawLine(valx + x, valy + y, valx + x, valy + y); //(x,y)
              g2d.drawLine(valx - x, valy - y, valx - x, valy - y); //(-x,-y)

              g2d.drawLine(valx + y, valy + x, valx + y, valy + x); //(y,x)
              g2d.drawLine(valx - y, valy - x, valx - y, valy - x); //(-y,-x)

              g2d.drawLine(valx + x, valy - y, valx + x, valy - y); //(x,-y)
              g2d.drawLine(valx - x, valy + y, valx - x, valy + y); //(-x,y)

              g2d.drawLine(valx + y, valy - x, valx + y, valy - x); //(y,-x)
              g2d.drawLine(valx - y, valy + x, valx - y, valy + x); //(-y,x)
          }
          cont++;
      }        
  } 
 
  /**
   * Esta función crea un frame en donde se hará la circunferencia de "N" 
   * círculos por medio del Algoritmo de Bresenham.
   * @param args 
   */
   public static void main(String[] args) {
      JFrame frame = new JFrame("Algoritmo De Bresenham");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
      DibujarCirculo circulo = new DibujarCirculo();
      frame.add(circulo);
      circulo.setBackground(Color.BLACK);
      frame.setSize(500, 500);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }    
}
