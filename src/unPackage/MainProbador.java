package unPackage;

import java.io.FileNotFoundException;

public class MainProbador {

	public static void main(String[] args) throws DistDimException {
		String in = "sel1k.in";
		String out = "sel1k.out";
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
