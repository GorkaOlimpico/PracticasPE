package individuos;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import arbol.Arbol;
import gen.Gen;

public abstract class Individuo {
	protected Object genes;
	protected int fenotipo;
	protected double valor;
	protected String id;  
	protected boolean multiplexor6;
	List<List<Boolean>> entradas;
	
	public Individuo(boolean m6)
	{
		multiplexor6 = m6;
		genes = new ArrayList<>();
		entradas = generaEntradas();
		fenotipo = -1;
	}
	
	public Individuo()
	{
		genes = new ArrayList<>();
		entradas = generaEntradas();
		fenotipo = -1;
	}
	

	
	public abstract boolean max();
	
	public abstract double getValor();
	
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


	public static Individuo[] seleccionarIndividuo(int tam, Object[] datos) 	
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
		int tam_ejemplo = -1;
		if(multiplexor6) {
			tam_ejemplo = 6;
		}
		else {
			tam_ejemplo = 11;
		}
														//Cada ejemplo es [A0, A1, D0, D1, D2, D3]
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

	protected static boolean solucionar6(List<Boolean> c)
	{
		if(c.get(0))	//A0
			if(c.get(1))	//A1
				return c.get(5);	//D3
			else
				return c.get(3);	//D1
		else
			if(c.get(1))	//A1
				return c.get(4);	//D2
			else
				return c.get(2);	//d0
	}
	
	protected static boolean solucionar11(List<Boolean> c)
	{		
		if(c.get(0))	//A0
			if(c.get(1))	//A1
				if(c.get(2))	//A2
					return c.get(10);	//D7
				else
					return c.get(6);	//D3
			else
				if(c.get(2))	//A2
					return c.get(8);	//D5
				else
					return c.get(4);	//D1
		else
			if(c.get(1))	//A1
				if(c.get(2))	//A2
					return c.get(9);	//D6
				else
					return c.get(5);	//D2
			else
				if(c.get(2))	//A2
					return c.get(7);	//D4
				else
					return c.get(3);	//D0
	}
	
	public void setGenes(Object aux) {
		genes = aux;
	}

	public void bloating(Individuo[] poblacion) {}

	public void setBloating(double k) {}

	public abstract List<String> getSolucion();
}
