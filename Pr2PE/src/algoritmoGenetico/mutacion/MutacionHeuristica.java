package algoritmoGenetico.mutacion;

import individuos.Individuo;

public class MutacionHeuristica extends Mutacion {

	private final String type = "Heuristica";
	
	public MutacionHeuristica()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionHeuristica();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		// TODO Auto-generated method stub

	}

}
