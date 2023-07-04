package unlam.paradigmas.tp.modelos;

import org.junit.Assert;
import org.junit.Test;

public class UsuarioTests {

	@Test
	public void obtenerNombreTrue() {
		// Arrange
		boolean esperado = true;
		String nombre = "Frodo";
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		boolean obtenido = user.getNombre() == nombre ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void obtenerNombreFalse() {
		// Arrange
		boolean esperado = false;
		String nombre = "Sam";
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		boolean obtenido = user.getNombre() == nombre ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void obtenerPresupuestoTrue() {
		// Arrange
		boolean esperado = true;
		double presupuesto = 10.0;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		boolean obtenido = user.getPresupuesto() == presupuesto ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void obtenerPresupuestoFalse() {
		// Arrange
		boolean esperado = false;
		double presupuesto = 1.0;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		boolean obtenido = user.getPresupuesto() == presupuesto ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void obtenerTiempoTrue() {
		// Arrange
		boolean esperado = true;
		double tiempo = 7.5;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		boolean obtenido = user.getTiempo() == tiempo ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void obtenerTiempoFalse() {
		// Arrange
		boolean esperado = false;
		double tiempo = 75;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		boolean obtenido = user.getTiempo() == tiempo ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void obtenerTipoPreferidoTrue() {
		// Arrange
		boolean esperado = true;
		String tipo = "Aventura";
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		boolean obtenido = user.getTipoPreferido() == tipo ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void obtenerTipoPreferidoFalse() {
		// Arrange
		boolean esperado = false;
		String tipo = "Paisaje";
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		boolean obtenido = user.getTipoPreferido() == tipo ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void reducirPresupuestoTrue() {
		// Arrange
		boolean esperado = true;
		double presupuestoReducido = 6.7;
		double montoAReducir = 3.3;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		user.reducirPresupuesto(montoAReducir);
		boolean obtenido = user.getPresupuesto() == presupuestoReducido ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void reducirPresupuestoFalse() {
		// Arrange
		boolean esperado = false;
		double presupuestoReducido = 6.7;
		double montoAReducir = 3.5;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		user.reducirPresupuesto(montoAReducir);
		boolean obtenido = user.getPresupuesto() == presupuestoReducido ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void reducirPresupuestoError() {
		// Arrange
		boolean noReduce = false;

		double montoAReducir = 9999;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		try {
			user.reducirPresupuesto(montoAReducir);
		} catch (Exception e) {
			noReduce = true;
		}
		// Assert
		Assert.assertTrue(noReduce);
	}

	@Test
	public void reducirTiempoTrue() {
		// Arrange
		boolean esperado = true;
		double tiempoReducido = 4.2;
		double tiempoAReducir = 3.3;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		user.reducirTiempo(tiempoAReducir);
		boolean obtenido = user.getTiempo() == tiempoReducido ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void reducirTiempoFalse() {
		// Arrange
		boolean esperado = false;
		double tiempoReducido = 4.2;
		double tiempoAReducir = 3.5;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		user.reducirTiempo(tiempoAReducir);
		boolean obtenido = user.getTiempo() == tiempoReducido ? true : false;

		// Assert
		Assert.assertEquals(esperado, obtenido);
	}

	@Test
	public void reducirTiempoError() {
		// Arrange
		boolean noReduce = false;
		double tiempoAReducir = 9999;
		Usuario user = new Usuario("Frodo", 10.0, 7.5, "Aventura");

		// Act
		try {
			user.reducirTiempo(tiempoAReducir);
		} catch (Exception e) {
			noReduce = true;
		}
		// Assert
		Assert.assertTrue(noReduce);
	}
}
