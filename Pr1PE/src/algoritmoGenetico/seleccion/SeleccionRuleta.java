package algoritmoGenetico.seleccion;

public class SeleccionRuleta extends Seleccion {
	private final String type = "Ruleta";
	
	public SeleccionRuleta()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionRuleta();
		return null;
	}
}
