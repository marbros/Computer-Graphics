/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package housepaint;

/**
 *
 * @author Anderson,Pablo,Mario
 */
public class Matriz2D {

    private double matriz2D[][];
    private double identidad[][]={{1,0,0},
                                 {0,1,0},
                                 {0,0,1}}; 
    public Matriz2D(){
        this.matriz2D = this.identidad;
    }
    public Matriz2D(double[][] matriz){
        this.matriz2D = this.identidad;
        this.matriz2D = matriz;      
    }
    
    public void scaling (double x, double y){ 
        this.matriz2D = this.identidad;
        matriz2D[0][0] = x;
        matriz2D[1][1] = y;
    }
    
    public void translation (int x, int y){ 
        this.matriz2D = this.identidad;
        matriz2D[0][2] = x;
        matriz2D[1][2] = y;
    }
    
    public void rotation (double grados){
        this.matriz2D = this.identidad; 
        this.matriz2D[0][0] = Math.cos(grados);
        this.matriz2D[0][1] = -1 * Math.sin(grados);
        this.matriz2D[1][0] = Math.sin(grados);
        this.matriz2D[1][1] = Math.cos(grados);
    }
        
    public double[][] getMatriz2D() {
        return matriz2D;
    }
 
    public void setMatriz(double[][] matriz) {
        this.matriz2D = matriz;
    }    
    
}
