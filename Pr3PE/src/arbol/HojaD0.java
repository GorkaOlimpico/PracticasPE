package arbol;

import java.util.List;
import java.util.Random;

public class HojaD0 extends Hoja {

	public HojaD0(int profundidad, Arbol padre, Random rand) {
		super(profundidad, padre, rand);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(2);
	}

}
