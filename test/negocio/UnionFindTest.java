package negocio;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnionFindTest {
	private Vertice a,b,c;
	@BeforeEach
	void setUp() {
		a = new Vertice("A", 0, 0);
		b = new Vertice("B", 0, 0);
		c = new Vertice("C", 0, 0);
	}
	
	@Test
	void verticesComienzanSeparados() {

		List<Vertice> vertices = Arrays.asList(a, b);
		UnionFind unionFind = new UnionFind(vertices);
		assertNotEquals(unionFind.buscar(a), unionFind.buscar(b));
	}

	@Test
	void unionUneDosVertices() {

		List<Vertice> vertices = Arrays.asList(a, b);
		UnionFind unionFind = new UnionFind(vertices);
		boolean resultado = unionFind.union(a, b);
		assertTrue(resultado);

		assertEquals(unionFind.buscar(a), unionFind.buscar(b));
	}

	@Test
	void unionConectaVerticesDeFormaTransitiva() {

		List<Vertice> vertices = Arrays.asList(a, b, c);
		UnionFind unionFind = new UnionFind(vertices);
		unionFind.union(a, b);
		unionFind.union(b, c);

		assertEquals(unionFind.buscar(a), unionFind.buscar(c));
	}
}