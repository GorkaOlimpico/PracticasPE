package gen;

import java.util.LinkedList;
import java.util.Random;

public class GenReal extends Gen {

	public GenReal() {
		super(1);
		this.alelo = new LinkedList();
	}

	public void initializeGen(Random randomNumber, double max, double min) {
		this.alelo.add(randomNumber.nextDouble() * (max - min) + min);
	}
}
