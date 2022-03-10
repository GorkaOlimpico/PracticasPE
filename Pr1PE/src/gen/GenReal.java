package gen;

import java.util.Random;

public class GenReal extends Gen {

	public GenReal() {
		super(1);
	}

	public void initializeGen(Random randomNumber, double max, double min) {
		this.alelo.add(randomNumber.nextDouble() * (max - min) + min);
	}
	
	public Object getAlelo(int pos){
		return alelo.get(0);
	}
	
	public void setAlelo(int pos, Object o) {
		alelo.set(0, o);
	}
}
