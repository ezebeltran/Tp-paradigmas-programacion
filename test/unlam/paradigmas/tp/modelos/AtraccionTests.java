package unlam.paradigmas.tp.modelos;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AtraccionTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void crearAtraccion() {
		Atraccion atraccion = new Atraccion("Moria", 10, 2.0, 6, "Aventura");

		Assert.assertTrue(atraccion.getNombre().equals("Moria") && atraccion.getPrecio() == 10
				&& atraccion.getTiempo() == 2.0 && atraccion.getCupo() == 6 && atraccion.getTipo().equals("Aventura"));
	}

	@Test
	public void reducirCupo() {
		Atraccion atraccionAreducir = new Atraccion("Moria", 10, 2.0, 6, "Aventura");

		atraccionAreducir.reducirCupo();

		Assert.assertTrue(atraccionAreducir.getCupo() == 5);
	}

	@Test
	public void atraccionNoTieneCupo() {
		Atraccion atraccionSinCupo = new Atraccion("Moria", 10, 2.0, 0, "Aventura");

		Assert.assertFalse(atraccionSinCupo.tieneCupo());
	}

}
