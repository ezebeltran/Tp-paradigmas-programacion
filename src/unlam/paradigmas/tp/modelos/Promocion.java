package unlam.paradigmas.tp.modelos;

import java.util.List;
import java.util.Scanner;

public abstract class Promocion implements Comparable<Promocion>, Sugerencia {

	protected String nombre;
	protected List<Atraccion> atracciones;
	protected String tipo;
	protected double tiempoTotal;
	protected int precioNormal;
	protected int precioPromocion;

	public Promocion(String nombre, List<Atraccion> atracciones, String tipo) {
		this.nombre = nombre;
		this.atracciones = atracciones;
		this.tipo = tipo;

		for (Atraccion atraccion : atracciones) {
			this.precioNormal += atraccion.getPrecio();
		}

		for (Atraccion atraccion : atracciones) {
			this.tiempoTotal += atraccion.getTiempo();
		}
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
			if (!atraccion.tieneCupo()) {
				return false;
			}
		}
		return true;
	}

	public boolean tieneAtraccion(String nombreAtracion) {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getNombre().equals(nombreAtracion))
				return true;
		}
		return false;
	}

	public boolean hayAtraccionSinCupo() {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getCupo() == 0)
				return true;
		}
		return false;
	}

	/**
	 * Verifica si la promocion tiene alguna atraccion de algun tipo
	 * 
	 * @param tipoAtraccion
	 * @return
	 */
	public boolean algunaAtraccionTipo(String tipoAtraccion) {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.getTipo().equals(tipoAtraccion))
				return true;
		}

		return false;

	}

	public void reducirCupo() {
		for (Atraccion atraccion : atracciones)
			atraccion.reducirCupo();
	}

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
		String res = "";
		for (Atraccion atraccion : atracciones) {
			res = res + atraccion.getNombre() + "(" + atraccion.getTipo() + "), ";
		}

		return res.substring(0, res.length() - 2);
	}

	@Override
	public String toString() {
		return "-Atracciones incluidas: [" + this.listAtraccionesAString() + "]\n" + "-Duracion: " + this.tiempoTotal
				+ " horas\n" + "-Precio original: $" + this.precioNormal + "\n" + "-Precio con descuento: $"
				+ this.precioPromocion + "\n"

		;
	}

	@Override
	public int compareTo(Promocion otraPromo) {
		int res = Integer.compare(otraPromo.precioPromocion, this.precioPromocion);
		if (res != 0)
			return res;

		return Double.compare(otraPromo.tiempoTotal, this.tiempoTotal);
	}

	@Override
	public boolean sugerir(Scanner scanner) {
		System.out.println(this.toString());

		String respuesta = "";
		do {
			System.out.println("Acepta sugerencia? Ingrese S o N");
			respuesta = scanner.nextLine();
		} while (!respuesta.equals("S") && !respuesta.equals("s") && !respuesta.equals("N") && !respuesta.equals("n"));

		if (respuesta.equals("S") || respuesta.equals("s")) {
			System.out.println("¡Aceptada!");
			return true;
		}
		return false;

	}

}
