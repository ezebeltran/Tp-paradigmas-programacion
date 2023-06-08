package unlam.paradigmas.tp.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import unlam.paradigmas.tp.logica.Logica;
import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Itinerario;
import unlam.paradigmas.tp.modelos.Promocion;
import unlam.paradigmas.tp.modelos.Usuario;

public class Main {
	final static String RUTA_PROMOCIONES = "entrada/promociones.txt";


	public static void main(String[] args) {


		System.out.println("\n\t Bienvenido/a a Tierra Media");
		System.out.println("----------------------------------------------------------------------------------\n");
		Scanner scanner = new Scanner(System.in);

		List<Itinerario> itinerarios = new ArrayList<Itinerario>();

		/** leer datos de entrada **/
		List<Usuario> usuarios = Logica.getUsuariosAchivo();
		List<Atraccion> atracciones = Logica.getAtraccionesAchivo();
		List<Promocion> promociones = Logica.getPromocionesAchivo(atracciones);
		

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

				if (
						usuario.getPresupuesto() >= promocion.getPrecioPromocion()
						&& usuario.getTiempo() >= promocion.getTiempoTotal() 
						&& promocion.tieneCupo()
						&& noRepitePromocion(promocion,promosAceptadas)// se pueden repetir las promociones ??
						&& promocion.algunaAtraccionTipo(usuario.getTipoPreferido())	) {
					/** Sugerir promo **/
//					System.out.println(promocion.toString());
//					System.out.println("Acepta sugerencia? Ingrese S o N");
//					String respuesta = scanner.nextLine();

					//if (respuesta.equals("S") || respuesta.equals("s")) {
					if (promocion.sugerir(scanner)) {
						promosAceptadas.add(promocion);
						//agregarAtraccionesDePromo(promocion, atraccionesAceptadas);
						
						usuario.reducirPresupuesto(promocion.getPrecioPromocion());
						usuario.reducirTiempo(promocion.getTiempoTotal());
						itinerario.sumarPrecio(promocion.getPrecioPromocion());
						itinerario.sumarTiempo(promocion.getTiempoTotal());
						
						//System.out.println("ï¿½Aceptada!");
						
						//reducirCuposDePromocion(promocion);
						promocion.reducirCupo();

						/** como verifico el cupo de la promocion **/
						/*if (promocion.hayAtraccionSinCupo())
							itPromoPref.remove();*/
					}
					
					System.out.println("----------------------------------------------------------------------------------\n");
				}

			
			}
			
			/**Iterar lista atracciones preferidas**/

			Iterator<Atraccion> itAtraccionesPref = atracciones.iterator();

			while (itAtraccionesPref.hasNext()) {
				Atraccion atraccion = (Atraccion) itAtraccionesPref.next();

				if (
						usuario.getPresupuesto() >= atraccion.getPrecio() 
						&& usuario.getTiempo() >= atraccion.getTiempo() 
						&& atraccion.tieneCupo()
						&& norepiteAtraccion(atraccion,atraccionesAceptadas)
						&& norepiteAtraccionEnPromo(atraccion, promosAceptadas)
						&& atraccion.getTipo().equals(usuario.getTipoPreferido())	) {

//					System.out.println(atraccion.toString());
//					System.out.println("Acepta sugerencia? Ingrese S o N");
//					String respuesta = scanner.nextLine();
//
//					if (respuesta.equals("S") || respuesta.equals("s")) {
					if (atraccion.sugerir(scanner)) {
						atraccionesAceptadas.add(atraccion);
						
						usuario.reducirPresupuesto(atraccion.getPrecio());
						usuario.reducirTiempo(atraccion.getTiempo());
						
						itinerario.sumarPrecio(atraccion.getPrecio());
						itinerario.sumarTiempo(atraccion.getTiempo());
						
						//System.out.println("ï¿½Aceptada!");
						atraccion.reducirCupo();
						
						/*if (atraccion.getCupo() == 0)
							itAtraccionesPref.remove();*/
					}

					System.out.println("----------------------------------------------------------------------------------\n");
				}

				
			}
			
			/**Iterar lista promociones restantes**/

			Iterator<Promocion> itPromoRestantes = promociones.iterator();

