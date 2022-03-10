package individuos;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.mutacion.MutacionBasica;
import gen.Gen;
import gen.GenBinario;

public abstract class Individuo {
	protected List<Gen> genes;
	protected List<Double> min;
	protected List<Double> max;
	protected List<Double> fenotipo;
	protected double valorError;
	protected String id;  
	
	public Individuo(double valorError)
	{
		this.valorError = valorError;
		genes = new ArrayList<>();
		min = new ArrayList<>();
		max = new ArrayList<>();
		fenotipo = new ArrayList<>();
	}
	
	public double getMax(int i) {return max.get(i);}
	
	public double getMin(int i) {return min.get(i);}
	
	public abstract boolean max();
	
	public abstract double getValor();
	
	public abstract double getFitness();
	
	protected abstract double getFenotipo(int i);
	
	public static Cruce[] getCruces()
	{
		return null;
	}
	
	public static Mutacion[] getMutaciones()
	{
		return null;
	}
	
	public static String[] getCrucesId()
	{
		return null;
	}
	
	public static String[] getMutacionesId()
	{
		return null;
	}
	
	private static Individuo[] individuos= {
			new IndividuoFuncion1(),
			new IndividuoFuncion2(),
			new IndividuoFuncion3(),
			new IndividuoFuncion4Bin(),
			new IndividuoFuncion4Real(),
	};
	
	public static String[] getStrings()
	{
		String[] s = new String[5];
		for(int i = 0; i < s.length; i++)
			s[i] = individuos[i].getId();
		return s;
	}
	
	protected String getId() {
		return id;
	}


	public static Individuo[] seleccionarIndividuo(int tam, String[] datos) //datos = id, valorError, n (si necesario)
	{
		Individuo ind[] = null;
		if(datos.length > 0)
			for(int i = 0; i < individuos.length && ind == null; i++)
			{
				ind = individuos[i].parse(tam, datos);
			}
		return ind;
	}
	
	protected abstract Individuo[] parse(int tam, String[] datos);

	public List<Gen> getGenes() {
		return genes;
	}


	public void recalcularFenotipo() {
		for(int i = 0; i < genes.size(); i++)
		{
			fenotipo.set(i, getFenotipo(i));
		}
	}
	
	//Creo que no es necesario
	//protected abstract void setGen(int i, double gen);
}
