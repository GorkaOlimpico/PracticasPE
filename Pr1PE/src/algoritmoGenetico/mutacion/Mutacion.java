package algoritmoGenetico.mutacion;

import java.util.Random;

import individuos.Individuo;

public abstract class Mutacion {

	protected String id;
	
	public Mutacion() {}
	
	private static Mutacion[] mutacionBin= {
			new MutacionBasica(),
	};
	
	public static Mutacion[] getMutacionesBin()
	{
		return mutacionBin;
	}
	
	public static String[] getMutacionesBinId()
	{
		String[] s = new String[mutacionBin.length];
		for(int i = 0; i < s.length; i++)
			s[i] = mutacionBin[i].getId();
		return s;
	}
	
	private static Mutacion[] mutacionReal= {
			new MutacionUniforme(),
	};
	
	public static Mutacion[] getMutacionesReal()
	{
		return mutacionReal;
	}
	
	public static String[] getMutacionesRealId()
	{
		String[] s = new String[mutacionReal.length];
		for(int i = 0; i < s.length; i++)
			s[i] = mutacionReal[i].getId();
		return s;
	}
	
	protected String getId()
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
				mutarIndividuo(ind[i]);
	}
	
	protected abstract void mutarIndividuo(Individuo ind);
}
