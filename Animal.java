import java.util.*;
/**
 * Clase Animal, la cual esta compuesta por:
 * 
 * Constantes:
 *  .
 *  .
 *  .
 * Campos:
 *  .
 *  .
 *  .
 * 
 * Constructor/es:
 *  .
 *  .
 * 
 * Metodos:
 * --Consulta--
 *  .
 *  .
 *  .
 *  .
 *  .
 *  .
 * --Modificacion--
 *  .
 *  .
 *  .
 * --Privados--
 *  .
 *  .
 * @author (Alex) 
 * @version (fecha)
 */
public abstract class Animal {

    //----------------------------Constantes--------------------------------
    protected static final Random generador = Randomizer.getRandom(); // N�mero aleatorio para gestionar la reproducci�n

    //----------------------------Campos------------------------------------
    private boolean vive;       // Indica si el conejo est� vivo o no
    private Location posicion;  // La posici�n del conejo dentro del campo
    private Field campo;        // El campo donde se desarrolla la simulaci�n
    private int edad;			// La edad del animal

    //----------------------------Constructores-----------------------------
    public Animal(boolean edadAzar, Field campo, Location posicion) 
    {
        this.vive = true;
        this.campo = campo;
        this.posicion = posicion;
        setPosicion(posicion);
        setEdad(0);
        if(edadAzar) {
            setEdad(generador.nextInt(getEdadMaxima()));
        }
    }


    //----------------------------Metodos-----------------------------------
    //                            *Consulta*

    /**
     * Comprueba si el animal est� vivo o muerto
     * @return true si est� vivo
     */
    public boolean estaVivo()
    {
        return vive;
    }

    /**
     * Devuelve la posici�n del conejo
     * @return La posici�n del conejo
     */
    public Location getPosicion()
    {
        return posicion;
    }

    /**
     * Devuelve el campo
     * @return -campo -El campo donde esta el animal
     */
    public Field getCampo() 
    {
        return this.campo;
    }

    /**
     * Devuelve la edad del animal
     * @return
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Un Animal puede reproducrise si ha alcanzado una determinada edad
     * @return true si el conejo puede reproducirse, falso en otro caso
     */
    protected boolean puedeReproducirse() {
        return edad >= getEdadReproduccion();
    }

	
    //                            *Modificacion*

    /**
     * 
     * @param edad
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }

    /**
     * Genera un n�mero al azar que representa el n�mero de nacimientos
     * Eso si el conejo se puede reproducir
     * @return El n�mero de nacimientos (puede ser 0).
     */
    protected int reproducir()
    {
        int nacimientos = 0;
        if(puedeReproducirse() && generador.nextDouble() <= getProbabilidadReproduccion()) {
            nacimientos = generador.nextInt(getMaxCamada()) + 1;
        }
        return nacimientos;
    }

    /**
     * El animal ha llegado a su m�ximo de edad y muere
     * Se elimina del campo
     */
    public void setMuerto()
    {
        vive = false;
        if(posicion != null) {
            campo.clear(posicion);
            posicion = null;        // Y as� el objeto puede ser liberado
            campo = null;           // por el recolector de basura
        }
    }

    /**
     * Coloca al animal en una nueva posici�n del campo actual
     * @param nuevaPosicion La nueva posici�n del animal
     */
    protected void setPosicion(Location nuevaPosicion)
    {
        if(posicion != null) {
            campo.clear(posicion);
        }
        posicion = nuevaPosicion;
        campo.place(this, nuevaPosicion);
    }

    /**
     * Incrementa la edad del animal Esto puede ocasionar que muera
     */
    protected void incrementarEdad() {
        setEdad(getEdad()+1);
        if(getEdad() > getEdadMaxima()) {
            setMuerto();
        }
    }

    /**
     * 
     * @param nuevosAnimales
     */
    protected void producirNacimientos(List<Animal> nuevosAnimales)
    {
        // Los nuevos conejos se colocan en posiciones adyacentes
        // Para ello, primero obtiene una lista de posiciones adaycentes libres
        List<Location> libres = getCampo().getPosicionesLibresAdyacentes(getPosicion());
        int nacimientos = reproducir();
        for(int n = 0; n < nacimientos && libres.size() > 0; n++) {
            Location pos = libres.remove(0);
            Animal animal = crearAnimal(false, getCampo(), pos);
            nuevosAnimales.add(animal);
        }
    }

    //								*Abstractos*

    /**
     * Metodo abstracto. Hace que los animales hagan lo que le es intrinseco a la naturaleza programada
     * @param listaAnimales -La lista de animales de 
     */
    public abstract void actuar(List<Animal> listaAnimales);

    /**
     * 
     * @return
     */
    public abstract int getEdadReproduccion();

    /**
     * 
     * @return
     */
    public abstract int getEdadMaxima();

    /**
     * 
     * @return
     */
    public abstract double getProbabilidadReproduccion();

    /**
     * 
     * @return
     */
    public abstract int getMaxCamada();

    /**
     * 
     * @param edadAzar
     * @param campo
     * @param posicion
     * @return
     */
    public abstract Animal crearAnimal(boolean edadAzar, Field campo, Location posicion);
    //                            *Privados*

}

