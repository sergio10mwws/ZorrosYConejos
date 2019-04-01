import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Un modelo simple de un zorro
 * 
 * @author Apah (Traducción de la misma clase del CD)
 * @version 13.02.2012
 */
public class Zorro extends Animal{
	// ----- Características comunes para todos los zorros (campos estáticos)

	private static final int EDAD_REPRODUCCION = 10; // Edad a la que puede empezar a reproducirse
	private static final int EDAD_MAXIMA = 12; // Edad hasta la que puede vivier un zorro
	private static final double PROBABILIDAD_REPRODUCCION = 0.35; // Probabilidad de reproducción de un zorro
	private static final int MAX_CAMADA = 5; // Máximo número de cachorros
	private static final int VALOR_COMIDA = 7; // Número de pasos que puede dar un zorro (hasta que vuelva a comer)
	private static final int MAX_COMIDOS = 2; //Numero maximo de conejos que puede comerse
	

	// ----- Características individuales (campos de instancia)

	private int nivelComida; // Es el nivel de comidad. Se incrementa comienzo conejos

	/**
	 * Crea un zorro Un zorro puede ser creado como un nuevo nacimiento (con edad 0
	 * y no hambriento) o con una edad y nivel de comida aleatorios.
	 * 
	 * @param edadAzar
	 *            Si es true, el zorro tendrá una edad y un nivel de comida
	 *            aleatorios
	 * @param campo
	 *            El campo actual donde se desarrolla la simulación
	 * @param posicion
	 *            La posición en el campo
	 */
	public Zorro(boolean edadAzar, Field campo, Location posicion) {
    	super(edadAzar, campo, posicion);
    	nivelComida = VALOR_COMIDA;
		if (edadAzar) {
			nivelComida = generador.nextInt(VALOR_COMIDA);
		}
			
		
	}

	/**
	 * Esto es lo que un zorro hace la mayoría del tiempo: cazar conejos. Además
	 * puede reproducirse, morir de hambre o morir de viejo.
	 * 
	 * @param campo
	 *            El campo donde se desarrolla la simulación
	 * @param nuevosZorros
	 *            Una lista a la que se van añadiendo los nacimientos
	 */
	public void actuar(List<Animal> nuevosZorros) {
		incrementarEdad();
		incrementarHambre();
		if (estaVivo()) {
			producirNacimientos(nuevosZorros);
			// Se mueve buscando comida
			ArrayList<Location> listaPosicionesComidas = buscarComidaV2(getPosicion());
			Location nuevaPosicion = null;
			if (listaPosicionesComidas == null) { // Si la list esta vacia es que no ha comido. Se intenta mover a una
													// posición libre
				nuevaPosicion = getCampo().posicionAdyacenteLibre(this.getPosicion()); // Busca una posicion para moverse
				if (nuevaPosicion != null) { // Si la encuentra, se mueve
					setPosicion(nuevaPosicion);
				} else { // Si no se muere por sobre poblacion
					setMuerto();
				}
			}
			
			if (listaPosicionesComidas != null) {
				int posicionSorteada = generador.nextInt(listaPosicionesComidas.size()); // Genera aletoriamente un
																							// numero segun el numero de
																							// conejos comidos
				nuevaPosicion = listaPosicionesComidas.get(posicionSorteada);// Cogemos la posicion y la ponemos como
																				// nueva
				setPosicion(nuevaPosicion); // Siempre se movera pues hay espacio al comerse a un conejo de los de
											// alrededor

			}
		}
	}

	/**
	 * Hace más hambriento al zorro Esto puede ocasionar que muera
	 */
	private void incrementarHambre() {
		nivelComida--;
		if (nivelComida <= 0) {
			setMuerto();
		}
	}

	/**
	 * Busca algún conejo en los alrededores Solo el primer conejo vivo es comido
	 * 
	 * @param posicion
	 *            Situación en el campo donde está el zorro
	 * @return La posición donde hay comida, o null si no la encuentra
	 */
	private Location buscarComida(Location posicion) {
		List<Location> adyacentes = getCampo().adjacentLocations(posicion);
		Iterator<Location> it = adyacentes.iterator();
		while (it.hasNext()) {
			Location donde = it.next();
			Object animal = getCampo().getObjectAt(donde);
			if (animal instanceof Conejo) {
				Conejo conejo = (Conejo) animal;
				if (conejo.estaVivo()) {
					conejo.setMuerto();
					nivelComida = VALOR_COMIDA;
					return donde;
				}
			}
			
			if (animal instanceof Perdiz) {
				Perdiz perdiz = (Perdiz) animal;
				if (perdiz.estaVivo()) {
					perdiz.setMuerto();
					nivelComida = VALOR_COMIDA;
					return donde;
				}
			}
		}
		return null;
	}

	/**
	 * Busca algún conejo en los alrededores Se come todo conejo que este en sus
	 * alrededores
	 * 
	 * @param posicion
	 *            Situación en el campo donde está el zorro
	 * @return La posición donde hay comida, o null si no la encuentra
	 */
	private ArrayList<Location> buscarComidaV2(Location posicion) {
		List<Location> adyacentes = getCampo().adjacentLocations(posicion);
		ArrayList<Location> listaConejosZampados = new ArrayList<Location>();
		int numConejosZampados = 0;
		Iterator<Location> it = adyacentes.iterator();
		while (it.hasNext()) {
			Location donde = it.next();
			Object animal = getCampo().getObjectAt(donde);
			if (animal instanceof Conejo) {
				Conejo conejo = (Conejo) animal;
				if (conejo.estaVivo()) {
					conejo.setMuerto();
					if(numConejosZampados <= MAX_COMIDOS) {
						nivelComida += VALOR_COMIDA;
						numConejosZampados++;
					}
					listaConejosZampados.add(donde);
				}
			}
		}
		if (listaConejosZampados.isEmpty()) {
			return null;
		}
		return listaConejosZampados;
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
		return new Zorro(edadAzar, campo, posicion);
	}
}
