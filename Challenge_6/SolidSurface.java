package Model;
import Math.Intersectable;

/**
 * All objects implementing this interface have to return a material.
 */
public interface SolidSurface {
    public Material getMaterial();
    public Intersectable getIntersectable();
}
