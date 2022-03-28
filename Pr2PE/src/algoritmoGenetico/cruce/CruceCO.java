package algoritmoGenetico.cruce;

import individuos.Individuo;

public class CruceCO extends Cruce {
	private final String type = "CO";
	
	public CruceCO()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceCO();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		// TODO Auto-generated method stub

	}

}
