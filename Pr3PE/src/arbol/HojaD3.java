package arbol;

import java.util.List;
import java.util.Random;

public class HojaD3 extends Hoja {

	public HojaD3(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(5);
	}

}
