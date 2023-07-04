package unlam.paradigmas.tp.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unlam.paradigmas.tp.main.GeneradorDeSugerencias;
import unlam.paradigmas.tp.main.Main;

public class SistemaTests {
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
	public void compraDeAtracionSinCupo() {

		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 1, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		atraccionesPromo.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		Promocion promoAventuraAbsoluta = new Absoluta("PACK River", atraccionesPromo, "Absoluta", 30);

		promoAventuraAbsoluta.reducirCupo();

		Assert.assertFalse(promoAventuraAbsoluta.tieneCupo());

	}

	@Test
	public void noOfertaAtraccionSinCupo() {
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 1, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 0, "Aventura"));
		atraccionesPromo.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		Promocion promoAventuraAbsoluta = new Absoluta("PACK River", atraccionesPromo, "Absoluta", 30);

		Assert.assertFalse(promoAventuraAbsoluta.tieneCupo());

	}

	@Test
	public void OfertaAtraccionConCupo() {
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 10, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 20, "Aventura"));
		atraccionesPromo.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		Promocion promoAventuraAbsoluta = new Absoluta("PACK River", atraccionesPromo, "Absoluta", 30);

		Assert.assertTrue(promoAventuraAbsoluta.tieneCupo());

	}

	@Test
	public void ordenacionSegunUsuario() {
		Boolean fallo = false;

		Usuario usuario = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		Collections.sort(atraccionesEjemplo);

		List<Atraccion> atraccionesFiltradas = new ArrayList<Atraccion>();
		for (Atraccion atraccionOrdenada : atraccionesEjemplo) {
			if (atraccionOrdenada.getTipo().equals(usuario.getTipoPreferido())) {
				atraccionesFiltradas.add(atraccionOrdenada);
			}
		}

		for (Atraccion atraccionRecomendada : atraccionesFiltradas) {
			if (!atraccionRecomendada.getTipo().equals(usuario.getTipoPreferido())) {
				fallo = true;
			}
		}

		Assert.assertFalse(fallo);

	}

	@Test
	public void noCompraPorTiempo() {

		Usuario usuario = new Usuario("Frodo", 10.0, 1.0, "Aventura");

		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 1, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 0, "Aventura"));
		atraccionesPromo.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		Promocion promoAventuraAbsoluta = new Absoluta("PACK River", atraccionesPromo, "Absoluta", 30);

		Assert.assertFalse(usuario.getTiempo() >= promoAventuraAbsoluta.getTiempoTotal());

	}

	@Test
	public void noCompraPorDinero() {

		Usuario usuario = new Usuario("Frodo", 1.0, 100.0, "Aventura");

		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 1, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 0, "Aventura"));
		atraccionesPromo.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		Promocion promoAventuraAbsoluta = new Absoluta("PACK River", atraccionesPromo, "Absoluta", 30);

		Assert.assertFalse(usuario.getPresupuesto() >= promoAventuraAbsoluta.getPrecioPromocion());

	}

	@Test
	public void noCompraPromocionYaComprado() {

		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 1, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 0, "Aventura"));
		atraccionesPromo.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		Promocion promoAventuraAbsoluta = new Absoluta("PACK River", atraccionesPromo, "Absoluta", 30);

		List<Promocion> promosAceptadas = new ArrayList<Promocion>();
		promosAceptadas.add(promoAventuraAbsoluta);

		Assert.assertFalse(GeneradorDeSugerencias.noRepitePromocion(promoAventuraAbsoluta, promosAceptadas));

	}

	@Test
	public void noCompraAtraccionYaComprado() {

		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 1, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 0, "Aventura"));
		atraccionesPromo.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		Promocion promoAventuraAbsoluta = new Absoluta("PACK River", atraccionesPromo, "Absoluta", 30);

		List<Promocion> promosAceptadas = new ArrayList<Promocion>();
		promosAceptadas.add(promoAventuraAbsoluta);

		Atraccion atraccionQueYaCompre = new Atraccion("Erebor", 12, 3.0, 32, "Paisaje");

		Assert.assertFalse(GeneradorDeSugerencias.norepiteAtraccionEnPromo(atraccionQueYaCompre, promosAceptadas));
	}

}
