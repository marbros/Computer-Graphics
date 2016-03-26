package triangles;

public class Vector3D {

    float x, y, z, w;

    public Vector3D(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public static float magnitud(Vector3D vector) {
        float magnitud = (float)Math.sqrt(vector.x * vector.x + vector.y * vector.y + vector.z * vector.z);
        return magnitud;
    }

    public Vector3D  normalizar(Vector3D vector) { //revisar
        float magnitud = magnitud(vector);
        float x1 = vector.x / magnitud;
        float y1 = vector.y / magnitud;
        float z1 = vector.z / magnitud;
        Vector3D vector1 = new Vector3D(x1,y1,z1,1); 
        return vector1;
    }

    public static float productoPunto(Vector3D v1, Vector3D v2) {
        float prodPunto = (v1.x * v2.x) + (v1.y * v2.y) + (v1.z * v2.z) + (v1.w * v2.w);
        return prodPunto;
    }

    public static Vector3D  productoCruz(Vector3D v1, Vector3D v2) {
        Vector3D vector;
               
        float x1 = (v1.y * v2.z) - (v2.y * v1.z);
        float y1 = (-1)*((v1.x*v2.z)-(v2.x*v1.z));
        float z1 = (v1.x*v2.y)-(v2.x*v1.y);

        vector = new Vector3D(x1, y1, z1, 1);
        return vector;
    }

    public static Vector3D producto(Matriz3D m, Vector3D v2) {
        Vector3D vector;

        float x1 = (m.m[0][0] * v2.x) + (m.m[0][1] * v2.y) + (m.m[0][2] * v2.z) + (m.m[0][3] * v2.w);
        float y1 = (m.m[1][0] * v2.x) + (m.m[1][1] * v2.y) + (m.m[1][2] * v2.z) + (m.m[1][3] * v2.w);
        float z1 = (m.m[2][0] * v2.x) + (m.m[2][1] * v2.y) + (m.m[2][2] * v2.z) + (m.m[2][3] * v2.w);
        float w1 = (m.m[3][0] * v2.x) + (m.m[3][1] * v2.y) + (m.m[3][2] * v2.z) + (m.m[3][3] * v2.w);
        vector = new Vector3D(x1, y1, z1, w1);

        return vector;
    }

    public static Vector3D resta(Vector3D v1, Vector3D v2) {
        float x1 = v1.x - v2.x;
        float y1 = v1.y - v2.y;
        float z1 = v1.z - v2.z;
        float w1 = v1.w - v2.w;

        Vector3D vector = new Vector3D(x1, y1, z1, w1);

        return vector;
    }

    public static Vector3D invertir(Vector3D v) {
        float x1 = v.x * -1;
        float y1 = v.y * -1;
        float z1 = v.z * -1;
        float w1 = v.w * -1;

        Vector3D vector = new Vector3D(x1, y1, z1, w1);

        return vector;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }
}