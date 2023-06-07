package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Combo extends Promocion {
	
	//private List<Atraccion> atraccionesGratis;

	public Combo(String nombre, List<Atraccion> atracciones, String tipo) {
		super(nombre, atracciones, tipo);
		
		this.precioPromocion = this.precioNormal - atracciones.get(atracciones.size()-1).getPrecio();
		
	}
	
//	@Override
//	public boolean hayAtraccionSinCupo() {
//		
//		if(super.hayAtraccionSinCupo())
//			return true;
//		
//		for (Atraccion atraccion : this.atraccionesGratis) {
//			if(atraccion.getCupo()==0)
//				return true;
//		}
//		return false;
//	}
//	
//	@Override
//	public boolean algunaAtraccionTipo(String tipoAtraccion) {
//		
//		if(super.algunaAtraccionTipo(tipoAtraccion))
//			return true;
//		
//		for (Atraccion atraccion : this.atraccionesGratis) {
//			if(atraccion.getTipo().equals(tipoAtraccion))
//				return true;
//		}
//		
//		return false;
//		
//	}

	@Override
	public String toString() {
		return 
				"Promocion "+ this.getNombre() + ". Descuento AxB La ultima es gratis\n"
				 + super.toString() 
				;
	}

}
