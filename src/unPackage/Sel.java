package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Scanner;

public class Sel {
	/* Se implementa la clase MatrizMath y VectorMath */
	private VectorMath vectorIndep;
	private VectorMath vectorResul;
	private MatrizMath matriz;
	private double error = -1;


	public VectorMath getVectorIndep() {
		return vectorIndep;
	}

	public void setVectorIndep(VectorMath vectorIndep) {
		this.vectorIndep = vectorIndep;
	}

	public MatrizMath getMatriz() {
		return matriz;
	}

	public void setMatriz(MatrizMath matriz) {
		this.matriz = matriz;
	}

	public VectorMath getVectorResul() {
		return vectorResul;
	}

	public void setVectorResul(VectorMath vectorResul) {
		this.vectorResul = vectorResul;
	}

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
		this.vectorResul = new VectorMath(vectorIndep.getDim());
	}

	public void mostrarMatriz() {
		this.matriz.mostrarMatriz();
	}

	public void mostrarVectorIndep() {
		System.out.println(this.vectorIndep);
	}

	public void resolverSistema() throws DistDimException {
		Calendar tIniResol = new GregorianCalendar();
		MatrizMath mInv;
		mInv = this.matriz.inversaGauss();
		if(mInv == null){
			this.matriz = null;
			return;
		}
		this.vectorResul = mInv.producto(this.vectorIndep);
		Calendar tFinResol = new GregorianCalendar();
		long diff = tFinResol.getTimeInMillis() - tIniResol.getTimeInMillis();
		System.out.println("Tiempo de resolverSistema() = " + diff + " milisegs");
		
		this.calcularError();
	}

	public void mostrarResultado() {
		if(this.vectorResul == null)
			System.err.println("No se ha resuelto el sistema todavia");
		else
			System.out.println(this.vectorResul);
	}
	
	public boolean errorValido() throws DistDimException
	{
		if(error == -1){
			return false;
		}
		if(this.error < Math.pow(10, -6)){
			return true;
		}
		return false;
	}
	
	public void calcularError() throws DistDimException {
		VectorMath vectIndPrima;
		VectorMath vectError;
		vectIndPrima = this.matriz.producto(this.vectorResul);
		vectError = vectIndPrima.restaVectores(this.vectorIndep);
		this.error = vectError.normaDos();
	}

	public void imprimirResultado(String path) throws DistDimException {
		try {
			FileWriter archivo = new FileWriter(new File(path));
			PrintWriter pw = new PrintWriter(archivo);
			if(this.matriz == null){
				pw.println("NO TIENE SOLUCION");
				pw.close();
				return;
			}
			pw.println(this.vectorResul.length());
			for (int i = 0; i < this.vectorResul.length(); i++) {
				pw.println(this.vectorResul.getAt(i));
			}
			pw.println();
			pw.println();
			pw.print(this.error);
			
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
