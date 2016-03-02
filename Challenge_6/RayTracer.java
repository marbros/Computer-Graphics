package escena;

import imagen.Imagen;

public class RayTracer {

    Escena escena;
    LectorEscena lectorEscena;
    int ancho, alto;
    boolean DEBUG = true;

    public RayTracer(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        escena = new Escena();
        lectorEscena = new LectorEscena(this, "escena.txt");

    }

    public void GenerarImagen(){
        Rayo rayo;
        double deltax = 2d / ancho;
        double deltay = 2d / alto;
        Imagen imagen = new Imagen(ancho, alto);
    }

    public static void main(String [] args) {
        RayTracer rayTracer = new RayTracer(400, 400);
        rayTracer.GenerarImagen();
    }

}
