package algoritmoGenetico.mutacion;

import individuos.Individuo;

public class MutacionIntercambio extends Mutacion {

	private final String type = "Intercambio";
	
	public MutacionIntercambio()
	{
		super.id = type;
	}
	
	@Override
	protected Mutacion parse(String id) {
		if(id == type)
			return new MutacionIntercambio();
		return null;
	}

	@Override
	protected void mutarIndividuo(Individuo ind) {
		// TODO Auto-generated method stub

	}

}
