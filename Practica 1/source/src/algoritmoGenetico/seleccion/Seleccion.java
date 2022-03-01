package src.algoritmoGenetico.seleccion;

public abstract class Seleccion implements Cloneable {

	protected String type;
	
	public String getType()
	{
		return type;
	}

	protected abstract Seleccion parse(String text);
	
	public String toString() { return type; }
}
