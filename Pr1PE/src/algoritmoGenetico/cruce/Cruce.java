package algoritmoGenetico.cruce;

import individuos.Individuo;

public class Cruce {
	
	
	private static Cruce[] crucesBin= {
			new CruceMonopunto(),
			new CruceUniforme(),
	};
	
	public static Cruce[] getCrucesBin()
	{
		return crucesBin;
	}
	
	public void cruzar(Individuo[] ind, double prob)
	{
		
	}
}
