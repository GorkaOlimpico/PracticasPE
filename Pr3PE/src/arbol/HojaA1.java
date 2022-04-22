package arbol;

import java.util.List;

public class HojaA1 extends Hoja {

	public HojaA1(int profundidad, Arbol padre) {
		super(profundidad, padre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(List<Boolean> input) {
		return input.get(1);
	}

}
