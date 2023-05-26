package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Combo extends Promocion {
	
	private List<Atraccion> atraccionesGratis;

	public Combo(String nombre, List<Atraccion> atracciones, String tipo, List<Atraccion> atraccionesGratis) {
		super(nombre, atracciones, tipo);
		
		this.atraccionesGratis= atraccionesGratis;
		this.precioPromocion = this.precioNormal; // antes de cargar el precio de las gratis
		
		for (Atraccion atraccion : atraccionesGratis) {
			this.precioNormal += atraccion.getPrecio();
		}
		
		for (Atraccion atraccion : atraccionesGratis) {
			this.tiempoTotal += atraccion.getTiempo();
		}
		
	}
	
	@Override
	public boolean hayAtraccionSinCupo() {
		
		if(super.hayAtraccionSinCupo())
			return true;
		
		for (Atraccion atraccion : this.atraccionesGratis) {
			if(atraccion.getCupo()==0)
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return 
				"Promocion "+ this.getNombre() + ". Descuento AxB\n"
				 + super.toString() 
				;
	}

}
