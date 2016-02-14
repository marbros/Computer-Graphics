package housepaint;

/**
 *
 * @author Anderson,Pablo,Mario
 */
public class Punto3D {
    
    private double point[];
//    double x;
//    double y;
//    double z;
    
    public Punto3D(double x, double y,double z, double w){
        point = new double[4];
        point[0] = x;
        point[1] = y;
        point[2] = z;
        point[3] = w;
        
//        this.x = x;
//        this.y = y;
//        this.z = z;
    }

    public double getX() {
        return  point[0];
    }

    public double getY() {
        return  point[1];
    }

    public double getZ() {
        return  point[2];
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
    
     public void setZ(double z) {
        point[2] = z;
    }
     
    public void multiMatrizPunto(Matriz3D transformacion){
        double[] aux = new double[4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                 aux[i] += transformacion.getMatriz3D()[i][j]*point[j];
            }
        }
        point = aux;
    }
}
