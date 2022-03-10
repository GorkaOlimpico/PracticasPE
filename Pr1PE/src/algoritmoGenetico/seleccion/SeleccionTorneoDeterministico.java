package algoritmoGenetico.seleccion;

public class SeleccionTorneoDeterministico extends Seleccion {
	private final String type = "Torneo Deterministico";
	
	public SeleccionTorneoDeterministico()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionTorneoDeterministico();
		return null;
	}
}
