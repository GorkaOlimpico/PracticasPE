package individuos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.GenPr3;
import gramatica.Gramatica;


public class IndividuoPr3 extends Individuo {

	private final static String type = "Practica 3"; 
	private List<Integer> solucion; //Necesito la solucion puesta con los valores?
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
	
	private String archivoATexto(String nombreArchivo) throws FileNotFoundException {
		String total ="";
		File doc = new File("gramatica.txt");
		Scanner obj = new Scanner(doc);
		while (obj.hasNextLine()) {
            total += obj.nextLine();
        }
		System.out.println("Leido de archivo: \n" + total);
		return total;
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
