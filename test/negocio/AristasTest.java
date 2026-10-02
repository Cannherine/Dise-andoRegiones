package negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AristasTest {
	@Test
	void aristasInvertidasSonIguales() {
	    Vertice a = new Vertice("A");
	    Vertice b = new Vertice("B");
	    Arista arista1 = new Arista(a, b, 5);
	    Arista arista2 = new Arista(b, a, 5);

	    assertEquals(arista1, arista2);
	}
	@Test
	void aristasConVerticesDiferentesSonDiferentes() {
	    Vertice a = new Vertice("A");
	    Vertice b = new Vertice("B");
	    Vertice c = new Vertice("C");
	    Arista arista1 = new Arista(a, b, 5);
	    Arista arista2 = new Arista(a, c, 5);

	    assertNotEquals(arista1, arista2);
	}
	@Test
	void aristasConMismosVerticesYDistintoPesoSonIguales() {
	    Vertice a = new Vertice("A");
	    Vertice b = new Vertice("B");
	    Arista arista1 = new Arista(a, b, 5);
	    Arista arista2 = new Arista(b, a, 20);

	    assertEquals(arista1, arista2);
	}
	    @Test
	    void aristasInvertidasTienenMismoHashCode() {
	        Vertice a = new Vertice("A");
	        Vertice b = new Vertice("B");
	        Arista arista1 = new Arista(a, b, 5);
	        Arista arista2 = new Arista(b, a, 5);

	        assertEquals(arista1.hashCode(), arista2.hashCode());
	    }
	    
	    @Test
	    void aristaConMenorPesoEsMenorQueOtraConMayorPeso() {
	        Vertice a = new Vertice("A");
	        Vertice b = new Vertice("B");
	        Arista aristaMenor = new Arista(a, b, 5);
	        Arista aristaMayor = new Arista(a, b, 10);

	        assertTrue(aristaMenor.compareTo(aristaMayor) < 0);
	    }

	    @Test
	    void aristaConMayorPesoEsMayorQueOtraConMenorPeso() {
	        Vertice a = new Vertice("A");
	        Vertice b = new Vertice("B");
	        Arista aristaMayor = new Arista(a, b, 20);
	        Arista aristaMenor = new Arista(a, b, 5);

	        assertTrue(aristaMayor.compareTo(aristaMenor) > 0);
	    }

	    @Test
	    void aristasConElMismoPesoDevuelvenCeroAlCompararse() {
	        Vertice a = new Vertice("A");
	        Vertice b = new Vertice("B");
	        Vertice c = new Vertice("C");
	        
	        // Usamos distintos vértices pero mismo peso para asegurar que solo evalúa el peso
	        Arista arista1 = new Arista(a, b, 15);
	        Arista arista2 = new Arista(b, c, 15);

	        assertEquals(0, arista1.compareTo(arista2));
	    }

	    @Test
	    void gettersDevuelvenLosValoresAsignadosEnElConstructor() {
	        Vertice a = new Vertice("A");
	        Vertice b = new Vertice("B");
	        Arista arista = new Arista(a, b, 10);

	        assertEquals(a, arista.getVertice1());
	        assertEquals(b, arista.getVertice2());
	        assertEquals(10, arista.getPeso());
	    }
	}

