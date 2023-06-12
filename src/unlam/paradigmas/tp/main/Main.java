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

public class Main {

	public static void main(String[] args) {

		Archivo archivo = new Archivo();

		System.out.println("\n\t Bienvenido/a a Tierra Media");
		System.out.println("----------------------------------------------------------------------------------\n");
		Scanner scanner = new Scanner(System.in);

		List<Itinerario> itinerarios = new ArrayList<Itinerario>();

		/** leer datos de entrada **/
		List<Usuario> usuarios = archivo.leerArchivoUsuario();
		List<Atraccion> atracciones = archivo.leerArchivoAtracciones();
		List<Promocion> promociones = archivo.leerArchivoPromociones(atracciones);

		/** Ordenar lista promociones y atracciones **/

		Collections.sort(promociones);
		Collections.sort(atracciones);

		/** Iterar lista usuarios **/

		Iterator<Usuario> itUsuarios = usuarios.iterator();

		boolean atracCupo = true;
		while (itUsuarios.hasNext() && atracCupo) {
			boolean haySugerenciaUsu = false;
			Itinerario itinerario = new Itinerario();
			Usuario usuario = (Usuario) itUsuarios.next();
			List<Promocion> promosAceptadas = new ArrayList<Promocion>();
			List<Atraccion> atraccionesAceptadas = new ArrayList<Atraccion>();

			itinerario.setUsuario(usuario);

			System.out.println("Nombre de visitante: " + usuario.getNombre() + "\n");

			/** Iterar lista promociones preferidas **/

			Iterator<Promocion> itPromoPref = promociones.iterator();

			while (itPromoPref.hasNext()) {
				Promocion promocion = (Promocion) itPromoPref.next();

				if (promocion.tieneCupo() && usuario.getPresupuesto() >= promocion.getPrecioPromocion()
						&& usuario.getTiempo() >= promocion.getTiempoTotal()
						&& noRepitePromocion(promocion, promosAceptadas)
						&& promocion.algunaAtraccionTipo(usuario.getTipoPreferido())) {
					/** Sugerir promo **/
					haySugerenciaUsu = true;
					if (promocion.sugerir(scanner)) {
						promosAceptadas.add(promocion);

						usuario.reducirPresupuesto(promocion.getPrecioPromocion());
						usuario.reducirTiempo(promocion.getTiempoTotal());
						itinerario.sumarPrecio(promocion.getPrecioPromocion());
						itinerario.sumarTiempo(promocion.getTiempoTotal());

						promocion.reducirCupo();

					}

					System.out.println(
							"----------------------------------------------------------------------------------\n");
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

					System.out.println(
							"----------------------------------------------------------------------------------\n");
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
					/** Sugerir promo **/
					haySugerenciaUsu = true;
					if (promocion.sugerir(scanner)) {
						promosAceptadas.add(promocion);

						usuario.reducirPresupuesto(promocion.getPrecioPromocion());
						usuario.reducirTiempo(promocion.getTiempoTotal());

						itinerario.sumarPrecio(promocion.getPrecioPromocion());
						itinerario.sumarTiempo(promocion.getTiempoTotal());

						promocion.reducirCupo();

					}

					System.out.println(
							"----------------------------------------------------------------------------------\n");
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

					System.out.println(
							"----------------------------------------------------------------------------------\n");

				}

			}

			if (haySugerenciaUsu) {
				itinerario.setPromociones(promosAceptadas);
				itinerario.setAtracciones(atraccionesAceptadas);

				System.out.println("Itinerario\n");
				System.out.println(itinerario.toString());

				itinerarios.add(itinerario);
			} else {
				System.out.println("No hay sugerencias para el usuario\n");
			}

			System.out.println("----------------------------------------------------------------------------------\n");
			System.out.println("Presione la tecla Enter para continuar...");
			scanner.nextLine();
			System.out.println("----------------------------------------------------------------------------------\n");

			atracCupo = existeAtraccionConCupo(atracciones);
		}

		if (itUsuarios.hasNext() && !atracCupo) {
			System.out.println("Ya no hay atracciones con cupos disponibles...");
		} else {
			System.out.println("Ya no hay usuarios...");
		}

		System.out.println("Fin del programa.");

		System.out.println("----------------------------------------------------------------------------------\n");

		new Archivo().guardarArchivo(itinerarios);

		scanner.close();
		/** Armar lista itinerario por usuario **/

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
