package housepaint;

/**
 *
 * @author Anderson,Pablo,Mario
 */
public class Matriz3D {    
    
    private double matriz3D[][];   
    private double identidad[][]={{1,0,0,0},
                                  {0,1,0,0},
                                  {0,0,1,0},
                                  {0,0,0,1}};
    public Matriz3D() {
        this.matriz3D = this.identidad;
    }

    public double[][] getMatriz3D() {
        return matriz3D;
    }

    public void setMatriz3D(double[][] matriz) {
        this.matriz3D = matriz;
    }
    
    public void multiplicarConstante(int c){
        for (int i = 0; i < matriz3D.length; i++) {
            for (int j = 0; j < matriz3D[i].length; j++) {
                matriz3D[i][j] *= c;
            }
        }
    }
    
    public void scaling3D (double x, double y,double z){        
        this.matriz3D = this.identidad;
        matriz3D[0][0] = x;
        matriz3D[1][1] = y;
        matriz3D[2][2] = z;
    }
    
    public void translation3D (double x, double y,double z){        
        this.matriz3D = this.identidad;
        matriz3D[0][3] = x;
        matriz3D[1][3] = y;
        matriz3D[2][3] = z;
    }
    
    public void rotation3D(int eje ,double grados){        
        this.matriz3D = this.identidad;
        if(eje == 1){ //x
            this.matriz3D[1][1] = Math.cos(grados);
            this.matriz3D[1][2] = -1 * Math.sin(grados);
            this.matriz3D[2][1] = Math.sin(grados);
            this.matriz3D[2][2] = Math.cos(grados);
        }else if(eje == 2){ //y           
            this.matriz3D[0][0] = Math.cos(grados);
            this.matriz3D[0][2] = -1 * Math.sin(grados);
            this.matriz3D[2][0] = Math.sin(grados);
            this.matriz3D[2][2] = Math.cos(grados);
        }else if(eje == 3){ //z
            this.matriz3D[0][0] = Math.cos(grados);
            this.matriz3D[0][1] = -1 * Math.sin(grados);
            this.matriz3D[1][0] = Math.sin(grados);
            this.matriz3D[1][1] = Math.cos(grados);
        }
    }
}