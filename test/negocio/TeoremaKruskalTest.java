package negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import java.util.List;


class TeoremaKruskalTest {

    private Grafo grafo;
    private Vertice a, b, c, d;

    @BeforeEach
    void setUp() {
        grafo = new Grafo();
        a = new Vertice("A");
        b = new Vertice("B");
        c = new Vertice("C");
        d = new Vertice("D");

        grafo.agregarVertice(a);
        grafo.agregarVertice(b);
        grafo.agregarVertice(c);
        grafo.agregarVertice(d);

        // Agregamos aristas creando un ciclo (A-B-C-A) para forzar a Kruskal a filtrar la más pesada
        grafo.agregarArista(new Arista(a, b, 3));
        grafo.agregarArista(new Arista(a, c, 5));
        grafo.agregarArista(new Arista(c, b, 2)); // Arista liviana que cierra el ciclo
        grafo.agregarArista(new Arista(b, d, 8));
    }

    @Test
    void testCalcularAGM_CantidadAristasYPesoMinimo() {
        // Llamada correcta al método estático
        Grafo agm = TeoremaKruskal.calcularAGM(grafo);

        // 1. Verifica que tenga exactamente V - 1 aristas (4 vértices -> 3 aristas)
        assertEquals(3, agm.obtenerTodasAristas().size());

        // 2. Verifica que el peso total sea el mínimo posible (2 + 3 + 8 = 13)
        int pesoTotal = 0;
        for (Arista arista : agm.obtenerTodasAristas()) {
            pesoTotal += arista.getPeso();
        }
        assertEquals(13, pesoTotal);
    }

    @Test
    void testGenerarRegiones_KValido() {
        int k = 2; // Queremos separar el grafo en 2 regiones
        List<List<Vertice>> regiones = TeoremaKruskal.generarRegiones(grafo, k);

        // Debe retornar 2 regiones
        assertEquals(k, regiones.size());

        // Comprueba que todos los vértices sigan presentes
        int totalVertices = 0;
        for (List<Vertice> region : regiones) {
            totalVertices += region.size();
        }
        assertEquals(4, totalVertices);
    }

    @Test
    void testGenerarRegiones_KIgualAVertices() {
        int k = 4; // Tantas regiones como vértices
        List<List<Vertice>> regiones = TeoremaKruskal.generarRegiones(grafo, k);

        assertEquals(4, regiones.size());

        // Cada región debe contener exactamente 1 vértice
        for (List<Vertice> region : regiones) {
            assertEquals(1, region.size());
        }
    }

    @Test
    void testGenerarRegiones_KInvalido_LanzaExcepcion() {
        // Caso k = 0
        assertThrows(IllegalArgumentException.class, () -> {
            TeoremaKruskal.generarRegiones(grafo, 0);
        });

        // Caso k mayor a la cantidad de vértices (k = 5)
        assertThrows(IllegalArgumentException.class, () -> {
            TeoremaKruskal.generarRegiones(grafo, 5);
        });
    }
}
