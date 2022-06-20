package gen;

import java.util.Random;

public class GenPr3 extends Gen {

	public GenPr3(int long_gen) {
		super(long_gen);
	}

	public void initializeGen(Random randomNumber, boolean mux6) {
		for(int i = 0; i < long_gen; i++) {
			int prob =  randomNumber.nextInt(101);
			int mod;
			int numero;
			if (prob > 50 || i == 0 || i == 1) { // Escoge una función
				numero = randomNumber.nextInt(257);
				if(mux6) {
					mod = numero % 10;
				}
				else {
					mod = numero % 15;
				}
				while(mod >= 4) {
					if(mux6) {
						mod = numero % 10;
					}
					else {
						mod = numero % 15;
					}
					numero = randomNumber.nextInt(257);
				}
			}
			else{ // Escoge un terminal				
				numero = randomNumber.nextInt(257);
				if(mux6) {
					mod = numero % 10;
				}
				else {
					mod = numero % 15;
				}
				while(mod < 4) {
					if(mux6) {
						mod = numero % 10;
					}
					else {
						mod = numero % 15;
					}
					numero = randomNumber.nextInt(257);
				}
			}
			this.alelo.add(numero);
			/*
			int numero = randomNumber.nextInt(255 + 1) + 1;
			int mod;
			if(mux6) {
				mod = numero % 10;
			}
			else {
				mod = numero % 15;
			}
			while(i==0 && (mod >= 4 || mod == 2)) {
				numero = randomNumber.nextInt(255 + 1) + 1;
				if(mux6) {
					mod = numero % 10;
				}
				else {
					mod = numero % 15;
				}
			}
			this.alelo.add(numero);
			*/
		}
			
		/*
		int aux, pos1, pos2, n_intercambios = randomNumber.nextInt(long_gen);
		for(int i = 0; i < n_intercambios; i++)
		{
			pos1 = randomNumber.nextInt(long_gen);
			pos2 = randomNumber.nextInt(long_gen);
			aux = (int) alelo.get(pos1);
			alelo.set(pos1, (int) alelo.get(pos2));
			alelo.set(pos2, aux);
		}
		*/
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
