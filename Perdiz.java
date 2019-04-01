import java.util.*;
/**
 * Write a description of class Perdiz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Perdiz extends Animal
{
    // instance variables - replace the example below with your own
    private static final int EDAD_REPRODUCCION = 1;                 // La edad a la que puede empezar a procrear
    private static final int EDAD_MAXIMA = 200;                      // La edad máxima que puede vivir
    private static final double PROBABILIDAD_REPRODUCCION = 0.75;   // La probabilidad con la que un conjeo se reproduce
    private static final int MAX_CAMADA = 5;  

    /**
     * Constructor for objects of class Perdiz
     */
    public Perdiz(boolean edadAzar, Field campo, Location posicion)
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
	
	@Override
    public void actuar(List<Animal> nuevasPerdices)
    {
        incrementarEdad();
        if (estaVivo()) {
            producirNacimientos (nuevasPerdices);            
            // Intenta moverlo a una posición libre
            Location nuevaPosicion = getCampo().posicionAdyacenteLibre(getPosicion());
            if(nuevaPosicion != null) {
                setPosicion(nuevaPosicion);
            }
            else {
                setMuerto(); // Muere por superpoblación
            }
        }
    }
}
