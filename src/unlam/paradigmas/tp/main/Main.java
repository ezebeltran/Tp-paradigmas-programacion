package unlam.paradigmas.tp.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.sun.source.tree.WhileLoopTree;

import unlam.paradigmas.tp.logica.Logica;
import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Itinerario;
import unlam.paradigmas.tp.modelos.Promocion;
import unlam.paradigmas.tp.modelos.Usuario;
import unlam.paradigmas.tp.utils.LectorDePromocion;

public class Main {
	final static String RUTA_PROMOCIONES = "entrada/promociones.txt";

	private static Set<Atraccion> atraccionesDisponibles() {
		// Necesario para cargar las promociones
		LinkedHashSet<Atraccion> atracciones = new LinkedHashSet<Atraccion>();
		atracciones.add(new Atraccion("Moria", 10, 2.0, 6, "Aventura"));
		atracciones.add(new Atraccion("Minas Tirith", 5, 2.5, 25, "Paisaje"));
		atracciones.add(new Atraccion("La Comarca", 3, 6.5, 150, "DegustaciÃ³n"));
		atracciones.add(new Atraccion("Mordor", 25, 3.0, 4, "Aventura"));
		atracciones.add(new Atraccion("Abismo de Helm", 5, 2.0, 15, "Paisaje"));
		atracciones.add(new Atraccion("LothlÃ³rien", 35, 1.0, 30, "DegustaciÃ³n"));
		atracciones.add(new Atraccion("Erebor", 12, 3.0, 32, "Paisaje"));
		atracciones.add(new Atraccion("Bosque Negro", 3, 4.0, 12, "Aventura"));
		return atracciones;
	}

