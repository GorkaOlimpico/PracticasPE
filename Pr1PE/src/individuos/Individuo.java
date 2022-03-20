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
	protected double valor;
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
	
	public double getFitness()
	{
		return valor;
	}
	
	protected abstract double getFenotipo(int i);
	
	public abstract Cruce[] getCruces();
	
	public abstract Mutacion[] getMutaciones();

	
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
		valor = getValor();
	}
	
	public List<Double> getFenotipo(){
		return fenotipo;
	}

	public void copiarIndividuo(Individuo ind) //Copia ind a this
	{
		for(int i = 0; i < genes.size(); i++)
			genes.get(i).copiarGen(ind.getGenes().get(i));
		recalcularFenotipo();
		if(valor == ind.getFitness())
			System.out.println("Si");
		else
			System.out.println("No");
	}
}
