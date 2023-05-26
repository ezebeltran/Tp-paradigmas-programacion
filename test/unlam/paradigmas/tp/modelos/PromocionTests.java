package unlam.paradigmas.tp.modelos;

import java.util.LinkedHashSet;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unlam.paradigmas.tp.utils.LectorDePromocion;

public class PromocionTests {
	private LinkedHashSet<Atraccion> atraccionesEjemplo;

	@Before
	public void setUp() {
		
		atraccionesEjemplo = new LinkedHashSet<Atraccion>();
		atraccionesEjemplo.add(new Atraccion("Moria"			,10	,2.0,6	,"Aventura"));
		atraccionesEjemplo.add(new Atraccion("Minas Tirith"		,5	,2.5,25	,"Paisaje"));
		atraccionesEjemplo.add(new Atraccion("La Comarca"		,3	,6.5,150,"Degustación"));
		atraccionesEjemplo.add(new Atraccion("Mordor"			,25	,3.0,4	,"Aventura"));
		atraccionesEjemplo.add(new Atraccion("Abismo de Helm"	,5	,2.0,15	,"Paisaje"));
		atraccionesEjemplo.add(new Atraccion("Lothlórien"		,35	,1.0,30	,"Degustación"));
		atraccionesEjemplo.add(new Atraccion("Erebor"			,12	,3.0,32	,"Paisaje"));
		atraccionesEjemplo.add(new Atraccion("Bosque Negro"		,3	,4.0,12	,"Aventura"));
	}

	/*
	@Test
	public void existeElArchivo() {
		Boolean fallo = false;
		try {
			LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promociones.txt");
			archivoPromo.existeArvhivo();
		} catch (Exception e) {
			fallo = true;
		}
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void noExisteElArchivo () {
		Boolean fallo = true;
		try {
			LectorDePromocion archivoPromo = new LectorDePromocion("");
			archivoPromo.existeArvhivo();
			
		} catch (Exception e) {
			fallo = false;
		}
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void archivoVacio() {
		Boolean fallo = true;
		ArrayList<Promocion> promocionesEsperado = new ArrayList<Promocion>();
		ArrayList<Promocion> promocionesObtenido = null;
		try {
			LectorDePromocion archivoPromo = new LectorDePromocion("entrada/vacio.txt");
			promocionesObtenido = archivoPromo.leerPromociones(atraccionesEjemplo);
		} catch (Exception e) {
			fallo = true;
		}
		if (promocionesEsperado.isEmpty() && promocionesObtenido.isEmpty())
			fallo=false;
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void leeUnaPromocionPorcentual() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesConDescuento = new ArrayList<Atraccion>();
		atraccionesConDescuento.add(new Atraccion("Bosque Negro", 3, 4.0, 12, "Aventura"));
		atraccionesConDescuento.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		Porcentual promoAventuraPorcentual = new Porcentual(atraccionesConDescuento, "Aventura", 20);
		promocionEsperada.add(promoAventuraPorcentual);
				
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promocionPorcentual.txt");
		
		ArrayList<Promocion> promocionObtenida = null;
		try {
			promocionObtenida = archivoPromo.leerPromociones(atraccionesEjemplo);
		} catch (Exception e) {
			fallo = true;
		}
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void leeUnaPromocionAbsoluta() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesConDescuento = new ArrayList<Atraccion>();
		atraccionesConDescuento.add(new Atraccion("Lothlórien"		,35	,1.0,30	,"Degustación"));
		atraccionesConDescuento.add(new Atraccion("La Comarca"		,3	,6.5,150,"Degustación"));
		Absoluta promoAventuraAbsoluta = new Absoluta(atraccionesConDescuento, "Degustacion", 36);
		promocionEsperada.add(promoAventuraAbsoluta);
				
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promocionAbsoluta.txt");
		ArrayList<Promocion> promocionObtenida = null;
		
		try {
			promocionObtenida = archivoPromo.leerPromociones(atraccionesEjemplo);
		} catch (Exception e) {
			fallo = true;
		}
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void leeUnaPromocionCombo() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesConDescuento = new ArrayList<Atraccion>();
		atraccionesConDescuento.add(new Atraccion("Minas Tirith"		,5	,2.5,25	,"Paisaje"));
		atraccionesConDescuento.add(new Atraccion("Abismo de Helm"	,5	,2.0,15	,"Paisaje"));
		atraccionesConDescuento.add(new Atraccion("Erebor"			,12	,3.0,32	,"Paisaje"));
		Combo promoAventuraCombo = new Combo(atraccionesConDescuento, "Paisaje", 0);
		promocionEsperada.add(promoAventuraCombo);
				
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promocionCombo.txt");
		ArrayList<Promocion> promocionObtenida = null;
		
		try {
			promocionObtenida = archivoPromo.leerPromociones(atraccionesEjemplo);
		} catch (Exception e) {
			fallo = true;
		}
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}
*/
}
