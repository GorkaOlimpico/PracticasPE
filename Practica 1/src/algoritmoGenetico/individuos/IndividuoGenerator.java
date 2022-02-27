package src.algoritmoGenetico.individuos;

import java.util.List;

public class IndividuoGenerator {
	private static Individuo[] funciones = { 
			new IndividuoFuncion1(), 
			new IndividuoFuncion2(),
			new IndividuoFuncion3(),
			new IndividuoFuncion4Bin(),
			new IndividuoFuncion4Real(),
	};

	public static List<Individuo> factoria(String[] j, int tam, double valorError)  {
		List<Individuo> aux = null;
		for (int i = 0; i < funciones.length && aux == null; i++) {
			aux = funciones[i].parse(j, tam, valorError);
		}
		return aux;
	}
	
	public static String[] getStrings()
	{
		String[] aux = new String[funciones.length];
		for (int i = 0; i < funciones.length; i++) {
			aux[i] = funciones[i].getType();
		}
		return aux;
	}
	
	public static Individuo[] getClasses() {
		return funciones;
	}
}
