package algoritmoGenetico.cruce;

import individuos.Individuo;
import individuos.IndividuoPr2;

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

		Individuo i3 = i1;
		for(int i = 0; i < i3.getGenes().size(); i++) { // no diferencio entre gen y alelo. Suppongo que seria alelos.size()
			i3.getGenes()
		}

	}

}
