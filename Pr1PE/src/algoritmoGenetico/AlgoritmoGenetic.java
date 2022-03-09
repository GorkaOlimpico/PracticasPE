package algoritmoGenetico;

import algoritmoGenetico.seleccion.Seleccion;

import individuos.Individuo;

public class AlgoritmoGenetic {

	private Individuo[] poblacion;
	private int tam_pob;
	private int num_max_gen;
	private Individuo elMejor;
	private int pos_mejor;
	private double prob_cruce;
	private double prob_mutacion;
	private double precision;
	private double error_val;
	private double prob_elitismo;
	
	private Seleccion seleccion;
	//private Cruce cruce;
	//private Mutacion mutacion;
	
	//falta el porcentaje de elitismo
	// la condicion de finalizacion depende de la funcion usada? Debería estar aqui?
	
	public AlgoritmoGenetic() {//esto es de prueba
		tam_pob = 100;
		num_max_gen = 10;
		prob_cruce = 10.3;
		prob_mutacion = 0.6;
		error_val = 0.01;
		prob_elitismo= 11.1;
		
		
	}
	
	public AlgoritmoGenetic(int problema, int tam, int max_gen, float p_cruce, float p_mut, float prec) {
		tam_pob = tam;
		num_max_gen = max_gen;
		prob_cruce = p_cruce;
		prob_mutacion = p_mut;
		precision = prec;
		//falta hacer la opción por defecto de estos valores
		poblacion = creaPoblacion(problema);
	}
	
	public Individuo[] creaPoblacion(int problema) {
		//dado un tipo de Individuo (F1, F2, F3, F4) crea la población inicial
		
		Individuo [] poblacion = null;
		
		// if problema == 1...
		// poblacion = new IndividuoFuncion1...
		
		return poblacion;
	}
	
	
	public void run() {
		// 1. Generar poblacion inicial
		
		
		// 2. Evaluar p(t)
		
		// 3. While (no ha llegado al numero máximo de generaciones
		//				&& no se ha cumplido la condición de terminar){
		// 		t++
		//		p(t) = Seleccion(p(t-1))
		// 		Reproduccion(p(t))
		// 		Mutacion(p(t))
		//		Evaluar(p(t))
		// 		Guarda el mejor Individuo
		
	}
	
	public int getTamPob() {
		return tam_pob;
	}
	
	public String muestraMejor() {
		String resultado = "";
		
		// resultado = toString(elMejor.getPuntuacion());
		
		return resultado;
	}
}
