package algoritmoGenetico;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.seleccion.Seleccion;

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
	private double prob_elite;
	
	private int num_variables;
	
	
	private double[] mejoresGeneracion;
	private double[] mejoresGlobales;
	private double[] mediaGeneracion;
	
	private Seleccion seleccion;
	private Cruce cruce;
	private Mutacion mutacion;
	
	//falta el porcentaje de elitismo
	// la condicion de finalizacion depende de la funcion usada? Debería estar aqui?
	
	public AlgoritmoGenetico() {// Aquí valores por defecto
		tam_pob = 100;
		num_max_gen = 10;
		prob_cruce = 10.3;
		prob_mutacion = 0.6;
		error_val = 0.01;
		prob_elite= 11.1;
		
		num_variables = 2; // está bien así?
		
	}
	
	
	public Individuo[] creaPoblacion(String problema) {
		//TODO
	
		Individuo [] poblacion = null;
		
		// if problema == 1...
		// poblacion = new IndividuoFuncion1...
		
		return poblacion;
	}
	
	
	public void run() {
		//TODO
				
		// 1. Evaluar p(t)
		evaluarPoblacion();
		
		// 2. While (no ha llegado al numero máximo de generaciones
		//				&& no se ha cumplido la condición de terminar){
		
		//		ordena pob
		
		// 		t++
		//		p(t) = Seleccion(p(t-1))
		// 		Reproduccion(p(t))
		// 		Mutacion(p(t))
		//		Evaluar(p(t))
		// 		Guarda el mejor Individuo, mejoresGlobales, mejoresGeneracion, mediaGeneracion
		
		// 		MainFrame.generaGrafica(mejoresGlobales, mejoresGeneracion, mediaGeneracion);
		
		//		generacionActual++;
		
		//		ordena pob
		
		// 3. MainFrame.setSol(generaSolucion());
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
	public double getProbElite() {
		return prob_elite;
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
	public void setProbElite(double probElite) {
		prob_elite = probElite;
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

		sol += "Valor de la función: " + elMejor.getFitness();
		
		return sol;
	}
	
	public void setVariables(int num) {
		num_variables = num;
	}
	
	public void evaluarPoblacion() {
		// Hace la funcion fitness para todos los elementos de la poblacion
		
		// saca la media de la generacion y la añade a la lista
		
		// saca el mejor de la generacion y lo añade a la lista
		
		// actualiza la lista mejor global si es necesario
		
		
	}
}
