package algoritmoGenetico.seleccion;

import algoritmoGenetico.mutacion.Mutacion;
import algoritmoGenetico.mutacion.MutacionBasica;

public class SeleccionPrueba extends Seleccion {
	private final String type = "Prueba";
	
	public SeleccionPrueba() {
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionPrueba();
		return null;
	}
}
