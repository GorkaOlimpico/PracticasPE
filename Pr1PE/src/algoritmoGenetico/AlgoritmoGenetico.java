package algoritmoGenetico;

import java.util.ArrayList;
import java.util.List;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.seleccion.Seleccion;
import gui.MainFrame;
import individuos.Individuo;

public class AlgoritmoGenetico {

	private Individuo[] poblacion;
	private int tam_pob;
	private int num_max_gen;
	private Individuo elMejor;
	private int pos_mejor;
	private double prob_cruce;
	private double prob_mutacion;
	private double precision;
	private double error_val;
	private double elite;
	
	private String problema;
	private int num_variables;
	
	
	private double[] mejoresGeneracion;
	private double[] mejoresGlobales;
	private double[] mediaGeneracion;
	
	private Seleccion seleccion;
	private Cruce cruce;
	private Mutacion mutacion;
	
	//falta el porcentaje de elitismo
	// la condicion de finalizacion depende de la funcion usada? Deber�a estar aqui?
	
	public AlgoritmoGenetico() {// Aqu� valores por defecto
		tam_pob = 100;
		num_max_gen = 10;
		prob_cruce = 10.3;
		prob_mutacion = 0.6;
		error_val = 0.01;
		elite= 11.1;
		
		num_variables = 2; // est� bien as�?
		
	}
	
	
	private Individuo[] creaPoblacion(String problema) {		
		this.problema = problema;
		return Individuo.seleccionarIndividuo(tam_pob, new String[]{problema, Double.toString(error_val), Integer.toString(num_variables)});
	}
	
	private void ordenarPoblacion()
	{
		List<Double> fitness = new ArrayList<>();
		for(int i = 0; i < fitness.size(); i++)
			fitness.add(poblacion[i].getFitness());
		
		for(int i = 0; i < fitness.size() - 1; i++)
		{
			int max = i;
			for(int j = i; j < fitness.size(); j++)
			{
				if(poblacion[0].max())
				{
					if(fitness.get(j) > fitness.get(max))
						max = j;
				}
				else
					if(fitness.get(j) < fitness.get(max))
						max = j;
					
			}
			double aux1 = fitness.get(i);
			fitness.set(i, fitness.get(max));
			fitness.set(max, aux1);
			
			Individuo aux2 = poblacion[i];
			poblacion[i] = poblacion[max];
			poblacion[max] = aux2;
		}
	}

	public void run() {
		//TODO
				
		// 1. Evaluar p(t)
		// 		Guarda el mejor Individuo, mejoresGlobales, mejoresGeneracion, mediaGeneracion
		evaluarPoblacion();
		
		// 2. While (no ha llegado al numero m�ximo de generaciones
		//				&& no se ha cumplido la condici�n de terminar){
		while((mediaGeneracion.length < num_max_gen)) {
			// cumplir la condicion de terminar quiere decir llegar al m�ximo de la funcion en cada problema?
		//		ordena pob
		
		// 		t++
		//		p(t) = Seleccion(p(t-1))
			Individuo[] poblacionAux = creaPoblacion(problema); // revisar esto. Solo lo he puesto as� por comodidad
			seleccion.select(poblacion, poblacionAux);
			
		// 		Reproduccion(p(t))
			cruce.cruzar(poblacion, prob_cruce);
			
		// 		Mutacion(p(t))
			mutacion.mutar(poblacion, prob_mutacion);
			
		//		Evaluar(p(t))
		// 		Guarda el mejor Individuo, mejoresGlobales, mejoresGeneracion, mediaGeneracion
			evaluarPoblacion();
			
		
		//		Actualiza la gr�fica
			MainFrame.generaGrafica(mejoresGlobales, mejoresGeneracion, mediaGeneracion);
		
		// Creo que no hace falta actualizar la generacion porque en mediaGeneracion siempre se a�ade un elemento al final
		
			//		ordena pob
		}
		
		MainFrame.setSolucion(generaSolucion());
	}
	

	
	public int getTamPoblacion() {
		return tam_pob;
	}
	public int getMaxGen() {
		return num_max_gen;
	}
	public double getErrorVal() {
		return error_val;
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
	public void setErrorVal(double errorVal) {
		error_val = errorVal;
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
	
	public String generaSolucion() {
		String sol = "";
		
		int x = 0;
		for(double fenotipo:elMejor.getFenotipo()) {
			sol += "X" + x + " = " + fenotipo + ", ";
		}

		sol += "Valor de la funci�n: " + elMejor.getFitness();
		
		return sol;
	}
	
	public void setVariables(int num) {
		num_variables = num;
	}
	
	public void evaluarPoblacion() {
		double media = 0;
		double fitness;
		double elMejorGeneracion = 0;
		
		for(Individuo ind:poblacion) {
			fitness= ind.getFitness();
			media += fitness;
			if(fitness > elMejorGeneracion) {
				elMejorGeneracion = fitness;
				mejoresGeneracion[mejoresGeneracion.length] = elMejorGeneracion;
				
				if(fitness >= elMejor.getFitness()) {
					elMejor = ind;
					mejoresGlobales[mejoresGlobales.length] = elMejor.getFitness();
				}
			}
			
		}
		
		media = media/poblacion.length;
		mediaGeneracion[mediaGeneracion.length] = media;
		
	}
}
