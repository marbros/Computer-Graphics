package triangles;

import java.awt.Color;

public class Segment {
    
    public Vector3D vector1;
    public Vector3D vector2;
    public Vector3D vector3;
    public float p;
    public Color color;

    public Segment(Vector3D vector1, Vector3D vector2, Vector3D vector3, float p, Color color) {
        this.vector1 = vector1;
        this.vector2 = vector2;
        this.vector3 = vector3;
        this.color = color;
        this.p = p;       
    }
    
    public void hallarP(){     
        this.p = (float)Math.min(vector1.getZ(),vector2.getZ());//(vector1.getZ()+vector2.getZ()+vector3.getZ())/3; 
        this.p=(float)Math.min(this.p,vector3.getZ());
    }
    
}
