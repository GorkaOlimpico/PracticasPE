package individuos;



import gen.Gen;
import gen.GenBinario;

public class IndividuoFuncion1 extends Individuo {
	// f(x1 , x2) = 21.5 + x1.sen(4π x1)+x2.sen(20π x2) 
	// donde x1∈ [-3.0,12.1] x2∈ [4.1,5.8]
	
	// Representación de IndividuoFuncion1:
	// 
	public IndividuoFuncion1(double valorError)
	{
		super(valorError);
		min.add(-3.0);															//x1
		max.add(12.1);
		genes.add(new GenBinario(tamGen(min.get(0), max.get(0)))); 
		min.add(4.1);															//x2
		max.add(5.8);
		genes.add(new GenBinario(tamGen(min.get(1), max.get(1)))); 
	}
	
	private int tamGen(double min, double max) {
		return (int) (Math.log10(((max - min) / valorError) + 1) / Math.log10(2));
	}
	
	public double getValor()
	{
		double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
		return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
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
