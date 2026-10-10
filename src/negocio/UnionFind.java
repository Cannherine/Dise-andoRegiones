package negocio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnionFind {
	private Map<Vertice, Vertice> padre;

	public UnionFind(List<Vertice> vertices) {
		padre = new HashMap<>();
		for (Vertice v : vertices) {
			padre.put(v, v);
		}
	}

	public Vertice buscar(Vertice v) {
		if (!padre.get(v).equals(v)) {
			padre.put(v, buscar(padre.get(v)));
		}
		return padre.get(v);
	}

	public boolean union(Vertice v1, Vertice v2) {
		Vertice raiz1 = buscar(v1);
		Vertice raiz2 = buscar(v2);

		if (raiz1.equals(raiz2)) {
			return false;
		}

		padre.put(raiz1, raiz2);
		return true;
	}

}
