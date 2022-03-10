package individuos;



import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import gen.Gen;
import gen.GenBinario;

public class IndividuoFuncion1 extends Individuo {
	// f(x1 , x2) = 21.5 + x1.sen(4π x1)+x2.sen(20π x2) 
	// donde x1∈ [-3.0,12.1] x2∈ [4.1,5.8]
	
	// Representación de IndividuoFuncion1:
	// 
	private final static String type = "1"; 
	
	public IndividuoFuncion1()
	{
		super(0);
		super.id = type;
	}
	
	public IndividuoFuncion1(double valorError)
	{
		super(valorError);
		super.id = type;
		min.add(-3.0);															//x1
		max.add(12.1);
		genes.add(new GenBinario(tamGen(min.get(0), max.get(0)))); 
		fenotipo.add(getFenotipo(0));
		min.add(4.1);															//x2
		max.add(5.8);
		genes.add(new GenBinario(tamGen(min.get(1), max.get(1)))); 
		fenotipo.add(getFenotipo(1));
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		return (21.5 + getFenotipo(0) * Math.sin(4 * Math.PI * getFenotipo(0)) + getFenotipo(1) * Math.sin(20 * Math.PI * getFenotipo(1)));
	}
	
	public double getFitness()
	{
		return getValor();
	}
	
	protected double getFenotipo(int i)
	{
		double fenotipo = 0;
		int potencia = 1;
		int alelo;
		
		for(int j = genes.get(i).getLongitud() - 1; j >= 0; j--)
		{
			if((boolean) genes.get(i).getAlelo(j))
				alelo = 1;
			else
				alelo = 0;	
			
			fenotipo += potencia * alelo;
			potencia *= 2;
		}
		return ((fenotipo * valorError) + min.get(i)) % (max.get(i) - min.get(i));
	}
	
	public static Cruce[] getCruces()
	{
		return Cruce.getCrucesBin();
	}
	
	public static Mutacion[] getMutaciones()
	{
		return Mutacion.getMutacionesBin();
	}
	
	public static String[] getCrucesId()
	{
		return Cruce.getCrucesBinId();
	}
	
	public static String[] getMutacionesId()
	{
		return Mutacion.getMutacionesBinId();
	}

	@Override
	protected Individuo[] parse(int tam, String[] datos) {
		IndividuoFuncion1[] ind = null;
		if(datos[0] == id)
		{
			ind = new IndividuoFuncion1[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoFuncion1();
			else
				for(int i = 0; i < tam; i++)
					ind[i] = new IndividuoFuncion1(Double.parseDouble(datos[1]));
		}
		return ind;
	}

	@Override
	public boolean max() {
		return true;
	}

	////Creo que no es necesario, esta funcion pasa un double a array de bool
//	@Override
//	protected void setGen(int i, double gen) {
//		gen %= max.get(i) - min.get(i);	
//		gen -= min.get(i);
//		gen /= valorError;
//		for(int j = genes.get(i).getLongitud() - 1; j >= 0; j--)
//		{
//			genes.get(i).setAlelo(j, gen % 2 == 1);
//			gen /= 2;
//		}
//	}
}
