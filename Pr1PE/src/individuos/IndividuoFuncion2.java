package individuos;

import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenBinario;

public class IndividuoFuncion2 extends Individuo {
	private final static String type = "2"; 
	
	public IndividuoFuncion2()
	{
		super(0);
		super.id = type;
	}
	
	public IndividuoFuncion2(double valorError)
	{
		super(valorError);
		super.id = type;
		GenBinario aux;
		Random rand = new Random();
		min.add(-10.0);															//x1
		max.add(10.0);
		aux = new GenBinario(tamGen(min.get(0), max.get(0)));
		aux.initializeGen(rand);
		genes.add(aux);  
		fenotipo.add(getFenotipo(0));
		min.add(10.0);															//x2
		max.add(10.0);
		aux = new GenBinario(tamGen(min.get(1), max.get(1)));
		aux.initializeGen(rand);
		genes.add(aux); 
		fenotipo.add(getFenotipo(1));
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		double sum1 = 0, sum2 = 0;
		for(int i = 1; i <= 5; i++)
		{
			sum1 += i * Math.cos((i + 1) * fenotipo.get(0) + i) + i;
			sum2 += i * Math.cos((i + 1) * fenotipo.get(1) + i) + i;
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

	public static Cruce[] getCruces()
	{
		return Cruce.getCrucesBin();
	}
	
	public static Mutacion[] getMutaciones()
	{
		return Mutacion.getMutacionesBin();
	}
	
	public static String[] getCrucesId()
	{
		return Cruce.getCrucesBinId();
	}
	
	public static String[] getMutacionesId()
	{
		return Mutacion.getMutacionesBinId();
	}
	
	protected Individuo[] parse(int tam, String[] datos) {
		IndividuoFuncion2[] ind = null;
		if(datos[0] == id)
		{
			ind = new IndividuoFuncion2[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoFuncion2();
			else
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoFuncion2(Double.parseDouble(datos[1]));
		}
		return ind;
	}
	
	@Override
	public boolean max() {
		return false;
	}
}
