package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class VectorMath {
	private int dim;
	private double [] coord;
	
	public VectorMath(double[] coord) {
		this.dim = coord.length;
		this.coord = coord;
	}
	
	public VectorMath(int dim)
	{
		this.dim = dim;
		coord = new double[dim];
	}
	
	public VectorMath(String path) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(path));
		int i=0;
		sc.useLocale(Locale.ENGLISH);
		dim = sc.nextInt();
		coord = new double[dim];
		while(sc.hasNextDouble()){
			coord[i] = sc.nextDouble();
			i++;
		}
		sc.close();
	}
	
	@Override
	public String toString() {
		return "Vector :\n" + Arrays.toString(coord);
	}

	public VectorMath sumaVectores(VectorMath v1) throws DistDimException{
		if(v1.dim == dim){
			double [] aux = new double[dim];
			for(int i = 0;i<dim;i++)
				aux[i] = coord[i] + v1.coord[i];
			return new VectorMath(aux);	
		}
		throw new DistDimException("No pueden sumarse dos vectores de distintas dimensiones");
	}
	
	public VectorMath restaVectores(VectorMath v1) throws DistDimException{
		if(v1.dim == dim){
			double [] aux = new double[dim];
			for(int i = 0;i<dim;i++)
				aux[i]= coord[i] - v1.coord[i];
			return new VectorMath(aux);	
		}
		throw new DistDimException("No pueden restarse dos vectores de distintas dimensiones");
	}

	public double producto(VectorMath v1) throws DistDimException{
		if(v1.dim == dim){
			double res=0;
			for(int i = 0;i<dim;i++){
				res+=v1.coord[i]*coord[i];
			}
			return res;
		}
		throw new DistDimException("No pueden multiplicarse dos vectores de distintas dimensiones");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VectorMath other = (VectorMath) obj;
		if (!Arrays.equals(coord, other.coord))
			return false;
		if (dim != other.dim)
			return false;
		return true;
	}

	@Override
	protected VectorMath clone() throws CloneNotSupportedException {
		return new VectorMath(coord);
	}
	
	public double normaUno(){
		double a = 0;
		for (int i = 0; i < coord.length; i++) {
			a += coord[i];
		}
		return a;
	}
	
	public double normaDos(){
		double a = 0;
		for (int i = 0; i < coord.length; i++) {
			a += Math.pow(coord[i], 2);
		}
		return Math.sqrt(a);
	}
	
	public double normaInfinita(){
		double max=Math.abs(coord[0]);
		for(int i=1;i<dim;i++)
			if (max < Math.abs(coord[i]))
				max = Math.abs(coord[i]);
		return max;
	}
	
	public VectorMath producto(double r) {
		double [] aux = new double[dim];
		for(int i=0;i<dim;i++){
			aux[i] = coord[i]*r;
		}
		return new VectorMath(aux);
	}
	
	public int length()
	{
		return this.coord.length;
	}
	
	/*Retorna el valor de la posicion i*/
	public double getAt(int i)
	{
		return this.coord[i];
	}

	public double[] getCoord() {
		return coord;
	}

	public void setCoord(double[] coord) {
		this.coord = coord;
	}
	

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}
}
