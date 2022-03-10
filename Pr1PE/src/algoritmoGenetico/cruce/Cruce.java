package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuos.Individuo;

public abstract class Cruce {
	
	
	private static Cruce[] crucesBin= {
			new CruceMonopunto(),
			new CruceUniforme(),
	};
	
	public static Cruce[] getCrucesBin()
	{
		return crucesBin;
	}
	
	public void cruzar(Individuo[] ind, double prob)
	{
		Random rand = new Random();
		List<Individuo> paraCruzar = new ArrayList<>();
		for(int i = 0; i < ind.length; i++)
			if(rand.nextDouble() < prob)
				paraCruzar.add(ind[i]);
		
		while(paraCruzar.size() > 1)
		{
			int i = rand.nextInt(paraCruzar.size()), j = rand.nextInt(paraCruzar.size());
			while(i == j)
				j = rand.nextInt(paraCruzar.size());
			cruzarIndividuos(paraCruzar.get(i), paraCruzar.get(j));
			if(i < j)
			{
				paraCruzar.remove(j);
				paraCruzar.remove(i);
			}
			else
			{
				paraCruzar.remove(i);
				paraCruzar.remove(j);
			}
		}
	}
	
	protected abstract void cruzarIndividuos(Individuo i1, Individuo i2);
}
