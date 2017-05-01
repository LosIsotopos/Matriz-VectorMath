package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
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

	public void resolverSistema() throws DistDimException {
		this.vectorResul = this.matriz.inversaGauss().producto(this.vectorIndep);
	}

	public void mostrarResultado() {
		if(this.vectorResul == null)
			System.err.println("No se ha resuelto el sistema todavia");
		else
			System.out.println(this.vectorResul);
	}
	
	public boolean errorValido() throws DistDimException
	{
		GregorianCalendar tErrorIn = new GregorianCalendar();
		if(this.calcularError() < Math.pow(10, -6)){
			
			GregorianCalendar tErrorOut = new GregorianCalendar();
			System.out.println("Tiempo de calcularError() = " + (tErrorOut.getTimeInMillis() - tErrorIn.getTimeInMillis())+ " milisegs");
			return true;
		}
		
		GregorianCalendar tErrorOut = new GregorianCalendar();
		System.out.println("Tiempo de calcularError() = " + (tErrorOut.getTimeInMillis() - tErrorIn.getTimeInMillis())+ " milisegs");
		return false;
	}
	public double calcularError() throws DistDimException {
		MatrizMath identidadPrima = new MatrizMath(this.matriz.getDimFil(), this.matriz.getDimCol());
		MatrizMath identidad = new MatrizMath(this.matriz.getDimFil(), this.matriz.getDimCol());
		identidad.matIdentidad();
		identidadPrima = this.matriz.inversaGauss().producto(this.matriz);
		identidadPrima = identidad.restarMatriz(identidadPrima);
		
		GregorianCalendar tI = new GregorianCalendar();
		
		double aux = identidadPrima.normaDos();
		GregorianCalendar tF = new GregorianCalendar();
		System.out.println("Tiempo de norma2() = " + (tF.getTimeInMillis() - tI.getTimeInMillis())+ " milisegs");
		return aux;
	}

	public void imprimirResultado(String path) throws DistDimException {
		if (this.vectorResul == null)
			this.resolverSistema();
		try {
			GregorianCalendar tImprI = new GregorianCalendar();
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
			GregorianCalendar tImprF = new GregorianCalendar();
			System.out.println("Tiempo de imprimirResultado() = " + (tImprF.getTimeInMillis() - tImprI.getTimeInMillis())+ " milisegs");
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
