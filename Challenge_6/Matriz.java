/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Bezier;

/**
 *
 * @author MarioG,Anderson,Pablo
 */
public class Matriz {
    
    public static float[][] multiply(float a[][], float b[][]) {
        int filaA = a.length,
            columnaA = a[0].length,
            filaB = b.length,
            columnaB = b[0].length;

        if ( columnaA != filaB ) {
            throw new IllegalArgumentException("A:Rows: " + columnaA + " did not match B:Columns " + filaB + ".");
        }

        float[][] res = new float[filaA][columnaB];

        for(int i = 0; i < filaA; i++) { // aRow
            for(int j = 0; j < columnaB; j++) { // bColumn
                for(int k = 0; k < columnaA; k++) { // aColumn
                    res[i][j] += a[i][k] * b[k][j];
                }
            }  
        }
        return res;
    }
    
    public static float[][] transpose(float a[][]) {
        int aRows = a.length,
            aColumns = a[0].length;

        float[][] resultant = new float[aColumns][aRows];
        
        for(int i=0; i<aRows; i++)
            for (int j=0; j<aColumns; j++)
                resultant[j][i] = a[i][j];
        return resultant;
    }
    
    public static float[][] normalize(float a[][]){
        int aRows = a.length,
            aColumns = a[0].length;
        float[][] resultant = new float[aRows-1][aColumns];
        for (int i=0; i < aRows-1; i++){
            for (int j=0; j < aColumns; j++)
                resultant[i][j] = a[i][j]/a[aRows-1][j];
        }
        return resultant;
    }
}


