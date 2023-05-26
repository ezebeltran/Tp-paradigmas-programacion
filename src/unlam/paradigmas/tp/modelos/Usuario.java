package unlam.paradigmas.tp.modelos;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoPreferido;
	//Itinerario?
	
	//Constructor
	public Usuario(String nombre, float presupuesto, float tiempoDisponible, String tipoPreferido) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoPreferido = tipoPreferido;
	}
	
	
	public double getPresupuesto() {
		return this.presupuesto;
	}

	public double getTiempo() {
		return this.tiempoDisponible;
	}
	
	public String getTipoPreferido() {
		return this.tipoPreferido;
	}
	
	public void reducirPresupuesto(float valor) {
		if(valor > this.presupuesto) {
			throw new Error("El precio es mayor al presupuesto.");
		} else {
			this.presupuesto -= valor;
		}
	}
	
	public void reducirTiempo(float tiempo) {
		if(tiempo > this.tiempoDisponible) {
			throw new Error("El tiempo es mayor al tiempo disponible del usuario.");
		} else {
			this.tiempoDisponible -= tiempo;
		}
	}
	
	//agregarItinerarios
	//mostrarItinerario
}
