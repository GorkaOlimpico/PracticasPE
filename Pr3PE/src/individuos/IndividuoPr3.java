package individuos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenPr3;
import gramatica.Gramatica;


public class IndividuoPr3 extends Individuo {

	private final static String type = "Practica 3"; 
	private List<List<String>> lista; // Es del estilo: IF ( IF A0 D0) D1 D2 donde cada operacion es una lista
	private Gramatica gramatica;
	private int longitud;
	
	public IndividuoPr3(int longitud, int n_wraps, String nombreArchivo) throws FileNotFoundException {
		super.id = type;

		String texto_gramatica = archivoATexto(nombreArchivo);
		
		gramatica = new Gramatica(n_wraps, texto_gramatica);
		this.longitud = longitud;
		Random rand = new Random();
		GenPr3 aux = new GenPr3(longitud);
		aux.initializeGen(rand);
		genes.add(aux);
		recalcularFenotipo();
		
	}
	public IndividuoPr3()
	{
		super.id = type;
	}
	
	public String archivoATexto(String nombreArchivo) throws FileNotFoundException {
		String total ="";
		File doc = new File("gramatica.txt");
		Scanner obj = new Scanner(doc);
		while (obj.hasNextLine()) {
            total += obj.nextLine();
        }
		System.out.println("Leido de archivo: \n" + total);
		return total;
	}
	
	public void traduceALista() {
		// Esta función hace que el array numérico pase a ser la lista con la que podemos operar
		
		
		for(int i = 0; i < longitud; i++) {
			// 1. Módulo 3 para elegir entre: funcion = IF | func1 | func2
			int num = Math.floorMod((int) this.getGenes().get(0).getAlelo(i), 3);
			
			switch (num) {
			case 0: {
				// IF
				List<String> elemento =  new ArrayList<String>();
				elemento.add("IF");
				
				String arg1 = generaUnaExp();
				String arg2 = generaUnaExp();
				elemento.add(arg1);
				elemento.add(arg2);
			}
			case 1: {
				// func1
				List<String> elemento =  new ArrayList<String>();
				elemento.add("NOT");
				String arg1 = generaUnaExp();
				elemento.add(arg1);

			}
			
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
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
		IndividuoPr3[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoPr3[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoPr3();
			else
			{
				for(int i = 0; i < tam; i++)
					ind[i] = nuevoInd(datos);
			}
		}
		return ind;
	}
	
	public IndividuoPr3 nuevoInd(Object[] datos) {
		IndividuoPr3 individuo = new IndividuoPr3();
		
		int longitud = (int) datos[1];	
		int n_wraps =  (int) datos[2];									
		String nombreArchivo = (String) datos[3];
		
		try {
			individuo = new IndividuoPr3(longitud, n_wraps, nombreArchivo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return individuo;
	}

	@Override
	public String solutionToString() {
		String s = "El valor de la funcion es: " + super.getFitness() + "\n";
		
		return s;
	}
	
	public String genToString()
	{
		String s = "";
		for(int i = 0; i < longitud; i++)
			s += ((int) genes.get(0).getAlelo(i) + 1) + " ";
		return s + "\n";
	}

	
	

	@Override
	public double getFitness2() {
		// TODO Auto-generated method stub
		return 0;
	}
}
