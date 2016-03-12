package Math;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



public class Vector {
    double x;
    double y;
    double z;

    public MiVector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public MiVector(Punto p1, Punto p2) {
        this.x = p2.getX() - p1.getX();
        this.y = p2.getY() - p1.getY();
        this.z = p2.getZ() - p1.getZ();
    }

    public MiVector(Punto p){
        this.x = p.getX();
        this.y = p.getY();
        this.z = p.getZ();
    }

    /**
     * Computes the dot product of two vectors
     * @param v1 Vector 1
     * @param v2 Vector 2
     * @return dot product of v1 . v2
     */
    public static double dotProduct(Vector3 v1, Vector3 v2) {
        double acum = v1.vector[0] * v2.vector[0];
        acum += v1.vector[1] * v2.vector[1];
        acum += v1.vector[2] * v2.vector[2];
        return acum;
    }

    /**
     * Compute the cross product of two vectors
     * @param v1 vector 1
     * @param v2 vector 2
     * @return Cross product of v1 and v2
     */
    public static Vector3 crossProduct(Vector3 v1, Vector3 v2) {
        double x = v1.vector[1] * v2.vector[2] - v1.vector[2] * v2.vector[1];
        double y = -(v1.vector[0] * v2.vector[2] - v1.vector[2] * v2.vector[0]);
        double z = v1.vector[0] * v2.vector[1] - v1.vector[1] * v2.vector[0];
        return new Vector3(x, y, z);
    }

    /**
     * Normalize this vector. Length is one after this operation. 
     */
    public void normalize() {
        double length = Math.sqrt(vector[0]*vector[0] + 
                vector[1]*vector[1] + 
                vector[2]*vector[2]);
        vector[0] = vector[0] / length;
        vector[1] = vector[1] / length;
        vector[2] = vector[2] / length;
    }

    public double magnitud(){
        double r;
        r = Math.sqrt(x*x + y*y + z*z);
        return r;
    }

    /**
     * Adds two vectors and returns the result
     * @param v1 Vector one to be added
     * @param v2 Vector two to be added
     * @return new vector with the result of v1 + v2
     */
    public static Vector3 add(Vector3 v1, Vector3 v2) {
        double newX = v1.vector[0] + v2.vector[0];
        double newY = v1.vector[1] + v2.vector[1];
        double newZ = v1.vector[2] + v2.vector[2];
        return new Vector3(newX, newY, newZ);
    }
    

    /**
     * Returns a new vector equals v2 - v1
     * @param v2 Vector to subtract from
     * @param v1 Vector to subtract 
     * @return new Vector3 with the result of v2 - v1
     */
    public static Vector3 subtract(Vector3 v2, Vector3 v1) {
        double newX = v2.vector[0] - v1.vector[0];
        double newY = v2.vector[1] - v1.vector[1];
        double newZ = v2.vector[2] - v1.vector[2];
        return new Vector3(newX, newY, newZ);
    }

    /**
     * Convert to string
     * @return printed vector 
     */
    @Override
    public String toString() {
        String s = new String();
        s += vector[0] + " " + vector[1] + " " + vector[2];
        return s;
    }

}
