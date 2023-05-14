package unlam.paradigmas.tp.modelos;

import java.util.ArrayList;

public class Combo extends Promocion {
	
	private Atraccion atraccionGratis;

	public Combo(ArrayList<Atraccion> atracciones, String tipo, double descuento) {
		super(atracciones, tipo, descuento);
		
		atraccionGratis= atracciones.get(atracciones.size() - 1);
	}

	@Override
	public void mostrarPromocion() {
		// TODO Auto-generated method stub

	}

}
