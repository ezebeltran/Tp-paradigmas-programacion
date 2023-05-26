package unlam.paradigmas.tp.modelos;

import java.util.List;

public class Itinerario {

	private Usuario usuario;
	private List<Promocion> promociones;
	private List<Atraccion> atracciones;
	private int total;
	private int tiempo;
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
	
	public int getTiempo() {
		return tiempo;
	}
	public void sumarTiempo(double tiempo) {
		this.tiempo += tiempo;
	}
	@Override
	public String toString() {
		return "Itinerario [usuario=" + usuario + ", promociones=" + promociones + ", atracciones=" + atracciones
				+ ", total=" + total + ", tiempo=" + tiempo + "]";
	}
	
	
}
