package src.algoritmoGenetico.cruces;

import src.algoritmoGenetico.individuos.Individuo;

public class CruceGenerator {
	
	private static Cruce[] crucesBin = { 
			new CruceUniforme(),
			new CruceMonopunto(),
	};
	
	public static Cruce[] getCrucesBin()
	{return crucesBin;}

	private static Cruce[] crucesReal = { 
			new CruceAritmetico(), 
			new CruceBLX(), 
			new CruceDiscretoUniforme(),
			new CruceMonopunto(),
	};
	
	public static Cruce[] getCrucesReal()
	{return crucesReal;}
	
	public static Cruce factoria(String text, Individuo m)  {
		Cruce aux = null;
		for (int i = 0; i < m.getCruces().length && aux == null; i++) {
			aux = m.getCruces()[i].parse(text);
		}
		return aux;
	}
	
	public static String[] getStrings(Individuo m)
	{
		String[] aux = new String[m.getCruces().length];
		for (int i = 0; i < m.getCruces().length; i++) {
			aux[i] = m.getCruces()[i].getType();
		}
		return aux;
	}
	
	public static Cruce[] getClasses(Individuo m) {
		return m.getCruces();
	}
}