	public static void main(String[] args) {

		// Leo el archivo y lo muestro por consola

		/*
		 * ArrayList<Promocion> promociones = null;
		 * 
		 * try { LectorDePromocion archivoPromocion = new
		 * LectorDePromocion(RUTA_PROMOCIONES); promociones =
		 * archivoPromocion.leerPromociones(atraccionesDisponibles()); } catch
		 * (Exception e) { e.printStackTrace(); }
		 * 
		 * for (Promocion i : promociones) { System.out.println( i ); }
		 */

		System.out.println("\n\t Bienvenido/a a Tierra Media");
		System.out.println("----------------------------------------------------------------------------------\n");
		Scanner scanner = new Scanner(System.in);

		List<Itinerario> itinerarios = new ArrayList<Itinerario>();

		/** leer datos de entrada **/
		List<Usuario> usuarios = Logica.getUsuariosAchivo();
		List<Promocion> promociones = Logica.getPromocionesAchivo();
		List<Atraccion> atracciones = Logica.getAtraccionesAchivo();

		/** Ordenar lista promociones y atracciones **/

		Collections.sort(promociones);
		Collections.sort(atracciones);


		/** Iterar lista usuarios **/

		Iterator<Usuario> itUsuarios = usuarios.iterator();

		while (itUsuarios.hasNext()) {
			Itinerario itinerario = new Itinerario();
			Usuario usuario = (Usuario) itUsuarios.next();
			List<Promocion> promosAceptadas = new ArrayList<Promocion>();
			List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();

			itinerario.setUsuario(usuario);
			
			System.out.println("Nombre de visitante: " + usuario.getNombre()+ "\n");
			
			/**Iterar lista promociones preferidas**/

			Iterator<Promocion> itPromoPref = promociones.iterator();

			while (itPromoPref.hasNext()) {
				Promocion promocion = (Promocion) itPromoPref.next();

				if (usuario.getPresupuesto() >= promocion.getPrecioPromocion()
						&& usuario.getTiempo() >= promocion.getTiempoTotal()
						&& promocion.getTipo().equals(usuario.getTipoPreferido())) {
					/** Sugerir promo **/
					System.out.println(promocion.toString());
					System.out.println("Acepta sugerencia? Ingrese S o N");
					String respuesta = scanner.nextLine();

					if (respuesta.equals("S") || respuesta.equals("s")) {
						promosAceptadas.add(promocion);
						
						usuario.reducirPresupuesto(promocion.getPrecioPromocion());
						usuario.reducirTiempo(promocion.getTiempoTotal());
						itinerario.sumarPrecio(promocion.getPrecioPromocion());
						itinerario.sumarTiempo(promocion.getTiempoTotal());
						
						System.out.println("¡Aceptada!");
						
						/** como verifico el cupo de la promocion **/
						if (promocion.hayAtraccionSinCupo())
							itPromoPref.remove();
					}
					
					System.out.println("----------------------------------------------------------------------------------\n");
				}

			
			}
			
			/**Iterar lista atracciones preferidas**/

			Iterator<Atraccion> itAtraccionesPref = atracciones.iterator();

			while (itAtraccionesPref.hasNext()) {
				Atraccion atraccion = (Atraccion) itAtraccionesPref.next();

				if (usuario.getPresupuesto() >= atraccion.getPrecio() && usuario.getTiempo() >= atraccion.getTiempo() && atraccion.getTipo().equals(usuario.getTipoPreferido())) {

					System.out.println(atraccion.toString());
					System.out.println("Acepta sugerencia? Ingrese S o N");
					String respuesta = scanner.nextLine();

					if (respuesta.equals("S") || respuesta.equals("s")) {
						atraccionesAceptadas.add(atraccion);
						
						usuario.reducirPresupuesto(atraccion.getPrecio());
						usuario.reducirTiempo(atraccion.getTiempo());
						
						itinerario.sumarPrecio(atraccion.getPrecio());
						itinerario.sumarTiempo(atraccion.getTiempo());
						
						System.out.println("¡Aceptada!");
						
						if (atraccion.getCupo() == 0)
							itAtraccionesPref.remove();
					}

					System.out.println("----------------------------------------------------------------------------------\n");
				}

				
			}
			
			/**Iterar lista promociones preferidas**/

			Iterator<Promocion> itPromoRestantes = promociones.iterator();

			while (itPromoRestantes.hasNext()) {
				Promocion promocion = (Promocion) itPromoRestantes.next();

				if (usuario.getPresupuesto() >= promocion.getPrecioPromocion()
						&& usuario.getTiempo() >= promocion.getTiempoTotal() && !usuario.getTipoPreferido().equals(promocion.getTipo()) ) {
					/** Sugerir promo **/
					System.out.println(promocion.toString());
					System.out.println("Acepta sugerencia? Ingrese S o N");
					String respuesta = scanner.nextLine();

					if (respuesta.equals("S") || respuesta.equals("s")) {
						promosAceptadas.add(promocion);
						
						usuario.reducirPresupuesto(promocion.getPrecioPromocion());
						usuario.reducirTiempo(promocion.getTiempoTotal());
						
						itinerario.sumarPrecio(promocion.getPrecioPromocion());
						itinerario.sumarTiempo(promocion.getTiempoTotal());
						
						System.out.println("¡Aceptada!");
						/** como verifico el cupo de la promocion **/

						if (promocion.hayAtraccionSinCupo())
							itPromoRestantes.remove();
					}
					
					System.out.println("----------------------------------------------------------------------------------\n");
				}

				
			}

			/**Iterar lista atracciones restantes**/
			
			Iterator<Atraccion> itAtraccionesRestantes = atracciones.iterator();

			while (itAtraccionesRestantes.hasNext()) {
				Atraccion atraccion = (Atraccion) itAtraccionesRestantes.next();

				if (usuario.getPresupuesto() >= atraccion.getPrecio() && usuario.getTiempo() >= atraccion.getTiempo() && !usuario.getTipoPreferido().equals(atraccion.getTipo())) {
					System.out.println(atraccion.toString());
					System.out.println("Acepta sugerencia? Ingrese S o N");
					String respuesta = scanner.nextLine();

					if (respuesta.equals("S") || respuesta.equals("s")) {
						atraccionesAceptadas.add(atraccion);
						
						usuario.reducirPresupuesto(atraccion.getPrecio());
						usuario.reducirTiempo(atraccion.getTiempo());
						
						itinerario.sumarPrecio(atraccion.getPrecio());
						itinerario.sumarTiempo(atraccion.getTiempo());
						
						System.out.println("¡Aceptada!");
						
						if (atraccion.getCupo() == 0)
							itAtraccionesRestantes.remove();
					}
					
					System.out.println("----------------------------------------------------------------------------------\n");

				}

				
			}
			
			itinerario.setPromociones(promosAceptadas);
			itinerario.setAtracciones(atraccionesAceptadas);
			
			itinerarios.add(itinerario);
		}

		scanner.close();
		/** Armar lista itinerario por usuario **/
		
		for (Itinerario itinerario2 : itinerarios) {
			System.out.println(itinerario2.toString());
		}
	}

}
