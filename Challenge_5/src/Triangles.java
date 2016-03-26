package triangles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Mario
 */
public class Triangles extends JPanel {
    
    ArrayList<Vector3D> puntos;
    ArrayList<Segment> segmentos;
    
    public void asignarColores() {
        Random rand = new Random();
        Segment segmento = new Segment(null, null, null, 0, null);
        for (int i = 0; i < segmentos.size(); i++) {
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color randomColor = new Color(r, g, b);
            segmento = segmentos.get(i);
            segmento.color = randomColor;
            segmentos.set(i, segmento);
            //System.out.println("segmento " + i + " " + segmento.color);
        }
    }
    
   //Lee los datos pasados por el archivo Datos.txt
    public void leer() throws IOException{
        float x, y, z;
        int s1, s2, s3;

        Segment segment;
        Vector3D vector, vector1, vector2, vector3;        
        puntos = new ArrayList<>();
        
        BufferedReader br = new BufferedReader(new FileReader("Datos.txt"));
        int numberOfPoints = Integer.parseInt(br.readLine());
        
        for(int i=0; i<numberOfPoints; i++){
            String point = br.readLine();
            String[] auxPoints = point.split(",");
            x =(float) Double.parseDouble(auxPoints[0]);
            y =(float) Double.parseDouble(auxPoints[1]);
            z =(float) Double.parseDouble(auxPoints[2]);
            vector = new Vector3D(x, y, z, 1);
            puntos.add(vector);
        }
        
        /* used to verify input data
        for(int i=0;i<puntos.size() ;i++){
            System.out.println(puntos.get(i).getX() + "," + puntos.get(i).getY() + "," + puntos.get(i).getZ());
        }*/
        
        int numberOfSegments = Integer.parseInt(br.readLine());
        segmentos = new ArrayList<>();        
        //lista de sementos
        String[] segmento;
        
        for(int i=0;i<numberOfSegments;i++){
           String line = br.readLine();
           segmento = line.split("-");
           s1 = Integer.parseInt(segmento[0]);
           s2 = Integer.parseInt(segmento[1]);
           s3 = Integer.parseInt(segmento[2]);
           vector1 = puntos.get(s1-1);
           vector2 = puntos.get(s2-1);
           vector3 = puntos.get(s3-1);
           segment = new Segment(vector1, vector2, vector3, 0f, null);
           segment.hallarP();
           //System.out.println("segmento " + i + " " + segment.p);
           segmentos.add(segment);     
        }

    } 
    
    public void ordenarSegmentos() {
        Segment buffer;
        int i, j;        
        
        for (i = 0; i < segmentos.size(); i++) {
            for (j = 0; j < i; j++) {
                if (segmentos.get(i).p < segmentos.get(j).p) {
                    buffer = segmentos.get(j);
                    segmentos.set(j, segmentos.get(i));
                    segmentos.set(i, buffer);
                }
            }
        }
    }
    
    public boolean hayConflicto(Segment s1, Segment s2){
        boolean conflict=false;

        //encontrar los puntos mínimos y máximos
        Vector3D p1min=s1.vector1;
        Vector3D p1max=s1.vector2;
        if(s1.vector2.getZ()<p1min.getZ()){
            p1min=s1.vector2;
            p1max=s1.vector1;
        }
        Vector3D p2min=s2.vector1;
        Vector3D p2max=s2.vector2;
        if(s2.vector2.getZ()<p2min.getZ()){
            p2min=s2.vector2;
            p2max=s2.vector1;
        }
        
        
        //criterio1
        if(!(p2min.getZ()>p1max.getZ())){
            //hay conflicto
            conflict=true;
        }
        //criterio2
        if(!(p1max.getX()<p2min.getX()||p1min.getX()>p2max.getX())){
            //hay conflicto
            conflict=true;
        }
        //criterio 3
        if(!(p1max.getZ()<p2max.getZ())){
            //hay conflicto
            conflict=true;
        }
        if(!(p2max.getZ()>=p1max.getZ())){
            //hay conflicto
            conflict=true;
        }
        return conflict;
    }
    
    
    public void OrdenarCriterioConflicto(){
        Segment buffer;
        int i, j;
        for (i = 0; i < segmentos.size()-1;i++) {
            for (j = i+1; j < segmentos.size(); j++) {
                if (hayConflicto(segmentos.get(i),segmentos.get(j))) {
                    buffer = segmentos.get(j);
                    segmentos.set(j, segmentos.get(i));
                    segmentos.set(i, buffer);
                }
            }
        }
    }    
    
    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
      
        // size es el tamano de la ventana.
        Dimension size = getSize();
        // Insets son los bordes y los titulos de la ventana.
        Insets insets = getInsets();

        int w =  size.width - insets.left - insets.right; // Ancho del Frame  
        int h =  size.height - insets.top - insets.bottom;    //Alto del Frame
      
        int[] x = new int[3];
        int[] y = new int[3];
        Vector3D vector1, vector2, vector3;
        
        try {
            leer();
        } catch (IOException ex) {
        }
        
        asignarColores();        
        ordenarSegmentos();
        OrdenarCriterioConflicto();
        Segment segmento;
        for (int i = 0; i < segmentos.size(); i++) {
            segmento = segmentos.get(i);
            //System.out.println("segmento " + i + " " + segmento.p);
            vector1 = segmento.vector1;
            vector2 = segmento.vector2;
            vector3 = segmento.vector3;
            x[0] = (int) vector1.getX() + w / 2;
            x[1] = (int) vector2.getX() + w / 2;
            x[2] = (int) vector3.getX() + w / 2;
            y[0] = (int) -vector1.getY() + h / 2;
            y[1] = (int) -vector2.getY() + h / 2;
            y[2] = (int) -vector3.getY() + h / 2;
            g2d.setColor(segmento.color);
            g2d.fillPolygon(x, y, 3);
        }
 
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Triangles");
        // Al cerrar el frame, termina la ejecuciÃ³n de este programa
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Agregar un JPanel
        Triangles t = new Triangles();
        frame.add(t);
        // Color de Fondo
        t.setBackground(Color.BLACK);
        // Asignarle tamaÃ±o
        frame.setSize(720,550);
        // Poner el frame en el centro de la pantalla
        frame.setLocationRelativeTo(null);
        // Mostrar el frame
        frame.setVisible(true);   
    }
    
}
