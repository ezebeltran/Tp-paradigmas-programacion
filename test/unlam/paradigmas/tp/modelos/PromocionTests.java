package unlam.paradigmas.tp.modelos;

import java.util.Vector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import unlam.paradigmas.tp.utils.LectorDePromocion;

public class PromocionTests {

	@Before
	public void setUp() throws Exception {
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
	
	@Test
	public void leeLineaPromocion() {
		
		String lineaEsperadaPromo = "Aventura,Porcentual,Moria,Mordor,Bosque Negro,50";
		
		LectorDePromocion archivoPromo = new LectorDePromocion("entrada/promociones.txt");
		String lineaLeidaPromo =  archivoPromo.leerLineaPromocion();
		
		Assert.assertEquals(lineaEsperadaPromo, lineaLeidaPromo);
		//System.out.println(lineaLeidaPromo);
	}

}
