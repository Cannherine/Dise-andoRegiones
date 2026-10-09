package negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VerticeTest {

	@Test
	void verticesConMismoNombreSonIguales() {
	    Vertice v1 = new Vertice("A");
	    Vertice v2 = new Vertice("A");
	    assertEquals(v1, v2);
	}
	@Test
    void verticesConDistintoNombreSonDiferentes() {
        Vertice v1 = new Vertice("A");
        Vertice v2 = new Vertice("B");

        assertNotEquals(v1, v2);
    }
	@Test
	void verticesIgualesTienenMismoHashCode() {
	    Vertice v1 = new Vertice("A");
	    Vertice v2 = new Vertice("A");

	    assertEquals(v1.hashCode(), v2.hashCode());
	}
	
	@Test
    void getNombreDevuelveElNombreCorrecto() {
        Vertice v = new Vertice("Buenos Aires");
        assertEquals("Buenos Aires", v.getNombre());
    }

    @Test
    void toStringDevuelveElNombre() {
        Vertice v = new Vertice("Córdoba");
        assertEquals("Córdoba", v.toString());
    }

    @Test
    void verticeEsIgualASiMismo() {
        Vertice v = new Vertice("A");
        assertEquals(v, v); // Evalúa (this == obj)
    }

    @Test
    void verticeNoEsIgualANull() {
        Vertice v = new Vertice("A");
        assertNotEquals(null, v); // Evalúa (obj == null)
    }

    @Test
    void verticeNoEsIgualAObjetoDeOtraClase() {
        Vertice v = new Vertice("A");
        assertNotEquals("A", v); // Evalúa (!(obj instanceof Vertice))
    }
    @Test
    void coordenadasSeGuardanCorrectamente() {
        Vertice v = new Vertice("Buenos Aires", 250, 180);

        assertEquals(250, v.getX());
        assertEquals(180, v.getY());
    }
}
