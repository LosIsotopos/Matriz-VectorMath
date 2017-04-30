package unPackage;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws CloneNotSupportedException, DistDemException {
//		double result;
		try {
//			VectorMath v1 = new VectorMath("vector.in");
//			System.out.println(v1.toString());
//			VectorMath v2 = new VectorMath("vector2.in");
//			System.out.println(v2.toString());
//			VectorMath v3 = v1.sumaVectores(v2);
//			System.out.println(v3.toString());
//			result = v1.producto(v2);
//			System.out.println("Producto Escalar=" + result);
//			
//			System.out.println("Norma Uno = " + v1.normaUno());
//			System.out.println("Norma Dos = " + v1.normaDos());
//			
//			System.out.println(v1.producto(3));
//			
//			System.out.println(v1.equals(v2));
//			System.out.println(v1.equals(v1));
//			
//			VectorMath v5 = v1.clone();
//			System.out.println(v5.normaInfinita());
//			MatrizMath m1 = new MatrizMath("matriz.in");
//			m1.mostrarMatriz();
//			MatrizMath m2 = new MatrizMath("matriz1.in");
//			m2.mostrarMatriz();
//			MatrizMath m3 = new MatrizMath("matriz2.in");
//			m3.mostrarMatriz();
//			m3 = m1.sumarMatriz(m2);
//			m3.mostrarMatriz();
//			m3 = m1.restarMatriz(m2);
//			m3.mostrarMatriz();
//			m3 = m1.producto(m2);
//			m3.mostrarMatriz();
//			
//			MatrizMath m4 = new MatrizMath("matriz3.in");
//			VectorMath v6 = new VectorMath("vector4.in");
//			VectorMath v7 = new VectorMath("vector5.in");
//			v7 = m4.producto(v6);
//			System.out.println(v7);
//			
//			MatrizMath m5 = new MatrizMath("matriz3.in");
//			float n = 3.2f;
//			m5 = m4.producto(n);
//			m5.mostrarMatriz();
//			
//			System.out.println(m5.equals(m5));
//			System.out.println(m5.equals(m4));
//			
//			MatrizMath m6 = new MatrizMath("matriz5.in");
//			m6.mostrarMatriz();
//			System.out.println("Determinante = " + m6.determinante());
//			m6.mostrarMatriz();
			
//			Sel sistema1 = new Sel("matriz1.in");
//			System.out.println(sistema1);
//			MatrizMath m9 = new MatrizMath("matriz6.in");
//			m9.mostrarMatriz();
//			m9.moverFila(m9.getMatriz(), 0);
//			MatrizMath m10 = new MatrizMath(m9.invertir().clonar());
//			m9.mostrarMatriz();
//			m10.mostrarMatriz();
//			MatrizMath mat1 =  sistema1.invertir();
//			mat1.mostrarMatriz();
//			System.out.println(sistema1);
			
//			MatrizMath m1 = new MatrizMath("matriz2.in");
//			m1.mostrarMatriz();
//			
//			System.out.println("Determinante = " + m1.determinante());
//			
//			System.out.println("Norma 1 = " + m1.normaUno());
//			m1.mostrarMatriz();
//			System.out.println("Norma 2 = " + m1.normaDos());
//			m1.mostrarMatriz();
//			System.out.println("Norma Infinita = " + m1.normaInfinita());
//			m1.mostrarMatriz();
			
			Sel sistema1 = new Sel ("matriz.in");
			System.out.println(sistema1);
			sistema1.resolverSistema();
			sistema1.mostrarResultado();
			if (sistema1.calcularErrorSolucion()) {
				System.out.println("Insertidumbre Valida");
			}else 
				System.out.println("Insertidumbre NO VALIDA");
			
			} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (DistDemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

}
