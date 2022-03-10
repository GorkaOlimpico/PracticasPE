package individuos;

public class IndividuoFuncion4Real extends Individuo {
	private final static String type = "1"; 
	
	public IndividuoFuncion4Real()
	{
		super(0);
		super.id = type;
	}
	
	public IndividuoFuncion4Real(double valorError, int n)
	{
		super(valorError);
		super.id = type;
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getValor() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getFitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected double getFenotipo(int i) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected Individuo[] parse(int tam, String[] datos) {
		IndividuoFuncion4Real[] ind = null;
		if(datos[0] == id)
		{
			ind = new IndividuoFuncion4Real[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoFuncion4Real();
			else
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoFuncion4Real(Double.parseDouble(datos[1]), Integer.parseInt(datos[2]));
		}
		return ind;
	}
}
