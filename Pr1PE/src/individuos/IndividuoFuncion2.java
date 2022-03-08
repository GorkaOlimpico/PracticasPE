package individuos;

import gen.GenBinario;

public class IndividuoFuncion2 extends Individuo {

	public IndividuoFuncion2(double valorError)
	{
		super(valorError);
		min.add(-10.0);															//x1
		max.add(10.0);
		genes.add(new GenBinario(tamGen(min.get(0), max.get(0)))); 
		min.add(10.0);															//x2
		max.add(10.0);
		genes.add(new GenBinario(tamGen(min.get(1), max.get(1)))); 
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1), sum1 = 0, sum2 = 0;
		for(int i = 1; i <= 5; i++)
		{
			sum1 += i * Math.cos((i + 1) * x1 + i) + i;
			sum2 += i * Math.cos((i + 1) * x2 + i) + i;
		}
		return sum1 * sum2;
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
		
		for(int j = genes.get(i).getLongitud() - 1; j >= 0; j--)
		{
			if((boolean) genes.get(i).getAlelo(j))
				alelo = 1;
			else
				alelo = 0;	
			
			fenotipo += potencia * alelo;
			potencia *= 2;
		}
		return ((fenotipo * valorError) + min.get(i)) % (max.get(i) - min.get(i));
	}

}
