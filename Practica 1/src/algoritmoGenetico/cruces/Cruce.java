package src.algoritmoGenetico.cruces;

public abstract class Cruce implements Cloneable {

	protected String type;
	
	public String getType()
	{
		return type;
	}

	protected abstract Cruce parse(String text);
	
	public String toString() { return type; }
}
