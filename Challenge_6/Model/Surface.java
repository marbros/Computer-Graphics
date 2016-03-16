package Model;
import Math.Intersectable;

/**
 * Class to hold the material and the geometry (intersectable) of this triangular
 surface.
 * @author htrefftz
 */
public class Surface implements SolidSurface  {
    /** Material of this surface */
    protected Material material;
    /** Geometry (intersectable) of this surface */
    protected Intersectable intersectable;

    /**
     * Constructor
     * @param material Material this surface is made of
     * @param intersectable Geometry of this triangular surface
     */
    public Surface(Material material, Intersectable intersectable) {
        this.material = material;
        this.intersectable = intersectable;
    }
    
    /**
     * Returns the material properties of this triangular surface
     * @return the material of this surface
     */
    @Override
    public Material getMaterial() {
        return material;
    }
    
    @Override
    public Intersectable getIntersectable() {
        return intersectable;
    }
}
