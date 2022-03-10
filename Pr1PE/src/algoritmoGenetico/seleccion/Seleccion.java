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
	
	protected void copiarIndividuo(Individuo i1, Individuo i2)
	{
		for(int i = 0; i < i1.getGenes().size(); i++)
		{
			i1.getGenes().get(i).copiarGen(i2.getGenes().get(i));
		}
	}
	
	protected void desplazamiento(List<Double> prob, boolean max)
	{
		if(max)
			desplazamientoMax(prob);
		else
			desplazamientoMin(prob);
	}

	private void desplazamientoMin(List<Double> prob) {
		// TODO Auto-generated method stub
		
	}

	protected void desplazamientoMax(List<Double> prob)
	{
		//TODO
	}
}
