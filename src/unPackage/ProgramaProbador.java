package unPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

public class ProgramaProbador {
	Sel sist;
	double errorOut;
	
	public ProgramaProbador(String pIn, String pOut) throws FileNotFoundException {
		sist = new Sel(pIn);
		Scanner scOut = new Scanner(new File(pOut));
		scOut.useLocale(Locale.ENGLISH);
		this.sist.getVectorResul().setDim(scOut.nextInt());
		int tope = this.sist.getVectorResul().getDim();
		for(int i = 0; i< tope; i++)
			this.sist.getVectorResul().getCoord()[i] = scOut.nextDouble();
		errorOut = scOut.nextDouble();
		scOut.close();
	}
	
	public boolean probar() throws DistDimException {
		VectorMath vectB;
		vectB = this.sist.getMatriz().producto(this.sist.getVectorResul());
		vectB = vectB.restaVectores(this.sist.getVectorIndep());
		if ((vectB.normaDos() - this.errorOut) < Math.pow(10, -6))
			return true;
		return false;
	}
	
}
