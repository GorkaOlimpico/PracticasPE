package individuos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenPr2;

public class IndividuoPr2 extends Individuo {
	private int n;	//nº vuelos
	private int m;	//nº pistas
	private final static String type = "Practica 2"; 
	private List<List<Pair<Integer, Double>>> solucion; //Pista, (vuelo, TLA)
	private List<Pair<Integer, String>> vuelos;			//Tipo de vuelo: W = 0, G = 1, P = 2; Nombre
	private List<List<Double>> TEL;						// m x n
	private double[][] tEspera;							// m x m
	
	public IndividuoPr2(List<Pair<Integer, String>> vuelos, List<List<Double>> TEL, double[][] tEspera) {
		super.id = type;
		n = TEL.get(0).size();
		m = TEL.size();
		this.vuelos = vuelos;
		this.TEL = TEL;
		this.tEspera = tEspera;
		Random rand = new Random();
		GenPr2 aux = new GenPr2(n);
		aux.initializeGen(rand);
		valor = getValor();
		solucion = new ArrayList<>();
		for(int i = 0; i < m; i++)
			solucion.add(new ArrayList<>());
	}
	
	public IndividuoPr2()
	{
		super.id = type;
	}

	@Override
	public boolean max() {
		return false;
	}

	@Override
	public double getValor() {
		asignarPista();
		double suma = 0;
		int min;
		for(int i = 0; i < solucion.size(); i++)
		{
			min = 0;
			for(int j = 1; j < solucion.get(i).size();j++)
				if(solucion.get(i).get(j).getSecond() < solucion.get(i).get(min).getSecond())
					min = j;
			suma += solucion.get(i).get(min).getSecond();
		}
		return suma;
	}
	
	private void asignarPista()
	{
		//TODO asignar a cada vuelo la pista de aterrizaje y su TLA
		double min, aux;
		int pos;
		for(int i = 0; i < genes.get(0).getLongitud(); i++)
		{
			min = Double.MAX_VALUE;
			pos = 0;
			for(int j = 0; j < TEL.size(); j++)
			{
				aux = 0;
				if(solucion.get(j).size() > 0)
					aux += tEspera[vuelos.get(solucion.get(j).get(solucion.get(j).size() - 1).getFirst()).getFirst()][vuelos.get((int) genes.get(0).getAlelo(i)).getFirst()];
				aux += TEL.get(j).get(i);
				if(aux < min)
				{
					min = aux;
					pos = j;
				}
			}
			Pair<Integer, Double> par = new Pair<Integer, Double>((int) genes.get(0).getAlelo(i), min);
			solucion.get(pos).add(par);
		}
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
				List<Pair<Integer, String>> vuelos = (List<Pair<Integer, String>>) datos[1];	//TODO comprobas por si acaso porque sale warning
				List<List<Double>> TEL = (List<List<Double>>) datos[2];									
				double[][] tEspera = (double[][]) datos[3];
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoPr2(vuelos, TEL, tEspera);
			}
		}
		return ind;
	}
}
