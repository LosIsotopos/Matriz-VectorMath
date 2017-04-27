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

	public MatrizMath sumarMatriz(MatrizMath m) throws DistDemException {
		if (this.dimCol != m.dimCol || this.dimFil != m.dimFil) {
			throw new DistDemException("No pueden sumarse matrices de distintas dimensiones");
		}
		double[][] matResult = new double[m.dimFil][m.dimCol];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matResult[i][j] = matriz[i][j] + m.matriz[i][j];
			}
		}
		return new MatrizMath(matResult);
	}

	public MatrizMath restarMatriz(MatrizMath m) throws DistDemException {
		if (this.dimCol != m.dimCol || this.dimFil != m.dimFil) {
			throw new DistDemException("No pueden sumarse matrices de distintas dimensiones");
		}
		double[][] matResult = new double[m.dimFil][m.dimCol];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matResult[i][j] = matriz[i][j] - m.matriz[i][j];
			}
		}
		return new MatrizMath(matResult);
	}

	public MatrizMath producto(MatrizMath m) throws DistDemException {
		if (this.dimCol != m.dimFil) {
			throw new DistDemException("No pueden multiplicarse las matrices, dimensiones erroneas");
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
	
	public VectorMath producto(VectorMath v) throws DistDemException {
		if (this.dimCol != v.getDim()) {
			throw new DistDemException("No pueden multiplicarse la matriz por el vector, dimensiones erroneas");
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

	public double determinante() throws DistDemException {
		int move;
		if (dimCol != dimFil) {
			throw new DistDemException("No puede calcular el determinante ya que la matriz no es cuadrada");
		}
		double res = 0;
		MatrizMath mDet = new MatrizMath(this.clonar());
 		move = mDet.cerosInferior();
 		if(move == 0){
 			return 0;
 		}
		for (int i = 0; i < this.matriz.length; i++) {
			if(i == 0){
				res+= mDet.matriz[i][i];
			}
			else{
				res*= mDet.matriz[i][i];
			}
		}
		return res * move;
	}
	
	private int cerosInferior() {
		double valor = 0;
		int movio = 1;
		if(this.matriz[0][0] == 0){
			if(this.colocarPrimerElementoNoNulo(this.matriz, 0)){
				movio *= -1;
			}
			else{
				return 0;
			}
		}
		for (int i = 0; i < this.matriz.length-1; i++) {
			for (int k = i+1; k < this.matriz.length; k++) {
				for (int j = i; j < this.matriz.length; j++) {
					if(i == j){
						valor = this.matriz[k][j]/this.matriz[i][j];
					}
					matriz[k][j] -= valor * matriz[i][j];
				}
			}
		}
		return movio;
	}

	/** Mueve filas hasta que el [0][0] sea no nulo */
	
	public boolean colocarPrimerElementoNoNulo(double[][] original,int i) {
		double [] aux = new double[i];
		boolean ret = false;
		for (int k = i+1; k < original.length; k++) {
				if(original[0][0] == 0){
					if(original[k][0] != 0){
						aux=original[i];
						original[i]=original[k];
						original[k]=aux;
						ret = true;
					}
				}
		}
		return ret;
	}
	
	// PABLJNN AMIGOOO

	public MatrizMath invertir() throws DistDemException {
		double[][] invertida = new double[matriz.length][matriz.length];
		MatrizMath mInvertida = new MatrizMath(this.clonar());
		if(mInvertida.determinante() == 0 || mInvertida.matriz.length != mInvertida.matriz[0].length) {
			//Asumiendo que anda piola después vemos si le mandamos un throw exception 
			//Yo creo que el syso es re catinga
			System.out.println("NO SE PUEDE INVERTIR");
			return null;
		}
		for (int o = 0; o < invertida.length; o++) {
			invertida[o][o] = 1;
		}
		for (int i = 0; i < mInvertida.matriz.length; i++) {
			if (mInvertida.matriz[i][i] == 0) {
				moverFila(mInvertida.matriz,i);
			}
			dividir(mInvertida.matriz, i, invertida);
			cerosInferiores(mInvertida.matriz, i, invertida);
		}
		for (int i = 0; i < mInvertida.matriz.length; i++) {
			cerosSuperiores(mInvertida.matriz, i, invertida);
		}
		mInvertida.setMatriz(invertida);
		return mInvertida;

	}

	private void cerosSuperiores(double[][] original, int i, double[][] invertida) {
		double valor;
		for (int j = i + 1; j < original.length; j++) {
			valor = original[i][j];
			for (int k = 0; k < original.length; k++) {
				original[i][k] -= valor * original[j][k];
				invertida[i][k] -= valor * invertida[j][k];

			}
		}

	}

	private void cerosInferiores(double[][] original, int i, double[][] invertida) {
		double valor;
		for (int j = i + 1; j < original.length; j++) {
			valor = original[j][i];
			for (int k = 0; k < original.length; k++) {
				original[j][k] -= valor * original[i][k];
				invertida[j][k] -= valor * invertida[i][k];

			}
		}
	}

	private void dividir(double[][] original, int i, double[][] invertida) {
		// Guardo el valor previo para no perderlo y aprovechar el for para
		// también
		// modificar la invertida
		double valorInicial = original[i][i];
		for (int j = 0; j < original.length; j++) {
			original[i][j] /= valorInicial;
			invertida[i][j] /= valorInicial;
		}

	}

	public int length() {
		return this.matriz.length;
	}

	/* Retorna el valor de la posicion i j */
	public double getAt(int i, int j) {
		return this.matriz[i][j];
	}

	/* Clona una matriz de dobles */
	public double[][] clonar() {
		double [][] mat = new double [this.dimFil][this.dimCol];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				mat[i][j] = this.matriz[i][j];
			}
		}
		return mat;
	}

	public void moverFila(double[][] original,int i) {
		double [] aux = new double[i];
		int filaOriginal = i;
		while(i < original[i].length){
			if(original[i+1][filaOriginal] != 0){
				aux=original[filaOriginal];
				original[filaOriginal]=original[i+1];
				original[i+1]=aux;
				break;
			}
			i++;
		}
		
	}
	
	public double normaUno() throws DistDemException{
		if(this.dimCol != this.dimFil){
			throw new DistDemException("No puede calcularse la NormaUno debido a que no es una matriz cuadrada");
		}
		double mayor = 0;
		double sumaParcial = 0;
		for (int j = 0; j < this.matriz.length; j++) {
			for (int i = 0; i < this.matriz.length; i++) {
				sumaParcial += Math.abs(this.matriz[i][j]);
			}
			if(mayor < sumaParcial){
				mayor = sumaParcial;
			}
			sumaParcial = 0;
		}	
		return mayor;
	}
	
	public double normaDos() throws DistDemException{
		if(this.dimCol != this.dimFil){
			throw new DistDemException("No puede calcularse la NormaUno debido a que no es una matriz cuadrada");
		}
		double mayor = 0;
		double [][] mat = new double [this.dimFil][this.dimCol];
		MatrizMath mAtxA = new MatrizMath(mat);
		MatrizMath mTraspuesta = new MatrizMath(this.clonar());
		mTraspuesta.trasponer();
		mAtxA = this.producto(mTraspuesta);
		mayor = Math.sqrt(mAtxA.buscarMayor());
		return mayor;
	}

	public double normaInfinita() throws DistDemException{
		if(this.dimCol != this.dimFil){
			throw new DistDemException("No puede calcularse la NormaUno debido a que no es una matriz cuadrada");
		}
		double mayor = 0;
		double sumaParcial = 0;
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				sumaParcial += Math.abs(this.matriz[i][j]);
			}
			if(mayor < sumaParcial){
				mayor = sumaParcial;
			}
			sumaParcial = 0;
		}	
		return mayor;
	}
	
	public void trasponer(){
		double aux = 0;
		for (int i = 0; i < this.matriz.length-1; i++) {
			for (int j = i+1; j < this.matriz.length; j++) {
				aux = this.matriz[i][j];
				this.matriz[i][j] = this.matriz[j][i];
				this.matriz[j][i] = aux;
			}
		}
	}
	
	private double buscarMayor() {
		double mayor = 0;
		for (int i = 0; i < this.matriz.length; i++) {
			for (int j = 0; j < this.matriz.length; j++) {
				if(mayor < Math.abs(this.matriz[i][j])){
					mayor = Math.abs(this.matriz[i][j]);
				}
			}
		}
		return mayor;
	}
}
