import java.util.*;
/**
 * Clase Posicion, la cual esta compuesta por:
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
public class Posicion {

	//----------------------------Constantes--------------------------------
    
    //----------------------------Campos------------------------------------
	private int fila;
	private int columna;
	

    //----------------------------Constructores-----------------------------
	public Posicion(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}

    //----------------------------Metodos-----------------------------------
    //                            *Consulta*
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}

	public String toString() {
		return ("("+getFila()+","+getColumna()+")");
	}
    //                            *Modificacion*
	public void setColumna(int columna) {
		this.columna = columna;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

    //                            *Privados*
	
}

