package arbol;

import java.util.List;

public class HojaA0 extends Hoja {

	public HojaA0(int profundidad, Arbol padre) {
		super(profundidad, padre);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(0);
	}

}
