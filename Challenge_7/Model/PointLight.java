package Model;

import Math.Point3;

/**
 *
 * @author Mario
 */

public class PointLight {
    Point3 point;
    MyColor color;

    /**
     * Constructor
     * @param point Place of the light in the scene
     * @param color Color of the light
     */
    public PointLight(Point3 point, MyColor color) {
        this.point = point;
        this.color = color;
    }
        
}