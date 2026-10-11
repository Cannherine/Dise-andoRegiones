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

		double pesoTotal = 0;
		for (Arista arista : agm.obtenerTodasAristas()) {
			pesoTotal += arista.getPeso();
		}
		assertEquals(13.0, pesoTotal, 0.000001);
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

@Test
public void grafoDesconectadoNoPermiteGenerarRegiones() {

    Grafo grafoDesconectado = new Grafo();

    Vertice a = new Vertice("A", 0, 0);
    Vertice b = new Vertice("B", 0, 0);
    Vertice c = new Vertice("C", 0, 0);

    grafoDesconectado.agregarVertice(a);
    grafoDesconectado.agregarVertice(b);
    grafoDesconectado.agregarVertice(c);

    grafoDesconectado.agregarArista(new Arista(a, b, 2));

    assertThrows(IllegalArgumentException.class, () ->
        AlgoritmoKruskal.generarRegiones(grafoDesconectado, 2)
    );
}

@Test
public void generarUnaRegionContieneTodosLosVertices() {

    Grafo grafoPrueba = new Grafo();

    Vertice a = new Vertice("A", 0, 0);
    Vertice b = new Vertice("B", 0, 0);
    Vertice c = new Vertice("C", 0, 0);
    Vertice d = new Vertice("D", 0, 0);

    grafoPrueba.agregarVertice(a);
    grafoPrueba.agregarVertice(b);
    grafoPrueba.agregarVertice(c);
    grafoPrueba.agregarVertice(d);

    grafoPrueba.agregarArista(new Arista(a, b, 1));
    grafoPrueba.agregarArista(new Arista(b, c, 2));
    grafoPrueba.agregarArista(new Arista(c, d, 3));

    List<List<Vertice>> regiones =
        AlgoritmoKruskal.generarRegiones(grafoPrueba, 1);

    assertEquals(1, regiones.size());
    assertEquals(4, regiones.get(0).size());
}

@Test
public void agmConPesosDecimales() {

    Grafo grafoPrueba = new Grafo();

    Vertice a = new Vertice("A", 0, 0);
    Vertice b = new Vertice("B", 0, 0);
    Vertice c = new Vertice("C", 0, 0);

    grafoPrueba.agregarVertice(a);
    grafoPrueba.agregarVertice(b);
    grafoPrueba.agregarVertice(c);

    grafoPrueba.agregarArista(new Arista(a, b, 1.5));
    grafoPrueba.agregarArista(new Arista(b, c, 2.25));
    grafoPrueba.agregarArista(new Arista(a, c, 10));

    Grafo agm = AlgoritmoKruskal.calcularAGM(grafoPrueba);

    double pesoTotal = 0;

    for (Arista arista : agm.obtenerTodasAristas()) {
        pesoTotal += arista.getPeso();
    }

    assertEquals(2, agm.obtenerTodasAristas().size());
    assertEquals(3.75, pesoTotal, 0.000001);
}

}