			while (itPromoRestantes.hasNext()) {
				Promocion promocion = (Promocion) itPromoRestantes.next();

				if (
						usuario.getPresupuesto() >= promocion.getPrecioPromocion()
						&& usuario.getTiempo() >= promocion.getTiempoTotal() 
						&& promocion.tieneCupo()
						&& noRepitePromocion(promocion,promosAceptadas)
						&& !promocion.algunaAtraccionTipo(usuario.getTipoPreferido()) ) {
					/** Sugerir promo **/
//					System.out.println(promocion.toString());
//					System.out.println("Acepta sugerencia? Ingrese S o N");
//					String respuesta = scanner.nextLine();
//
//					if (respuesta.equals("S") || respuesta.equals("s")) {
					if (promocion.sugerir(scanner)) {
						promosAceptadas.add(promocion);
						//agregarAtraccionesDePromo(promocion, atraccionesAceptadas);
						
						usuario.reducirPresupuesto(promocion.getPrecioPromocion());
						usuario.reducirTiempo(promocion.getTiempoTotal());
						
						itinerario.sumarPrecio(promocion.getPrecioPromocion());
						itinerario.sumarTiempo(promocion.getTiempoTotal());
						
						//System.out.println("ï¿½Aceptada!");

						//reducirCuposDePromocion(promocion); // podria ser un metodo de la clase promocion???
						promocion.reducirCupo();
						/*if (promocion.hayAtraccionSinCupo())
							itPromoRestantes.remove();*/
					}
					
					System.out.println("----------------------------------------------------------------------------------\n");
				}

				
			}

			/**Iterar lista atracciones restantes**/
			
			Iterator<Atraccion> itAtraccionesRestantes = atracciones.iterator();

			while (itAtraccionesRestantes.hasNext()) {
				Atraccion atraccion = (Atraccion) itAtraccionesRestantes.next();

				if (
						usuario.getPresupuesto() >= atraccion.getPrecio() 
						&& usuario.getTiempo() >= atraccion.getTiempo() 
						&& atraccion.tieneCupo()
						&& norepiteAtraccion(atraccion,atraccionesAceptadas)
						&& norepiteAtraccionEnPromo(atraccion, promosAceptadas)
						&& !usuario.getTipoPreferido().equals(atraccion.getTipo())	) {
					
//					System.out.println(atraccion.toString());
//					System.out.println("Acepta sugerencia? Ingrese S o N");
//					String respuesta = scanner.nextLine();
//
//					if (respuesta.equals("S") || respuesta.equals("s")) {
					if (atraccion.sugerir(scanner)) {
						atraccionesAceptadas.add(atraccion);
						
						usuario.reducirPresupuesto(atraccion.getPrecio());
						usuario.reducirTiempo(atraccion.getTiempo());
						
						itinerario.sumarPrecio(atraccion.getPrecio());
						itinerario.sumarTiempo(atraccion.getTiempo());
						
						//System.out.println("ï¿½Aceptada!");
						atraccion.reducirCupo();
						
						/*if (atraccion.getCupo() == 0)
							itAtraccionesRestantes.remove();*/
					}
					
					System.out.println("----------------------------------------------------------------------------------\n");

				}

				
			}
			
			itinerario.setPromociones(promosAceptadas);
			itinerario.setAtracciones(atraccionesAceptadas);
			
			System.out.println("Itinerario\n");
			System.out.println(itinerario.toString());
			System.out.println("----------------------------------------------------------------------------------\n");
			
			itinerarios.add(itinerario);
		}

		scanner.close();
		/** Armar lista itinerario por usuario **/
		
		/*System.out.println("Itinerarios\n");
		for (Itinerario itinerario2 : itinerarios) {
			System.out.println(itinerario2.toString());
			System.out.println("----------------------------------------------------------------------------------\n");
		}*/
	}
	

	private static void agregarAtraccionesDePromo(Promocion promocionAgregada, List<Atraccion> atraccionesAceptadas) {
		for (Atraccion atraccion : promocionAgregada.getAtracciones() )
			atraccionesAceptadas.add(atraccion);
	}
	
	/*private static void reducirCuposDePromocion(Promocion promocionAceptada ) {
		for (Atraccion atraccionAceptada : promocionAceptada.getAtracciones() )
			atraccionAceptada.reducirCupo();
	}*/

	private static boolean norepiteAtraccion(Atraccion atraccionPedida, List<Atraccion> atraccionesAceptadas) {
		for (Atraccion atraccionAceptada : atraccionesAceptadas )
			if (atraccionAceptada.getNombre().equals(atraccionPedida.getNombre()) )
				return false;
		return true;
	}
	
	private static boolean norepiteAtraccionEnPromo(Atraccion atraccionPedida, List<Promocion> promosAceptadas) {
		for (Promocion promo : promosAceptadas ) {
			if(promo.tieneAtraccion(atraccionPedida.getNombre()))
				return false;
		}
		return true;
	}

	private static boolean noRepitePromocion(Promocion promocionPedida, List<Promocion> promosAceptadas) {
		for (Promocion promocionAceptada : promosAceptadas ) {
			for (Atraccion atraccion : promocionAceptada.getAtracciones()) {
				if(promocionPedida.tieneAtraccion(atraccion.getNombre()))
					return false;
			}
		}
		return true;
	}

}
