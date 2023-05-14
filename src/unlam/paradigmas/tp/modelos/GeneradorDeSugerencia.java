package unlam.paradigmas.tp.modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class GeneradorDeSugerencia {
	//lista sin atracciones duplicadas
	private LinkedHashSet<Atraccion> atraccionesPrincipales = null;
	//lista sin usuarios duplicados
	private LinkedHashSet<Usuario> usuariosUnicos = null;
	private ArrayList<Promocion> promociones = null;
	
	public Atraccion buscarAtraccion(String nombre) {
		Atraccion encontrado = null;
		Iterator<Atraccion> it = atraccionesPrincipales.iterator();
		while (it.hasNext()) {
			Atraccion elemento = it.next();
			if (elemento.obtenerNombre().equals(nombre) ) {
				encontrado = elemento;
				break;
			}
		}
		return encontrado;
	}
	
	
}
