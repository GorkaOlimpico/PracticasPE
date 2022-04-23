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
	
	public IndividuoPG(int profundidad)
	{
		super.id = type;
		genes = Nodo.generar(new Random(), profundidad, null);
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
	public double getFenotipo(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

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
				for(int i = 0; i < tam; i++)
					ind[i] = nuevoInd(datos);
			}
		}
		return ind;
	}
	
	public IndividuoPG nuevoInd(Object[] datos) {
		IndividuoPG individuo = new IndividuoPG();
		
		int profundidad = (int) datos[1];	
		
		individuo = new IndividuoPG(profundidad);
		
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
