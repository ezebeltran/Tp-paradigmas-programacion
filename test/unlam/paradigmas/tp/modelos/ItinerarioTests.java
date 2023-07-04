package unlam.paradigmas.tp.modelos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItinerarioTests {
	private List<Atraccion> atraccionesEjemplo;

	@Before
	public void setUp() {
		atraccionesEjemplo = new ArrayList<Atraccion>();
		atraccionesEjemplo.add(new Atraccion("Moria", 10, 2.0, 6, "Aventura"));
		atraccionesEjemplo.add(new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje"));
		atraccionesEjemplo.add(new Atraccion("La Comarca", 3, 6.5, 150, "Degustación"));
		atraccionesEjemplo.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		atraccionesEjemplo.add(new Atraccion("Abismo de Helm", 5, 2.0, 15, "Paisaje"));
		atraccionesEjemplo.add(new Atraccion("Lothlórien", 35, 1.0, 30, "Degustación"));
		atraccionesEjemplo.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		atraccionesEjemplo.add(new Atraccion("Bosque Negro", 3, 4.0, 12, "Aventura"));
	}

	@Test
	public void tieneUsuario() {
		Usuario usuario = new Usuario("Frodo", 10.0, 7.5, "Aventura");
		Itinerario itinerario = new Itinerario();
		itinerario.setUsuario(usuario);

		Assert.assertTrue(itinerario.getUsuario().equals(usuario));
	}

	@Test
	public void sumarPrecioDeViajes() {
		Itinerario itinerario = new Itinerario();
		Usuario usuario = new Usuario("Frodo", 10.0, 7.5, "Aventura");
		itinerario.setUsuario(usuario);

		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 25, 2.0, 6, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 75, 3.0, 4, "Aventura"));

		Porcentual promoAventuraPorcentual = new Porcentual("PACK Aventura", atraccionesPromo, "Porcentual", 10);

		itinerario.sumarPrecio(promoAventuraPorcentual.getPrecioPromocion());

		Assert.assertTrue(itinerario.getTotal() == 90);

	}

	@Test
	public void sumaTiempoDeViajes() {
		Itinerario itinerario = new Itinerario();
		Usuario usuario = new Usuario("Frodo", 10.0, 7.5, "Aventura");
		itinerario.setUsuario(usuario);
		
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 25, 6.0, 6, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 75, 10.0, 4, "Aventura"));
		
		Absoluta promoAventuraAbsoluta = new Absoluta("PACK River",atraccionesPromo, "Absoluta",30);
		
		itinerario.sumarTiempo(promoAventuraAbsoluta.getTiempoTotal());
		
		Assert.assertTrue(itinerario.getTiempo() == 16.0);

	}

}
