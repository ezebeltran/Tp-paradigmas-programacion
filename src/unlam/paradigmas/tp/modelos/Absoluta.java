package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Absoluta extends Promocion {

	public Absoluta(String nombre, List<Atraccion> atracciones, String tipo) {
		super(nombre, atracciones, tipo);

		//this.precioConDescuento = descuento;
	}

	@Override
	public String toString() {
		return 
				"Promocion "+ this.getNombre() + ". Descuento absoluto\n"
				+ super.toString() + "\n";
	}

}
