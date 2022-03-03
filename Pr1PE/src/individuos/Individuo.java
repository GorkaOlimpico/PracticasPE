package individuos;

import java.util.List;

import gen.Gen;

public abstract class Individuo {
	protected List<Gen> genes;
	private float fenotipo;
	private float aptitud;
	private float puntuacion;
	private float punt_acumulada;
	private int longitud;
}
