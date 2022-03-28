package algoritmoGenetico.cruce;

import individuos.Individuo;

public class CrucePMX extends Cruce {
private final String type = "PMX";
	
	public CrucePMX()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CrucePMX();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		// TODO Auto-generated method stub

	}

}
