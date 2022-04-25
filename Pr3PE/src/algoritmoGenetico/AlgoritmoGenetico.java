package algoritmoGenetico;

import java.io.File;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.seleccion.Seleccion;
import gramatica.Gramatica;
import gui.MainFrame;
import individuos.Individuo;
import individuos.Pair;


public class AlgoritmoGenetico {

	private Individuo[] poblacion;
	private int tam_pob;
	private int num_max_gen;
	private Individuo elMejor;
	private double prob_cruce;
	private double prob_mutacion;
	private double elite;
	private String problema;
	private int longitud;
	private int n_wraps;
	private String nombreArchivo;
	
	
	private double[] mejoresGeneracion;
	private double[] mejoresGlobales;
	private double[] mediaGeneracion;
	private int generacionActual;
	
	private Seleccion seleccion;
	private Cruce cruce;
	private Mutacion mutacion;
		
	public AlgoritmoGenetico(String tipoInicializacion) {
		
	}
	
	public AlgoritmoGenetico(String nombreArchivo, String wraps, String longitud) 
	{		
		tam_pob = 100;
		num_max_gen = 100;
		prob_cruce = 60;
		prob_mutacion = 5;
		elite= 2;
	
		problema = "Practica 3";
		
		this.nombreArchivo = nombreArchivo;
		this.n_wraps = Integer.parseInt(wraps);
		this.longitud = Integer.parseInt(longitud);


		generacionActual = 0;
	}
	
	public Individuo[] creaPoblacion(String problema, int tam) {		
		this.problema = problema;
		return Individuo.seleccionarIndividuo(tam, new Object[]{problema, longitud, n_wraps, nombreArchivo});
	}
	
	
	
	public void run() {
	
		
		// Inicializo elMejor con el primer individuo de la poblacion
		poblacion = creaPoblacion(problema, tam_pob);
		ordenarPoblacion();
		elMejor = creaPoblacion(problema,(int) 1)[0];
		elMejor.copiarIndividuo(poblacion[0]);
		generacionActual = 0;
		mejoresGeneracion = new double[num_max_gen + 1]; //Hay num_max_gen generaciones + la generacion 0 (la creada de forma aleatoria)
		mejoresGlobales = new double[num_max_gen + 1];
		mediaGeneracion = new double[num_max_gen + 1];
		// 1. Evaluar p(t)
		// 		Guarda el mejor Individuo, mejoresGlobales, mejoresGeneracion, mediaGeneracion
		poblacion[0].bloating(poblacion);
		evaluarPoblacion();
		
		Individuo[] elites = creaPoblacion(problema, (int) (tam_pob * elite / 100));
		Individuo[] poblacionAux = creaPoblacion(problema, tam_pob);
		// 2. While (no ha llegado al numero m�ximo de generaciones
		//				&& no se ha cumplido la condici�n de terminar){
		
		/*for(Individuo pob:poblacion) {
			System.out.println(pob.getFitness());
		}*/

		while(generacionActual < num_max_gen) {
			
			generacionActual++;
			
			sacarElites(elites);
			
			seleccion.select(poblacion, poblacionAux);
			
			cruce.cruzar(poblacion, prob_cruce / 100);
			
			mutacion.mutar(poblacion, prob_mutacion / 100);
			
			poblacion[0].bloating(poblacion);
			
			ordenarPoblacion();
			meterElites(elites);
			
			evaluarPoblacion();
		}
		
		MainFrame.imprimeGrafica(mejoresGlobales, mejoresGeneracion, mediaGeneracion);
		
		MainFrame.setSolucion(generaSolucion(), elMejor.solutionToString());
	}
	
	private void sacarElites(Individuo[] e) //Guarda los mejores antes del proceso de seleccion, cruce y mutacion
	{
		for(int i = 0; i < e.length; i++)
		{
			e[i].copiarIndividuo(poblacion[i]);
		}
	}
	
	private void meterElites(Individuo[] e) //Sustituye los mejores de la generacion anterior por los peores de la actual
	{
		for(int i = 0; i < e.length; i++)
		{
			poblacion[poblacion.length - i - 1].copiarIndividuo(e[i]);
		}
		ordenarPoblacion();
	}
	
