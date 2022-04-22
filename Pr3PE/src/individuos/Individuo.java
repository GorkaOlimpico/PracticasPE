package individuos;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.Gen;

public abstract class Individuo {
	protected Object genes;
	protected int fenotipo;
	protected double valor;
	protected double valorError;
	protected String id;  
	
	public Individuo(double valorError)
	{
		this.valorError = valorError;
		genes = new ArrayList<>();

		fenotipo = -1;
	}
	
	public Individuo()
	{
		genes = new ArrayList<>();

		fenotipo = -1;
	}
	

	
	public abstract boolean max();
	
	public abstract int getValor();
	
	public double getFitness()
	{
		return valor;
	}
	
	public abstract double getFenotipo(int i);
	
	public abstract Cruce[] getCruces();
	
	public abstract Mutacion[] getMutaciones();
	
	private static Individuo[] individuos= { 
			new IndividuoGE(),
			new IndividuoPG(),
	};
	
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


	public static Individuo[] seleccionarIndividuo(int tam, Object[] datos) 	//datos = id, vuelos, TEL, tEspera
	{																					
		Individuo ind[] = null;
		if(datos.length > 0)
			for(int i = 0; i < individuos.length && ind == null; i++)
			{
				ind = individuos[i].parse(tam, datos);
			}
		return ind;
	}
	
	protected abstract Individuo[] parse(int tam, Object[] datos);

	public Object getGenes() {
		return genes;
	}


	public void recalcularFenotipo() {
		valor = getValor();			
	}
	
	public abstract String genToString();
	
	public int getFenotipo(){
		return fenotipo;
	}

	public abstract void copiarIndividuo(Individuo ind); //Copia ind a this
	
	
	public abstract String solutionToString();

}
