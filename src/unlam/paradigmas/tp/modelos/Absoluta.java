package unlam.paradigmas.tp.modelos;

import java.util.List;

public class Absoluta extends Promocion {

	public Absoluta(String nombre, List<Atraccion> atracciones, String tipo,int descuento) {
		super(nombre, atracciones, tipo);

		this.precioPromocion = descuento;
	}

	@Override
	public String toString() {
		return 
				"Promocion "+ this.getNombre() + ". Descuento absoluto de "+ this.precioPromocion +"\n"
				+ super.toString() + "\n";
	}

}
