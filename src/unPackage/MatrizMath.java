package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;


public class MatrizMath {
	private int dimCol;
	private int dimFil;
	private double[][] matriz;

	public MatrizMath(double[][] matriz) {
		this.matriz = matriz;
		this.dimFil = matriz.length;
		this.dimCol = matriz[0].length;
	}

	public MatrizMath(int fila, int columna) {
		this.dimFil = fila;
		this.dimCol = columna;
		this.matriz = new double[fila][columna];
	}

	public MatrizMath(String path) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path));
		sc.useLocale(Locale.ENGLISH);
		matriz = new double[sc.nextInt()][sc.nextInt()];
		dimFil = matriz.length;
		dimCol = matriz[0].length;
		while (sc.hasNextLine())
			matriz[sc.nextInt()][sc.nextInt()] = sc.nextDouble();
		sc.close();
	}

	public int getDimCol() {
		return dimCol;
	}

	public void setDimCol(int dimCol) {
		this.dimCol = dimCol;
	}

	public int getDimFil() {
		return dimFil;
	}

	public void setDimFil(int dimFil) {
		this.dimFil = dimFil;
	}

	public double[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(double[][] matriz) {
		this.matriz = matriz;
	}

	public void mostrarMatriz() {
		System.out.println("Matriz: ");
		for (int i = 0; i < matriz.length; i++) {
			System.out.println(Arrays.toString(matriz[i]));
		}
	}

	public MatrizMath sumarMatriz(MatrizMath m) throws DistDimException {
		if (this.dimCol != m.dimCol || this.dimFil != m.dimFil) {
			throw new DistDimException("No pueden sumarse matrices de distintas dimensiones");
		}
		double[][] matResult = new double[m.dimFil][m.dimCol];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matResult[i][j] = matriz[i][j] + m.matriz[i][j];
			}
		}
		return new MatrizMath(matResult);
	}

	public MatrizMath restarMatriz(MatrizMath m) throws DistDimException {
		if (this.dimCol != m.dimCol || this.dimFil != m.dimFil) {
			throw new DistDimException("No pueden sumarse matrices de distintas dimensiones");
		}
		double[][] matResult = new double[m.dimFil][m.dimCol];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matResult[i][j] = matriz[i][j] - m.matriz[i][j];
			}
		}
		return new MatrizMath(matResult);
	}

	public MatrizMath producto(MatrizMath m) throws DistDimException {
		if (this.dimCol != m.dimFil) {
			throw new DistDimException("No pueden multiplicarse las matrices, dimensiones erroneas");
		}
		double[][] matResult = new double[dimFil][m.dimCol];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < m.matriz[0].length; j++) {
				for (int k = 0; k < matriz[0].length; k++) {
					matResult[i][j] += matriz[i][k] * m.matriz[k][j];
				}
			}
		}
		return new MatrizMath(matResult);
	}

	public VectorMath producto(VectorMath v) throws DistDimException {
		if (this.dimCol != v.getDim()) {
			throw new DistDimException("No pueden multiplicarse la matriz por el vector, dimensiones erroneas");
		}
		double[] vecResult = new double[dimFil];
		double[] aux = new double[v.getDim()];
		aux = v.getCoord();
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				vecResult[i] += matriz[i][j] * aux[j];
			}
		}
		return new VectorMath(vecResult);
	}

	public MatrizMath producto(float n) {
		double[][] matResult = new double[dimFil][dimCol];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matResult[i][j] = matriz[i][j] * n;
			}
		}
		return new MatrizMath(matResult);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatrizMath other = (MatrizMath) obj;
		if (dimCol != other.dimCol)
			return false;
		if (dimFil != other.dimFil)
			return false;
		if (!Arrays.deepEquals(matriz, other.matriz))
			return false;
		return true;
	}

	public double determinante() throws DistDimException {
		int move;
		if (dimCol != dimFil) {
			throw new DistDimException("No puede calcular el determinante ya que la matriz no es cuadrada");
		}
		double res = 0;
		MatrizMath mDet = new MatrizMath(this.clonar());
		move = mDet.cerosInferior();
		if (move == 0) {
			return 0;
		}
		for (int i = 0; i < this.matriz.length; i++) {
			if (mDet.matriz[i][i] == 0) {
				return 0;
			}
			if (i == 0) {
				res += mDet.matriz[i][i];
			} else {
				res *= mDet.matriz[i][i];
			}
		}
		return res * move;
	}

	private int cerosInferior() {
		int movio = 1;
		int i = 0, j = 0, fila;
		while (j < this.dimCol - 1) {
			fila = i;
			while (i < this.dimFil) {
				if (this.matriz[fila][j] == 0 && this.matriz[i][j] != 0) {
					this.colocarPrimerElementoNoNulo(this.matriz, fila, i);
					fila++;
					movio *= -1;
				}
				i++;
			}
			j++;
			i = j;
		}
		return movio;
	}

	/** Mueve filas hasta que el [0][0] sea no nulo */

	public boolean colocarPrimerElementoNoNulo(double[][] original, int origen, int destino) {
		double[] aux = new double[origen];
		aux = original[origen];
		original[origen] = original[destino];
		original[destino] = aux;
		return true;
	}

	/* Retorna el valor de la posicion i j */
	public double getAt(int i, int j) {
		return this.matriz[i][j];
	}

	/**
	 * FERNANDO AMIGOO
	 * 
	 * @throws DistDimException
	 *             Version beta
	 */
	
	public MatrizMath inversaGauss() throws DistDimException {
		if (this.dimFil != this.dimCol)
			throw new DistDimException("Inversa solo de matrices cuadradas");
		double pivote, auxiliar;
		int z,i,j;
		MatrizMath matAuxiliar = new MatrizMath(this.clonar());
		MatrizMath matInversa = new MatrizMath(this.dimFil, this.dimCol);

		matInversa.matIdentidad();
		for (z = 0; z < matAuxiliar.dimFil - 1; z++) {
			if (matAuxiliar.matriz[z][z] == 0)
				matAuxiliar.cambiarFila(z, matInversa);
			
			if (matAuxiliar.matriz[z][z] == 0)
				throw new DistDimException("No tiene inversa");
			else if (matAuxiliar.matriz[z][z] != 1)
				matAuxiliar.dividirFila(z, matInversa);
			
			for (i = z + 1; i < matAuxiliar.dimFil; i++) {
				auxiliar = matAuxiliar.matriz[i][z];
				if (auxiliar != 0) {
					for (j = 0; j < matAuxiliar.dimCol; j++) {
						matAuxiliar.matriz[i][j] -= matAuxiliar.matriz[z][j] * auxiliar;
						matInversa.matriz[i][j] -= matInversa.matriz[z][j] * auxiliar;
					}
				}
			}
		}
		
		if (matAuxiliar.detIgual0()) {
//			throw new DistDimException("El determinante es 0, no tiene inversa. No es un Sistema Compatible Determinado");
			return null;
		}
		
		if(matAuxiliar.matriz[matAuxiliar.dimFil-1][matAuxiliar.dimFil-1] !=1)
			matAuxiliar.dividirFila(z, matInversa);
		// Una vez triangulada la matriz pregunto si su det es 0 para no
		// continuar

		for (z = matAuxiliar.dimFil - 1; z > 0; z--) {
			pivote = matAuxiliar.matriz[z][z];
			for (i = z - 1; i >= 0; i--) {
				auxiliar = matAuxiliar.matriz[i][z];
				if (auxiliar != 0) {
					for (j = 0; j < matAuxiliar.dimCol; j++) {
						matAuxiliar.matriz[i][j] -= matAuxiliar.matriz[z][j] * auxiliar / pivote;
						matInversa.matriz[i][j] -= matInversa.matriz[z][j] * auxiliar / pivote;
					}
				}
			}
		}
		

		return matInversa;
	}

	protected MatrizMath clone() {
		MatrizMath clon = new MatrizMath(this.dimFil, this.dimCol);

		for (int i = 0; i < this.dimFil; i++) {
			for (int j = i; j < this.dimCol; j++) {
				clon.matriz[i][j] = this.matriz[i][j];

				if (i != j)
					clon.matriz[j][i] = this.matriz[j][i];
			}
		}

		return clon;
	}

	/* Clona una matriz de dobles */
	public double[][] clonar() {
		double[][] mat = new double[this.dimFil][this.dimCol];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				mat[i][j] = this.matriz[i][j];
			}
		}
		return mat;
	}

	/* Transforma la matriz llamador en identidad */
	public void matIdentidad() {
		for (int i = 0; i < this.dimFil; i++) {
			this.matriz[i][i] = 1;
		}
	}
	/* Ordena desde la fila indicada en inicio */

	public void cambiarFila(int inicio, MatrizMath mat2) throws DistDimException {
		int i = inicio + 1;

		while (i < this.dimFil && this.matriz[i][inicio] == 0)
			i++;
		if (i != this.dimFil) {
			this.mover(inicio, i);
			mat2.mover(inicio, i);
		}

	}

	public void mover(int origen, int destino) throws DistDimException {
		if (origen < 0 || destino >= this.dimFil)
			throw new DistDimException("SE PASO DE LAS FILAS DE LA MATRIZ");
		double[] aux = new double[this.dimFil];
		aux = this.matriz[origen];
		this.matriz[origen] = this.matriz[destino];
		this.matriz[destino] = aux;
	}

	public void dividirFila(int origen, MatrizMath mat2) {
		double auxiliar = this.matriz[origen][origen];
		for (int i = 0; i < this.dimCol; i++) {
			this.matriz[origen][i] /= auxiliar;
			mat2.matriz[origen][i] /= auxiliar;
		}
	}

	/* Una vez triangulada la matriz pregunta si el det es 0 */
	private boolean detIgual0() {
		int i = 0;
		while (i < this.dimFil && this.matriz[i][i] != 0) {
			i++;
		}

		if (i == this.dimFil - 1)
			return true;

		return false;
	}

	public double normaUno() throws DistDimException {
		if (this.dimCol != this.dimFil) {
			throw new DistDimException("No puede calcularse la NormaUno debido a que no es una matriz cuadrada");
		}
		double mayor = 0;
		double sumaParcial = 0;
		for (int j = 0; j < this.matriz.length; j++) {
			for (int i = 0; i < this.matriz.length; i++) {
				sumaParcial += Math.abs(this.matriz[i][j]);
			}
			if (mayor < sumaParcial) {
				mayor = sumaParcial;
			}
			sumaParcial = 0;
		}
		return mayor;
	}

	public double normaDos() throws DistDimException {
		if (this.dimCol != this.dimFil) {
			throw new DistDimException("No puede calcularse la NormaUno debido a que no es una matriz cuadrada");
		}
		MatrizMath mAtxA = new MatrizMath(this.dimFil,this.dimCol);
		MatrizMath mTraspuesta = new MatrizMath(this.clonar());
		mTraspuesta.trasponer();
		mAtxA = this.producto(mTraspuesta);
		return Math.sqrt(mAtxA.buscarMayor());
	}

	public double normaInfinita() throws DistDimException {
		if (this.dimCol != this.dimFil) {
			throw new DistDimException("No puede calcularse la NormaUno debido a que no es una matriz cuadrada");
		}
		double mayor = 0;
		double sumaParcial = 0;
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				sumaParcial += Math.abs(this.matriz[i][j]);
			}
			if (mayor < sumaParcial) {
				mayor = sumaParcial;
			}
			sumaParcial = 0;
		}
		return mayor;
	}

	public void trasponer() {
		double aux = 0;
		for (int i = 0; i < this.matriz.length - 1; i++) {
			for (int j = i + 1; j < this.matriz.length; j++) {
				aux = this.matriz[i][j];
				this.matriz[i][j] = this.matriz[j][i];
				this.matriz[j][i] = aux;
			}
		}
	}

	
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < this.dimFil; i++) {
			cadena.append("[");
			for (int j = 0; j < this.dimCol; j++) {
				cadena.append(this.matriz[i][j] + " ");
			}
			cadena.deleteCharAt(cadena.length() - 1);
			cadena.append("]\n");
		}
		return cadena.toString();
	}

	private double buscarMayor() {
		double mayor = 0;
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				if (mayor < Math.abs(this.matriz[i][j])) {
					mayor = Math.abs(this.matriz[i][j]);
				}
			}
		}
		return mayor;
	}



}
