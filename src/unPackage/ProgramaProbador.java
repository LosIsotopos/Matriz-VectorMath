package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProgramaProbador {
	MatrizMath matIn;
	VectorMath vectInde, vectResu;

	public ProgramaProbador(String pIn, String pOut) throws FileNotFoundException {
		Scanner scIn = new Scanner(new File(pIn));
		Scanner scOut = new Scanner(new File(pOut));

		this.vectInde = new VectorMath(scIn.nextInt());
		this.matIn = new MatrizMath(vectInde.length(), vectInde.length());
		
		this.vectResu = new VectorMath(scOut.nextInt());

		for (int i = 0; i < vectInde.length() * vectInde.length(); i++) {
			this.matIn.getMatriz()[scIn.nextInt()][scIn.nextInt()] = scIn.nextDouble();
		}
		for(int i = 0; i < vectInde.length(); i++)
		{
			this.vectInde.getCoord()[i] = scIn.nextDouble();
			this.vectResu.getCoord()[i] = scOut.nextDouble();
		}

	}

	public boolean probar() throws DistDimException {
		VectorMath vectAux;
		
		vectAux = matIn.producto(vectResu);
		
		vectAux = vectAux.restaVectores(vectInde);
		
		if(vectAux.normaDos() < Math.pow(10, -6))
			return true;
		return false;
		
	}
}
