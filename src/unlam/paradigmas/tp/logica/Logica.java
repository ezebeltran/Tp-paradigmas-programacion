package unlam.paradigmas.tp.logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import unlam.paradigmas.tp.modelos.Absoluta;
import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Combo;
import unlam.paradigmas.tp.modelos.Porcentual;
import unlam.paradigmas.tp.modelos.Promocion;
import unlam.paradigmas.tp.modelos.Usuario;

public class Logica {
	
	private static String RUTA_USUARIOS = "entrada/usuarios.txt";
	private static	String RUTA_ATRACCIONES = "entrada/atracciones.txt";
	private static	String RUTA_PROMOS = "entrada/promos.txt";

	public static List<Usuario> getUsuariosAchivo() {

		String Archivo = RUTA_USUARIOS;
	    List<Usuario> usuarios = new ArrayList<Usuario>();
		
	    // Declarar una variable BufferedReader
		BufferedReader buffeReader = null;
		try {
		    // Crear un objeto BufferedReader al que se le pasa 
		    //   un objeto FileReader con el nombre del fichero
			buffeReader = new BufferedReader(new FileReader(Archivo));
		    // Leer la primera l�nea, guardando en un String
		    String texto = buffeReader.readLine();
	// Repetir mientras no se llegue al final del fichero
		    while(texto != null) {
		    //  Separamos los datos
	// Cargamos la lista de usuarios	    	
			    String[] splitText = texto.split(",");
			    if (splitText.length == 4)
			    usuarios.add( new Usuario( splitText[0],Float.valueOf( splitText[1]).floatValue(),Float.valueOf(splitText[2]).floatValue(), splitText[3] ));    
		        // Leer la siguiente l�nea
		        texto = buffeReader.readLine();
		    }
		}
		// Captura de excepci�n por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Captura de cualquier otra excepci�n
		catch(Exception ex) {
		    System.out.println("Error de lectura del fichero");
		    ex.printStackTrace();
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {

		    try{
		        // Cerrar el fichero si se ha podido abrir
		        if(buffeReader != null) {
		            buffeReader.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    	return null;
		    }
		}
		return usuarios;		
		
	}

	public static List<Promocion> getPromocionesArchivoRuta(String rutaArchivo, List<Atraccion> atraccionesPrincipales) {

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
			while (texto.matches(lineaPromocion) || texto.isEmpty() ) {
				
				if (texto.isEmpty() ) {
					texto = scanner.nextLine();
					continue;
				}
				if (texto.matches(lineaPromocion) ) {
					nuevaPromo = texto.split(",");
					
					promociones = new ArrayList<Promocion>();
					atracciones = new ArrayList<Atraccion>();
					
					texto = scanner.nextLine();
					while (texto.matches(lineaAtraccion) || texto.isEmpty() ) {
						if (texto.isEmpty() ) {
							texto = scanner.nextLine();
							continue;
						}
						if (texto.matches(lineaAtraccion) ) {
							nuevaAtraccion = texto.split(",");
							for (Atraccion atraccion : atraccionesPrincipales) {
								if ( atraccion.getNombre().equals(nuevaAtraccion[1]) ) {
									atracciones.add(atraccion);
									texto = scanner.nextLine();
									break;
								}
							}
						}
					}
				}
				
				if (!atracciones.isEmpty() ) {
				
					switch (nuevaPromo[2]) {
					case "Porcentual":
						promociones.add(new Porcentual(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
						break;
					case "Absoluta":
						promociones.add(new Absoluta(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
						break;
					case "Combo":
						promociones.add(new Combo(nuevaPromo[1], atracciones, nuevaPromo[2] ));
						break;
					}	
				}
			
			}
		}
		// Captura de excepci�n por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Se acabo el archivo queda procesar el ultimo
		catch(NoSuchElementException ex) {
			if (!atracciones.isEmpty() ) {
				
				switch (nuevaPromo[2]) {
				case "Porcentual":
					promociones.add(new Porcentual(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
					break;
				case "Absoluta":
					promociones.add(new Absoluta(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
					break;
				case "Combo":
					promociones.add(new Combo(nuevaPromo[1], atracciones, nuevaPromo[2] ));
					break;
				}	
			}
			
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {

		    try{
		        // Cerrar el fichero si se ha podido abrir
		        if(scanner != null) {
		            scanner.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    }
		}
		return promociones;		
	}
	
	public static List<Promocion> getPromocionesAchivo(List<Atraccion> atraccionesPrincipales) {

		String archivo = RUTA_PROMOS;
		List<Promocion> promociones = new ArrayList<Promocion>();
		List<Atraccion> atracciones = null;
		String lineaPromocion = "^1\\,[\\u0020-\\u007e\\u00a0-\\u00ff]+\\,(Porcentual|Absoluta|Combo)\\,(\\d+$|\\d+\\.\\d+$)";
		String lineaAtraccion = "^2\\,[\\u0020-\\u007e\\u00a0-\\u00ff]+$";
		String texto = null;
		String[] nuevaPromo = null;
		String[] nuevaAtraccion = null;
		

		// Declarar una variable Scanner
		Scanner scanner = null;
		try {

			scanner = new Scanner(new FileInputStream(archivo));
			
			
			texto = scanner.nextLine();
			while (texto.matches(lineaPromocion) || texto.isEmpty() ) {
				
				if (texto.isEmpty() ) {
					texto = scanner.nextLine();
					continue;
				}
				if (texto.matches(lineaPromocion) ) {
					nuevaPromo = texto.split(",");
					
					atracciones = new ArrayList<Atraccion>();
					
					texto = scanner.nextLine();
					while (texto.matches(lineaAtraccion) || texto.isEmpty() ) {
						if (texto.isEmpty() ) {
							texto = scanner.nextLine();
							continue;
						}
						if (texto.matches(lineaAtraccion) ) {
							nuevaAtraccion = texto.split(",");
							for (Atraccion atraccion : atraccionesPrincipales) {
								if ( atraccion.getNombre().equals(nuevaAtraccion[1]) ) {
									atracciones.add(atraccion);
									texto = scanner.nextLine();
									break;
								}
							}
						}
					}
				}
				
				if (!atracciones.isEmpty() ) {
				
					switch (nuevaPromo[2]) {
					case "Porcentual":
						promociones.add(new Porcentual(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
						break;
					case "Absoluta":
						promociones.add(new Absoluta(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
						break;
					case "Combo":
						promociones.add(new Combo(nuevaPromo[1], atracciones, nuevaPromo[2] ));
						break;
					}	
				}
			
			}
		}
		// Captura de excepci�n por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Se acabo el archivo queda procesar el ultimo
		catch(NoSuchElementException ex) {
			if (!atracciones.isEmpty() ) {
				
				switch (nuevaPromo[2]) {
				case "Porcentual":
					promociones.add(new Porcentual(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
					break;
				case "Absoluta":
					promociones.add(new Absoluta(nuevaPromo[1], atracciones, nuevaPromo[2], Integer.parseInt(nuevaPromo[3])));
					break;
				case "Combo":
					promociones.add(new Combo(nuevaPromo[1], atracciones, nuevaPromo[2] ));
					break;
				}	
			}
			
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {

		    try{
		        // Cerrar el fichero si se ha podido abrir
		        if(scanner != null) {
		            scanner.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    }
		}
		return promociones;			
	}

	public static List<Atraccion> getAtraccionesAchivo() {

		
		String Archivo = RUTA_ATRACCIONES;
		List<Atraccion> atracciones = new ArrayList<Atraccion>();

		// Declarar una variable BufferedReader
		BufferedReader buffeReader = null;
		try {
		    // Crear un objeto BufferedReader al que se le pasa 
		    //   un objeto FileReader con el nombre del fichero
			buffeReader = new BufferedReader(new FileReader(Archivo));
		    // Leer la primera l�nea, guardando en un String
		    String texto = buffeReader.readLine();
	// Repetir mientras no se llegue al final del fichero
		    while(texto != null) {
		    //  Separamos los datos
	// Cargamos la lista de usuarios	    	
			    String[] splitText = texto.split(",");
			    if (splitText.length == 5)
			    atracciones.add( new Atraccion( splitText[0],Integer.parseInt( splitText[1]),Double.parseDouble(splitText[2]), Integer.parseInt(splitText[3]),splitText[4]));    
		        // Leer la siguiente l�nea
		        texto = buffeReader.readLine();
		    }
		}
		// Captura de excepci�n por fichero no encontrado
		catch (FileNotFoundException ex) {
		    System.out.println("Error: Fichero no encontrado");
		    ex.printStackTrace();
		}
		// Captura de cualquier otra excepci�n
		catch(Exception ex) {
		    System.out.println("Error de lectura del fichero");
		    ex.printStackTrace();
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {

		    try{
		        // Cerrar el fichero si se ha podido abrir
		        if(buffeReader != null) {
		            buffeReader.close();
		        }
		    }
		    catch (Exception ex) {
		        System.out.println("Error al cerrar el fichero");
		        ex.printStackTrace();
		    }
		}
		return atracciones;
	}
	
	

}
