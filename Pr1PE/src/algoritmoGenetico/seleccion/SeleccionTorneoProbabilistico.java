package algoritmoGenetico.seleccion;

public class SeleccionTorneoProbabilistico extends Seleccion {
	private final String type = "Torneo Probabilistico";
	
	public SeleccionTorneoProbabilistico()
	{
		super.id = type;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionTorneoProbabilistico();
		return null;
	}
}
