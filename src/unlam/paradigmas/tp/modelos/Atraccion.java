package unlam.paradigmas.tp.modelos;

import unlam.paradigmas.tp.utils.ColorC;

public class Atraccion {
	private String nombre;
	private Integer costo;
	private Double tiempo;
	private Integer cupo;
	private String tipo;

	public Atraccion(String nombre, Integer costo, Double tiempo, Integer cupo, String tipo) {
		this.nombre = nombre;
		this.costo = costo;
		this.tiempo = tiempo;
		this.cupo = cupo;
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((costo == null) ? 0 : costo.hashCode());
		result = prime * result + ((cupo == null) ? 0 : cupo.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tiempo == null) ? 0 : tiempo.hashCode());
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
		Atraccion other = (Atraccion) obj;
		if (costo == null) {
			if (other.costo != null) {
				return false;
			}
		} else if (!costo.equals(other.costo)) {
			return false;
		}
		if (cupo == null) {
			if (other.cupo != null) {
				return false;
			}
		} else if (!cupo.equals(other.cupo)) {
			return false;
		}
		if (nombre == null) {
			if (other.nombre != null) {
				return false;
			}
		} else if (!nombre.equals(other.nombre)) {
			return false;
		}
		if (tiempo == null) {
			if (other.tiempo != null) {
				return false;
			}
		} else if (!tiempo.equals(other.tiempo)) {
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

	public String obtenerNombre() {
		return this.nombre;
	}

	public String obtenerTipo() {
		return this.tipo;
	}

	public Integer obtenerCosto() {
		return this.costo;
	}

	public Double obtenerTiempo() {
		return this.tiempo;
	}

	@Override
	public String toString() {
		return ColorC.TEXT_BLUE 
				+ "\n\tAtraccion [nombre=" 
				+ ColorC.TEXT_WHITE 
				+ nombre 
				+ ColorC.TEXT_BLUE
				+ ", costo=" + costo + ", tiempo=" + tiempo 
				+ ", cupo="	+ cupo + ", tipo=" + tipo + "]" 
				+ ColorC.TEXT_RED;
	}

}
