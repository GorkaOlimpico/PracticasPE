package algoritmoGenetico.seleccion;

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
	
	public static Seleccion seleccionarIndividuo(String id)
	{
		Seleccion c = null;
		for(int i = 0; i < getSelecciones().length && c == null; i++)
		{
			c = getSelecciones()[i].parse(id);
		}
		return c;
	}
	
	protected abstract Seleccion parse(String id);
}
