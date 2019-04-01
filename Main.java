import java.util.ArrayList;

/**
 * @author (Alex) 
 * @version (fecha)
 */
public class Main{

	public static void main(String[] args) {
//		Posicion pos = new Posicion(3,3);
//		Campo campo = new Campo(10);
//		
//		ArrayList<Posicion> list= campo.listaPosicionesAdyacentes(pos);
//		System.out.println("Las posicones adyacentes a: "+pos);
//		for(Posicion posicion: list) {
//        	System.out.println(posicion);
//        }
		Simulador misim = new Simulador();
		misim.correrSimulacionLarga();
	}
}
