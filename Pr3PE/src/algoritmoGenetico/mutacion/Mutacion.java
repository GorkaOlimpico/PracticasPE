package algoritmoGenetico.mutacion;

import java.util.Random;

import individuos.Individuo;

public abstract class Mutacion implements Cloneable {

	protected String id;
	
	public Mutacion() {}
	
	private static Mutacion[] mutacionPr2= {
			new MutacionInsercion(),
			new MutacionIntercambio(),
			new MutacionInversion(),
			new MutacionHeuristica(),
	};
	
	public static Mutacion[] getMutacionesPr2()
	{
		return mutacionPr2;
	}
	
	private static Mutacion[] mutacionPG= {
			new MutacionTerminal(),
			new MutacionFuncional(),
			new MutacionArbol(),
			new MutacionPermutacion(),
			new MutacionHoist(),
			new MutacionContraccion(),
			new MutacionExpansion(),
	};
	
	public static Mutacion[] getMutacionesPG()
	{
		return mutacionPG;
	}
	
	public String getId()
	{
		return id;
	}
	
	public static Mutacion seleccionarMutacion(String id, Individuo ind)
	{
		Mutacion m = null;
		for(int i = 0; i < ind.getMutaciones().length && m == null; i++)
		{
			m = ind.getMutaciones()[i].parse(id);
		}
		return m;
	}
	
	protected abstract Mutacion parse(String id);
	
	public void mutar(Individuo[] ind, double prob)
	{
		Random rand = new Random();
		for(int i = 0; i < ind.length; i++)
			if(rand.nextDouble() < prob)
			{
				mutarIndividuo(ind[i]);
				ind[i].recalcularFenotipo();
			}
	}
	
	protected abstract void mutarIndividuo(Individuo ind);
}
