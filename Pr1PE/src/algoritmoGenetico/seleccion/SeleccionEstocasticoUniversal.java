package algoritmoGenetico.seleccion;

public class SeleccionEstocasticoUniversal extends Seleccion {
	private final String type = "Estocastico Universal";
	
	public SeleccionEstocasticoUniversal()
	{
		super.id = type;;
	}
	
	protected Seleccion parse(String id) {
		if(id == type)
			return new SeleccionEstocasticoUniversal();
		return null;
	}
}
