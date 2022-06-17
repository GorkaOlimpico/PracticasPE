package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.GE.CruceCO;
import algoritmoGenetico.cruce.GE.CruceMonopunto;
import algoritmoGenetico.cruce.GE.CruceOX;
import algoritmoGenetico.cruce.GE.CruceOXPP;
import algoritmoGenetico.cruce.PG.CruceSubArboles;
import individuos.Individuo;

public abstract class Cruce implements Cloneable{
	protected String id;
	
	public Cruce() {}
	
	private static Cruce[] crucesPr2= {
			new CruceMonopunto(),

			new CruceOX(),
			new CruceOXPP(),
			
			new CruceCO(),
			//new CruceOPTIMO(),
	};
	
	public static Cruce[] getCrucesPr2()
	{
		return crucesPr2;
	}
	
	private static Cruce[] crucesPG= {
			new CruceSubArboles(),
	};
	
	public static Cruce[] getCrucesPG()
	{
		return crucesPG;
	}
	
	
	public String getId()
	{
		return id;
	}
	
	public static Cruce seleccionarCruce(String id, Individuo ind)
	{
		Cruce c = null;
		for(int i = 0; i < ind.getCruces().length && c == null; i++)
		{
			c = ind.getCruces()[i].parse(id);
		}
		return c;
	}
	
	protected abstract Cruce parse(String id);
	
	public void cruzar(Individuo[] ind, double prob)
	{
		Random rand = new Random();
		List<Individuo> paraCruzar = new ArrayList<>();
		for(int i = 0; i < ind.length; i++)
			if(rand.nextDouble() < prob)
				paraCruzar.add(ind[i]);
		
		while(paraCruzar.size() > 1)
		{
			int i = rand.nextInt(paraCruzar.size()), j = rand.nextInt(paraCruzar.size());
			while(i == j)
				j = rand.nextInt(paraCruzar.size());
			//System.out.print(1 + " Antes:" + paraCruzar.get(i).genToString());
			//System.out.print(2 + " Antes:" + paraCruzar.get(j).genToString() + "\n");
			cruzarIndividuos(paraCruzar.get(i), paraCruzar.get(j));
			//System.out.print(1 + " Despues:" + paraCruzar.get(i).genToString());
			//System.out.print(2 + " Despues:" + paraCruzar.get(j).genToString() + "\n\n");
			paraCruzar.get(i).recalcularFenotipo();
			paraCruzar.get(j).recalcularFenotipo();
			if(i < j)
			{
				paraCruzar.remove(j);
				paraCruzar.remove(i);
			}
			else
			{
				paraCruzar.remove(i);
				paraCruzar.remove(j);
			}
		}
	}
	
	protected abstract void cruzarIndividuos(Individuo i1, Individuo i2);
}
