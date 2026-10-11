
package archivo;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import negocio.Grafo;

class LectorGrafoTest {

	@TempDir
	Path directorioTemporal;

	private File crearArchivo(String contenido) throws IOException {
		Path archivo = directorioTemporal.resolve("grafo.csv");
		Files.writeString(archivo, contenido);
		return archivo.toFile();
	}

	@Test
	void cargarGrafoValido() throws IOException {
		File archivo = crearArchivo("VERTICE,Buenos Aires,-34.6,-58.4\n" + "VERTICE,Cordoba,-31.4,-64.2\n"+ "ARISTA,Buenos Aires,Cordoba,650.5\n");

		Grafo grafo = LectorGrafo.cargarDesdeArchivo(archivo);

		assertNotNull(grafo);
		assertEquals(2, grafo.obtenerVertices().size());
		assertEquals(1, grafo.obtenerTodasAristas().size());
		assertEquals(650.5, grafo.obtenerTodasAristas().get(0).getPeso(), 0.000001);
	}

	@Test
	void cargarArchivoVacio() throws IOException {
		File archivo = crearArchivo("");

		Grafo grafo = LectorGrafo.cargarDesdeArchivo(archivo);

		assertNotNull(grafo);
	}

	@Test
	void ignorarComentariosYLineasVacias() throws IOException {
		File archivo = crearArchivo("# Comentario\n" + "\n" + "VERTICE,Buenos Aires,-34.6,-58.4\n" + "   \n");

		Grafo grafo = LectorGrafo.cargarDesdeArchivo(archivo);

		assertNotNull(grafo);
	}

	@Test
	void archivoNuloLanzaExcepcion() {
		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(null));

		assertEquals("El archivo no existe.", excepcion.getMessage());
	}

	@Test
	void archivoInexistenteLanzaExcepcion() {
		File archivo = directorioTemporal.resolve("inexistente.csv").toFile();

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertEquals("El archivo no existe.", excepcion.getMessage());
	}

	@Test
	void verticeConFormatoIncorrecto() throws IOException {
		File archivo = crearArchivo("VERTICE,Buenos Aires,-34.6\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("línea 1"));
		assertTrue(excepcion.getMessage().contains("Formato de vértice incorrecto."));
	}

	@Test
	void verticeConLatitudFueraDeRango() throws IOException {
		File archivo = crearArchivo("VERTICE,Buenos Aires,95,-58.4\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("Datos de vértice inválidos."));
	}

	@Test
	void verticeConLongitudFueraDeRango() throws IOException {
		File archivo = crearArchivo("VERTICE,Buenos Aires,-34.6,200\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("Datos de vértice inválidos."));
	}

	@Test
	void verticeConCoordenadasNoNumericas() throws IOException {
		File archivo = crearArchivo("VERTICE,Buenos Aires,abc,-58.4\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("For input string"));
	}

	@Test
	void verticeDuplicadoLanzaExcepcion() throws IOException {
		File archivo = crearArchivo("VERTICE,Buenos Aires,-34.6,-58.4\n" + "VERTICE,Buenos Aires,-35.0,-59.0\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("Vértice repetido"));
		assertTrue(excepcion.getMessage().contains("línea 2"));
	}

	@Test
	void aristaConFormatoIncorrecto() throws IOException {
		File archivo = crearArchivo("VERTICE,A,-34,-58\n" + "VERTICE,B,-35,-59\n" + "ARISTA,A,B\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("Formato de arista incorrecto."));
	}

	@Test
	void aristaConVerticeInexistente() throws IOException {
		File archivo = crearArchivo("VERTICE,A,-34,-58\n" + "ARISTA,A,B,10\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("La arista utiliza un vértice no cargado."));
	}

	@Test
	void aristaConPesoNoNumerico() throws IOException {
		File archivo = crearArchivo("VERTICE,A,-34,-58\n" + "VERTICE,B,-35,-59\n" + "ARISTA,A,B,abc\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("For input string"));
	}

	@Test
	void registroDesconocidoLanzaExcepcion() throws IOException {
		File archivo = crearArchivo("OTRO,A,B,10\n");

		IOException excepcion = assertThrows(IOException.class, () -> LectorGrafo.cargarDesdeArchivo(archivo));

		assertTrue(excepcion.getMessage().contains("Tipo de registro desconocido."));
	}


}
