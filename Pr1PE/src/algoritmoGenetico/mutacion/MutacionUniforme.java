package algoritmoGenetico.mutacion;

import individuos.Individuo;

public class MutacionUniforme extends Mutacion {
	private final String type = "Uniforme";
	
	public MutacionUniforme()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionUniforme();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		// TODO Auto-generated method stub

	}

}
