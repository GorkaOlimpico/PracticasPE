package src.algoritmoGenetico.mutacion;

import src.algoritmoGenetico.individuos.Individuo;

public class MutacionGenerator {
	private static Mutacion[] mutacionesBin = { 
			new MutacionBasica(), 
	};
	
	public static Mutacion[] getMutacionesBin()
	{return mutacionesBin;}
	
	private static Mutacion[] mutacionesReal = { 
			new MutacionUniforme(),  
	};
	
	public static Mutacion[] getMutacionesReal()
	{return mutacionesReal;}
	
	public static Mutacion factoria(String text, Individuo m)  {
		Mutacion aux = null;
		for (int i = 0; i < m.getMutaciones().length && aux == null; i++) {
			aux = m.getMutaciones()[i].parse(text);
		}
		return aux;
	}
	
	public static String[] getStrings(Individuo m)
	{
		String[] aux = new String[m.getMutaciones().length];
		for (int i = 0; i < m.getMutaciones().length; i++) {
			aux[i] = m.getMutaciones()[i].getType();
		}
		return aux;
	}
	
	public static Mutacion[] getClasses(Individuo m) {
		return m.getMutaciones();
	}
}
