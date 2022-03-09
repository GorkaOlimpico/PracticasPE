package individuos;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenBinario;

public class IndividuoFuncion3 extends Individuo {

	public IndividuoFuncion3(double valorError)
	{
		super(valorError);
		min.add(-512.0);															//x1
		max.add(512.0);
		genes.add(new GenBinario(tamGen(min.get(0), max.get(0)))); 
		min.add(-512.0);															//x1
		max.add(512.0);
		genes.add(new GenBinario(tamGen(min.get(1), max.get(1)))); 
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1), sum1 = 0, sum2 = 0;
		sum1 = -(x2 + 47) * Math.sin(Math.sqrt(Math.abs(x2 + (x1 / 2) + 47)));
		sum2 = -x1 * Math.sin(Math.sqrt(Math.abs(x1 - (x2 + 47))));
		return sum1 + sum2;
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

	public static Cruce[] getCruces()
	{
		return Cruce.getCrucesBin();
	}
	
	public static Mutacion[] getMutaciones()
	{
		return Mutacion.getMutacionesBin();
	}
}
