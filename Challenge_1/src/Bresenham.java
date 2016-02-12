package bresenham;

/**
 * @author Mario
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.Random; 

public class Bresenham extends JPanel {

  public static JPanel panel ;    

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      Dimension size = getSize();
      Insets insets = getInsets();

      int w =  size.width - insets.left - insets.right;
      int h =  size.height - insets.top - insets.bottom;

      int valx, valy, rad; // Ubicación y radio de cada circulo  

      for (int i = 0; i <= 1000 ; i++) {

          rad = (int)(Math.random()*100);
          valx = (int)(Math.random()*w);
          valy = (int)(Math.random()*h);

          coloR();

          int x,y,param;
          x = 0;
          y = rad;
          param = 3-2*rad; 

          while(x < y) {
              if(param < 0) {
                  param = param + 4 * x +6;
              }else {
                  param = param + 4* (x-y) +10;
                  y = y-1;
              }
              x++;
              paintPoints(valx, valy, x, y, g2d);
          }                   
      } 

  }


  public static void coloR(Graphics2D g2d) {
      String colors []= {"255,0,255", "0,255,255"};
      int r , g , b;
      r = g = b = 255;
      String [] c;
      int num = (int)(Math.random()*color.length);
      for(int i = num; i <= color.length; ++i) {
          c = colors[i].split(",");
          r = Integer.parseInt(c[0]);
          g = Integer.parseInt(c[1]);
          b = Integer.parseInt(c[2]);
          i = colors.length;
      }
      Color rgb = new Color(r,g,b);                  
      g2d.setColor(rgb);  

      //to get rainbow, pastel colors
      Random random = new Random();
      final float hue = random.nextFloat();
      final float saturation = 1.0f;//1.0 for brilliant, 0.0 for dull
      final float luminance = 9.9f; //1.0 for brighter, 0.0 for black
      rgb = Color.getHSBColor(hue, saturation, luminance);          
      g2d.setColor(rgb);      
  }     

  public static void paintPoints(int valx, int valy, int x, int y, Graphics2D g2d) {
    //x1, y1, x2, y2
    g2d.drawLine(valx + x, valy + y, valx + x, valy + y); //(x,y)
    g2d.drawLine(valx - x, valy - y, valx - x, valy - y); //(-x,-y)

    g2d.drawLine(valx + y, valy + x, valx + y, valy + x); //(y,x)
    g2d.drawLine(valx - y, valy - x, valx - y, valy - x); //(-y,-x)

    g2d.drawLine(valx + x, valy - y, valx + x, valy - y); //(x,-y)
    g2d.drawLine(valx - x, valy + y, valx - x, valy + y); //(-x,y)

    g2d.drawLine(valx + y, valy - x, valx + y, valy - x); //(y,-x)
    g2d.drawLine(valx - y, valy + x, valx - y, valy + x); //(-y,x)    
  }    
    
      
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bresenham");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showInputDialog("");
        Bresenham circle = new Bresenham();
        frame.add(circle);
        frame.setBackground(Color.BLACK);
        frame.setSize(300, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);      
    }
}
  


