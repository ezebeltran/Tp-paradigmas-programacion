package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Porcentual extends Promocion {
	
	private double descuento;
	
	public Porcentual(String nombre, List<Atraccion> atracciones, String tipo, double descuento) {
		super(nombre, atracciones, tipo);
		
		this.descuento=this.precioNormal-(this.precioNormal*descuento/100);
	}

	@Override
	public String toString() {
		return 
				"Promocion "+ this.getNombre() + ". Descuento porcentual\n" 
				+ super.toString();
	}

	

}
