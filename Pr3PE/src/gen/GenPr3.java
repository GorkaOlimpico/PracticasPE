package gen;

import java.util.Random;

public class GenPr3 extends Gen {

	public GenPr3(int long_gen) {
		super(long_gen);
	}

	public void initializeGen(Random randomNumber) {
		for(int i = 0; i < long_gen; i++)
			this.alelo.add(i);
		
		int aux, pos1, pos2, n_intercambios = randomNumber.nextInt(long_gen);
		for(int i = 0; i < n_intercambios; i++)
		{
			pos1 = randomNumber.nextInt(long_gen);
			pos2 = randomNumber.nextInt(long_gen);
			aux = (int) alelo.get(pos1);
			alelo.set(pos1, (int) alelo.get(pos2));
			alelo.set(pos2, aux);
		}
	}
	
	@Override
	public void setAlelo(int pos, Object o) {
		alelo.set(pos, (int) o);
	}

	@Override
	public void intercambiarAlelo(int pos, Gen g) {
		int aux = (int) g.getAlelo(pos);
		g.setAlelo(pos, (int) alelo.get(pos));
		setAlelo(pos, aux);
	}
}
