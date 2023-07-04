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

		GeneradorDeSugerencias generador = new GeneradorDeSugerencias();
		
		generador.generar();

	}
}
