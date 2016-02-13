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

public class Lian_Barsky extends JPanel {

  public static JPanel panel ;    

    @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      Dimension size = getSize();
      Insets insets = getInsets();

      int w =  size.width - insets.left - insets.right;
      int h =  size.height - insets.top - insets.bottom;

      int xp = w/2;
      int yp = h/2;
      int P_Izquierdo = w/4;
      int P_Derecho = w*3/4;
      int P_Superior = h/4;
      int P_Inferior = h*3/4;      
      
      //g2d.drawLine(xp, yp, xp, yp); //punto centro
      g2d.drawLine(P_Izquierdo,P_Superior,P_Izquierdo,P_Inferior);//Linea Izquierda
      g2d.drawLine(P_Izquierdo,P_Superior,P_Derecho,P_Superior);//Linea Superior
      g2d.drawLine(P_Derecho,P_Inferior,P_Derecho,P_Superior);//Linea Derecha
      g2d.drawLine(P_Derecho,P_Inferior,P_Izquierdo,P_Inferior);//Linea Inferior

  }    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Algorithm Of Liang Barsky");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showInputDialog("");
        Lian_Barsky LiangB = new Lian_Barsky();
        frame.add(LiangB);
        frame.setBackground(Color.BLACK);
        frame.setSize(512, 512);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);      
    }
}
  


