package housepaint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Anderson,Pablo,Mario
 */

public class HousePaint extends JPanel {
    Punto3D[] puntos;
    int[][] lineas;
    Graphics g;
    Matriz3D[] listaMatrices; 
    int ancho,alto,xP,yP,zP;
    Graphics2D g2d;
    String[] auxLines;
    Matriz3D[] transformaciones;    
    
    @Override    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setColor(Color.BLUE);     
        Dimension size = getSize();    
        Insets insets = getInsets();
        ancho =  size.width - insets.left - insets.right;
        alto =  size.height - insets.top - insets.bottom;   
        yP = alto/2;
        xP = ancho/2;
        zP = alto/2; 
        g2d.setColor(Color.red);     
        try {
            g2d.setColor(Color.red);
            leer();           
        } catch (IOException ex) {
            
        }
    }
  
    //mapeo en X
    public int mapeoX(double x){
        return (int)x+ xP;
    } 
    
    //mapeo en y
    public int mapeoY(double y){
        return (int)y*(-1)+ yP;
    }
    
    public int mapeoZ(double z){
        return (int)z+ zP;
    }
    
    //Lee los datos pasados por el archivo Datos.txt
    public void leer() throws IOException{
        String transformacion = "";
        g2d.setColor(Color.blue);
        g2d.drawLine(mapeoX(-1000), mapeoY(0), mapeoX(1000), mapeoY(0));
         g2d.drawLine(mapeoX(0), mapeoY(-1000), mapeoX(0), mapeoY(1000));
        BufferedReader br = new BufferedReader(new FileReader("Datos.txt"));
        int numberOfPoints = Integer.parseInt(br.readLine());
        //lista de puntos
        puntos = new Punto3D[numberOfPoints];
        for(int i=0; i<numberOfPoints; i++){
           puntos[i] = new Punto3D(0,0,0,1);
           String point = br.readLine();
           String[] auxPoints = point.split(",");
           int x = Integer.parseInt(auxPoints[0]);
           puntos[i].setX(x);
           int y = Integer.parseInt(auxPoints[1]);
           puntos[i].setY(y); 
           int z = Integer.parseInt(auxPoints[2]);
           puntos[i].setZ(z);
        }
        
        for(int i=0;i<puntos.length ;i++){
            System.out.println(puntos[i].getX() + "," + puntos[i].getY() + "," + puntos[i].getZ());
        }
        
        int numberOfLines = Integer.parseInt(br.readLine());
        //lista de lineas
        lineas = new int [3][numberOfLines];
        for(int i=0;i<numberOfLines;i++){
           String line = br.readLine();
           auxLines = line.split(",");
           int number1 = Integer.parseInt(auxLines[0]);
           int number2 = Integer.parseInt(auxLines[1]);
           lineas[0][i] = number1;
           lineas[1][i] = number2;           
        }
        
        for(int i=0;i<lineas[0].length ;i++){
            System.out.println(lineas[0][i]+ "," + lineas[1][i]);
        }
        
        Integer numberOfTransforms = Integer.parseInt(br.readLine());
        //Lista de transformaciones
        transformaciones = new Matriz3D[numberOfTransforms];
        for(int i=0; i<numberOfTransforms; i++){         
           String trans = br.readLine();
           String[] aux = trans.split(",");
           // transformacion a realizar
           transformacion = aux[0];
           //valor para realizar las transformaciones
           String valorA= aux[1];
           String valorB="";
           String valorC="";
           if(aux.length>2){
              valorB = aux[2];
           }
           if(aux.length>3){
               valorC = aux[3];
           }         
           dibujarLineas();
           transformaciones[i]= aplicar(transformacion, valorA, valorB, valorC);          
        }
    }      
    
    //dibujas las lineas segun los puntos pasados por el archivo Datos.txt
    public void dibujarLineas(){
        for(int i=0;i<lineas[0].length;i++){
           int number1 = lineas[0][i];
           int number2 = lineas[1][i];
           int number3 = lineas[2][i];
           double[] punto1 = puntos[number1].getPoint();
           double[] punto2 = puntos[number2].getPoint();
           double[] punto3 = puntos[number3].getPoint();
           g2d.setColor(Color.red);
           //x1,y1,x2,y2
           g2d.drawLine(mapeoX(punto1[0]), mapeoY(punto1[1]), mapeoX(punto2[0]), mapeoY(punto2[1]));
        }
    }
    
    //aplica las operaciones dichas en el archivo Datos.txt a la figura ya dibujada
    public Matriz3D aplicar(String transformacion, String param1,String param2, String param3){
       // matriz para aplicar las operaciones
       Matriz3D a = new Matriz3D();
       switch (transformacion) {
            case "rotar":
                int eje = Integer.parseInt(param1);
                double alfa = Math.toRadians(Double.parseDouble(param2));               
                a.rotation3D(eje,alfa);
                break;
            case "transladar":
                {                   
                    int x = Integer.parseInt(param1);
                    int y = Integer.parseInt(param2);  
                    int z = Integer.parseInt(param3);  
                    a.translation3D(x, y, z);
                    break;
                }
            case "escalar":
                {                   
                    double x = Double.parseDouble(param1);
                    double y = Double.parseDouble(param2); 
                    double z = Double.parseDouble(param3);  
                    a.scaling3D(x, y, z);
                    break;
                }
            default:
                return a;
        }
        Multiplicar(a);
        dibujarLineas();
        return a;
    }
  
    //realiza la multiplicacion de la matriz por el vector
    public void Multiplicar (Matriz3D matriz){
        for(int i = 0; i < puntos.length; i++){
            puntos[i].multiMatrizPunto(matriz);
        }
    }

    public static void main(String[] args) {
        final JFrame frame = new JFrame("HousePaint3D");
        // Al cerrar el frame, termina la ejecuciÃ³n de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel que se llama Points (esta clase)
        final HousePaint house = new HousePaint();
        frame.add(house);
        // Asignarle tamaÃ±o
        frame.setSize(1280,720);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);       
    }
}