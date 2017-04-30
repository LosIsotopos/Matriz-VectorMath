package unPackage;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, DistDemException {
		try {
			Sel sistema1 = new Sel ("sel04.in");
			System.out.println(sistema1);
			sistema1.resolverSistema();
			sistema1.mostrarResultado();
			if (sistema1.calcularErrorSolucion()) {
				System.out.println("Insertidumbre Valida");
			}else 
				System.out.println("Insertidumbre NO VALIDA");
			
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
