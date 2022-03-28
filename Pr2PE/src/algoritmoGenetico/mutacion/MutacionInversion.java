package algoritmoGenetico.mutacion;

import individuos.Individuo;

public class MutacionInversion extends Mutacion {

	private final String type = "Inversion";
	
	public MutacionInversion()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionInversion();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		// TODO Auto-generated method stub

	}

}
