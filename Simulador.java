import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 * Describe una simulaci�n simple del tipo predador - presa. Se basa en un campo
 * rectangular que contiene zorros y conejos.
 * 
 * @author Apah (Es una traducci�n del proyecto del CD)
 * @version 13.02.2012
 */
public class Simulador {
    // ----- Constantes con la informaci�n necesaria para configurar el Simulador

    private static final int ANCHURA_DEFECTO = 50; // Anchura por defecto del campo 50
    private static final int PROFUNDIDAD_DEFECTO = 50; // Profundidad por defecto del campo 50
    private static final double PROBABILIDAD_CREACION_ZORRO = 0.02; // Indica la probabilidad con la que un zorro se                                                                    // crea en una determinda posici�n 0.02
    private static final double PROBABILIDAD_CREACION_CONEJO = 0.08; // Indica la probabilidad con la que un conejo se
    private static final double PROBABILIDAD_CREACION_PERDIZ = 0.04;
    // crea en una determinda posici�n 0.08
    private static final int NUM_PASOS_LARGOS = 500; // N�mero de pasos que tengo ...
    private static final Color COLOR_CONEJO = Color.ORANGE;
    private static final Color COLOR_ZORRO = Color.BLUE;
    private static final Color COLOR_PERDIZ = Color.GRAY;

    // ------ Campos de instancia

    private List<Animal> listaAnimales; // Lista de animales del campo
    private Field campo; // Describe la situaci�n actual del campo
    private int paso; // Es el paso actual de la simulaci�n
    private SimulatorView vista; // Es la vista gr�fica de la simulaci�n

    /**
     * Constructor por defecto. Crea el campo con el tama�o por defecto
     */
    public Simulador() {
        this(PROFUNDIDAD_DEFECTO, ANCHURA_DEFECTO); // LLama al constructor con par�metros
    }

    /**
     * Crea un campo de simulaci�n con un tama�o dado.
     * 
     * @param profundidad
     *            Profundidad del campo. Debe ser mayor que 0
     * @param anchura
     *            Anchura del campo. Debe ser mayor que 0
     */
    public Simulador(int profundidad, int anchura) {
        if (anchura <= 0 || profundidad <= 0) {
            System.out.println("Las dimensiones deben ser mayores que 0");
            System.out.println("Se van a usar los valores por defecto");
            profundidad = PROFUNDIDAD_DEFECTO;
            anchura = ANCHURA_DEFECTO;
        }

        listaAnimales = new ArrayList<Animal>();
        campo = new Field(profundidad, anchura);

        // Crea una vista del estado de cada ubicaci�n en el campo
        vista = new SimulatorView(profundidad, anchura);
        vista.setColor(Conejo.class, COLOR_CONEJO);
        vista.setColor(Zorro.class, COLOR_ZORRO);

        // Inicia desde un punto de partida v�ido
        iniciarSimulacion();
    }

    /**
     * Corre la simulaci�n desde su estado actual un periodo razonablemente largo
     * (500 pasos)
     */
    public void correrSimulacionLarga() {
        simular(NUM_PASOS_LARGOS);
    }

    /**
     * Corre la simulaci�n desde su estado actual un n�mero determinado de pasos
     * Para antes si se alcanza una situaci�n no viable
     * 
     * @param numPasos
     *            El n�mero de pasos a correr
     */
    public void simular(int numPasos) {
        for (int paso = 1; paso <= numPasos && vista.isViable(campo); paso++) {
            simularUnPaso();
        }
    }

    /**
     * Corre un solo paso la simulaci�n desde el estado actual Recorre todo el campo
     * actualizando el estado de los zorros y los conejos
     */
    public void simularUnPaso() {
        paso++;

        // ------ Proporciona espacio para los nuevos nacimientos de conejos
        List<Animal> nuevosAnimales = new ArrayList<Animal>();

        for(Iterator<Animal> it = listaAnimales.iterator(); it.hasNext();) {
            Animal animal = it.next();
            animal.actuar(nuevosAnimales);
            if(!animal.estaVivo()) {
                it.remove();
            }
        }
        // A�ade los nuevos nacimientos de zorros y conejos a las listas principales
        listaAnimales.addAll(nuevosAnimales);

        vista.showStatus(paso, campo);
    }

    /**
     * Inicializa la simulaci�n desde el principio
     */
    public void iniciarSimulacion() {
        paso = 0;
        listaAnimales.clear();
        listaAnimales.clear();
        poblarCampo();

        // Muestra el estado inicial del cmapo
        vista.showStatus(paso, campo);
    }

    /**
     * Puebla aleatoriamente el campo con zorros y conejos
     */
    private void poblarCampo() {
        Random aleatorio = Randomizer.getRandom();
        campo.clear();
        for (int fila = 0; fila < campo.getDepth(); fila++) {
            for (int columna = 0; columna < campo.getWidth(); columna++) {
                if (aleatorio.nextDouble() <= PROBABILIDAD_CREACION_ZORRO) {
                    Location ubicacion = new Location(fila, columna);
                    Zorro zorro = new Zorro(true, campo, ubicacion);
                    listaAnimales.add(zorro);
                } 
                else if (aleatorio.nextDouble() <= PROBABILIDAD_CREACION_CONEJO) {
                    Location ubicacion = new Location(fila, columna);
                    Conejo conejo = new Conejo(true, campo, ubicacion);
                    listaAnimales.add(conejo);
                }
                else if (aleatorio.nextDouble() <= PROBABILIDAD_CREACION_PERDIZ) {
                Location ubicacion = new Location(fila, columna);
                Perdiz perdiz = new Perdiz(true, campo, ubicacion);
                listaAnimales.add(perdiz);
            }
            // si no, no nace ni zorro ni conejo y se deja la ubicaci�n vac�a
        }
    }
}
}
