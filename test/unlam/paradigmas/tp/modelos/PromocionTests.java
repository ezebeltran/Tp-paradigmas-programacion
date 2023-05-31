package unlam.paradigmas.tp.modelos;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unlam.paradigmas.tp.logica.Logica;
import unlam.paradigmas.tp.utils.LectorDePromocion;

public class PromocionTests {
	private List<Atraccion> atracciones;

//	@Before
//	public void setUp() {
//		
//		atracciones = Logica.getAtraccionesAchivo();
//	}

	
//	@Test
//	public void existeElArchivo() {
//		
//		Boolean fallo = false;
//		try {
//			List<Promocion> promociones = Logica.getPromocionesAchivo();
//		} catch (Exception e) {
//			fallo = true;
//		}
//		Assert.assertFalse(fallo);
//	}
//	
//	@Test
//	public void noExisteElArchivo () {
//		Boolean fallo = true;
//		try {
//			LectorDePromocion archivoPromo = new LectorDePromocion("");
//			archivoPromo.existeArvhivo();
//			
//		} catch (Exception e) {
//			fallo = false;
//		}
//		Assert.assertFalse(fallo);
//	}
//	
//	@Test
//	public void archivoVacio() {
//		Boolean fallo = true;
//		ArrayList<Promocion> promocionesEsperado = new ArrayList<Promocion>();
//		ArrayList<Promocion> promocionesObtenido = null;
//		try {
//			LectorDePromocion archivoPromo = new LectorDePromocion("entrada/vacio.txt");
//			promocionesObtenido = archivoPromo.leerPromociones(atraccionesEjemplo);
//		} catch (Exception e) {
//			fallo = true;
//		}
//		if (promocionesEsperado.isEmpty() && promocionesObtenido.isEmpty())
//			fallo=false;
//		Assert.assertFalse(fallo);
//	}
//	
	@Test
	public void leeUnaPromocionPorcentual() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 6, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		atraccionesPromo.add(new Atraccion("Edoras", 30, 3.5, 5, "Aventura"));
		Porcentual promoAventuraPorcentual = new Porcentual("PACK Aventura", atraccionesPromo, "Porcentual", 10);
		promocionEsperada.add(promoAventuraPorcentual);
				
		List<Promocion> promocionObtenida = Logica.getPromocionesArchivoRuta("entrada/promocionPorcentual.txt");
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void leeUnaPromocionAbsoluta() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Los Argonah"	,25	,4.0,20,"Paisaje"));
		atraccionesPromo.add(new Atraccion("Rio de Rohan",20,1.5,10,"Aventura"));
		atraccionesPromo.add(new Atraccion("Gollums pool",12,4.0,15,"Aventura"));
		Absoluta promoAventuraAbsoluta = new Absoluta("PACK River",atraccionesPromo, "Absoluta",30);
		promocionEsperada.add(promoAventuraAbsoluta);
				
		List<Promocion> promocionObtenida = Logica.getPromocionesArchivoRuta("entrada/promocionAbsoluta.txt");
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void leeUnaPromocionCombo() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Minas Tirith"	,5	,2.5,25	,"Paisaje"));
		atraccionesPromo.add(new Atraccion("Abismo de Helm"	,5	,2.0,15	,"Paisaje"));
		atraccionesPromo.add(new Atraccion("Erebor"			,12	,3.0,32	,"Paisaje"));
		Combo promoAventuraCombo = new Combo("PACK AXB",atraccionesPromo, "Combo");
		promocionEsperada.add(promoAventuraCombo);
				
		List<Promocion> promocionObtenida = Logica.getPromocionesArchivoRuta("entrada/promocionCombo.txt");
		
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}

}
