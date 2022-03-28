package algoritmoGenetico.cruce;

import individuos.Individuo;

public class CruceCX extends Cruce {
private final String type = "CX";
	
	public CruceCX()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceCX();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		// TODO Auto-generated method stub

	}

}
