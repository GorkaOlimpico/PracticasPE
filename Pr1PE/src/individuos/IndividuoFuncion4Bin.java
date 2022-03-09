package individuos;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenBinario;

public class IndividuoFuncion4Bin extends Individuo {
	private int n;
	private int tam;
	private final static String id = "4-Bin";  
	
	public IndividuoFuncion4Bin()
	{
		super(0, id);
	}
	
	public IndividuoFuncion4Bin(double valorError, int n)
	{
		super(valorError, id);
		this.n = n;
		min.add(0.0);															
		max.add(Math.PI);
		int tam = tamGen(min.get(0), max.get(0));
		for(int i = 0; i < n; i++)
		{
			genes.add(new GenBinario(tam));
			fenotipo.add(getFenotipo(i));
		}
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		double sum = 0;
		for(int i = 0; i < n; i++)
		{
			sum += Math.sin(getFenotipo(i)) * Math.pow(Math.sin(((i + 1) * getFenotipo(i) * getFenotipo(i)) / Math.PI), 20);
		}
		return sum;
	}
	
	public double getFitness()
	{
		return getValor();
	}
	
	protected double getFenotipo(int i)
	{
		double fenotipo = 0;
		int potencia = 1;
		int alelo;
		
		for(int j = tam - 1; j >= 0; j--)
		{
			if((boolean) genes.get(i).getAlelo(j))
				alelo = 1;
			else
				alelo = 0;	
			
			fenotipo += potencia * alelo;
			potencia *= 2;
		}
		return ((fenotipo * valorError) + min.get(0)) % (max.get(0) - min.get(0));
	}

	public static Cruce[] getCruces()
	{
		return Cruce.getCrucesBin();
	}
	
	public static Mutacion[] getMutaciones()
	{
		return Mutacion.getMutacionesBin();
	}
	
	protected Individuo[] parse(int tam, String[] datos) {
		IndividuoFuncion4Bin[] ind = null;
		if(datos[0] == id)
		{
			ind = new IndividuoFuncion4Bin[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoFuncion4Bin();
			else
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoFuncion4Bin(Double.parseDouble(datos[1]), Integer.parseInt(datos[2]));
		}
		return ind;
	}
}
