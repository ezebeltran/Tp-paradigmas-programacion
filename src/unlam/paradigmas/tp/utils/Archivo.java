package unlam.paradigmas.tp.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import unlam.paradigmas.tp.modelos.Itinerario;

public class Archivo {
	
	public void guardarArchivo(List<Itinerario> itinerarios) {
		FileWriter file = null;
		PrintWriter printerWriter = null;

		try {
			file = new FileWriter("salida/itinerarios.txt");
			printerWriter = new PrintWriter(file);

			Iterator<Itinerario> itItinerario=itinerarios.iterator();
			
			printerWriter.println("\nItinerarios\n");
			printerWriter.println("----------------------------------------------------------------------------------\n");
			
			while(itItinerario.hasNext()) {
				Itinerario iti=(Itinerario) itItinerario.next();
				printerWriter.println(iti.toString());
				printerWriter.println("----------------------------------------------------------------------------------\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
