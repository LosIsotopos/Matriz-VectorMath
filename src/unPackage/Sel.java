package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class Sel {
	/* Se implementa la clase MatrizMath y VectorMath */
	private VectorMath vectorIndep;
	private VectorMath vectorResul;
	private MatrizMath matriz;

	public Sel(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		sc.useLocale(Locale.ENGLISH);
		/* Se utiliza un vector y matriz doble auxiliar para leer los datos */
		double[] vectorAux = new double[sc.nextInt()];
		double[][] matrizAux = new double[vectorAux.length][vectorAux.length];
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

		sc.close();
		/* Se instancia los objetos vector y matriz con los auxiliares */
		this.matriz = new MatrizMath(matrizAux);
		this.vectorIndep = new VectorMath(vectorAux);
	}

	public void mostrarMatriz() {
		this.matriz.mostrarMatriz();
	}

	public void mostrarVectorIndep() {
		System.out.println(this.vectorIndep);
	}

	public void resolverSistema(String path) throws DistDemException {
		this.vectorResul = this.matriz.inversaGauss().producto(this.vectorIndep);
		this.imprimirResultado(path);

	}

	public void mostrarResultado() {
		if(this.vectorResul == null)
			System.err.println("No se ha resuelto el sistema todavia");
		else
			System.out.println(this.vectorResul);
	}
	
	public boolean errorValido() throws DistDemException
	{
		if(this.calcularError() < Math.pow(10, -6))
			return true;
		return false;
	}
	public double calcularError() throws DistDemException {
		MatrizMath identidadPrima = new MatrizMath(this.matriz.getDimFil(), this.matriz.getDimCol());
		MatrizMath identidad = new MatrizMath(this.matriz.getDimFil(), this.matriz.getDimCol());

		identidad.matIdentidad();
		identidadPrima = this.matriz.inversaGauss().producto(this.matriz);

		identidadPrima = identidad.restarMatriz(identidadPrima);

		return identidadPrima.normaDos();

	}

	public MatrizMath invertir() throws DistDemException {
		return this.matriz.inversaGauss();
	}

	public void imprimirResultado(String path) throws DistDemException {
		if (this.vectorResul == null)
			this.resolverSistema(path);
		try {
			FileWriter archivo = new FileWriter(new File(path));
			PrintWriter pw = new PrintWriter(archivo);
			pw.println(this.vectorResul.length());
			for (int i = 0; i < this.vectorResul.length(); i++) {
				pw.println(this.vectorResul.getAt(i));
			}
			pw.println();
			pw.println();
			pw.print(this.calcularError());
			
			pw.close();
		} catch (IOException e) {
			System.err.println("NO SE PUDO GENERAR EL ARCHIVO");
		}

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
			cadena.append("== [" + vectorIndep.getAt(i) + "]\n");
		}

		return cadena.toString();
	}

}
