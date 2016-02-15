package housepaint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.SwingUtilities;

/**
 *
 * @author Mario
 */
public class designPanel extends JPanel implements MouseListener, MouseMotionListener {

    private BufferedImage preImage;    // re-drawing all shapes
    private Graphics2D preImageG2D;      
    private window window;    
    private int dimX;                 
    private int dimY;                    
    private int midX;                  
    private int midY; 
    private int cursorX;                  
    private int cursorY; 
    
    Punto3D[] puntos;
    int[][] lineas;
    Graphics g;
    Matriz3D[] listaMatrices; 
    int ancho,alto,xP,yP,zP;
    Graphics2D g2d;
    String[] auxLines;
    Matriz3D[] transformaciones;  
    
    public designPanel(window window){
	this.window = window; 
//        action = new actionPanel(window);
	addMouseListener(this);
	addMouseMotionListener(this);    
	dimY = window.getDimY();         
	dimX = window.getDimX();         
	midY = dimY / 2;              
	midX = dimX / 2;
        this.setBackground(Color.BLACK);
    }   

    // Reset the currently rendered image
    private void resetCurrentImage(){
	preImage = null;
	preImageG2D = null;
    }     
    
    @Override
    public void mouseClicked(MouseEvent me) {
	cursorX = me.getX() - midX;
	cursorY = me.getY() - midY; 
        resetCurrentImage();
	paintImmediately(0, 0, dimX, dimY);        
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
	cursorX = cursorY = 31313;
	paintImmediately(0, 0, dimX, dimY);        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
	cursorX = me.getX() - midX;
	cursorY = me.getY() - midY;
	paintImmediately(0, 0, dimX, dimY);        
    }  
 
    private void cartesianPlane(Graphics2D g2d){
        g2d.setStroke(new BasicStroke(5));
	g2d.setColor(Color.BLUE); 
        g2d.drawLine(midX, 0, midX, dimY); //vertical line        
        g2d.drawLine(0, midY, dimX, midY); //horizontal line        
    }
    
    private void paintCursor(Graphics2D g2d){
	g2d.setColor(Color.GRAY);
	String pos = cursorX + ", " + cursorY*-1;
	int mx = cursorX + midX;
	int my = cursorY + midY;
        // x, y, w, h, arcw, arch
	g2d.fillRoundRect(mx, my - 15, pos.length()*7, 20, 7, 7); //Paint Rect of Coor X, Y
	g2d.setColor(Color.WHITE);
	g2d.drawString(pos, mx + 5, my);
    }  

    //mapeo en X
    public int mapeoX(double x){
        return (int)x+ midX;
    } 
    
    //mapeo en y
    public int mapeoY(double y){
        return (int)y*(-1)+ midY;
    }
    
    public int mapeoZ(double z){
        return (int)z+ midY;
    }
    
    //aplica las operaciones dichas en el archivo Datos.txt a la figura ya dibujada
    public Matriz3D aplicar(String transformacion, String param1,String param2, String param3, Graphics2D g2d){
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
        dibujarLineas(g2d);
        return a;
    }
    
    //realiza la multiplicacion de la matriz por el vector
    public void Multiplicar (Matriz3D matriz){
        for(int i = 0; i < puntos.length; i++){
            puntos[i].multiMatrizPunto(matriz);
        }
    }
    
    //dibujas las lineas segun los puntos pasados por el archivo Datos.txt
    public void dibujarLineas(Graphics2D g2d){
        for(int i=0;i<lineas[0].length;i++){
           int number1 = lineas[0][i];
           int number2 = lineas[1][i];
           int number3 = lineas[2][i];
           double[] punto1 = puntos[number1].getPoint();
           double[] punto2 = puntos[number2].getPoint();
           double[] punto3 = puntos[number3].getPoint();
           g2d.setColor(Color.CYAN);
           //x1,y1,x2,y2
           g2d.drawLine(mapeoX(punto1[0]), mapeoY(punto1[1]), mapeoX(punto2[0]), mapeoY(punto2[1]));
        }
    }
    
   //Lee los datos pasados por el archivo Datos.txt
    public void leer(Graphics2D g2d) throws IOException{
        String transformacion = "";
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
           dibujarLineas(g2d);
           transformaciones[i]= aplicar(transformacion, valorA, valorB, valorC, g2d);          
        }
    }   
    
    // Method that paints into the panel
    @Override
    public void paintComponent(Graphics g){
	Graphics2D g2d = (Graphics2D) g;
	super.paintComponent(g2d); // Clears the screen 

        cartesianPlane(g2d);         
        if (preImage == null){
            preImage = new BufferedImage(dimX, dimY, BufferedImage.TYPE_INT_ARGB);
            preImageG2D = preImage.createGraphics();
	}else{
            g2d.drawImage(preImage, null, 0, 0);
	}
        
      
        Dimension size = getSize();    
        Insets insets = getInsets();
        ancho =  size.width - insets.left - insets.right;
        alto =  size.height - insets.top - insets.bottom;   
        yP = alto/2;
        xP = ancho/2;
        zP = alto/2; 
        try {
            g2d.setColor(Color.red);
            leer(g2d);           
        } catch (IOException ex) {
            
        }

        paintCursor(g2d);
    }          
}
