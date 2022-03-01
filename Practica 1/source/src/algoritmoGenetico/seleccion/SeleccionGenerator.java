package src.algoritmoGenetico.seleccion;

public class SeleccionGenerator {
	private static Seleccion[] selecciones = { 
			new SeleccionEstocasticaUniversal(), 
			new SeleccionRestos(), 
			new SeleccionRuleta(),
			new SeleccionTorneoProbabilistico(), 
			new SeleccionTorneoDeterministico(), 
			new SeleccionTruncamiento(), 
	};

	public static Seleccion factoria(String text)  {
		Seleccion aux = null;
		for (int i = 0; i < selecciones.length && aux == null; i++) {
			aux = selecciones[i].parse(text);
		}
		return aux;
	}
	
	public static String[] getStrings()
	{
		String[] aux = new String[selecciones.length];
		for (int i = 0; i < selecciones.length; i++) {
			aux[i] = selecciones[i].getType();
		}
		return aux;
	}
	
	public static Seleccion[] getClasses() {
		return selecciones;
	}
}
