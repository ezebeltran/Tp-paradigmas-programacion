package unlam.paradigmas.tp.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class LectorDePromocion {
	private File file;
	//private String carpeta = "entrada/";

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
			// Cerrar el archivo, eso es mucho muy importante
			scanner.close();
		}
		
		return linea;
	}

}
