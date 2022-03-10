package algoritmoGenetico.seleccion;

import individuos.Individuo;

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

	@Override
	protected void seleccionar(Individuo[] ind, Individuo[] aux) {}
}
