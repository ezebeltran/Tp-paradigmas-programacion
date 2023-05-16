package unlam.paradigmas.tp.main;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Promocion;
import unlam.paradigmas.tp.utils.LectorDePromocion;

public class Main {
	final static String RUTA_PROMOCIONES = "entrada/promociones.txt";

	private static Set<Atraccion> atraccionesDisponibles() {
		// Necesario para cargar las promociones
		LinkedHashSet<Atraccion> atracciones = new LinkedHashSet<Atraccion>();
		atracciones.add(new Atraccion("Moria", 10, 2.0, 6, "Aventura"));
		atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje"));
		atracciones.add(new Atraccion("La Comarca", 3, 6.5, 150, "Degustación"));
		atracciones.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		atracciones.add(new Atraccion("Abismo de Helm", 5, 2.0, 15, "Paisaje"));
		atracciones.add(new Atraccion("Lothlórien", 35, 1.0, 30, "Degustación"));
		atracciones.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		atracciones.add(new Atraccion("Bosque Negro", 3, 4.0, 12, "Aventura"));
		return atracciones;
	}

	public static void main(String[] args) {

		// Leo el archivo y lo muestro por consola
		
		ArrayList<Promocion> promociones = null;
		
		try {
			LectorDePromocion archivoPromocion = new LectorDePromocion(RUTA_PROMOCIONES);
			promociones = archivoPromocion.leerPromociones(atraccionesDisponibles());
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Promocion i : promociones) {
			System.out.println( i );
		}
	}

}
