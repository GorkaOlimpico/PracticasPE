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
	public List<Double> fenotipo;
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
	
	public Individuo()
	{
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
	
	public abstract double getFenotipo(int i);
	
	public abstract Cruce[] getCruces();
	
	public abstract Mutacion[] getMutaciones();
	
	private static Individuo[] individuos= { //Practica 1
			new IndividuoFuncion1(),
			new IndividuoFuncion2(),
			new IndividuoFuncion3(),
			new IndividuoFuncion4Bin(),
			new IndividuoFuncion4Real(),
	};
	
//	private static Individuo[] individuos= { //Practica 2
//			new IndividuoPr2(),
//	};
	
	public static String[] getStrings()
	{
		String[] s = new String[individuos.length];
		for(int i = 0; i < s.length; i++)
			s[i] = individuos[i].getId();
		return s;
	}
	
	public String getId() {
		return id;
	}


	public static Individuo[] seleccionarIndividuo(int tam, Object[] datos) //datos = id, valorError, n (si necesario) 	Practica 1
	{																		//datos = id, vuelos, TEL, tEspera			Practica 2
		Individuo ind[] = null;
		if(datos.length > 0)
			for(int i = 0; i < individuos.length && ind == null; i++)
			{
				ind = individuos[i].parse(tam, datos);
			}
		return ind;
	}
	
	protected abstract Individuo[] parse(int tam, Object[] datos);

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
		if(valor == ind.getFitness()) {//Se ha copiado bien
			//System.out.println("Si");
		}
		else
			System.out.println("No"); //No se ha copiado bien
	}
	
	public String getSolucion()
	{
		String s = "El valor de la funcion es: " + getFitness() + ", con los siguientes valores de x: ";
		for(int i = 0; i < genes.size(); i++)
			s += "x" + i + "(" + getFenotipo(i) + ") ";
		return s;
	}

	
}
