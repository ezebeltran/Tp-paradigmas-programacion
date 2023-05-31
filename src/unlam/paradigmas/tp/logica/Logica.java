package unlam.paradigmas.tp.logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
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

		/*
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("frodo", 100, 12, "Aventura"));
		usuarios.add(new Usuario("Galardiel", 100, 5, "Paisaje"));
		usuarios.add(new Usuario("Sam", 36, 8, "Degustaci�n"));

		return usuarios;
*/
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

	public static List<Promocion> getPromocionesArchivoRuta(String rutaArchivo) {
		
		
//		List<Promocion> promociones = new ArrayList<Promocion>();
//
//		List<Atraccion> atraccionesPackAventura = new ArrayList<Atraccion>();
//		atraccionesPackAventura.add(new Atraccion("Bosque Negro", 3, 4.0, 12, "Aventura"));
//		atraccionesPackAventura.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
//
//		Promocion packAventura = new Porcentual("Pack aventura", atraccionesPackAventura, "Porcentual", 20);
//
//		promociones.add(packAventura);
//
//		List<Atraccion> atraccionesPackDegustacion = new ArrayList<Atraccion>();
//		atraccionesPackDegustacion.add(new Atraccion("Lothlórien", 35, 1.0, 30, "Degustación"));
//		atraccionesPackDegustacion.add(new Atraccion("La Comarca", 3, 6.5, 150, "Degustación"));
//
//		Promocion packDegustacion = new Absoluta("Pack degustacion", atraccionesPackDegustacion, "Absoluta");
//
//		promociones.add(packDegustacion);
//
//		List<Atraccion> atraccionesPackPaisajes = new ArrayList<Atraccion>();
//		List<Atraccion> atraccionesPackPaisajesGratis = new ArrayList<Atraccion>();
//		atraccionesPackPaisajes.add(new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje"));
//		atraccionesPackPaisajes.add(new Atraccion("Abismo de Helm", 5, 2.0, 15, "Paisaje"));
//		atraccionesPackPaisajesGratis.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
//
//		Promocion packPaisaje = new Combo("Pack paisaje", atraccionesPackPaisajes, "Combo",
//				atraccionesPackPaisajesGratis);
//
//		promociones.add(packPaisaje);
//
//		return promociones;
		 
		
		
		String Archivo = rutaArchivo;
		List<Promocion> promociones = new ArrayList<Promocion>();
		List<Atraccion> atracciones = new ArrayList<Atraccion>();

		// Declarar una variable BufferedReader
		BufferedReader buffeReader = null;
		try {
			// Crear un objeto BufferedReader al que se le pasa
			// un objeto FileReader con el nombre del fichero
			buffeReader = new BufferedReader(new FileReader(Archivo));
			// Leer la primera l�nea, guardando en un String
			String texto = buffeReader.readLine();
			// Repetir mientras no se llegue al final del fichero
			String[] splitTextHdr = texto.split(",");
			String[] splitTextHdrNew = null;
			while (texto != null) {
				// Separamos los datos
				// Cargamos la lista de usuarios
				String[] splitText = texto.split(",");
				if (!splitText[0].equals("1") && !splitTextHdrNew[1].equals(splitTextHdr[1])) {

					if (splitTextHdr[2].equals("Porcentual")) {
						promociones.add(new Porcentual(splitTextHdr[1], new ArrayList<Atraccion>(atracciones),
								"Porcentual", Integer.parseInt(splitTextHdr[3])));
					} else {
						if (splitTextHdr[2].equals("Absoluta")) {
							promociones.add(new Absoluta(splitTextHdr[1], new ArrayList<Atraccion>(atracciones),
									"Absoluta", Integer.parseInt(splitTextHdr[3])));
						}
						else {
							if (splitTextHdr[2].equals("Combo")) {
								promociones.add(new Combo(splitTextHdr[1], new ArrayList<Atraccion>(atracciones),
										"Combo"));
							}
						}
					}

					splitTextHdr = splitTextHdrNew;
					atracciones.clear();

				}
				if (splitText[0].equals("1")) {
					splitTextHdrNew = texto.split(",");

				} else {
					atracciones.add(new Atraccion(splitText[1], Integer.parseInt(splitText[2]),
							Double.parseDouble(splitText[3]), Integer.parseInt(splitText[4]), splitText[5]));
				}

				// Leer la siguiente l�nea
				texto = buffeReader.readLine();
			}
			if (!atracciones.isEmpty()) {
				if (splitTextHdr[2].equals("Porcentual")) {
					promociones.add(new Porcentual(splitTextHdr[1], new ArrayList<Atraccion>(atracciones), "Porcentual",
							Integer.parseInt(splitTextHdr[3])));
				} else {
					if (splitTextHdr[2].equals("Absoluta")) {
						promociones.add(new Absoluta(splitTextHdr[1], new ArrayList<Atraccion>(atracciones), "Absoluta",
								Integer.parseInt(splitTextHdr[3])));
					}
					else {
						if (splitTextHdr[2].equals("Combo")) {
							promociones.add(new Combo(splitTextHdr[1], new ArrayList<Atraccion>(atracciones),
									"Combo"));
						}
					}
				}
			}

		}
		// Captura de excepci�n por fichero no encontrado
		catch (FileNotFoundException ex) {
			System.out.println("Error: Fichero no encontrado");
			ex.printStackTrace();
		}
		// Captura de cualquier otra excepci�n
		catch (Exception ex) {
			System.out.println("Error de lectura del fichero");
			ex.printStackTrace();
		}
		// Asegurar el cierre del fichero en cualquier caso
		finally {

			try {
				// Cerrar el fichero si se ha podido abrir
				if (buffeReader != null) {
					buffeReader.close();
				}
			} catch (Exception ex) {
				System.out.println("Error al cerrar el fichero");
				ex.printStackTrace();
			}
		}
		return promociones;
	}
	
	public static List<Promocion> getPromocionesAchivo() {

		String Archivo = RUTA_PROMOS;
	    List<Promocion> promociones = new ArrayList<Promocion>();
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
		    String[] splitTextHdr =  texto.split(",");
		    String[] splitTextHdrNew = null;
		    while(texto != null) {
		    //  Separamos los datos
	// Cargamos la lista de usuarios	    	
			    String[] splitText = texto.split(",");
			    if (!splitText[0].equals("1")&& !splitTextHdrNew[1].equals(splitTextHdr[1])) 
			    {

			    	if (splitTextHdr[2].equals("Porcentual")) {
						promociones.add(new Porcentual(splitTextHdr[1], new ArrayList<Atraccion>(atracciones),
								"Porcentual", Integer.parseInt(splitTextHdr[3])));
					} else {
						if (splitTextHdr[2].equals("Absoluta")) {
							promociones.add(new Absoluta(splitTextHdr[1], new ArrayList<Atraccion>(atracciones),
									"Absoluta", Integer.parseInt(splitTextHdr[3])));
						}
						else {
							if (splitTextHdr[2].equals("Combo")) {
								promociones.add(new Combo(splitTextHdr[1], new ArrayList<Atraccion>(atracciones),
										"Combo"));
							}
						}
					}

			    	splitTextHdr = splitTextHdrNew;			    	 
			    	 atracciones.clear();

			    	
			    }
			     if (splitText[0].equals("1")){
			    	splitTextHdrNew = texto.split(",");
			    	
			    	}			    
			     else{
			    	atracciones.add(new Atraccion( splitText[1],Integer.parseInt( splitText[2]),Double.parseDouble(splitText[3]), Integer.parseInt(splitText[4]),splitText[5]));
			     }
			    
			        // Leer la siguiente l�nea
			        texto = buffeReader.readLine();    
			    }
		    
		    if (!atracciones.isEmpty()) {
				if (splitTextHdr[2].equals("Porcentual")) {
					promociones.add(new Porcentual(splitTextHdr[1], new ArrayList<Atraccion>(atracciones), "Porcentual",
							Integer.parseInt(splitTextHdr[3])));
				} else {
					if (splitTextHdr[2].equals("Absoluta")) {
						promociones.add(new Absoluta(splitTextHdr[1], new ArrayList<Atraccion>(atracciones), "Absoluta",
								Integer.parseInt(splitTextHdr[3])));
					}
					else {
						if (splitTextHdr[2].equals("Combo")) {
							promociones.add(new Combo(splitTextHdr[1], new ArrayList<Atraccion>(atracciones),
									"Combo"));
						}
					}
				}
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
