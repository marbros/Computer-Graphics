package Math;

/**
 * All objects that are intersectable with a ray have to implement function
 * findIntersections, which return an array with intersections of a ray with
 * this object.
 */
public interface Intersectable {
    /**
     * 
     * @param ray Ray to intersect this object with.
     * @return array with values of t for the ray intersection(s) with 
     * this object.
     */
    public double [] findIntersections(Ray ray);
}
