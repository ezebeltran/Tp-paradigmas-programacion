package unlam.paradigmas.tp.modelos;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import unlam.paradigmas.tp.utils.ColorC;

public abstract class Promocion implements Comparable<Promocion>, Sugerencia {

	protected String nombre;
	protected List<Atraccion> atracciones;
	protected String tipo;
	//protected double descuento;
	protected double tiempoTotal;
	protected int precioNormal;
	protected int precioPromocion;

	public Promocion(String nombre, List<Atraccion> atracciones, String tipo) {
		this.nombre=nombre;
		this.atracciones = atracciones;
		this.tipo = tipo;
		//this.descuento = descuento;
		
		
		for (Atraccion atraccion : atracciones) {
			this.precioNormal += atraccion.getPrecio();
		}
		
		
		for (Atraccion atraccion : atracciones) {
			this.tiempoTotal+= atraccion.getTiempo();
		}
		//this.precioConDescuento = 0;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return this.tipo;
	}
	
	public double getTiempoTotal() {
		return tiempoTotal;
	}

	public int getPrecioPromocion() {
		return precioPromocion;
	}
	
	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public boolean tieneCupo() {
		for (Atraccion atraccion : atracciones) {
			if ( !atraccion.tieneCupo() ) {
				return false;
			}
		}
		return true;
	}
	
	/**tener en cuenta el combo**/
	public boolean hayAtraccionSinCupo() {
		for (Atraccion atraccion : atracciones) {
			if(atraccion.getCupo()==0)
				return true;
		}
		return false;
	}
	
	/**
	 * Verifica si la promocion tiene alguna atraccion de algun tipo
	 * @param tipoAtraccion
	 * @return
	 */
	public boolean algunaAtraccionTipo(String tipoAtraccion) {
		for (Atraccion atraccion : atracciones) {
			if(atraccion.getTipo().equals(tipoAtraccion))
				return true;
		}
		
		return false;
		
	}
	
	//public abstract void mostrarPromocion();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atracciones == null) ? 0 : atracciones.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + precioNormal;
		result = prime * result + precioPromocion;
		long temp;
		temp = Double.doubleToLongBits(tiempoTotal);
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
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (precioNormal != other.precioNormal) {
			return false;
		}
		if (precioPromocion != other.precioPromocion) {
			return false;
		}
		if (Double.doubleToLongBits(tiempoTotal) != Double.doubleToLongBits(other.tiempoTotal)) {
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
	

	private String listAtraccionesAString() {
		String res="";
		for (Atraccion atraccion : atracciones) {
			res=res+atraccion.getNombre()+"("+atraccion.getTipo()+"), ";
		}
		
		return res.substring(0, res.length()-2);
	}
	
	
	@Override
	public String toString() {
		return //ColorC.TEXT_RED
				"-Atracciones incluidas: [" + this.listAtraccionesAString() + "]\n"
				+"-Duracion: " + this.tiempoTotal + " horas\n"
				+"-Precio original: $" + this.precioNormal + "\n"
				+"-Precio con descuento: $" + this.precioPromocion + "\n"
				//+ ColorC.TEXT_RED
				//+ ", tipo=" + tipo + ", descuento=" + descuento
//				+ ", precioSinDescuento=" + precioSinDescuento 
//				+ ", precioConDescuento=" + precioConDescuento + "]"
				;
	}
	
	@Override
	public int compareTo(Promocion otraPromo) {
		int res=Integer.compare(this.precioNormal, otraPromo.precioNormal);
		if(res!=0) 
			return res;
		
		return Double.compare(this.tiempoTotal, otraPromo.tiempoTotal);
	}
	
	@Override
	public boolean sugerir(Scanner scanner) {
		System.out.println(this.toString());
		System.out.println("Acepta sugerencia? Ingrese S o N");
		String respuesta = scanner.nextLine();
		
		if (respuesta.equals("S") || respuesta.equals("s")) {
			System.out.println("�Aceptada!");
			return true;
		}
		return false;

	}

}
