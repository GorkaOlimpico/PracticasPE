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
	
	protected List<List<Boolean>> generaEntradas(){
		List<List<Boolean>> listaTotal = new ArrayList<>();
		int tam_ejemplo = 6;												//Cada ejemplo es [A0, A1, D0, D1, D2, D3]
		listaTotal.add(new ArrayList<>());
		
		for(int i = 0; i < tam_ejemplo; i++)
			listaTotal.get(0).add(false);
		
		int j = 0;
		boolean llevar = false;
		while(!llevar)
		{
			ArrayList<Boolean> aux = new ArrayList<>();
			llevar = true;
			int i = 0;
			for(int k = 0; k < tam_ejemplo; k++)
				aux.add(listaTotal.get(j).get(k));
			
			while(llevar && i < tam_ejemplo)
			{
				if(!aux.get(i))
				{
					aux.set(i, true);
					llevar = false;
				}
				else
					aux.set(i, false);
				i++;
			}
			j++;
			if(!llevar)
				listaTotal.add(aux);
		}
		return listaTotal;
	}
	
	public abstract String solutionToString();

	protected static boolean[] solucion = {
			false, 	false, 	false, 	false,
			false, 	false, 	false, 	false,
			true, 	true,	true, 	true,
			true, 	true, 	true, 	true,
			false, 	false, 	false, 	false,
			true, 	true, 	true, 	true,
			false, 	false, 	false, 	false,
			true, 	true,	true, 	true,
			false, 	false, 	true, 	true,
			false, 	false, 	true, 	true,
			false, 	false, 	true, 	true,
			false, 	false, 	true, 	true,
			false, 	true, 	false, 	true,
			false, 	true, 	false, 	true,
			false, 	true, 	false, 	true,
			false, 	true, 	false, 	true,
	};
}
