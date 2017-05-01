package unPackage;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, DistDimException {
		String in = "selError.in";
		String out = "selError.out";
		try {
			
			Sel sistema1 = new Sel("entrada//" + in);
			sistema1.resolverSistema();

			if (sistema1.errorValido()) {
				System.out.println("Insertidumbre Valida");
			} else {
				System.out.println("Insertidumbre NO VALIDA");
			}
			sistema1.imprimirResultado("salida//" + out);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
