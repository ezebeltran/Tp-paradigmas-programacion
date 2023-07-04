package unlam.paradigmas.tp.modelos;

import java.util.List;

public class Combo extends Promocion {

	public Combo(String nombre, List<Atraccion> atracciones, String tipo) {
		super(nombre, atracciones, tipo);

		this.precioPromocion = this.precioNormal - atracciones.get(atracciones.size() - 1).getPrecio();

	}

	@Override
	public String toString() {
		return "Promocion " + this.getNombre() + ". Descuento AxB La ultima es gratis\n" + super.toString();
	}

}
