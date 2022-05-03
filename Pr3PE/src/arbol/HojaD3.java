package arbol;

import java.util.List;
import java.util.Random;

public class HojaD3 extends Hoja {

	public HojaD3(int profundidad, Arbol padre, Random rand, boolean m6) {
		super(profundidad, padre, rand, m6);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		if(m6)
			return input.get(5);
		else
			return input.get(6);
	}

	@Override
	public String toString() {
		return "D3";
	}

	@Override
	public Arbol clonar(Arbol padre) {
		return new HojaD3(profundidad, padre, new Random(), m6);
	}
}
