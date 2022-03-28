package algoritmoGenetico.cruce;

import java.util.Random;

import individuos.Individuo;

public class CruceOX extends Cruce {
	private final String type = "OX";		//Hago yo el cruce OX que es igual que el OX-PP, haz tu el PMX en su lugar
	
	public CruceOX()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceOX();
		return null;
	}

	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		Random rand = new Random();
		for(int i = 0; i < i1.getGenes().size(); i++)
		{
			int ini = rand.nextInt(2 * i1.getGenes().get(i).getLongitud() / 3), fin = rand.nextInt(i1.getGenes().get(i).getLongitud() - ini) + ini;
			
			OXPP(i1, i, ini, fin);
			OXPP(i2, i, ini, fin);
		}
  	}

	private void OXPP(Individuo i1, int i, int ini, int fin)
	{
		int next = fin + 1;		
		for(int j = 1; j < i1.getGenes().get(i).getLongitud(); j++)
		{
			int posicion = (fin + j) % i1.getGenes().get(i).getLongitud();
			next %= i1.getGenes().get(i).getLongitud();
			
			boolean valido = true;									//Se comprueba si el elemento en la posicion (posicion) esta entre los intercambiados
			for(int k = ini; k < fin && valido; k++)
				if(i1.getGenes().get(i).getAlelo(k) == i1.getGenes().get(i).getAlelo(posicion))
					valido = false;
			
			if(valido)												//Si no esta se pone en la siguiente posicion (next)
				i1.getGenes().get(i).setAlelo(next, i1.getGenes().get(i).getAlelo(posicion));
		}
	}
}
