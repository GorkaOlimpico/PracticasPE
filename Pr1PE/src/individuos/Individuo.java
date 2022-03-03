package individuos;

import java.util.List;

import gen.Gen;

public abstract class Individuo<T> {
	//protected List<Gen> genes;
	protected T[] genes;
	private float fenotipo;
	private float aptitud;
	private float puntuacion;
	private float punt_acumulada;
	private int longitud;
}
