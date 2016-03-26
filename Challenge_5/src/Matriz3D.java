/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package triangles;

import triangles.Vector3D;

/**
 *
 * @author Mario
 */
public class Matriz3D {

    float m[][]; //4*4

    public float[][] getM() {
        return m;
    }

    public void setM(float[][] m) {
        this.m = m;
    }

    public Matriz3D(float m11, float m12, float m13, float m14,
            float m21, float m22, float m23, float m24,
            float m31, float m32, float m33, float m34,
            float m41, float m42, float m43, float m44) {
        m = new float[4][4];
        m[0][0] = m11;
        m[0][1] = m12;
        m[0][2] = m13;
        m[0][3] = m14;
        m[1][0] = m21;
        m[1][1] = m22;
        m[1][2] = m23;
        m[1][3] = m24;
        m[2][0] = m31;
        m[2][1] = m32;
        m[2][2] = m33;
        m[2][3] = m34;
        m[3][0] = m41;
        m[3][1] = m42;
        m[3][2] = m43;
        m[3][3] = m44;
    }
    public Matriz3D(float m[][]){
        this.m = m;
    }

    public static Matriz3D producto(Matriz3D m1, Matriz3D m2) {
        float mAux[][] = new float[4][4];

        Vector3D vector1;
        Vector3D vector2;
        for (int i = 0; i < m1.m.length; i++) {
            for (int j = 0; j < m2.m.length; j++) {
                vector1 = new Vector3D(m1.m[i][0], m1.m[i][1], m1.m[i][2], m1.m[i][3]);
                vector2 = new Vector3D(m2.m[0][j], m2.m[1][j], m2.m[2][j], m2.m[3][j]);
                mAux[i][j] = Vector3D.productoPunto(vector1, vector2);
            }
        }

        Matriz3D matriz = new Matriz3D(mAux);

        return matriz;
    }
}
