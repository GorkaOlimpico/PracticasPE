package gramatica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gramatica {
	 
	 
	 /*
	 	S = exp
	 	(A) exp = IF | func1 | func2 | A0 | A1 | D0 | D1 | D2 | D3
	 	(B) IF = if exp exp exp
	 	(C) func1 = NOT exp
	 	(D) func2 = AND exp exp | OR exp exp
	 	
	 	Regla A opciones = 9
	 	Regla B opciones = 0
	 	Regla C opciones = 0
	 	Regla D opciones = 2
	 	
	 */
	 private int wraps;
	 private int maxWraps;
	 private Map<String, List<List<String>>> mapa;

	 public Gramatica(int maxWraps, String texto){
		 this.wraps = 0;
		 this.maxWraps = maxWraps;
		 mapa = new HashMap<String, List<List<String>>>();
		 
		 //Separamos por líneas la gramatica
		 String r[]= texto.split("\n");
		 
		 for(int i = 0; i < r.length;i++) {
			//Separamos las reglas de sus variables
			String regla[] = r[i].split("::=");
			//Separamos cada una de las variables
			String variables[] = regla[1].split("\\|");
			//Creamos la lista para las listas de reglas
			List<List<String>> list1 = new ArrayList<List<String>>();
			
			for(int j = 0; j < variables.length; j++) {
				//Hacemos un split de las reglas por los espacios
				String aux[] = variables[j].split(" ");
				List<String> argumentos = Arrays.asList(aux);
				list1.add(argumentos);
			}
			
			
			mapa.put(regla[0], list1); // Se introduce la regla con sus argumentos
		 }

	 }
	 
}