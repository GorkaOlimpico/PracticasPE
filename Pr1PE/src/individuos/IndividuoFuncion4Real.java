package individuos;

import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenReal;

public class IndividuoFuncion4Real extends Individuo {
	private final static String type = "4-Real"; 
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
			fenotipo.add(getFenotipo(i));
		}
		valor = getValor();
	}

	@Override
	public double getValor() {
		double sum = 0;
		for(int i = 1; i <= n; i++)
		{
			sum += Math.sin(fenotipo.get(i - 1)) * Math.pow(Math.sin(((i + 1) * fenotipo.get(i - 1) * fenotipo.get(i - 1)) / Math.PI), 20);
		}
		return -sum;
	}

	@Override
	public double getFenotipo(int i) {
		// Hay que evitar que los cruces o mutaciones saquen del rango permitido
		double valor = (double) genes.get(i).getAlelo(0);
		if(valor > max.get(0)) {
			valor = max.get(0);
		}
		else if (valor < min.get(0)) {
			valor = min.get(0);
		}
		return valor;
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
	
	public double getMax(int i) {return max.get(0);}
	
	public double getMin(int i) {return min.get(0);}

	@Override
	public Cruce[] getCruces() {
		return Cruce.getCrucesReal();
	}

	@Override
	public Mutacion[] getMutaciones() {
		return Mutacion.getMutacionesReal();
	}
}
