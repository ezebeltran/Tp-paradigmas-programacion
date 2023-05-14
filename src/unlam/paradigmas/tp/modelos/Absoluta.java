package unlam.paradigmas.tp.modelos;

import java.util.ArrayList;

public class Absoluta extends Promocion {
	
	private double precioFinal;

	public Absoluta(ArrayList<Atraccion> atracciones, String tipo, double descuento) {
		super(atracciones, tipo, descuento);
		this.precioFinal = descuento;
	}

	@Override
	public void mostrarPromocion() {
		// TODO Auto-generated method stub

	}

}
