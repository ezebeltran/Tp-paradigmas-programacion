package unlam.paradigmas.tp.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import unlam.paradigmas.tp.modelos.Atraccion;
import unlam.paradigmas.tp.modelos.Itinerario;
import unlam.paradigmas.tp.modelos.Promocion;
import unlam.paradigmas.tp.modelos.Usuario;
import unlam.paradigmas.tp.utils.Archivo;

public class GeneradorDeSugerencias {

	public void generar() {
		Archivo archivo = new Archivo();

		mensajeBienvenido();

		Scanner scanner = new Scanner(System.in);

		List<Itinerario> itinerarios = new ArrayList<Itinerario>();

		List<Usuario> usuarios = archivo.leerArchivoUsuario();
		List<Atraccion> atracciones = archivo.leerArchivoAtracciones();
		List<Promocion> promociones = archivo.leerArchivoPromociones(atracciones);

		Collections.sort(promociones);
		Collections.sort(atracciones);

		Iterator<Usuario> itUsuarios = usuarios.iterator();

		boolean atracCupo = true;
		while (itUsuarios.hasNext() && atracCupo) {
			boolean haySugerenciaUsu = false;
			Itinerario itinerario = new Itinerario();
			Usuario usuario = (Usuario) itUsuarios.next();
			List<Promocion> promosAceptadas = new ArrayList<Promocion>();
			List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();

			itinerario.setUsuario(usuario);

			mensajeVisitante(usuario);

			/** Iterar lista promociones preferidas **/
			Iterator<Promocion> itPromoPref = promociones.iterator();

			while (itPromoPref.hasNext()) {
				Promocion promocion = (Promocion) itPromoPref.next();

				if (promocion.tieneCupo() && usuario.getPresupuesto() >= promocion.getPrecioPromocion()
						&& usuario.getTiempo() >= promocion.getTiempoTotal()
						&& noRepitePromocion(promocion, promosAceptadas)
						&& promocion.algunaAtraccionTipo(usuario.getTipoPreferido())) {

					haySugerenciaUsu = true;
					if (promocion.sugerir(scanner)) {
						promosAceptadas.add(promocion);

						usuario.reducirPresupuesto(promocion.getPrecioPromocion());
						usuario.reducirTiempo(promocion.getTiempoTotal());
						itinerario.sumarPrecio(promocion.getPrecioPromocion());
						itinerario.sumarTiempo(promocion.getTiempoTotal());

						promocion.reducirCupo();

					}

					mensajeSeparador();
				}

			}

			/** Iterar lista atracciones preferidas **/
			Iterator<Atraccion> itAtraccionesPref = atracciones.iterator();

			while (itAtraccionesPref.hasNext()) {
				Atraccion atraccion = (Atraccion) itAtraccionesPref.next();

				if (usuario.getPresupuesto() >= atraccion.getPrecio() && usuario.getTiempo() >= atraccion.getTiempo()
						&& atraccion.tieneCupo() && norepiteAtraccion(atraccion, atraccionesAceptadas)
						&& norepiteAtraccionEnPromo(atraccion, promosAceptadas)
						&& atraccion.getTipo().equals(usuario.getTipoPreferido())) {
					haySugerenciaUsu = true;
					if (atraccion.sugerir(scanner)) {
						atraccionesAceptadas.add(atraccion);

						usuario.reducirPresupuesto(atraccion.getPrecio());
						usuario.reducirTiempo(atraccion.getTiempo());

						itinerario.sumarPrecio(atraccion.getPrecio());
						itinerario.sumarTiempo(atraccion.getTiempo());

						atraccion.reducirCupo();

					}

					mensajeSeparador();
				}

			}

			/** Iterar lista promociones restantes **/
			Iterator<Promocion> itPromoRestantes = promociones.iterator();

			while (itPromoRestantes.hasNext()) {
				Promocion promocion = (Promocion) itPromoRestantes.next();

				if (usuario.getPresupuesto() >= promocion.getPrecioPromocion()
						&& usuario.getTiempo() >= promocion.getTiempoTotal() && promocion.tieneCupo()
						&& noRepitePromocion(promocion, promosAceptadas)
						&& !promocion.algunaAtraccionTipo(usuario.getTipoPreferido())) {

					haySugerenciaUsu = true;
					if (promocion.sugerir(scanner)) {
						promosAceptadas.add(promocion);

						usuario.reducirPresupuesto(promocion.getPrecioPromocion());
						usuario.reducirTiempo(promocion.getTiempoTotal());

						itinerario.sumarPrecio(promocion.getPrecioPromocion());
						itinerario.sumarTiempo(promocion.getTiempoTotal());

						promocion.reducirCupo();

					}

					mensajeSeparador();
				}

			}

			/** Iterar lista atracciones restantes **/
			Iterator<Atraccion> itAtraccionesRestantes = atracciones.iterator();

			while (itAtraccionesRestantes.hasNext()) {
				Atraccion atraccion = (Atraccion) itAtraccionesRestantes.next();

				if (usuario.getPresupuesto() >= atraccion.getPrecio() && usuario.getTiempo() >= atraccion.getTiempo()
						&& atraccion.tieneCupo() && norepiteAtraccion(atraccion, atraccionesAceptadas)
						&& norepiteAtraccionEnPromo(atraccion, promosAceptadas)
						&& !usuario.getTipoPreferido().equals(atraccion.getTipo())) {
					haySugerenciaUsu = true;
					if (atraccion.sugerir(scanner)) {
						atraccionesAceptadas.add(atraccion);

						usuario.reducirPresupuesto(atraccion.getPrecio());
						usuario.reducirTiempo(atraccion.getTiempo());

						itinerario.sumarPrecio(atraccion.getPrecio());
						itinerario.sumarTiempo(atraccion.getTiempo());

						atraccion.reducirCupo();

					}

					mensajeSeparador();

				}

			}

			if (haySugerenciaUsu) {
				itinerario.setPromociones(promosAceptadas);
				itinerario.setAtracciones(atraccionesAceptadas);

				mensajeItinerarioDelUsuario(itinerario);

				itinerarios.add(itinerario);
			} else {
				mensajeNoHaySugerencias();
			}

			mensajeSeparador();
			mensajeTeclaParaContinuar();
			scanner.nextLine();
			mensajeSeparador();

			atracCupo = existeAtraccionConCupo(atracciones);
		}

		if (itUsuarios.hasNext() && !atracCupo) {
			mensajeNoHayCuposDisponibles();
		} else {
			mensajeNoHayMasUsuarios();
		}

		mensajeTerminoPrograma();

		mensajeSeparador();

		new Archivo().guardarArchivo(itinerarios);

		scanner.close();
	}

