package algoritmoGenetico;

public class Main {

	public static void main(String[] args) {
		
		
		
		AlgoritmoGenetico AG = new AlgoritmoGenetico();
		AG.run();
		
		System.out.println("Mejor individuo: " + AG.muestraMejor());
	}

}
