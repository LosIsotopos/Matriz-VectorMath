package unPackage;

import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, DistDemException {
		try {
			Sel sistema1 = new Sel ("sel1k.in");
			
			Calendar tIni = new GregorianCalendar();
			sistema1.resolverSistema();
			Calendar tFin = new GregorianCalendar();
			long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
			System.out.println("Tiempo de resolverSistema() = " + diff + " milisegs");
			
			if (sistema1.errorValido()) {
				System.out.println("Insertidumbre Valida");
			}else{
				System.out.println("Insertidumbre NO VALIDA");
			}
			sistema1.imprimirResultado("sol1k.out");
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
