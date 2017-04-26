package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class Sel {
	/* Se implementa la clase MatrizMath y VectorMath */
	private VectorMath vector;
	private MatrizMath matriz;
	private char[] variables; // Preguntar si hay que incluirlas o no

	public Sel(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		sc.useLocale(Locale.ENGLISH);
		/* Se utiliza un vector y matriz doble auxiliar para leer los datos */
		double[] vectorAux = new double[sc.nextInt()];
		double[][] matrizAux = new double[vectorAux.length][vectorAux.length];
		variables = new char[vectorAux.length];
		int i = 0;

		while (sc.hasNextLine() && i < (Math.pow(matrizAux.length, 2))) {
			matrizAux[sc.nextInt()][sc.nextInt()] = sc.nextDouble();
			i++;
		}

		i = 0;
		while (sc.hasNextDouble()) {
			vectorAux[i] = sc.nextDouble();
			i++;
		}

		for (i = 0; i < matrizAux.length; i++) {
			variables[i] = (char) ('A' + i);
		}
		sc.close();
		/* Se instancia los objetos vector y matriz con los auxiliares */
		this.matriz = new MatrizMath(matrizAux);
		this.vector = new VectorMath(vectorAux);
	}

	// public void invertir()
	// {
	// this.matriz.
	// }

	public void mostrarMatriz() {
		// System.out.println("Matriz: ");
		// for (int j = 0; j < matriz.length; j++) {
		// System.out.println(Arrays.toString(matriz[j]));
		// }

		this.matriz.mostrarMatriz();
	}

	public void mostrarVector() {
		// System.out.println("Vector = " + Arrays.toString(vector));
		System.out.println(this.vector);
	}

	// public VectorMath resolverSistema() throws DistDemException
	// {
	// return this.matriz.invertir().producto(this.vector);
	// }

	public MatrizMath invertir() throws DistDemException {
		return this.matriz.invertir();
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < matriz.getDimFil(); i++) {
			cadena.append("[");
			for (int j = 0; j < matriz.getDimCol(); j++) {
				cadena.append(matriz.getAt(i, j) + " ");
			}
			cadena.setCharAt(cadena.length() - 1, ']');
			cadena.append(" [" + variables[i] + "] ==" + " [" + vector.getAt(i) + "]\n");
		}

		return cadena.toString();
	}

}
