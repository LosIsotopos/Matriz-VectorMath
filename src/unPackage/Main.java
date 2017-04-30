package unPackage;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, DistDemException {
		try {
			
			Sel sistema1 = new Sel ("sel10x10.in");
			sistema1.resolverSistema("sol10x10.out");
			sistema1.mostrarResultado();
			if (sistema1.errorValido()) {
				System.out.println("Insertidumbre Valida");
			}else 
				System.out.println("Insertidumbre NO VALIDA");
			
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
