package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class ProgramaProbador {
	MatrizMath matIn;
	VectorMath vectInde, vectResu;
	double errorOut;

	public ProgramaProbador(String pIn, String pOut) throws FileNotFoundException {
		Scanner scIn = new Scanner(new File(pIn));
		Scanner scOut = new Scanner(new File(pOut));
		scIn.useLocale(Locale.ENGLISH);
		scOut.useLocale(Locale.ENGLISH);

		this.vectInde = new VectorMath(scIn.nextInt());
		this.matIn = new MatrizMath(vectInde.length(), vectInde.length());

		this.vectResu = new VectorMath(scOut.nextInt());

		for (int i = 0; i < vectInde.length() * vectInde.length(); i++) 
			this.matIn.getMatriz()[scIn.nextInt()][scIn.nextInt()] = scIn.nextDouble();
		
		for(int i = 0; i< vectInde.length(); i++)
			this.vectInde.getCoord()[i] = scIn.nextDouble();
		scIn.close();
		for(int i = 0; i<vectResu.length(); i++)
			vectResu.getCoord()[i] = scOut.nextDouble();
		errorOut = scOut.nextDouble();
		scOut.close();
	}

	public boolean probar() throws DistDimException {
		VectorMath vectB;
		vectB = matIn.producto(vectResu);

		vectB = vectB.restaVectores(vectInde);

		if ((vectB.normaDos() - this.errorOut) < Math.pow(10, -6))
			return true;
		return false;

	}
}
