package clipping.sutherland;
/**
 *
 * @author Mario G, Anderson, Pablito
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JFrame;

import java.util.Random; 

public class ClippingSutherland extends JPanel {

  //Genera las lineas y inicia el método para validarlas dentro o fuera del clip
  public void add_lines(Graphics2D g2d, int w, int h, int pSuperior, 
    int pInferior, int pIzquierdo, int pDerecho){
    
    g2d.setColor(Color.red);
    
    //Generar 100 lineas
    
    for( int i = 0; i < 100; ++i ){
        Random r = new Random();
        int x1 = Math.abs(r.nextInt()) % w;
        int y1 = Math.abs(r.nextInt()) % h;
        int x2 = Math.abs(r.nextInt()) % w;
        int y2 = Math.abs(r.nextInt()) % h;   
        
        //punto inicial de la linea
        punto2D puntox = new punto2D(x1,y1);
        //punto final de la linea
        punto2D puntoy = new punto2D(x2,y2);     

      boolean outside = begin_validations(puntox,puntoy,pSuperior,pInferior, 
              pIzquierdo, pDerecho);
      if(outside) cohen_sutherland(puntox,puntoy, g2d,pSuperior, pInferior, 
              pIzquierdo, pDerecho);
    }
}
  
  // Verifica si el punto entregado esta dentro del clip
  public boolean not_is_inside(punto2D p, int pSuperior, int pInferior, 
          int pIzquierdo, int pDerecho){
    if( p.getX() < pIzquierdo || p.getX() > pDerecho || p.getY() < pSuperior || 
            p.getY()> pInferior ) return true;
    else return false;
  }  
  
  // Calcula la distancia entre punto x1 y punto x2 de cada linea generada
  public double dist(punto2D p1, punto2D p2){
    double x = p1.getX() - p2.getX();
    if( x < 0 ) x = -x;
    double y = p1.getY() - p2.getY();
    if( y < 0 ) y = -y;
    return Math.sqrt(x*x + y*y);
  }  
  
  // Halla el punto de intersección con la linea del clip
  public punto2D intersection(punto2D pi, punto2D pf, int i, int pSuperior, 
           int pInferior, int pIzquierdo, int pDerecho){
    int x0 = pi.getX();
    int x1 = pf.getX();
    int y0 = pi.getY();
    int y1 = pf.getY();
    int x=0,y=0;
    
    if (i == 8) {
      x = (x0 + (pInferior - y0) * (x1 - x0) / (y1 - y0));
      y = pInferior;
    } else if (i == 4) {
      x = (x0 + (pSuperior - y0) * (x1 - x0) / (y1 - y0));
      y = pSuperior;
    } else if (i == 1) {
      x = pIzquierdo;
      y = (y0 + (y1 - y0) * (pIzquierdo - x0) / (x1 - x0));
    } else if (i == 2) {
      x = pDerecho;
      y = (y0 + (y1 - y0) * (pDerecho - x0) / (x1 - x0));
    }
    return new punto2D(x,y);
  }
  
  //Ubica el lado por el cual sale el punto del clip(si lo hace)
  int lado_salida(punto2D p, int pSuperior, 
           int pInferior, int pIzquierdo, int pDerecho){
    if( p.getX() < pIzquierdo ) return 1;
    else if( p.getX() > pDerecho ) return 2;
    else if( p.getY() < pSuperior ) return 4;
    else if( p.getY() > pInferior ) return 8;
    return 0;
  }  
  
  //Entrega el punto de intersccion(continuación de Intersección* facilita)
  punto2D Punto_Result(punto2D pi, punto2D pf, int pSuperior, 
           int pInferior, int pIzquierdo, int pDerecho){
    int salida = lado_salida(pi, pSuperior, 
            pInferior, pIzquierdo, pDerecho);
    punto2D result;
    
    for( int i = 1;; i*=2){
      if( (salida & i) > 0 ){ 
        result = intersection(pi,pf,i, pSuperior, 
            pInferior, pIzquierdo, pDerecho);
        break;
      }
    }
    return result;
  }  
  
 //Ecuación de sutherland omitiendo los comprobantes que son otros métodos
 void cohen_sutherland(punto2D p1, punto2D p2, Graphics2D g2d, int pSuperior, 
    int pInferior, int pIzquierdo, int pDerecho){
    punto2D cpy1 = p1, cpy2 = p2;
    double total_dist = dist(p1,p2);
    int error = 0;
    
    while( ( not_is_inside(cpy1, pSuperior, 
           pInferior, pIzquierdo, pDerecho) || not_is_inside(cpy2, pSuperior, 
           pInferior, pIzquierdo, pDerecho) ) ){
      if( total_dist - (dist(p1,cpy1)+dist(p2,cpy2)) <= 0 ){ 
        error = 1;
        break;
      }
      
      if( not_is_inside(cpy1, pSuperior, 
           pInferior, pIzquierdo, pDerecho) ) cpy1 = Punto_Result(cpy1,p2,pSuperior, 
           pInferior, pIzquierdo, pDerecho);
      if( not_is_inside(cpy2,pSuperior, 
           pInferior, pIzquierdo, pDerecho) ) cpy2 = Punto_Result(cpy2,p1,pSuperior, 
           pInferior, pIzquierdo, pDerecho);
    }
    
    if( error == 1 ) g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    else{
      g2d.setColor(Color.red);
      g2d.drawLine(p1.getX(), p1.getY(), cpy1.getX(), cpy1.getY());
      g2d.setColor(Color.blue);
      g2d.drawLine(cpy1.getX(), cpy1.getY(), cpy2.getX(), cpy2.getY());
      g2d.setColor(Color.RED);
      if(cpy2 != p2) g2d.drawLine(cpy2.getX(), cpy2.getY(), p2.getX(), p2.getY());
    }
  } 
    
    @Override    
public void paintComponent(Graphics g) {
      super.paintComponent(g);

      Graphics2D g2d = (Graphics2D) g;

      g2d.setStroke(new BasicStroke(5));
      g2d.setColor(Color.green);

      // size es el tamano de la ventana.
      Dimension size = getSize();
      // Insets son los bordes y los titulos de la ventana.
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
      
      g2d.setStroke(new BasicStroke(0));
      add_lines(g2d, w, h, P_Superior, 
      P_Inferior, P_Izquierdo, P_Derecho);      
   }    
    
    //Se comienzan las validaciones para implementar la ecuación de sutherland
   boolean begin_validations(punto2D p1, punto2D p2, int pSuperior, 
           int pInferior, int pIzquierdo, int pDerecho){
    if( p1.getX() < pIzquierdo  && p2.getX() < pIzquierdo  ||
        p1.getX() > pDerecho && p2.getX() > pDerecho ||
        p1.getY() < pSuperior  && p2.getY() < pSuperior  ||
        p1.getY() > pInferior && p2.getY() > pInferior ) return false;
    return true;  
  }

  public static void main(String[] args) {
      // Crear un nuevo Frame
      JFrame frame = new JFrame("Sutherland");
      // Al cerrar el frame, termina la ejecucion de este programa
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      // Agregar un JPanel que se llama Points (esta clase)
      frame.add(new ClippingSutherland());
      // Asignarle tamaÃ±o
      frame.setSize(728, 730);
      ClippingSutherland clip = new ClippingSutherland();
      frame.add(clip);
      clip.setBackground(Color.BLACK);
      // Poner el frame en el centro de la pantalla
      frame.setLocationRelativeTo(null);
      // Mostrar el frame
      frame.setVisible(true);
  }
}