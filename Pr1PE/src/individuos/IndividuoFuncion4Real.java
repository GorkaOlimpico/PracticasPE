package individuos;

import java.util.Random;

import gen.GenReal;

public class IndividuoFuncion4Real extends Individuo {
	private final static String type = "1"; 
	private int n;
	
	public IndividuoFuncion4Real()
	{
		super(0);
		super.id = type;
	}
	
	public IndividuoFuncion4Real(double valorError, int n)
	{
		super(valorError);
		super.id = type;
		this.n = n;
		GenReal aux;
		Random rand = new Random();
		min.add(0.0);															
		max.add(Math.PI);
		for(int i = 0; i < n; i++)
		{
			aux = new GenReal();
			aux.initializeGen(rand, max.get(0), min.get(0));
			genes.add(aux); 
		}
	}

	@Override
	public double getValor() {
		double sum = 0;
		for(int i = 0; i < n; i++)
		{
			sum += Math.sin(getFenotipo(i)) * Math.pow(Math.sin(((i + 1) * getFenotipo(i) * getFenotipo(i)) / Math.PI), 20);
		}
		return sum;
	}

	@Override
	public double getFitness() {
		return getValor();
	}

	@Override
	protected double getFenotipo(int i) {
		return (double) genes.get(i).getAlelo(0);
	}
	
	protected Individuo[] parse(int tam, String[] datos) {
		IndividuoFuncion4Real[] ind = null;
		if(datos[0] == id)
		{
			ind = new IndividuoFuncion4Real[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoFuncion4Real();
			else
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoFuncion4Real(Double.parseDouble(datos[1]), Integer.parseInt(datos[2]));
		}
		return ind;
	}
	
	@Override
	public boolean max() {
		return false;
	}
}