	private void mensajeItinerarioDelUsuario(Itinerario itinerario) {
		System.out.println("Itinerario\n");
		System.out.println(itinerario.toString());
	}

	private void mensajeVisitante(Usuario usuario) {
		System.out.println("Nombre de visitante: " + usuario.getNombre() + "\n");
	}

	private void mensajeTerminoPrograma() {
		System.out.println("Fin del programa.");
	}

	private void mensajeNoHayMasUsuarios() {
		System.out.println("Ya no hay usuarios...");
	}

	private void mensajeNoHayCuposDisponibles() {
		System.out.println("Ya no hay atracciones con cupos disponibles...");
	}

	private void mensajeTeclaParaContinuar() {
		System.out.println("Presione la tecla Enter para continuar...");
	}

	private void mensajeNoHaySugerencias() {
		System.out.println("No hay sugerencias para el usuario\n");
	}

	private void mensajeSeparador() {
		System.out.println("----------------------------------------------------------------------------------\n");
	}

	private void mensajeBienvenido() {
		System.out.println("\n\t Bienvenido/a a Tierra Media");
		mensajeSeparador();
	}

	public static boolean existeAtraccionConCupo(List<Atraccion> atracciones) {
		for (Atraccion atraccion : atracciones) {
			if (atraccion.tieneCupo())
				return true;
		}
		return false;
	}

	public static boolean noRepitePromocion(Promocion promocionPedida, List<Promocion> promosAceptadas) {
		for (Promocion promocionAceptada : promosAceptadas) {
			for (Atraccion atraccion : promocionAceptada.getAtracciones()) {
				if (promocionPedida.tieneAtraccion(atraccion.getNombre()))
					return false;
			}
		}
		return true;
	}

	public static boolean norepiteAtraccionEnPromo(Atraccion atraccionPedida, List<Promocion> promosAceptadas) {
		for (Promocion promo : promosAceptadas) {
			if (promo.tieneAtraccion(atraccionPedida.getNombre()))
				return false;
		}
		return true;
	}

	public static boolean norepiteAtraccion(Atraccion atraccionPedida, List<Atraccion> atraccionesAceptadas) {
		for (Atraccion atraccionAceptada : atraccionesAceptadas)
			if (atraccionAceptada.getNombre().equals(atraccionPedida.getNombre()))
				return false;
		return true;
	}

}
