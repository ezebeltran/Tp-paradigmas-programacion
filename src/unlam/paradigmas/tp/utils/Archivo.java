package unlam.paradigmas.tp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import unlam.paradigmas.tp.modelos.Absoluta;
import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Combo;
import unlam.paradigmas.tp.modelos.Itinerario;
import unlam.paradigmas.tp.modelos.Porcentual;
import unlam.paradigmas.tp.modelos.Promocion;
import unlam.paradigmas.tp.modelos.Usuario;

public class Archivo {

	private static String RUTA_USUARIOS = "entrada/usuarios.txt";
	private static String RUTA_ATRACCIONES = "entrada/atracciones.txt";
	private static String RUTA_PROMOS = "entrada/promos.txt";
	private static String RUTA_SALIDA = "salida/itinerarios.txt";
	private static String RUTA_FOLDER_SALIDA = "salida";

	public void guardarArchivo(List<Itinerario> itinerarios) {
		File file = null;
		PrintWriter printerWriter = null;

		try {
			file = new File(RUTA_FOLDER_SALIDA);
			file.mkdirs();
			file = new File(RUTA_SALIDA);
            file.createNewFile();
            
			printerWriter = new PrintWriter(file);
			
			Iterator<Itinerario> itItinerario = itinerarios.iterator();

			printerWriter.println("\nItinerarios\n");
			printerWriter
					.println("----------------------------------------------------------------------------------\n");

			while (itItinerario.hasNext()) {
				Itinerario iti = (Itinerario) itItinerario.next();
				printerWriter.println(iti.toString());
				printerWriter.println(
						"----------------------------------------------------------------------------------\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (printerWriter != null) {
				printerWriter.close();
			}
		}
	}

	public List<Usuario> leerArchivoUsuario() {

		String Archivo = RUTA_USUARIOS;
		List<Usuario> usuarios = new ArrayList<Usuario>();

		BufferedReader buffeReader = null;
		try {

			buffeReader = new BufferedReader(new FileReader(Archivo));

			String texto = buffeReader.readLine();

			while (texto != null) {

				String[] splitText = texto.split(",");
				if (splitText.length == 4)
					usuarios.add(new Usuario(splitText[0], Float.valueOf(splitText[1]).floatValue(),
							Float.valueOf(splitText[2]).floatValue(), splitText[3]));
				texto = buffeReader.readLine();
			}
		}

		catch (FileNotFoundException ex) {
			System.out.println("Error: Fichero no encontrado");
			ex.printStackTrace();
		}

		catch (Exception ex) {
			System.out.println("Error de lectura del fichero");
			ex.printStackTrace();
		}

		finally {

			try {

				if (buffeReader != null) {
					buffeReader.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
				return null;
			}
		}
		return usuarios;
	}

	public List<Promocion> leerArchivoPromociones(List<Atraccion> atraccionesPrincipales) {
		String archivo = RUTA_PROMOS;
		List<Promocion> promociones = new ArrayList<Promocion>();
		List<Atraccion> atracciones = null;
		String lineaPromocion = "^1\\,[\\u0020-\\u007e\\u00a0-\\u00ff]+\\,(Porcentual|Absoluta|Combo)\\,(\\d+$|\\d+\\.\\d+$)";
		String lineaAtraccion = "^2\\,[\\u0020-\\u007e\\u00a0-\\u00ff]+$";
		String texto = null;
		String[] nuevaPromo = null;
		String[] nuevaAtraccion = null;

		Scanner scanner = null;
		try {

			scanner = new Scanner(new FileInputStream(archivo));

			texto = scanner.nextLine();
			while (texto.matches(lineaPromocion) || texto.isEmpty()) {

				if (texto.isEmpty()) {
					texto = scanner.nextLine();
					continue;
				}
				if (texto.matches(lineaPromocion)) {
					nuevaPromo = texto.split(",");

					atracciones = new ArrayList<Atraccion>();

					texto = scanner.nextLine();
					while (texto.matches(lineaAtraccion) || texto.isEmpty()) {
						if (texto.isEmpty()) {
							texto = scanner.nextLine();
							continue;
						}
						if (texto.matches(lineaAtraccion)) {
							nuevaAtraccion = texto.split(",");
							for (Atraccion atraccion : atraccionesPrincipales) {
								if (atraccion.getNombre().equals(nuevaAtraccion[1])) {
									atracciones.add(atraccion);
									texto = scanner.nextLine();
									break;
								}
							}
						}
					}
				}

				if (!atracciones.isEmpty()) {

					switch (nuevaPromo[2]) {
					case "Porcentual":
						promociones.add(new Porcentual(nuevaPromo[1], atracciones, nuevaPromo[2],
								Integer.parseInt(nuevaPromo[3])));
						break;
					case "Absoluta":
						promociones.add(new Absoluta(nuevaPromo[1], atracciones, nuevaPromo[2],
								Integer.parseInt(nuevaPromo[3])));
						break;
					case "Combo":
						promociones.add(new Combo(nuevaPromo[1], atracciones, nuevaPromo[2]));
						break;
					}
				}

			}
		}

		catch (FileNotFoundException ex) {
			System.out.println("Error: Fichero no encontrado");
			ex.printStackTrace();
		}

		catch (NoSuchElementException ex) {
			if (!atracciones.isEmpty()) {

				switch (nuevaPromo[2]) {
				case "Porcentual":
					promociones.add(
							new Porcentual(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
					break;
				case "Absoluta":
					promociones.add(
							new Absoluta(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
					break;
				case "Combo":
					promociones.add(new Combo(nuevaPromo[1], atracciones, nuevaPromo[2]));
					break;
				}
			}

		}

		finally {

			try {

				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
			}
		}
		return promociones;
	}

	public List<Atraccion> leerArchivoAtracciones() {
		String Archivo = RUTA_ATRACCIONES;
		List<Atraccion> atracciones = new ArrayList<Atraccion>();

		BufferedReader buffeReader = null;
		try {

			buffeReader = new BufferedReader(new FileReader(Archivo));

			String texto = buffeReader.readLine();

			while (texto != null) {

				String[] splitText = texto.split(",");
				if (splitText.length == 5)
					atracciones.add(new Atraccion(splitText[0], Integer.parseInt(splitText[1]),
							Double.parseDouble(splitText[2]), Integer.parseInt(splitText[3]), splitText[4]));

				texto = buffeReader.readLine();
			}
		}

		catch (FileNotFoundException ex) {
			System.out.println("Error: Fichero no encontrado");
			ex.printStackTrace();
		}

		catch (Exception ex) {
			System.out.println("Error de lectura del fichero");
			ex.printStackTrace();
		}

		finally {

			try {

				if (buffeReader != null) {
					buffeReader.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
			}
		}
		return atracciones;
	}

	public List<Promocion> getPromocionesArchivoRuta(String rutaArchivo, List<Atraccion> atraccionesPrincipales) {

		List<Promocion> promociones = null;
		List<Atraccion> atracciones = null;
		String lineaPromocion = "^1\\,[\\u0020-\\u007e\\u00a0-\\u00ff]+\\,(Porcentual|Absoluta|Combo)\\,(\\d+$|\\d+\\.\\d+$)";
		String lineaAtraccion = "^2\\,[\\u0020-\\u007e\\u00a0-\\u00ff]+$";
		String texto = null;
		String[] nuevaPromo = null;
		String[] nuevaAtraccion = null;

		Scanner scanner = null;
		try {

			scanner = new Scanner(new FileInputStream(rutaArchivo));

			texto = scanner.nextLine();
			while (texto.matches(lineaPromocion) || texto.isEmpty()) {

				if (texto.isEmpty()) {
					texto = scanner.nextLine();
					continue;
				}
				if (texto.matches(lineaPromocion)) {
					nuevaPromo = texto.split(",");

					promociones = new ArrayList<Promocion>();
					atracciones = new ArrayList<Atraccion>();

					texto = scanner.nextLine();
					while (texto.matches(lineaAtraccion) || texto.isEmpty()) {
						if (texto.isEmpty()) {
							texto = scanner.nextLine();
							continue;
						}
						if (texto.matches(lineaAtraccion)) {
							nuevaAtraccion = texto.split(",");
							for (Atraccion atraccion : atraccionesPrincipales) {
								if (atraccion.getNombre().equals(nuevaAtraccion[1])) {
									atracciones.add(atraccion);
									texto = scanner.nextLine();
									break;
								}
							}
						}
					}
				}

				if (!atracciones.isEmpty()) {

					switch (nuevaPromo[2]) {
					case "Porcentual":
						promociones.add(new Porcentual(nuevaPromo[1], atracciones, nuevaPromo[2],
								Integer.parseInt(nuevaPromo[3])));
						break;
					case "Absoluta":
						promociones.add(new Absoluta(nuevaPromo[1], atracciones, nuevaPromo[2],
								Integer.parseInt(nuevaPromo[3])));
						break;
					case "Combo":
						promociones.add(new Combo(nuevaPromo[1], atracciones, nuevaPromo[2]));
						break;
					}
				}

			}
		}

		catch (FileNotFoundException ex) {
			System.out.println("Error: Fichero no encontrado");
			ex.printStackTrace();
		}

		catch (NoSuchElementException ex) {
			if (!atracciones.isEmpty()) {

				switch (nuevaPromo[2]) {
				case "Porcentual":
					promociones.add(
							new Porcentual(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
					break;
				case "Absoluta":
					promociones.add(
							new Absoluta(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
					break;
				case "Combo":
					promociones.add(new Combo(nuevaPromo[1], atracciones, nuevaPromo[2]));
					break;
				}
			}

		}

		finally {

			try {

				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
			}
		}
		return promociones;
	}

}
