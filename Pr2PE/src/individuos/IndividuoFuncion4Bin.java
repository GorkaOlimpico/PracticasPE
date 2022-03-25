package individuos;

import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenBinario;

public class IndividuoFuncion4Bin extends Individuo {
	private int n;
	private int tam;
	private final static String type = "4-Bin"; 
	
	public IndividuoFuncion4Bin()
	{
		super.id = type;
	}
	
	public IndividuoFuncion4Bin(double valorError, int n)
	{
		super(valorError);
		super.id = type;
		this.n = n;
		GenBinario aux;
		Random rand = new Random();
		min.add(0.0);															
		max.add(Math.PI);
		tam = tamGen(min.get(0), max.get(0)); //Como todas tienen el mismo intervalo su tamaño de codificacion es el mismo
		for(int i = 0; i < n; i++)
		{
			aux = new GenBinario(tam);
			aux.initializeGen(rand);
			genes.add(aux); 
			fenotipo.add(getFenotipo(i));
		}
		valor = getValor();
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		double sum = 0;
		for(int i = 1; i <= n; i++)
		{
			sum += Math.sin(fenotipo.get(i - 1)) * Math.pow(Math.sin(((i + 1) * fenotipo.get(i - 1) * fenotipo.get(i - 1)) / Math.PI), 20);
		}
		return -sum;
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
		double valor = min.get(0) + fenotipo*((max.get(0) - min.get(0))/(Math.pow(2,genes.get(0).getLongitud()) - 1));

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
		IndividuoFuncion4Bin[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoFuncion4Bin[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoFuncion4Bin();
			else
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoFuncion4Bin((double) datos[1], (int) datos[2]);
		}
		return ind;
	}
	
	@Override
	public boolean max() {
		return false;
	}
	
	public double getMax(int i) {return max.get(0);}
	
	public double getMin(int i) {return min.get(0);}
}
