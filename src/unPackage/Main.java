package unPackage;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, DistDimException {
		String in = "sel2k.in";
		String out = "sel2k.out";
//		try {
//			
//			Sel sistema1 = new Sel("entrada//" + in);
//
//			sistema1.resolverSistema();
//
//			if (sistema1.errorValido()) {
//				System.out.println("Insertidumbre Valida");
//			} else {
//				System.out.println("Insertidumbre NO VALIDA");
//			}
//			sistema1.imprimirResultado("salida//" + out);
//			
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		ProgramaProbador p1;
		try {
			p1 = new ProgramaProbador(("entrada//" + in), ("salida//"+out));

			if (p1.probar()) {
				System.out.println("Salida correcta");
			} else
				System.err.println("Salida incorrecta");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
