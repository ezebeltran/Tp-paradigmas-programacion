package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Absoluta extends Promocion {

	public Absoluta(List<Atraccion> atracciones, String tipo, double descuento) {
		super(atracciones, tipo, descuento);

		this.precioConDescuento = descuento;
	}

	@Override
	public String toString() {
		return ColorC.TEXT_RED 
				+"Pack "+ this.obtenerTipo() + " con descuento absoluto "
				+ "[" + super.toString() + "]\n" 
				+ ColorC.RESET;
	}

}
