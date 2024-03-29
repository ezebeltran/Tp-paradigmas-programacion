package unlam.paradigmas.tp.modelos;

import java.util.List;

public class Itinerario {

	private Usuario usuario;
	private List<Promocion> promociones;
	private List<Atraccion> atracciones;
	private int total;
	private double tiempo;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Promocion> getPromociones() {
		return promociones;
	}

	public void setPromociones(List<Promocion> promociones) {
		this.promociones = promociones;
	}

	public List<Atraccion> getAtracciones() {
		return atracciones;
	}

	public void setAtracciones(List<Atraccion> atracciones) {
		this.atracciones = atracciones;
	}

	public int getTotal() {
		return total;
	}

	public void sumarPrecio(int precio) {
		this.total += precio;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void sumarTiempo(double tiempo) {
		this.tiempo += tiempo;
	}

	public String mostrarPromociones() {
		String promos = "";
		for (Promocion promocion : this.promociones) {
			promos += promocion.toString() + "\n";
		}
		return promos;
	}

	public String mostrarAtracciones() {
		String atracciones = "";
		for (Atraccion atraccion : this.atracciones) {
			atracciones += atraccion.toString() + "\n";
		}

		return atracciones;
	}

	@Override
	public String toString() {
		return "Visitante: " + usuario.getNombre() + "\n\n" + this.mostrarPromociones() + this.mostrarAtracciones()
				+ "Total: $" + total + "\n" + "Tiempo: " + tiempo + " horas\n";
	}

}
