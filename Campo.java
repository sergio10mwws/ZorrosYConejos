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
	private int tama�o;

    //----------------------------Constructores-----------------------------
	public Campo(int tama�o) 
	{
		this.tama�o = tama�o;
	}

    //----------------------------Metodos-----------------------------------
    //                            *Consulta*
	
	public int getTama�o() 
	{
		return tama�o;
	}
	
	public ArrayList<Posicion> listaPosicionesAdyacentes(Posicion posicion)
	{	
		ArrayList<Posicion> listaPosicionesAdyacentes = new ArrayList<Posicion>();
		int posX = posicion.getFila();
		int posY = posicion.getColumna();
		
		for(int miraColumnas = -1; miraColumnas <= 1; miraColumnas++ ) {
			int nuevaColumna = posY + miraColumnas;
			if(nuevaColumna >= 0 && nuevaColumna <= tama�o) {
				for(int miraFilas = -1; miraFilas <= 1; miraFilas++) {
					int nuevaFila = posX + miraFilas;
					if(!(nuevaFila == posX && nuevaColumna == posY)) {
					Posicion posicionActual = new Posicion(nuevaFila, nuevaColumna);
					if((posicionActual.getFila() >= 0 && posicionActual.getFila() <= tama�o)) {
						listaPosicionesAdyacentes.add(posicionActual);

					}
					}
				}
			}
		}
		
		return listaPosicionesAdyacentes;
	}

    //                            *Modificacion*

	public void setTama�o(int tama�o) {
		this.tama�o = tama�o;
	}
    //                            *Privados*


}

