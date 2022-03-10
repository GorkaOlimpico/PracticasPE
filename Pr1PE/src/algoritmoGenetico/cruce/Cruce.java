package algoritmoGenetico.cruce;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import individuos.Individuo;

public abstract class Cruce implements Cloneable{
	protected String id;
	
	public Cruce() {}
	
	private static Cruce[] crucesBin= {
			new CruceMonopuntoBin(),
			new CruceUniforme(),
	};
	
	public static Cruce[] getCrucesBin()
	{
		return crucesBin;
	}
	
	public static String[] getCrucesBinId()
	{
		String[] s = new String[crucesBin.length];
		for(int i = 0; i < s.length; i++)
			s[i] = crucesBin[i].getId();
		return s;
	}
	
	private static Cruce[] crucesReal= {
			new CruceMonopuntoReal(),
			new CruceDiscretoUniforme(),
			new CruceAritmetico(),
			new CruceBLX(),
	};
	
	public static Cruce[] getCrucesReal()
	{
		return crucesReal;
	}
	
	public static String[] getCrucesRealId()
	{
		String[] s = new String[crucesReal.length];
		for(int i = 0; i < s.length; i++)
			s[i] = crucesReal[i].getId();
		return s;
	}
	
	protected String getId()
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
			cruzarIndividuos(paraCruzar.get(i), paraCruzar.get(j));
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
