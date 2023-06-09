package unlam.paradigmas.tp.modelos;

public class Usuario {
	private String nombre;
	private double presupuesto;
	private double tiempoDisponible;
	private String tipoPreferido;
	//Itinerario?
	
	//Constructor
	public Usuario(String nombre, double presupuesto, double tiempoDisponible, String tipoPreferido) {
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.tipoPreferido = tipoPreferido;
	}
	
	public String getNombre() {
		return nombre;
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
	
	public void reducirPresupuesto(double valor) {
		if(valor > this.presupuesto) {
			throw new RuntimeException("El precio es mayor al presupuesto.");
		} else {
			this.presupuesto -= valor;
		}
	}
	
	public void reducirTiempo(double tiempo) {
		if(tiempo > this.tiempoDisponible) {
			throw new RuntimeException("El tiempo es mayor al tiempo disponible del usuario.");
		} else {
			this.tiempoDisponible -= tiempo;
		}
	}
	
	//agregarItinerarios
	//mostrarItinerario
}
