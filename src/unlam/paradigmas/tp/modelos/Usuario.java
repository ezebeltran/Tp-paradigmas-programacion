package unlam.paradigmas.tp.modelos;

public class Usuario {
	private String nombre;
	private float presupuesto;
	private float tiempoDisponible;
	private String tipoPreferido;
	//Itinerario?
	
	//Constructor
	
	public float obtenerPresupuesto() {
		return this.presupuesto;
	}
	
	public float obtenerTiempo() {
		return this.tiempoDisponible;
	}
	
	public String obtenerTipoPreferido() {
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
