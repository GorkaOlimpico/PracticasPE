package individuos;

import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenBinario;

public class IndividuoFuncion3 extends Individuo {
	private final static String type = "3"; 
	
	public IndividuoFuncion3()
	{
		super.id = type;
	}
	
	public IndividuoFuncion3(double valorError)
	{
		super(valorError);
		super.id = type;
		GenBinario aux;
		Random rand = new Random();
		min.add(-512.0);															//x1
		max.add(512.0);
		aux = new GenBinario(tamGen(min.get(0), max.get(0)));
		aux.initializeGen(rand);
		genes.add(aux); 
		fenotipo.add(getFenotipo(0));
		min.add(-512.0);															//x1
		max.add(512.0);
		aux = new GenBinario(tamGen(min.get(1), max.get(1)));
		aux.initializeGen(rand);
		genes.add(aux); 
		fenotipo.add(getFenotipo(1));
		valor = getValor();
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		double sum1 = 0, sum2 = 0;
		sum1 = -(fenotipo.get(1) + 47) * Math.sin(Math.sqrt(Math.abs(fenotipo.get(1) + (fenotipo.get(0) / 2) + 47)));
		sum2 = fenotipo.get(0) * Math.sin(Math.sqrt(Math.abs(fenotipo.get(0) - (fenotipo.get(1) + 47))));
		return sum1 - sum2;
	}
	
	public double getFenotipo(int i)
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

		double valor = min.get(i) + fenotipo*((max.get(i) - min.get(i))/(Math.pow(2,genes.get(i).getLongitud()) - 1));

		return valor;
	}

	public Cruce[] getCruces()
	{
		return Cruce.getCrucesBin();
	}
	
	public Mutacion[] getMutaciones()
	{
		return Mutacion.getMutacionesBin();
	}
	
	protected Individuo[] parse(int tam, Object[] datos) {
		IndividuoFuncion3[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoFuncion3[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoFuncion3();
			else
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoFuncion3((double) datos[1]);
		}
		return ind;
	}
	
	@Override
	public boolean max() {
		return false;
	}
}
