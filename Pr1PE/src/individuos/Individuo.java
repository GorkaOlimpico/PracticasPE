package individuos;

import java.util.ArrayList;
import java.util.List;

import gen.Gen;
import gen.GenBinario;

public abstract class Individuo {
	protected List<Gen> genes;
	private float aptitud;
	private float puntuacion;
	private float punt_acumulada;
	protected List<Double> min;
	protected List<Double> max;
	protected double valorError;
	
	public Individuo(double valorError)
	{
		this.valorError = valorError;
		genes = new ArrayList<>();
		min = new ArrayList<>();
		max = new ArrayList<>();
	}
	
	public abstract double getValor();
	
	public abstract double getFitness();
	
	protected abstract double getFenotipo(int i);
	
	//Creo que no es necesario
	//protected abstract void setGen(int i, double gen);
}
