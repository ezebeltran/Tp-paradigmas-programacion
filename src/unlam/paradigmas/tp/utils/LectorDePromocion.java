package unlam.paradigmas.tp.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

import unlam.paradigmas.tp.modelos.Absoluta;
import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Combo;
import unlam.paradigmas.tp.modelos.Porcentual;
import unlam.paradigmas.tp.modelos.Promocion;

public class LectorDePromocion {
	private File file;
	// private String carpeta = "entrada/";

	public LectorDePromocion(String nombre) {
		file = new File(nombre);
	}

	public Boolean existeArvhivo() {
		return file.exists();
	}

	public String leerLineaPromocion() {
		Scanner scanner = null;
		String linea = null;

		try {
			scanner = new Scanner(file);
			linea = scanner.nextLine();
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}

		return linea;
	}

	public ArrayList<Promocion> leerPromociones(LinkedHashSet<Atraccion> atraccionesEjemplo) {
		Scanner scanner = null;
		String linea = null;
		ArrayList<Promocion> promociones = null;
		
		try {
			scanner = new Scanner(file);
			
			while (scanner.hasNextLine()) {
				linea = scanner.nextLine();
				promociones = new ArrayList<Promocion>();
				ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
				
				String[] strSplit = linea.split(",");
				ArrayList<String> campos =  new ArrayList<String>(Arrays.asList(strSplit));
				
				String nombrePaquete = campos.remove(0);
				String tipoDePromocion = campos.remove(0);
				
				while (campos.size()>1) {
					
					String nombreAtraccion = campos.remove(0);
					Atraccion atraccionPromo = buscarAtraccion(atraccionesEjemplo,nombreAtraccion);
					if (atraccionPromo != null)
						atracciones.add(atraccionPromo);
				}
				Double descuento = Double.parseDouble(campos.remove(0));
				
				switch (tipoDePromocion) {
				case "Porcentual":
					promociones.add(new Porcentual(atracciones, nombrePaquete, descuento));
					break;
				case "Absoluta":
					promociones.add(new Absoluta(atracciones, nombrePaquete, descuento));
					break;
				case "Combo":
					promociones.add(new Combo(atracciones, nombrePaquete, descuento));
					break;
				default:
					break;
				}				
			}
			
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
		return promociones;
	}

	private Atraccion buscarAtraccion(LinkedHashSet<Atraccion> atracciones, String nombreAtraccion) {
		Atraccion encontrado = null;
		Iterator<Atraccion> it = atracciones.iterator();
		while (it.hasNext()) {
			Atraccion elemento = it.next();
			if (elemento.obtenerNombre().equals(nombreAtraccion) ) {
				encontrado = elemento;
				break;
			}
		}
		return encontrado;
	}

}
