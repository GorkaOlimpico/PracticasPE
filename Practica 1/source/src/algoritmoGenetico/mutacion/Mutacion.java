package src.algoritmoGenetico.mutacion;

public abstract class Mutacion implements Cloneable {

	protected String type;
	
	public String getType()
	{
		return type;
	}
	
	protected abstract Mutacion parse(String text);
	
	public String toString() { return type; }
}
