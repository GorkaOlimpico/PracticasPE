package individuos;

import java.io.File;



import java.io.FileNotFoundException;
import java.util.*;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import arbol.Arbol;
import gen.Gen;
import gen.GenPr3;
import gramatica.Gramatica;



public class IndividuoGE extends Individuo {

	private final static String type = "Gramática Evolutiva"; 
	private List<String> solucion;
	private Gramatica gramatica;
	private int max_wraps;
	private int wraps;
	private int pos;
	private int longitud;

	
	public IndividuoGE(int longitud, int n_wraps, String nombreArchivo, boolean m6) throws FileNotFoundException {
		super(m6);
		super.id = type;

		List<Gen> genes = new ArrayList<>();
		solucion = new ArrayList<String>();
		String texto_gramatica = archivoATexto(nombreArchivo);
		pos = 0;
		max_wraps = n_wraps;
		gramatica = new Gramatica(texto_gramatica);
		this.longitud = longitud;
		Random rand = new Random();
		GenPr3 aux = new GenPr3(longitud);
		aux.initializeGen(rand);
		genes.add(aux);
		this.genes = genes;
		recalcularFenotipo();
	

	}
	public IndividuoGE()
	{
		super.id = type;
	}
	
	public String archivoATexto(String nombreArchivo) throws FileNotFoundException {
		String total ="";
		File doc = new File(nombreArchivo);
		Scanner obj = new Scanner(doc);
		while (obj.hasNextLine()) {
            total += obj.nextLine();
            total += "\n";
        }
		//System.out.println("Leido de archivo: \n" + total);
		return total;
	}
	
	public void traduceALista() {
		// Esta función hace que el array numérico pase a ser la lista con la que podemos operar
		//List<Gen> genes = (List<Gen>) this.genes;
		//System.out.println("GEN: "+ genes.get(0).getAlelos());
		solucion.clear();
		addElemLista("S");
		//System.out.println("Individuo: "+ solucion);
		pos = 0;
		wraps = 0;
		
	}
	
