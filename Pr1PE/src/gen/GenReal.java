package gen;

import java.util.Random;

public class GenReal extends Gen {

	public GenReal() {
		super(1);
	}

	public void initializeGen(Random randomNumber, double max, double min) {
		this.alelo.add(randomNumber.nextDouble() * (max - min));
	}
	
	@Override
	public void setAlelo(int pos, Object o) {
		alelo.set(pos, (double) o);
	}
}
