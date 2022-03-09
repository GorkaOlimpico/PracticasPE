package algoritmoGenetico.mutacion;

public class Mutacion {

	
	private static Mutacion[] mutacionBin= {
			new MutacionBasica(),
	};
	
	public static Mutacion[] getMutacionesBin()
	{
		return mutacionBin;
	}
}
