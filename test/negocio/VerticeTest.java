package negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VerticeTest {

	@Test
	void verticesConMismoNombreSonIguales() {
		Vertice v1 = new Vertice("A", 0, 0);
		Vertice v2 = new Vertice("A", 0, 0);
		assertEquals(v1, v2);
	}

	@Test
	void verticesConDistintoNombreSonDiferentes() {
		Vertice v1 = new Vertice("A", 0, 0);
		Vertice v2 = new Vertice("B", 0, 0);

		assertNotEquals(v1, v2);
	}

	@Test
	void verticesIgualesTienenMismoHashCode() {
		Vertice v1 = new Vertice("A", 0, 0);
		Vertice v2 = new Vertice("A", 0, 0);

		assertEquals(v1.hashCode(), v2.hashCode());
	}

	@Test
	void getNombreDevuelveElNombreCorrecto() {
		Vertice v = new Vertice("Buenos Aires", 0, 0);
		assertEquals("Buenos Aires", v.getNombre());
	}

	@Test
	void toStringDevuelveElNombre() {
		Vertice v = new Vertice("Córdoba", 0, 0);
		assertEquals("Córdoba", v.toString());
	}

	@Test
	void verticeEsIgualASiMismo() {
		Vertice v = new Vertice("A", 0, 0);
		assertEquals(v, v);
	}

	@Test
	void verticeNoEsIgualANull() {
		Vertice v = new Vertice("A", 0, 0);
		assertNotEquals(null, v); 
	}

	@Test
	void verticeNoEsIgualAObjetoDeOtraClase() {
		Vertice v = new Vertice("A", 0, 0);
		assertNotEquals("A", v);
	}

	@Test
	void coordenadasSeGuardanCorrectamente() {
		Vertice v = new Vertice("Buenos Aires", 250, 180);

		assertEquals(250, v.getX());
		assertEquals(180, v.getY());
	}
}
