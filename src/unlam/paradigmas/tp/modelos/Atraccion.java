package unlam.paradigmas.tp.modelos;

import unlam.paradigmas.tp.utils.ColorC;

public class Atraccion implements Comparable<Atraccion> {
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

	public Integer getCupo() {
		return cupo;
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

	public String getNombre() {
		return this.nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public Integer getCosto() {
		return this.costo;
	}

	public Double getTiempo() {
		return this.tiempo;
	}

	@Override
	public String toString() {
		return ColorC.TEXT_BLUE 
				+ "\n\tAtraccion\n"
				+ "-Nombre: [" 
				+ ColorC.TEXT_WHITE 
				+ nombre 
				+ ColorC.TEXT_BLUE
				+ "]\n "
				+ "-Precio=" + costo + "\n"
				+ " -Duracion: " + tiempo 
				+ ColorC.TEXT_RED;
	}

	@Override
	public int compareTo(Atraccion otraAtraccion) {
		int res= Integer.compare(this.costo, otraAtraccion.costo);
		if(res!=0)
			return res;
		
		return Double.compare(this.tiempo, otraAtraccion.tiempo);
}

}
