package negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class UnionFindTest {

	@Test
	void verticesComienzanSeparados() {

		Vertice a = new Vertice("A");
		Vertice b = new Vertice("B");
		List<Vertice> vertices = Arrays.asList(a, b);
		UnionFind unionFind = new UnionFind(vertices);
		assertNotEquals(unionFind.buscar(a), unionFind.buscar(b));
	}

	@Test
	void unionUneDosVertices() {

		Vertice a = new Vertice("A");
		Vertice b = new Vertice("B");
		List<Vertice> vertices = Arrays.asList(a, b);
		UnionFind unionFind = new UnionFind(vertices);
		boolean resultado = unionFind.union(a, b);
		assertTrue(resultado);

		assertEquals(unionFind.buscar(a), unionFind.buscar(b));
	}

	@Test
	void unionConectaVerticesDeFormaTransitiva() {

		Vertice a = new Vertice("A");
		Vertice b = new Vertice("B");
		Vertice c = new Vertice("C");
		List<Vertice> vertices = Arrays.asList(a, b, c);
		UnionFind unionFind = new UnionFind(vertices);
		unionFind.union(a, b);
		unionFind.union(b, c);

		assertEquals(unionFind.buscar(a), unionFind.buscar(c));
	}
}