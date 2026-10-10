package archivo;



import negocio.Arista;
import negocio.Grafo;
import negocio.Vertice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class CargarArchivo {
	 public static Grafo cargarDesdeArchivoCSV(File archivo)
	            throws IOException {

	        if (archivo == null || !archivo.isFile()) {
	            throw new IOException("El archivo no existe.");
	        }

	        Grafo grafo = new Grafo();
	        Map<String, Vertice> vertices = new HashMap<>();

	        try (BufferedReader lector =  new BufferedReader(new FileReader(archivo))) {

	            String linea;
	            int numeroLinea = 0;

	            while ((linea = lector.readLine()) != null) {

	                numeroLinea++;
	                linea = linea.trim();

	                
	                if (linea.isEmpty() || linea.startsWith("#")) {
	                    continue;
	                }

	                String[] datos = linea.split(",", -1);

	                try {

	                    if (datos[0].trim().equalsIgnoreCase("VERTICE")) {

	                        if (datos.length != 4) {
	                            throw new IllegalArgumentException( "Formato de vértice incorrecto."
	                            );
	                        }

	                        String nombre = datos[1].trim();
	                        double latitud = Double.parseDouble(datos[2].trim());
	                        double longitud = Double.parseDouble(datos[3].trim());

	                        if (nombre.isEmpty() || !Double.isFinite(latitud)|| !Double.isFinite(longitud)|| latitud < -90 || latitud > 90 || longitud < -180 || longitud > 180) {
	                            throw new IllegalArgumentException( "Datos de vértice inválidos."  );
	                        }
	                        Vertice vertice = new Vertice(nombre, latitud, longitud);

	                        if (!grafo.agregarVertice(vertice)) {
	                            throw new IllegalArgumentException( "Vértice repetido: " + nombre );
	                        }

	                        vertices.put(nombre, vertice);

	                    } else if (datos[0].trim().equalsIgnoreCase("ARISTA")) {

	                        if (datos.length != 4) {
	                            throw new IllegalArgumentException("Formato de arista incorrecto." );
	                        }

	                        String nombre1 = datos[1].trim();
	                        String nombre2 = datos[2].trim();

	                        double peso = Double.parseDouble(datos[3].trim());

	                        if (!Double.isFinite(peso)) {
	                            throw new IllegalArgumentException( "El peso debe ser finito."  );
	                        }

	                        Vertice v1 = vertices.get(nombre1);
	                        Vertice v2 = vertices.get(nombre2);

	                        if (v1 == null || v2 == null) {
	                            throw new IllegalArgumentException( "La arista utiliza un vértice no cargado." );
	                        }
	                        Arista arista = new Arista(v1, v2, peso);

	                        if (!grafo.agregarArista(arista)) {
	                            throw new IllegalArgumentException(  "Arista inválida o repetida." );
	                        }

	                    } else {
	                        throw new IllegalArgumentException( "Tipo de registro desconocido." );
	                    }

	                } catch (IllegalArgumentException ex) {

	                    throw new IOException("Error en la línea " + numeroLinea  + ": " + ex.getMessage(), ex);
	                }
	            }
	        }

	        return grafo;
	    }
}
