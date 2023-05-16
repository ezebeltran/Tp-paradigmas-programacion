package unlam.paradigmas.tp.modelos;

import java.util.List;

import unlam.paradigmas.tp.utils.ColorC;

public abstract class Promocion {

	protected List<Atraccion> atracciones;
	protected String tipo;
	protected double descuento;
	protected double precioSinDescuento;
	protected double precioConDescuento;
	

	public Promocion(List<Atraccion> atracciones, String tipo, double descuento) {
		this.atracciones = atracciones;
		this.tipo = tipo;
		this.descuento = descuento;
		
		Double precios = 0.0;
		for (Atraccion atraccion : atracciones) {
			precios += atraccion.obtenerCosto();
		}
		this.precioSinDescuento=precios;
		this.precioConDescuento = 0;
	}
	
	//public abstract void mostrarPromocion();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atracciones == null) ? 0 : atracciones.hashCode());
		long temp;
		temp = Double.doubleToLongBits(descuento);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Promocion other = (Promocion) obj;
		if (atracciones == null) {
			if (other.atracciones != null) {
				return false;
			}
		} else if (!atracciones.equals(other.atracciones)) {
			return false;
		}
		if (Double.doubleToLongBits(descuento) != Double.doubleToLongBits(other.descuento)) {
			return false;
		}
		if (tipo == null) {
			if (other.tipo != null) {
				return false;
			}
		} else if (!tipo.equals(other.tipo)) {
			return false;
		}
		return true;
	}
	

	public String obtenerTipo() {
		return this.tipo;
	}
	
	@Override
	public String toString() {
		return ColorC.TEXT_RED
				+"atracciones=" + atracciones + "\n"
				+ ColorC.TEXT_RED
				+ ", tipo=" + tipo + ", descuento=" + descuento
				+ ", precioSinDescuento=" + precioSinDescuento 
				+ ", precioConDescuento=" + precioConDescuento + "]"
				;
	}
	
}
