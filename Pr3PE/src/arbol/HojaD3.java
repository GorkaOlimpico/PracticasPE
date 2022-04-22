package arbol;

import java.util.List;

public class HojaD3 extends Hoja {

	public HojaD3(int profundidad, Arbol padre) {
		super(profundidad, padre);
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(5);
	}

}
