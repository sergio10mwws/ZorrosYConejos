import java.util.List;
import java.util.Random;

/**
 * Un modelo simple de conejo
 * 
 * @author Apah (tradución de la versión del libro)
 * @version 12.02.2012
 */
public class Conejo extends Animal
{
    // ------ Características compartidas por todos los conejos (campos estáticos).

    private static final int EDAD_REPRODUCCION = 8;                 // La edad a la que puede empezar a procrear
    private static final int EDAD_MAXIMA = 7;                      // La edad máxima que puede vivir
    private static final double PROBABILIDAD_REPRODUCCION = 0.15;   // La probabilidad con la que un conjeo se reproduce
    private static final int MAX_CAMADA = 4;                        // El máximo número de la camada

    // ----- Características individuales (campos de instancia)

   

    /**
     * Crea un nuevo conejo. Un conejo se puede crear
     * con 0 años (nacimiento) o con una edad al azar.
     * 
     * @param edadAzar Si es true, el conejo tendrá una edad al azar
     * @param campo El campo actual de la simulación
     * @param posicion La posición dentro del campo
     */
    public Conejo (boolean edadAzar, Field campo, Location posicion)
    {
        super(edadAzar, campo, posicion);

    }

    @Override
    public int getEdadReproduccion() {
        return EDAD_REPRODUCCION;
    }

    @Override
    public int getEdadMaxima() {
        return EDAD_MAXIMA;
    }

    @Override
    public double getProbabilidadReproduccion() {
        return PROBABILIDAD_REPRODUCCION;
    }

    @Override
    public int getMaxCamada() {
        return MAX_CAMADA;
    }

    @Override
    public Animal crearAnimal(boolean edadAzar, Field campo, Location posicion) {
        return new Conejo(edadAzar, campo, posicion);
    }

    /**
     * Esto es lo que el conejo está siempre haciendo: corre alrededor. 
     * Algunas veces se reproduce y algunas veces muere de viejo
     * 
     * @param nuevosConejos Una lista a la que se le van añadiendo los nacimientos
     */
    @Override
    public void actuar(List<Animal> nuevosConejos)
    {
        incrementarEdad();
        if (estaVivo()) {
            producirNacimientos (nuevosConejos);            
            // Intenta moverlo a una posición libre
            Location nuevaPosicion = getCampo().posicionAdyacenteLibre(getPosicion());
            if(nuevaPosicion != null) {
                setPosicion(nuevaPosicion);
            }
            else {
                setMuerto (); // Muere por superpoblación
            }
        }
    }
}