package escena;


public class Esfera extends Entidad{
    Punto centro;
    double radio;

    public Esfera(Punto centro, double radio) {
        this.centro = centro;
        this.radio = radio;
    }

    /**
     * Finds the intersection(s) of this sphere with the ray provided as
     * parameter
     * @param ray Ray to check intersection with this sphere.
     * @return An Array with the value(s) of the parameter in the ray that
     *   determines the intersection(s) of the ray and the sphere.
     *   If there are no intersections, array ret has 0 positions.
     *   If there is only one intersection, array ret has 1 position.
     *   If there are two intersections, array ret has 2 positions.
     */
    @Override
    public double[] findIntersections(Ray ray) {
        double [] ret = new double[2];

        return ret;
    }

    @Override
    public String toString() {
        return "Esfera{" + "centro=" + centro + "radio=" + radio + '}';
    }


}

