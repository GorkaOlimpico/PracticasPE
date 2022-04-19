package individuos;

import java.util.ArrayList;

import java.util.List;
import java.util.Random;


import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.Gen;
import gen.GenPr2;
import gramatica.Gramatica;


public class IndividuoPr3 extends Individuo {

	private final static String type = "Practica 3"; 
	private List<Integer> solucion; //Necesito la solucion puesta con los valores?
	private Gramatica gramatica;
	
	public IndividuoPr3() {
		super.id = type;

		String texto_gramatica = archivoATexto(nombreArchivo);
		
		gramatica = new Gramatica(n_wraps, texto_gramatica);
		
		Random rand = new Random();
		GenPr2 aux = new GenPr2(n);
		aux.initializeGen(rand);
		genes.add(aux);
		recalcularFenotipo();
	}
	}

	@Override
	public boolean max() {
		return false;
	}

	
	
	@Override
	public double getValor() {

		double suma = 0;
		
		return suma;
	}
	
	
	@Override
	public double getFenotipo(int i) {
		return 0;
	}

	@Override
	public Cruce[] getCruces() {
		return Cruce.getCrucesPr2();
	}

	@Override
	public Mutacion[] getMutaciones() {
		return Mutacion.getMutacionesPr2();
	}

	@Override
	protected Individuo[] parse(int tam, Object[] datos) {
		IndividuoPr2[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoPr2[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoPr2();
			else
			{
				for(int i = 0; i < tam; i++)
					ind[i] = nuevoInd(datos);
			}
		}
		return ind;
	}
	
	public IndividuoPr2 nuevoInd(Object[] datos) {
		IndividuoPr2 individuo = new IndividuoPr2();
		
		List<Pair<Integer, String>> vuelos = (List<Pair<Integer, String>>) datos[1];	
		List<List<Double>> TEL = (List<List<Double>>) datos[2];									
		List<List<Double>> tEspera = (List<List<Double>>) datos[3];
		
		individuo = new IndividuoPr2(vuelos, TEL, tEspera);
		
		return individuo;
	}

	@Override
	public String solutionToString() {
		String s = "El valor de la funcion es: " + super.getFitness() + "\n";
		s += "Gen: ";
		for(int i = 0; i < n; i++)
			s += ((int) genes.get(0).getAlelo(i) + 1) + " ";
		s += "\n";
		int i = 1;
		for(List<Pair<Integer, Double>> pista : solucion) 
		{
			s += "Pista " + i + ":\n";
			for(Pair<Integer, Double> avion : pista) 
			{
				s += "Vuelo: "+ (avion.getFirst() + 1) + "\t Nombre: " + vuelos.get(avion.getFirst()).getSecond() + "\t TLA: " + avion.getSecond() + "\n";
			}
			s += "\n";
			i++;
		}
		return s;
	}
	
	public String genToString()
	{
		String s = "";
		for(int i = 0; i < n; i++)
			s += ((int) genes.get(0).getAlelo(i) + 1) + " ";
		return s + "\n";
	}

	@Override
	public List<List<Pair<Integer, Double>>> getSolucion(){
		return solucion;
	}
	
	private double asignaAPistaTLA(int avion, int numPista) {
		double retardo = 0;
		double aux_tla = -1;
		double tel = TEL.get(numPista).get((int) avion);
		double tlaAnterior = ultimoTLA(numPista);
		
		int tipoAnterior = -1;
		if(solucion.get(numPista).size() > 0) {
			int avionAnterior = solucion.get(numPista).get(solucion.get(numPista).size()-1).getFirst();
			tipoAnterior = vuelos.get(avionAnterior).getFirst();
		}
		int tipoActual = vuelos.get(avion).getFirst();
		if(tipoActual == -1) {
			System.out.println("tipoactual = -1");
		}
		
		double diferencia = diferencia(tipoActual, tipoAnterior);
		
		if(tel >= tlaAnterior + diferencia) {
			aux_tla = tel;
		}
		else {
			aux_tla = tlaAnterior + diferencia;
		}
		
		retardo = Math.pow(((double) aux_tla - tel), 2);
		
		return aux_tla;
	}
}
