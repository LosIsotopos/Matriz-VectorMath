package unPackage;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, DistDimException {
		String nombre = "07_col0";
		String in = nombre +".in";
		String out = nombre+ ".out";
		try {
			
			Sel sistema1 = new Sel("Lote de prueba//Entrada//" + in);
			sistema1.resolverSistema();

			if (sistema1.errorValido()) {
				System.out.println("Insertidumbre Valida");
			} else {
				System.out.println("Insertidumbre NO VALIDA");
			}
			sistema1.imprimirResultado("Lote de prueba//Salida//" + out);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
