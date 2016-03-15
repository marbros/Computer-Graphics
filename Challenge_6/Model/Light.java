package Model;
import Math.Point3;

/**
 * Contains information about a point light
 */
public class Light {
    /** Position of this light source */
    protected Point3 position;
    /** MyColor of this light source */
    protected MyColor color;

    /** 
     * Constructor for the point light
     * @param position Position (x, y, z) of the light source
     * @param color MyColor (r, g, b) of the light source
     */
    public Light(Point3 position, MyColor color) {
        this.position = position;
        this.color = color;
    }
    
}
