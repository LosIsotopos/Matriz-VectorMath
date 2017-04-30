package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
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

	 public void resolverSistema() throws DistDemException{
		 this.vectorResul = this.matriz.inversaGauss().producto(this.vectorIndep);
	 }
	 
	 public void mostrarResultado(){
		 System.out.println(this.vectorResul);
	 }
	 
	 public boolean calcularErrorSolucion() throws DistDemException{
		 MatrizMath identidadPrima = new MatrizMath(this.matriz.getDimFil(), this.matriz.getDimCol());
		 MatrizMath identidad = new MatrizMath(this.matriz.getDimFil(), this.matriz.getDimCol());
		 identidad.matIdentidad();
		 identidadPrima = this.matriz.inversaGauss().producto(this.matriz);
		 identidadPrima = identidad.restarMatriz(identidadPrima);
		 if(identidadPrima.normaDos() < Math.pow(10, -6))
			 return true;
		 return false;
		 
	 }

	public MatrizMath invertir() throws DistDemException {
		return this.matriz.inversaGauss();
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
			cadena.append(" ==" + " [" + vectorIndep.getAt(i) + "]\n");
		}
		return cadena.toString();
	}

}
