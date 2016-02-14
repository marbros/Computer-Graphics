package housepaint;

/**
 *
 * @author Anderson,Pablo,Mario
 */
public class Operations {
    
    //Multiplica 1 matriz 2D por un vector
    public static Punto2D multiplicar(Matriz2D matriz2D, Punto2D punto2D){
        double acum = 0;
        double[] array = new double[3];
        for (int i = 0; i < 3; i++) {
            for( int j = 0; j< punto2D.getPoint().length; j++){
                acum += matriz2D.getMatriz2D()[i][j] * punto2D.getPoint()[j];
            }
            array[i] = acum;
            acum = 0;
        }
        Punto2D res = new Punto2D(array[0], array[1], array[2]);
        return res;
    }

    //Multiplica 1 matriz 3D por un vector
    public static Punto3D multiplicar(Matriz3D matriz3D, Punto3D punto3D){
        double acum = 0;
        double[] array = new double[4];
        for(int i = 0; i < 4; i++){
            for (int j = 0; j < punto3D.getPoint().length; j++) {
                acum += matriz3D.getMatriz3D()[i][j] * punto3D.getPoint()[j];
            }
            array[i] = acum;
            acum = 0;
        }
        
        Punto3D res = new Punto3D(array[0], array[1], array[2], array[3]);
        return res;
    }

    //Multiplica 2 matrices 2D
    public double[][] multi_Matriz2D(Matriz2D matriz1, Matriz2D matriz2){
        double[][] res = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = 0;
                for (int k = 0; k < 10; k++) {
                    res[i][j] += matriz1.getMatriz2D()[i][k] * matriz2.getMatriz2D()[k][j];
                }
            }
        }
        return res;
    }

    //Multiplica 2 matrices 3D
    public double[][] multi_Matriz3D(Matriz3D matriz1, Matriz3D matriz2){       
     
        double[][] res = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[i][j] = 0;
                for (int k = 0; k < 10; k++) {
                    res[i][j] += matriz1.getMatriz3D()[i][k] * matriz2.getMatriz3D()[k][j];
                }
            }
        }
        return res;
    }

    //Suma 2 matrices 2D
    public double[][] sumaMatriz2D(Matriz2D matriz1, Matriz2D matriz2){     
        double[][] res = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                res[i][j] = matriz1.getMatriz2D()[i][j] + matriz2.getMatriz2D()[i][j];
            }
        }
        return res;
    }

    //Suma 2 matrices 3D
    public double[][] sumaMatrix3D(Matriz3D matriz1, Matriz3D matriz2){     
        double[][] res = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res[i][j] = matriz1.getMatriz3D()[i][j] + matriz2.getMatriz3D()[i][j];
            }
        }
        return res;
    }
}