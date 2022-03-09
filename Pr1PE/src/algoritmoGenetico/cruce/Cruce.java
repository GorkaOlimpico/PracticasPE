package algoritmoGenetico.cruce;

public class Cruce {
	
	
	private static Cruce[] crucesBin= {
			new CruceMonopunto(),
			new CruceUniforme(),
	};
	
	public static Cruce[] getCrucesBin()
	{
		return crucesBin;
	}
}
