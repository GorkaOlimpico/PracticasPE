package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuos.Individuo;

public class CruceOXPP extends Cruce {
	private final String type = "OX-PP";
	private final double prob = 0.15; //Probabilidad de elegir la posicion como punto de intercambio
	
	public CruceOXPP()
	{
		super.id = type;
	}
	
	@Override
	protected Cruce parse(String id) {
		if(id == type)
			return new CruceOXPP();
		return null;
	}

	@Override
	protected void cruzarIndividuos(Individuo i1, Individuo i2) {
		Random rand = new Random();
		for(int i = 0; i < i1.getGenes().size(); i++)
		{
			List<Integer> pos = new ArrayList<>();
			do {
				for(int j = 0; j < i1.getGenes().get(i).getLongitud(); j++)		//Se elige que alelos se intercambian
				{
					if(rand.nextDouble() < prob)
					{
						pos.add(j);
						i1.getGenes().get(i).intercambiarAlelo(j, i2.getGenes().get(i));
					}
				}
			}
			while(pos.size() == 0);
			
			OXPP(i1, pos, i);
			OXPP(i2, pos, i);
		}
  	}

	private void OXPP(Individuo i1, List<Integer> pos, int i)
	{
		int ini = pos.get(pos.size() - 1), next = ini + 1;		
		for(int j = 1; j < i1.getGenes().get(i).getLongitud(); j++)
		{
			int posicion = (ini + j) % i1.getGenes().get(i).getLongitud();
			next %= i1.getGenes().get(i).getLongitud();
			
			boolean valido = true;									//Se comprueba si el elemento en la posicion (posicion) esta entre los intercambiados
			for(int k = 0; k < pos.size() && valido; k++)
				if(i1.getGenes().get(i).getAlelo(pos.get(k)) == i1.getGenes().get(i).getAlelo(posicion))
					valido = false;
			
			if(valido)												//Si no esta se pone en la siguiente posicion (next)
				i1.getGenes().get(i).setAlelo(next, i1.getGenes().get(i).getAlelo(posicion));
		}
	}
	public String toString() {
		return "OXPP";
	}
}