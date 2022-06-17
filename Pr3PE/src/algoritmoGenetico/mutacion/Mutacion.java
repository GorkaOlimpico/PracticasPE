package algoritmoGenetico.mutacion;

import java.util.Random;

import algoritmoGenetico.mutacion.GE.MutacionBasica;
import algoritmoGenetico.mutacion.GE.MutacionHeuristica;
import algoritmoGenetico.mutacion.GE.MutacionInsercion;
import algoritmoGenetico.mutacion.GE.MutacionIntercambio;
import algoritmoGenetico.mutacion.GE.MutacionInversion;
import algoritmoGenetico.mutacion.PG.MutacionArbol;
import algoritmoGenetico.mutacion.PG.MutacionContraccion;
import algoritmoGenetico.mutacion.PG.MutacionExpansion;
import algoritmoGenetico.mutacion.PG.MutacionFuncional;
import algoritmoGenetico.mutacion.PG.MutacionHoist;
import algoritmoGenetico.mutacion.PG.MutacionPermutacion;
import algoritmoGenetico.mutacion.PG.MutacionTerminal;
import individuos.Individuo;

public abstract class Mutacion implements Cloneable {

	protected String id;
	
	public Mutacion() {}
	
	private static Mutacion[] mutacionPr2= {
			//new MutacionInsercion(),
			//new MutacionIntercambio(),
			//new MutacionInversion(),
			//new MutacionHeuristica(),
			new MutacionBasica(),
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
