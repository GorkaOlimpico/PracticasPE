package algoritmoGenetico;

public class Main {

	public static void main(String[] args) {
		
		// Primera versión sin GUI:
		// 1. Crea el AG dependiendo del tipode funcion (solo F1).
		// 2. Llama a la función run().
		// 3. Muestra el mejor individuo por pantalla.
		
		AlgoritmoGenetico AG = new AlgoritmoGenetico();
		AG.run();
		
		System.out.println("Mejor individuo: " + AG.muestraMejor());
	}

}
