package unlam.paradigmas.tp.logica;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import unlam.paradigmas.tp.modelos.Absoluta;
import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Combo;
import unlam.paradigmas.tp.modelos.Porcentual;
import unlam.paradigmas.tp.modelos.Promocion;
import unlam.paradigmas.tp.modelos.Usuario;

public class Logica {

	public static List<Usuario> getUsuariosAchivo() {

		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("frodo", 100, 12, "Aventura"));
		usuarios.add(new Usuario("Galardiel", 100, 5, "Paisaje"));
		usuarios.add(new Usuario("Sam", 36, 8, "Degustaci髇"));

		return usuarios;

	}

	public static List<Promocion> getPromocionesAchivo() {

		List<Promocion> promociones = new ArrayList<Promocion>();

		List<Atraccion> atraccionesPackAventura = new ArrayList<Atraccion>();
		atraccionesPackAventura.add(new Atraccion("Bosque Negro", 3, 4.0, 12, "Aventura"));
		atraccionesPackAventura.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));

		Promocion packAventura = new Porcentual("Pack aventura",atraccionesPackAventura, "Porcentual", 20);

		promociones.add(packAventura);
		
		List<Atraccion> atraccionesPackDegustacion = new ArrayList<Atraccion>();
		atraccionesPackDegustacion.add(new Atraccion("Lothl贸rien", 35, 1.0, 30, "Degustaci贸n"));
		atraccionesPackDegustacion.add(new Atraccion("La Comarca", 3, 6.5, 150, "Degustaci贸n"));

		Promocion packDegustacion = new Absoluta("Pack degustacion", atraccionesPackDegustacion, "Absoluta");

		promociones.add(packDegustacion);

		List<Atraccion> atraccionesPackPaisajes = new ArrayList<Atraccion>();
		List<Atraccion> atraccionesPackPaisajesGratis = new ArrayList<Atraccion>();
		atraccionesPackPaisajes.add(new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje"));
		atraccionesPackPaisajes.add(new Atraccion("Abismo de Helm", 5, 2.0, 15, "Paisaje"));
		atraccionesPackPaisajesGratis.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		
		Promocion packPaisaje = new Combo("Pack paisaje", atraccionesPackPaisajes, "Combo", atraccionesPackPaisajesGratis);

		promociones.add(packPaisaje);
		
		return promociones;

	}

	public static List<Atraccion> getAtraccionesAchivo() {

		List<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(new Atraccion("Moria", 10, 2.0, 6, "Aventura"));
		atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje"));
		atracciones.add(new Atraccion("La Comarca", 3, 6.5, 150, "Degustaci贸n"));
		atracciones.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		atracciones.add(new Atraccion("Abismo de Helm", 5, 2.0, 15, "Paisaje"));
		atracciones.add(new Atraccion("Lothl贸rien", 35, 1.0, 30, "Degustaci贸n"));
		atracciones.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		atracciones.add(new Atraccion("Bosque Negro", 3, 4.0, 12, "Aventura"));

		return atracciones;

	}
	
	

}
