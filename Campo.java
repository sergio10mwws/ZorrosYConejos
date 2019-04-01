import java.util.*;
/**
 * Clase Campo, la cual esta compuesta por:
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
public class Campo {
	//----------------------------Constantes--------------------------------
    
    //----------------------------Campos------------------------------------
	private int tamaño;

    //----------------------------Constructores-----------------------------
	public Campo(int tamaño) 
	{
		this.tamaño = tamaño;
	}

    //----------------------------Metodos-----------------------------------
    //                            *Consulta*
	
	public int getTamaño() 
	{
		return tamaño;
	}
	
	public ArrayList<Posicion> listaPosicionesAdyacentes(Posicion posicion)
	{	
		ArrayList<Posicion> listaPosicionesAdyacentes = new ArrayList<Posicion>();
		int posX = posicion.getFila();
		int posY = posicion.getColumna();
		
		for(int miraColumnas = -1; miraColumnas <= 1; miraColumnas++ ) {
			int nuevaColumna = posY + miraColumnas;
			if(nuevaColumna >= 0 && nuevaColumna <= tamaño) {
				for(int miraFilas = -1; miraFilas <= 1; miraFilas++) {
					int nuevaFila = posX + miraFilas;
					if(!(nuevaFila == posX && nuevaColumna == posY)) {
					Posicion posicionActual = new Posicion(nuevaFila, nuevaColumna);
					if((posicionActual.getFila() >= 0 && posicionActual.getFila() <= tamaño)) {
						listaPosicionesAdyacentes.add(posicionActual);

					}
					}
				}
			}
		}
		
		return listaPosicionesAdyacentes;
	}

    //                            *Modificacion*

	public void setTamaño(int tamaño) {
		this.tamaño = tamaño;
	}
    //                            *Privados*


}

