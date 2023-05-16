package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public class Combo extends Promocion {
	
	//private Atraccion atraccionGratis;

	public Combo(List<Atraccion> atracciones, String tipo, double descuento) {
		super(atracciones, tipo, descuento);
		
		Atraccion atraccionGratis= atracciones.get(atracciones.size() - 1);
		
		this.precioConDescuento = this.precioSinDescuento - atraccionGratis.obtenerCosto();
		
	}

	@Override
	public String toString() {
		return ColorC.TEXT_RED
				+"Pack "+ this.obtenerTipo() + " con descuento AxB "
				+"[" + super.toString() + "]\n"
				+ColorC.RESET;
	}

}
