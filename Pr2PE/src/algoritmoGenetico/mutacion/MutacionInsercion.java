package algoritmoGenetico.mutacion;

import individuos.Individuo;

public class MutacionInsercion extends Mutacion {

	private final String type = "Insercion";
	
	public MutacionInsercion()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionInsercion();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		// TODO Auto-generated method stub

	}

}