	public void addElemLista(String key) {
		List<String> terminales = null;
		if(multiplexor6) {
			terminales = Arrays.asList("A0", "A1", "D0", "D1", "D2", "D3");
		}
		else {
			terminales = Arrays.asList("A0", "A1", "A2", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7");
		}
		
		
		switch (key) {
			case "AND": {
				solucion.add(key);
			}
			break;
			case "OR": {
				solucion.add(key);
			}
			break;
			case "NOT": {
				solucion.add(key);
			}
			break;
			case "IF": {
				solucion.add(key);
			}
			break;
			case "(": {
				solucion.add(key);
			}
			break;
			case ")": {
				solucion.add(key);
			}			
			break;
			case "A0": {
				solucion.add(key);
			}			
			break;
			case "A1": {
				solucion.add(key);
			}
			break;
			case "A2": {
				solucion.add(key);
			}	
			break;
			case "D0": {
				solucion.add(key);
			}			
			break;
			case "D1": {
				solucion.add(key);
			}			
			break;
			case "D2": {
				solucion.add(key);
			}			
			break;
			case "D3": {
				solucion.add(key);
			}			
			break;
			case "D4": {
				solucion.add(key);
			}			
			break;
			case "D5": {
				solucion.add(key);
			}			
			break;
			case "D6": {
				solucion.add(key);
			}			
			break;
			case "D7": {
				solucion.add(key);
			}			
			break;
			
			
			default:{ // Si es un elemento key como por ejemplo expr
				
				// 0. Si es el último wrap entonces tiene que ser un terminal
				if (wraps == max_wraps -1) {
					List<Gen> genes = (List<Gen>) this.genes;
					int num = ((int) genes.get(0).getAlelo(pos)) % terminales.size();
					key = terminales.get(num);
					addElemLista(key);
					//System.out.println("Se llegó a max_wraps -1");
					
					break;
				}
				
				
				// 1. Busco en la tabla Hash. Obtengo su lista de opciones
			
				List<List<String>> opciones = gramatica.getMap().get(key);
				if (opciones == null){
					System.out.println("Error en opciones");
				}
				int num_opciones = opciones.size();
				
				// 2. Elijo una de las opciones disponibles según el número que toca del individuo
				int numero = 0;
				if(num_opciones > 1) {
					List<Gen> genes = (List<Gen>) this.genes;
					numero = ((int) genes.get(0).getAlelo(pos)) % num_opciones;
					pos++;
					if(pos == longitud) { // máximo indice
						pos = 0;
						wraps++;
						if(wraps > max_wraps) {
							System.out.println("ERROR: se supera el max_wraps");
						}
					}
				}
				
				List<String> elegida = opciones.get(numero);
				
				// 3. Añado todos los argumentos a la lista
				for(String argumento : elegida) {
					addElemLista(argumento);
				}
				
			}
			break;
			
		}
		
		
			
	}

	@Override
	public boolean max() {
		return true;
	}

	
	
	@Override
	public double getValor() {
		/*
		int suma = 0;
		Arbol arbol = (Arbol) genes;
		
		List<List<Boolean>> combinaciones = entradas;

		for(int i = 0; i < combinaciones.size(); i++)
		{
			boolean c = evaluaElemento(combinaciones.get(i), solucion)
					arbol.execute(combinaciones.get(i));
			if(multiplexor6)
			{
				if(c == solucionar6(combinaciones.get(i)))
					suma++;
			}
			else
				if(c == solucionar11(combinaciones.get(i)))
					suma++;
		}
		return suma;
		
		*/
		
		
		
		
		int aciertos = 0;
		
		// 1. Se genera la List<String> a partir del número
		traduceALista();		
		//System.out.println(solucion);
		
		// 2. Por cada entrada[6] evalúo la List<String> y evalúo su resultado correcto del MX-6
		//System.out.println("Solucion: " + solucion);
		
		for(List<Boolean> entrada : entradas) {
			Boolean [] aux =  new Boolean[entrada.size()];
			/* DEBUG
			entrada.set(0, true);
			entrada.set(1, false);
			entrada.set(2, false);
			entrada.set(3, true);
			entrada.set(4, false);
			entrada.set(5, false);
			*/
			entrada.toArray(aux);
			
			
			//4. Comparo los resultados. Si son iguales entonces sumo 1 a aciertos
			if(multiplexor6) {
				//System.out.println("Solucion: " + solucion);
				if(evaluaElemento(entrada, solucion) == multiplexor6(aux)) {
					aciertos++;
				}
				else {
					//System.out.println("entrada: " + entrada);
					//System.out.println("multiplexor6: " + multiplexor6(aux));
					//System.out.println("solucion: " + evaluaElemento(entrada, solucion));
				}
			}
			else {
				
				if(evaluaElemento(entrada, solucion) == multiplexor11(aux)) {
					aciertos++;
				}
			}
		}
			
		//System.out.println("Aciertos: " + aciertos);
		return aciertos;
		
	}
	
	
	
	public boolean evaluaElemento(List<Boolean> entrada, List<String> elemento) {
		boolean resultado = false;
		
		//System.out.println("Elemento: "+ elemento);
		String primero = buscaPrimero(elemento);
		//System.out.println("Primero: " + primero);
		if(multiplexor6) {
			switch (primero) {
			case "IF": {
				if(evaluaElemento(entrada, getSiguiente(elemento))) {
					return evaluaElemento(entrada, getSiguiente2(elemento));
				}
				else {
					return evaluaElemento(entrada, getSiguiente3(elemento));
				}
			}
			
			case "AND": {
				return evaluaElemento(entrada, getSiguiente(elemento)) && evaluaElemento(entrada, getSiguiente2(elemento));
			}
			
			case "OR": {
				
				return evaluaElemento(entrada, getSiguiente(elemento)) || evaluaElemento(entrada, getSiguiente2(elemento));
			}
			
			case "NOT": {
				return !(evaluaElemento(entrada, getSiguiente(elemento)));
			}
		
			case "A0": {
				return entrada.get(1);
			}
			
			case "A1": {
				return entrada.get(0);
			}
			
			
			case "D0": {
				return entrada.get(2);
			}
			
			case "D1": {
				return entrada.get(3);
			}
			
			case "D2": {
				return entrada.get(4);
			}
			
			case "D3": {
				return entrada.get(5);
			}
		
			}
		}
		else {
			switch (primero) {
			case "IF": {
				if(evaluaElemento(entrada, getSiguiente(elemento))) {
					return evaluaElemento(entrada, getSiguiente2(elemento));
				}
				else {
					return evaluaElemento(entrada, getSiguiente3(elemento));
				}
			}
			
			case "AND": {
				return evaluaElemento(entrada, getSiguiente(elemento)) && evaluaElemento(entrada, getSiguiente2(elemento));
			}
			
			case "OR": {
				
				return evaluaElemento(entrada, getSiguiente(elemento)) || evaluaElemento(entrada, getSiguiente2(elemento));
			}
			
			case "NOT": {
				return !(evaluaElemento(entrada, getSiguiente(elemento)));
			}
		
			case "A0": {
				return entrada.get(0);
			}
			
			case "A1": {
				return entrada.get(1);
			}
			
			case "A2": {
				return entrada.get(2);
			}
			
			case "D0": {
				return entrada.get(3);
			}
			
			case "D1": {
				return entrada.get(4);
			}
			
			case "D2": {
				return entrada.get(5);
			}
			
			case "D3": {
				return entrada.get(6);
			}
			case "D4": {
				return entrada.get(7);
			}
			case "D5": {
				return entrada.get(8);
			}
			case "D6": {
				return entrada.get(9);
			}
			case "D7": {
				return entrada.get(10);
			}
		}	
		}
		
		
		return resultado;
	}
	
	public List<String> getSiguiente(List<String> elemento){ // Devuelve todo lo que hay entre los siguientes paréntesis
		List<String> siguiente = new ArrayList<String>();
		if(elemento.size() >= 3) {
			int pos = 1;
			if(elemento.get(0).equals("(")) {
				pos = 0;
			}
			else {
				pos = 1;
			}
			int pIntermedios = 1;
			pos++;
			while(pos < elemento.size()) {
				if (pIntermedios == 0) { 
						break;
				}
				else if (elemento.get(pos).equals("(")) {
					pIntermedios++;
				}
				else if (elemento.get(pos).equals(")")) {
					pIntermedios-= 1;
				}
				
				siguiente.add(elemento.get(pos));
				pos ++;
			}		
			
			//elimina los paréntesis:
			
			siguiente.remove(siguiente.size()-1);
		}
		else {
			siguiente = elemento;
		}
		
		return siguiente;
	}
	
	public List<String> getSiguiente2(List<String> elemento){
		List<String> siguiente2 = new ArrayList<String>();

		
		
		int pos = 0;
		// Modificamos elemento para que no incluya el getSiguiente
		List<String> siguiente = getSiguiente(elemento);
		for(int i = -2; i< siguiente.size(); i++) {
			pos++;
		}
		
		
		if(pos + 3 <= elemento.size()) {
			pos++;
			// No añade el primer paréntesis
			pos++;
			int pIntermedios = 1;
			
			while(pos < elemento.size()) {
				if (pIntermedios == 0) { 
					break;
				}
				else if (elemento.get(pos).equals("(")) {
					pIntermedios++;
				}
				else if (elemento.get(pos).equals(")")) {
					pIntermedios--;
				}
				
				siguiente2.add(elemento.get(pos));
				pos ++;
			}		
			
			//elimina los paréntesis:
			
			siguiente2.remove(siguiente2.size()-1);
		}
		else {
			siguiente2 = elemento;
		}
		return siguiente2;
	}
	
	public List<String> getSiguiente3(List<String> elemento){
		List<String> siguiente3 = new ArrayList<String>();
		
		
		
		int pos = 0;
		if(elemento.get(0).equals("(")) {
			pos = -1;
		}
		
		// Modificamos elemento para que no incluya el getSiguiente
		List<String> siguiente = getSiguiente(elemento);
		for(int i = -2; i< siguiente.size(); i++) {
			pos++;
		}
		// Modificamos elemento para que no incluya el getSiguiente2
		List<String> siguiente2 = getSiguiente2(elemento);
		for(int i = -2; i< siguiente2.size(); i++) {
			pos++;
		}
		
		if(pos + 3 <= elemento.size()) {
			pos++;
			// No añade el primer paréntesis
			pos++;
			int pIntermedios = 1;
			
			while(pos < elemento.size()) {
				if (pIntermedios == 0) { 
					break;
				}
				else if (elemento.get(pos).equals("(")) {
					pIntermedios++;
				}
				else if (elemento.get(pos).equals(")")) {
					pIntermedios--;
				}
				
				siguiente3.add(elemento.get(pos));
				pos++;
			}
			
			//elimina los paréntesis:
			
			siguiente3.remove(siguiente3.size()-1);
		}
		else {
			siguiente3 = elemento;
		}
		return siguiente3;
	}
	
	public String buscaPrimero(List<String> elemento) {
		String primero = "";
		// Devuelve la primera operación o terminal.
		// Ejemplo: ((IF (A0) (A1) (D1)) AND D3). Esta función devuelve "AND" porque es la operación que se quiere realizar
		
		int pos =  0;
		// 1.1 Si llega un paréntesis se busca el siguiente elemento después de que se hayan cerrado todos los paréntesis que lleguen nuevos
		if(elemento.get(pos).equals("(")) {
			int pIntermedios = 0; // indica si existen paréntesis intermedios sin cerrar
			pos++;
			
			while(pos < elemento.size()) {
				if (elemento.get(pos).equals("(")) {
					pIntermedios++;
				}
				else if (elemento.get(pos).equals(")")) {
					pIntermedios-= 1;
				}
				else {
					if (pIntermedios == 0) { 
						primero = elemento.get(pos);
						pos = elemento.size(); // Esto es un break;
					}
				}
				pos++;
			}			
		}
		else {	// 1.2 Si llega un terminal entonces devuelve el terminal
			primero = elemento.get(pos);
		}

		
		return primero;
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
		IndividuoGE[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoGE[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoGE();
			else
			{
				for(int i = 0; i < tam; i++) {
					ind[i] = nuevoInd(datos);
					//System.out.println("Creado nuevo individuo. Total: " + (i+1));
				}
					
			}
		}
		return ind;
	}
	
	public IndividuoGE nuevoInd(Object[] datos) {
		IndividuoGE individuo = new IndividuoGE();
		int longitud = (int) datos[2]; //Integer.parseInt((String) datos[1]);
	
		int n_wraps = (int) datos[1]; // Integer.parseInt((String) datos[2]);		
		boolean m6 = (boolean) datos[4];
		String nombreArchivo = (String) datos[3];
		
		try {
			individuo = new IndividuoGE(longitud, n_wraps, nombreArchivo, m6);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return individuo;
	}

	@Override
	public String solutionToString() {
		String s = "El valor de la funcion es: " + super.getFitness() + "\n";
		
		String total = "";
		for(String str: solucion) {
			total += str;
		}
		s += "	Individuo: "+ total;
		
		List<Gen> genes = (List<Gen>) this.genes;
		System.out.println("Números: "+ genes.get(0).getAlelos().toString());
		return s;
	}
	
	public String genToString()		
	{
		List<Gen> genes = (List<Gen>) this.genes;
		String s = "";
		for(int i = 0; i < longitud; i++)
			s += ((int) genes.get(0).getAlelo(i) + 1) + " ";
		return s + "\n";
	}

						

	public boolean multiplexor6(Boolean entrada[]) {
		
		boolean d0 = !entrada[0] && !entrada[1] && entrada[2];
		boolean d1 = !entrada[0] && entrada[1] && entrada[3];
		boolean d2 = entrada[0] && !entrada[1] && entrada[4];
		boolean d3 = entrada[0] && entrada[1] && entrada[5];
		
		return d0 || d1 || d2 || d3;
	}
	
	public boolean multiplexor11(Boolean entrada[]) {
		boolean d0 = !entrada[0] && !entrada[1] && !entrada[2] && entrada[3];
		boolean d1 = !entrada[0] && !entrada[1] && entrada[2] && entrada[4];
		boolean d2 = !entrada[0] && entrada[1] && !entrada[2] && entrada[5];
		boolean d3 = !entrada[0] && entrada[1] && entrada[2] && entrada[6];
		boolean d4 = entrada[0] && !entrada[1] && !entrada[2] && entrada[7];
		boolean d5 = entrada[0] && !entrada[1] && entrada[2] && entrada[8];
		boolean d6 = entrada[0] && entrada[1] && !entrada[2] && entrada[9];
		boolean d7 = entrada[0] && entrada[1] && entrada[2] && entrada[10];
		
		return d0 || d1 || d2 || d3 || d4 || d5 || d6 || d7;
	}
	
	
	@Override
	public void copiarIndividuo(Individuo ind) {
		
		List<Gen> genes = (List<Gen>) this.genes, genes2 = (List<Gen>) ind.getGenes();
		for(int i = 0; i < genes.size(); i++)
			genes.get(i).copiarGen(genes2.get(i));
			
		recalcularFenotipo();
	
	}
	
	public List<String> getSolucion(){
		traduceALista();
		return solucion;
	}
}