	public int getTamPoblacion() {
		return tam_pob;
	}
	public int getMaxGen() {
		return num_max_gen;
	}
	public double getProbCruce() {
		return prob_cruce;
	}
	public double getProbMutacion() {
		return prob_mutacion;
	}
	public double getElite() {
		return elite;
	}
	public Seleccion getSeleccion() {
		return seleccion;
	}
	public Cruce getCruce() {
		return cruce;
	}
	public Mutacion getMutacion() {
		return mutacion;
	}
	
	public void setTamPoblacion(int tamPob) {
		tam_pob = tamPob;
	}
	public void setMaxGen(int maxGen) {
		num_max_gen = maxGen;
	}
	public void setProbCruce(double probCruce) {
		prob_cruce = probCruce;
	}
	public void setProbMutacion(double probMut) {
		prob_mutacion = probMut;
	}
	public void setElite(double elite) {
		this.elite = elite;
	}
	public void setSeleccion(Seleccion seleccion) {
		this.seleccion = seleccion;
	}
	public void setCruce(Cruce cruce) {
		this.cruce = cruce;
	}
	public void setMutacion(Mutacion mutacion) {
		this.mutacion = mutacion;
	}
	public void setProblema(String problema) {
		this.problema = problema;
	}
	public void setLongitud(String longitud)
	{
		this.longitud = Integer.parseInt(longitud);
	}
	public void setWraps(String wraps)
	{
		this.n_wraps = Integer.parseInt(wraps);
	}
	public void setNombreArchivo(String nombreArchivo)
	{
		this.nombreArchivo = nombreArchivo;
	}
	
	public String generaSolucion() {
		String sol = "";
		
//		int x = 0;
//		for(double fenotipo:elMejor.getFenotipo()) {
//			sol += "X" + x + " = " + fenotipo + ", ";
//			x++;
//		}

		sol += "Valor de la funci�n: " + elMejor.getFitness();
		System.out.print(elMejor.solutionToString());
		return sol;
	}
	
	private void ordenarPoblacion()
	{
		List<Double> fitness = new ArrayList<>();
		for(int i = 0; i < poblacion.length; i++)
			fitness.add(poblacion[i].getFitness());
		
		for(int i = 0; i < fitness.size() - 1; i++)
		{
			int max = i;
			for(int j = i + 1; j < fitness.size(); j++)
			{
				if(poblacion[0].max())
				{
					if(fitness.get(j) > fitness.get(max))
						max = j;
				}
				else
				{
					if(fitness.get(j) < fitness.get(max))
						max = j;
				}
			}
			double aux1 = fitness.get(i);
			fitness.set(i, fitness.get(max));
			fitness.set(max, aux1);
			
			Individuo aux2 = poblacion[i];
			poblacion[i] = poblacion[max];
			poblacion[max] = aux2;
		}
	}
	
	public void evaluarPoblacion() 
	{
		double media = 0;
		double fitness;
		Individuo elMejorGeneracion = creaPoblacion(problema,(int) 1)[0];
		elMejorGeneracion.copiarIndividuo(poblacion[0]); // Aqu� selecciona el mejor de la generaci�n
		//int max_aux = 0;
		//for(int i = 0; i < poblacion.length; i++)
		
		for(Individuo ind:poblacion) 
		{
			fitness = ind.getFitness();
			media += fitness;
			
		}
		media = media / poblacion.length;
//		elMejorGeneracion.copiarIndividuo(poblacion[max_aux]);
		
		if(elMejor.max())
		{
			if(elMejorGeneracion.getFitness() > elMejor.getFitness()) {
				elMejor = elMejorGeneracion;
				//System.out.println("fenotipo 0: " + elMejor.fenotipo.get(0));
				//System.out.println("fenotipo 1: " + elMejor.fenotipo.get(1));
			}
		}
		else if(elMejorGeneracion.getFitness() <= elMejor.getFitness()) {
				elMejor = elMejorGeneracion;
				//System.out.println("fenotipo 0: " + elMejor.fenotipo.get(0));
				//System.out.println("fenotipo 1: " + elMejor.fenotipo.get(1));
		}
		
		mejoresGlobales[generacionActual] = elMejor.getFitness();
		mejoresGeneracion[generacionActual] = elMejorGeneracion.getFitness();
		mediaGeneracion[generacionActual] = media;
	}
	
	
}
