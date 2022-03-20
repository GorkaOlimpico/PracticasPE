package algoritmoGenetico.seleccion;

import java.util.List;

import individuos.Individuo;

public abstract class Seleccion implements Cloneable{
	protected String id;
	
	private static Seleccion[] selecciones= {
			new SeleccionEstocasticoUniversal(),
			new SeleccionRestos(),
			new SeleccionRuleta(),
			new SeleccionTorneoDeterministico(),
			new SeleccionTorneoProbabilistico(),
			new SeleccionTruncamiento(),
	};
	
	public static Seleccion[] getSelecciones()
	{
		return selecciones;
	}
	
	public static String[] getSeleccionesId()
	{
		String[] s = new String[selecciones.length];
		for(int i = 0; i < s.length; i++)
			s[i] = selecciones[i].getId();
		return s;
	}
	
	protected String getId()
	{
		return id;
	}
	
	public static Seleccion seleccionarSeleccion(String id)
	{
		Seleccion c = null;
		for(int i = 0; i < getSelecciones().length && c == null; i++)
		{
			c = getSelecciones()[i].parse(id);
		}
		return c;
	}
	
	protected abstract Seleccion parse(String id);
	
	public void select(Individuo[] ind, Individuo[] aux)
	{
		seleccionar(ind, aux);
		ind = aux;
	}
	
	protected abstract void seleccionar(Individuo[] ind, Individuo[] aux);
	
	protected void desplazamiento(List<Double> prob, boolean max, Individuo[] ind)
	{
		ordenar(prob, ind);
		if(max)
			desplazamientoMax(prob);
		else
			desplazamientoMin(prob);
	}

	private void desplazamientoMin(List<Double> prob) {
		double max = 0;
		if(prob.get(prob.size() - 1) > 0)
			max = 1.05 * prob.get(prob.size() - 1);
		for(int i = 0; i < prob.size(); i++)
		{
			prob.set(i, - (prob.get(i) - max));
		}
	}

	private void desplazamientoMax(List<Double> prob)
	{
		if(prob.get(prob.size() - 1) < 0)
		{
			double min = 1.05 * prob.get(prob.size() - 1);
			for(int i = 0; i < prob.size(); i++)
			{
				prob.set(i, min + prob.get(i));
			}
		}
	}
	
	private void ordenar(List<Double> prob, Individuo[] ind)
	{
		for(int i = 0; i < prob.size() - 1; i++)
		{
			int max = i;
			for(int j = i; j < prob.size(); j++)
			{
				if(ind[0].max())
				{
					if(prob.get(j) > prob.get(max))
						max = j;
				}
				else if(prob.get(j) < prob.get(max))
					max = j;
						
			}
			double aux1 = prob.get(i);
			prob.set(i, prob.get(max));
			prob.set(max, aux1);
			
			Individuo aux2 = ind[i];
			ind[i] = ind[max];
			ind[max] = aux2;
		}
	}
}
