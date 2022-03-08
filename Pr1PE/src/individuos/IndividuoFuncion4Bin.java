package individuos;

import gen.GenBinario;

public class IndividuoFuncion4Bin extends Individuo {
	private int n;
	private int tam;
	
	public IndividuoFuncion4Bin(double valorError, int n)
	{
		super(valorError);
		this.n = n;
		min.add(0.0);															
		max.add(Math.PI);
		int tam = tamGen(min.get(0), max.get(0));
		for(int i = 0; i < n; i++)
			genes.add(new GenBinario(tam));
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		double x, sum = 0;
		for(int i = 0; i < n; i++)
		{
			x = getFenotipo(i);
			sum += Math.sin(x) * Math.pow(Math.sin(((i + 1) * x * x) / Math.PI), 20);
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

}
