/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package housepaint;

/**
 *
 * @author Anderson,Pablo,Mario
 */
public class Punto2D {
 
    private double point[];
    
    public Punto2D(double x, double y, double w){
        point = new double[3];
        point[0] = x;
        point[1] = y;
        point[2] = w;
    }

    public double[] getPoint() {
        return point;
    }
    
    public void setX(double x) {
        point[0] = x;
    }
    
    public void setY(double y) {
       point[1] = y;
    }    
    
    public double getX() {
        return point[0];
    }
    
    public double getY() {
        return point[1];
    }    
    
    public void multiMatriz2D_Vector(Matriz2D matriz){
        double[] aux = new double[3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                aux[i] += matriz.getMatriz2D()[i][j]*point[j];
            }
        }
        point = aux;
    }
}
