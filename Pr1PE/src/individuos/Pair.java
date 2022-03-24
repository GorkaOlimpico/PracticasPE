package individuos;
public class Pair<T, P> {
	T uno;
	P dos;
	
	public Pair(T uno, P dos)
	{
		this.uno = uno;
		this.dos = dos;
	}
	
	public void setFirst(T uno)
	{
		this.uno = uno;
	}
	
	public void setSecond(P dos)
	{
		this.dos = dos;
	}
	
	public T getFirst()
	{
		return uno;
	}
	
	public P getSecond()
	{
		return dos;
	}
}
