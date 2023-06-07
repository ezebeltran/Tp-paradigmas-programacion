package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Porcentual extends Promocion {
	
	private int descuento;
	
	public Porcentual(String nombre, List<Atraccion> atracciones, String tipo, int descuento) {
		super(nombre, atracciones, tipo);
		
		this.descuento=descuento;
		this.precioPromocion=this.precioNormal-(this.precioNormal*descuento/100);
	}

	@Override
	public String toString() {
		return 
				"Promocion "+ this.getNombre() + ". Descuento porcentual de "+ this.descuento +"%\n" 
				+ super.toString();
	}

	

}
