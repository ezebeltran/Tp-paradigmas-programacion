package unlam.paradigmas.tp.modelos;

import java.util.LinkedHashSet;
import java.util.Iterator;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unlam.paradigmas.tp.utils.LectorDePromocion;
import unlam.paradigmas.tp.modelos.Absoluta;
import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Combo;
import unlam.paradigmas.tp.modelos.Porcentual;
import unlam.paradigmas.tp.modelos.Promocion;

public class PromocionTests {
	private LinkedHashSet<Atraccion> atraccionesEjemplo;

	@Before
	public void setUp() throws Exception {
		
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

	@Test
	public void existeElArchivo() {
		
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promociones.txt");
		Assert.assertTrue(archivoPromo.existeArvhivo());
	}
	
	@Test
	public void noExisteElArchivo() {
		
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/nada.txt");
		Assert.assertFalse(archivoPromo.existeArvhivo());
	}
	
	@Test
	public void archivoVacio() {
		
		LectorDePromocion archivoPromo = new LectorDePromocion("");
		Assert.assertFalse(archivoPromo.existeArvhivo());
	}

	
//	@Test
//	public void leeLineaPromocion() {
//		
//		String lineaEsperadaPromo = "Aventura,Porcentual,Moria,Mordor,Bosque Negro,50";
//		
//		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promociones.txt");
//		String lineaLeidaPromo =  archivoPromo.leerLineaPromocion();
//		
//		Assert.assertEquals(lineaEsperadaPromo, lineaLeidaPromo);
//		//System.out.println(lineaLeidaPromo);
//	}
	
	
	@Test
	public void leeUnaPromocionPorcentual() {
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesConDescuento = new ArrayList<Atraccion>();
		atraccionesConDescuento.add(new Atraccion("Bosque Negro", 3, 4.0, 12, "Aventura"));
		atraccionesConDescuento.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		Porcentual promoAventuraPorcentual = new Porcentual(atraccionesConDescuento, "Aventura", 20);
		promocionEsperada.add(promoAventuraPorcentual);
				
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promocionPorcentual.txt");
		ArrayList<Promocion> promocionObtenida = archivoPromo.leerPromociones(atraccionesEjemplo);
		
		Assert.assertEquals(promocionEsperada, promocionObtenida);
	}
	
	@Test
	public void leeUnaPromocionAbsoluta() {
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesConDescuento = new ArrayList<Atraccion>();
		atraccionesConDescuento.add(new Atraccion("Lothlórien"		,35	,1.0,30	,"Degustación"));
		atraccionesConDescuento.add(new Atraccion("La Comarca"		,3	,6.5,150,"Degustación"));
		Absoluta promoAventuraPorcentual = new Absoluta(atraccionesConDescuento, "Degustacion", 36);
		promocionEsperada.add(promoAventuraPorcentual);
				
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promocionAbsoluta.txt");
		ArrayList<Promocion> promocionObtenida = archivoPromo.leerPromociones(atraccionesEjemplo);
		
		Assert.assertEquals(promocionEsperada, promocionObtenida);
	}
	
	@Test
	public void leeUnaPromocionCombo() {
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesConDescuento = new ArrayList<Atraccion>();
		atraccionesConDescuento.add(new Atraccion("Minas Tirith"		,5	,2.5,25	,"Paisaje"));
		atraccionesConDescuento.add(new Atraccion("Abismo de Helm"	,5	,2.0,15	,"Paisaje"));
		atraccionesConDescuento.add(new Atraccion("Erebor"			,12	,3.0,32	,"Paisaje"));
		Combo promoAventuraPorcentual = new Combo(atraccionesConDescuento, "Paisaje", 0);
		promocionEsperada.add(promoAventuraPorcentual);
				
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promocionCombo.txt");
		ArrayList<Promocion> promocionObtenida = archivoPromo.leerPromociones(atraccionesEjemplo);
		
		Assert.assertEquals(promocionEsperada, promocionObtenida);
	}

}
