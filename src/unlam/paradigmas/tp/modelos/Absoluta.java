package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Absoluta extends Promocion {

	public Absoluta(List<Atraccion> atracciones, String tipo) {
		super(atracciones, tipo);

		//this.precioConDescuento = descuento;
	}

	@Override
	public String toString() {
		return ColorC.TEXT_RED 
				+"Pack "+ this.getTipo() + " con descuento absoluto "
				+ "[" + super.toString() + "]\n" 
				+ ColorC.RESET;
	}

}
