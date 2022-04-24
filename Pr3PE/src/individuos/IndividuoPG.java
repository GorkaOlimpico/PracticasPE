package individuos;

import java.util.List;
import java.util.Random;

import algoritmoGenetico.cruce.Cruce;
import algoritmoGenetico.mutacion.Mutacion;
import arbol.Nodo;
import arbol.Arbol;

public class IndividuoPG extends Individuo{

	private final static String type = "Programación Genética"; 
	
	public IndividuoPG()
	{
		super.id = type;
	}
	
	public IndividuoPG(int profundidad, int tipo_generacion, int prof_generar)
	{
		super.id = type;
		if(tipo_generacion == 0)
			genes = Arbol.generarFull(new Random(), profundidad, null);						//Full inicialization
		if(tipo_generacion == 1)
			genes = Arbol.generarGrow(new Random(), profundidad, null, prof_generar);		//Grow inicialization (prof_generar tiene que ser >= 1)
	}
	
	@Override
	public boolean max() {
		return true;
	}

	@Override
	public int getValor() {
		int suma = 0;
		Arbol arbol = (Arbol) genes;
		List<List<Boolean>> combinaciones = generaEntradas();
		for(int i = 0; i < combinaciones.size(); i++)
		{
			if(arbol.execute(combinaciones.get(i)) == Individuo.solucion[i])
				suma++;
		}
		return suma;
	}

	@Override
	public double getFenotipo(int i) {return 0;}

	@Override
	public Cruce[] getCruces() {
		return Cruce.getCrucesPr2();
	}

	@Override
	public Mutacion[] getMutaciones() {
		return Mutacion.getMutacionesPr2();
	}

	@Override
	protected Individuo[] parse(int tam, Object[] datos) {
		IndividuoPG[] ind = null;
		if((String) datos[0] == id)
		{
			ind = new IndividuoPG[tam];
			if(datos.length == 1)
				ind[0] = new IndividuoPG();
			else
			{
				int profundidad = (int) datos[1];	
				int tipo_generacion = (int) datos[2];
				if(tipo_generacion == 2)							//Ramped and half inicialization
				{
					int aux = tam / (profundidad - 1);
					for(int j = 0; j < profundidad; j++)			//La profundidad de la hoja es 0 y la de la raiz profundidad
					{
						for(int i = 0; i < aux / 2; i++)
							ind[i + (aux * j)] = nuevoInd(profundidad, 0, j);		//Mitad de Full
						for(int i = (aux / 2) + 1; i < tam; i++)
							ind[i + (aux * j)] = nuevoInd(profundidad, 1, j);		//Mitad de Grow
					}
				}
				else												//Grow or Full inicialization
					for(int i = 0; i < tam; i++)
						ind[i] = nuevoInd(profundidad, tipo_generacion, profundidad);
			}
		}
		return ind;
	}
	
	public IndividuoPG nuevoInd(int profundidad, int tipo_generacion, int prof_generar) {
		IndividuoPG individuo = new IndividuoPG();
		individuo = new IndividuoPG(profundidad, tipo_generacion, prof_generar);
		
		return individuo;
	}

	@Override
	public String solutionToString() {
		String s = "El valor de la funcion es: " + super.getFitness() + "\n";
		s += "Solucion: " + genToString();
		return s;
	}

	@Override
	public String genToString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void copiarIndividuo(Individuo ind) {
		// TODO Auto-generated method stub
		
	}

}
