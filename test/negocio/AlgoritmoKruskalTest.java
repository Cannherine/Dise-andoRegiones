package negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import java.util.List;


class AlgoritmoKruskalTest {

	private Grafo grafo;
	private Vertice a, b, c, d;

	@BeforeEach
	void setUp() {
		grafo = new Grafo();
		a = new Vertice("A",0, 0);
		b = new Vertice("B",0, 0);
		c = new Vertice("C",0, 0);
		d = new Vertice("D",0, 0);

		grafo.agregarVertice(a);
		grafo.agregarVertice(b);
		grafo.agregarVertice(c);
		grafo.agregarVertice(d);

		grafo.agregarArista(new Arista(a, b, 3));
		grafo.agregarArista(new Arista(a, c, 5));
		grafo.agregarArista(new Arista(c, b, 2));
		grafo.agregarArista(new Arista(b, d, 8));
	}
	@Test
	void testCalcularAGMCantidadAristasYPesoMinimo() {
		Grafo agm = AlgoritmoKruskal.calcularAGM(grafo);

		assertEquals(3, agm.obtenerTodasAristas().size());

		int pesoTotal = 0;
		for (Arista arista : agm.obtenerTodasAristas()) {
			pesoTotal += arista.getPeso();
		}
		assertEquals(13, pesoTotal);
	}
	@Test
	void testGenerarRegionesKValido() {
		int k = 2; 
		List<List<Vertice>> regiones = AlgoritmoKruskal.generarRegiones(grafo, k);

		assertEquals(k, regiones.size());

		int totalVertices = 0;
		for (List<Vertice> region : regiones) {
			totalVertices += region.size();
		}
		assertEquals(4, totalVertices);
	}
	@Test
	void testGenerarRegionesKIgualAVertices() {
		int k = 4; 
		List<List<Vertice>> regiones = AlgoritmoKruskal.generarRegiones(grafo, k);

		assertEquals(4, regiones.size());

		for (List<Vertice> region : regiones) {
			assertEquals(1, region.size());
		}
	}
	@Test
	void testGenerarRegionesKInvalidoLanzaExcepcion() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			AlgoritmoKruskal.generarRegiones(grafo, 0);
		});

		assertThrows(IllegalArgumentException.class, () -> {
			AlgoritmoKruskal.generarRegiones(grafo, 5);
		});
	}
}
