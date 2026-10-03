package negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GrafoTest {

    private Grafo grafo;
    private Vertice a;
    private Vertice b;
    private Vertice c;

    @BeforeEach
    void setUp() {
        grafo = new Grafo();
        a = new Vertice("A");
        b = new Vertice("B");
        c = new Vertice("C");
    }

    @Test
    void agregarVerticeNuevoDevuelveTrue() {
        assertTrue(grafo.agregarVertice(a));
    }

    @Test
    void agregarVerticeRepetidoDevuelveFalse() {
        Vertice otroA = new Vertice("A");
        grafo.agregarVertice(a);

        assertFalse(grafo.agregarVertice(otroA));
    }

    @Test
    void agregarAristaValidaDevuelveTrue() {
        grafo.agregarVertice(a);
        grafo.agregarVertice(b);
        Arista ab = new Arista(a, b, 5);

        assertTrue(grafo.agregarArista(ab));
    }

    @Test
    void agregarAristaConVerticeInexistenteDevuelveFalse() {
        grafo.agregarVertice(a);
        Arista ac = new Arista(a, c, 7);

        assertFalse(grafo.agregarArista(ac));
    }

    @Test
    void agregarAristaConAmbosVerticesInexistentesDevuelveFalse() {
        Arista ab = new Arista(a, b, 5);
        assertFalse(grafo.agregarArista(ab));
    }

    @Test
    void agregarAristaConMismoVerticeDevuelveFalse() {
        grafo.agregarVertice(a);
        Arista aa = new Arista(a, a, 5);

        assertFalse(grafo.agregarArista(aa));
    }

    @Test
    void agregarAristaRepetidaDevuelveFalse() {
        grafo.agregarVertice(a);
        grafo.agregarVertice(b);
        Arista ab = new Arista(a, b, 5);
        Arista ba = new Arista(b, a, 20);

        assertTrue(grafo.agregarArista(ab));

        // A-B y B-A representan la misma conexión,
        // aunque se intente ingresar otro peso.
        assertFalse(grafo.agregarArista(ba));
    }

    @Test
    void obtenerAristasDeUnVertice() {
        grafo.agregarVertice(a);
        grafo.agregarVertice(b);
        grafo.agregarVertice(c);
        grafo.agregarArista(new Arista(a, b, 5));
        grafo.agregarArista(new Arista(a, c, 8));

        assertEquals(2, grafo.obtenerAristas(a).size());
    }

    @Test
    void agregarAristaGuardaLaAristaEnAmbosVertices() {
        grafo.agregarVertice(a);
        grafo.agregarVertice(b);
        Arista ab = new Arista(a, b, 10);
        grafo.agregarArista(ab);

        assertTrue(grafo.obtenerAristas(a).contains(ab));
        assertTrue(grafo.obtenerAristas(b).contains(ab));
    }

    @Test
    void obtenerTodasLasAristasSinRepetir() {
        grafo.agregarVertice(a);
        grafo.agregarVertice(b);
        grafo.agregarVertice(c);
        grafo.agregarArista(new Arista(a, b, 6));
        grafo.agregarArista(new Arista(a, c, 3));

        assertEquals(2, grafo.obtenerTodasAristas().size());
    }

    @Test
    void obtenerTodosLosVertices() {
        grafo.agregarVertice(a);
        grafo.agregarVertice(b);
        grafo.agregarVertice(c);
        assertEquals(3, grafo.obtenerVertices().size());
    }

    @Test
    void obtenerAristasDeVerticeInexistenteDevuelveNull() {
        assertNull(grafo.obtenerAristas(a));
    }

    @Test
    void obtenerTodasAristasDeGrafoVacioDevuelveListaVacia() {

        assertTrue(grafo.obtenerTodasAristas().isEmpty());
    }
}
