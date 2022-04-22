package individuos;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;

public class IndividuoPG extends Individuo{

	private final static String type = "Programación Genética"; 
	
	public IndividuoPG()
	{
		super.id = type;
	}
	
	@Override
	public boolean max() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getValor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFenotipo(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Cruce[] getCruces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Mutacion[] getMutaciones() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Individuo[] parse(int tam, Object[] datos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String genToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String solutionToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void copiarIndividuo(Individuo ind) {
		// TODO Auto-generated method stub
		
	}

}
