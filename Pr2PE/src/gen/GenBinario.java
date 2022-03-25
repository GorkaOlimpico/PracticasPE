package gen;

import java.util.Random;

public class GenBinario extends Gen{

	public GenBinario(int long_gen) {
		super(long_gen);
	}
	
	public void initializeGen(Random randomNumber) {
		for(int i=0; i < this.getLongitud(); i++){
			this.alelo.add(randomNumber.nextBoolean());
		}
	}

	@Override
	public void setAlelo(int pos, Object o) {
		this.alelo.set(pos, (boolean) o);
	}
}
