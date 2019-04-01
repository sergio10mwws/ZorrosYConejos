import java.util.List;
import java.util.Random;

/**
 * Un modelo simple de conejo
 * 
 * @author Apah (traduci�n de la versi�n del libro)
 * @version 12.02.2012
 */
public class Conejo extends Animal
{
    // ------ Caracter�sticas compartidas por todos los conejos (campos est�ticos).

    private static final int EDAD_REPRODUCCION = 8;                 // La edad a la que puede empezar a procrear
    private static final int EDAD_MAXIMA = 7;                      // La edad m�xima que puede vivir
    private static final double PROBABILIDAD_REPRODUCCION = 0.15;   // La probabilidad con la que un conjeo se reproduce
    private static final int MAX_CAMADA = 4;                        // El m�ximo n�mero de la camada

    // ----- Caracter�sticas individuales (campos de instancia)

   

    /**
     * Crea un nuevo conejo. Un conejo se puede crear
     * con 0 a�os (nacimiento) o con una edad al azar.
     * 
     * @param edadAzar Si es true, el conejo tendr� una edad al azar
     * @param campo El campo actual de la simulaci�n
     * @param posicion La posici�n dentro del campo
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
     * Esto es lo que el conejo est� siempre haciendo: corre alrededor. 
     * Algunas veces se reproduce y algunas veces muere de viejo
     * 
     * @param nuevosConejos Una lista a la que se le van a�adiendo los nacimientos
     */
    @Override
    public void actuar(List<Animal> nuevosConejos)
    {
        incrementarEdad();
        if (estaVivo()) {
            producirNacimientos (nuevosConejos);            
            // Intenta moverlo a una posici�n libre
            Location nuevaPosicion = getCampo().posicionAdyacenteLibre(getPosicion());
            if(nuevaPosicion != null) {
                setPosicion(nuevaPosicion);
            }
            else {
                setMuerto (); // Muere por superpoblaci�n
            }
        }
    }
}