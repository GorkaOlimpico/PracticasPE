package algoritmoGenetico.seleccion;

public class SeleccionRestos extends Seleccion {
	private final String type = "Restos";
	
	public SeleccionRestos()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionRestos();
		return null;
	}
}
