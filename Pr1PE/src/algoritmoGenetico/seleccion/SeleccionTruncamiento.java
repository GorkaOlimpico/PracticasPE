package algoritmoGenetico.seleccion;

public class SeleccionTruncamiento extends Seleccion {
	private final String type = "Truncamiento";
	
	public SeleccionTruncamiento()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionTruncamiento();
		return null;
	}
}
