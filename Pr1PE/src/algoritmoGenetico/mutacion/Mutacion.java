package algoritmoGenetico.mutacion;

import java.util.Random;

import individuos.Individuo;

public abstract class Mutacion {

	
	private static Mutacion[] mutacionBin= {
			new MutacionBasica(),
	};
	
	public static Mutacion[] getMutacionesBin()
	{
		return mutacionBin;
	}
	
	public void mutar(Individuo[] ind, double prob)
	{
		Random rand = new Random();
		for(int i = 0; i < ind.length; i++)
			if(rand.nextDouble() < prob)
				mutarIndividuo(ind[i]);
	}
	
	protected abstract void mutarIndividuo(Individuo ind);
}
