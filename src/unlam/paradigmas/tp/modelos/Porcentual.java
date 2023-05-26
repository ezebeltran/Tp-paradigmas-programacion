package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Porcentual extends Promocion {
	
	private double descuento;
	
	public Porcentual(List<Atraccion> atracciones, String tipo, double descuento) {
		super(atracciones, tipo);
		
		this.descuento=this.precioNormal-this.precioNormal*descuento/100;
	}

	@Override
	public String toString() {
		return ColorC.TEXT_RED
				//+"Pack "+ this.obtenerTipo() + " con descuento porcentual " 
				+ "[" + super.toString() + "]\n"
				+ColorC.RESET;
	}

	

}
