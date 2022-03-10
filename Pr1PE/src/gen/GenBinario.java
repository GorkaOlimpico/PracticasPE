package gen;

import java.util.LinkedList;
import java.util.Random;

public class GenBinario extends Gen{

	public GenBinario(int long_gen) {
		super(long_gen);
		this.alelo = new LinkedList();
	}
	
	public void initializeGen(Random randomNumber) {
		for(int i=0; i < this.getLongitud(); i++){
			this.alelo.add(randomNumber.nextBoolean());
		}
	}
}
