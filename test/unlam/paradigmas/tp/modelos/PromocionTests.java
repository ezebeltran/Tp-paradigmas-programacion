package unlam.paradigmas.tp.modelos;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unlam.paradigmas.tp.utils.Archivo;

public class PromocionTests {
	private List<Atraccion> atraccionesEjemplo;

	@Before
	public void setUp() {
		
		atraccionesEjemplo = new ArrayList<Atraccion>();
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
	public void leeUnaPromocionPorcentual() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Moria", 10, 2.0, 6, "Aventura"));
		atraccionesPromo.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		Porcentual promoAventuraPorcentual = new Porcentual("PACK Aventura", atraccionesPromo, "Porcentual", 10);
		promocionEsperada.add(promoAventuraPorcentual);
				
		List<Promocion> promocionObtenida = new Archivo().getPromocionesArchivoRuta("entrada/promocionPorcentual.txt",atraccionesEjemplo);
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void leeUnaPromocionAbsoluta() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Minas Tirith"		,5	,2.5,25	,"Paisaje"));
		atraccionesPromo.add(new Atraccion("Abismo de Helm"		,5	,2.0,15	,"Paisaje"));
		atraccionesPromo.add(new Atraccion("Bosque Negro"		,3	,4.0,12	,"Aventura"));
		Absoluta promoAventuraAbsoluta = new Absoluta("PACK River",atraccionesPromo, "Absoluta",30);
		promocionEsperada.add(promoAventuraAbsoluta);
				
		List<Promocion> promocionObtenida = new Archivo().getPromocionesArchivoRuta("entrada/promocionAbsoluta.txt",atraccionesEjemplo);
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}
	
	@Test
	public void leeUnaPromocionCombo() {
		Boolean fallo = true;
		
		ArrayList<Promocion> promocionEsperada = new ArrayList<Promocion>();
		
		ArrayList<Atraccion> atraccionesPromo = new ArrayList<Atraccion>();
		atraccionesPromo.add(new Atraccion("Lothlórien"		,35	,1.0,30	,"Degustación"));
		atraccionesPromo.add(new Atraccion("Erebor"			,12	,3.0,32	,"Paisaje"));
		atraccionesPromo.add(new Atraccion("La Comarca"		,3	,6.5,150,"Degustación"));
		Combo promoAventuraCombo = new Combo("PACK AXB",atraccionesPromo, "Combo");
		promocionEsperada.add(promoAventuraCombo);
				
		List<Promocion> promocionObtenida = new Archivo().getPromocionesArchivoRuta("entrada/promocionCombo.txt",atraccionesEjemplo);
		
		
		if (promocionEsperada.equals(promocionObtenida))
			fallo=false;
		Assert.assertFalse(fallo);
	}

}
