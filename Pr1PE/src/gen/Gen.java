package gen;

import java.util.LinkedList;
import java.util.List;

public abstract class Gen {
	protected List<Object> alelo;
	protected int long_gen;
	
	public Gen(int long_gen) {
		this.alelo = new LinkedList<>();
		this.long_gen = long_gen;
	}
	
	public int getLongitud(){
		return long_gen;
	}
	
	public Object getAlelo(int pos){
		return alelo.get(pos);
	}
	
	public abstract void setAlelo(int pos, Object o);
	
	public void copiarGen(Gen g) //Copia this a g
	{
		for(int i = 0; i < getLongitud(); i++)
			g.setAlelo(i, getAlelo(i));
	}
}
