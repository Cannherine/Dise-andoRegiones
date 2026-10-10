package negocio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeoremaKruskal {


	public static Grafo calcularAGM(Grafo grafo) {
		Grafo agm = new Grafo();
		List<Vertice> vertices = grafo.obtenerVertices();

		for (Vertice v : vertices) {
			agm.agregarVertice(v);
		}

		List<Arista> aristas = new ArrayList<>(grafo.obtenerTodasAristas());
		aristas.sort(Comparator.comparingDouble(Arista::getPeso));

		UnionFind uf = new UnionFind(vertices);

		for (Arista arista : aristas) {
			Vertice v1 = arista.getVertice1();
			Vertice v2 = arista.getVertice2();

			if (uf.union(v1, v2)) {
				agm.agregarArista(arista);
			}
		}

		return agm;
	}


	public static List<List<Vertice>> generarRegiones(Grafo grafo, int k) {
		List<Vertice> vertices = grafo.obtenerVertices();

		if (k <= 0 || k > vertices.size()) {
			throw new IllegalArgumentException("El número de regiones k debe estar entre 1 y la cantidad total de vértices.");
		}

		Grafo agm = calcularAGM(grafo);
		List<Arista> aristasAGM = new ArrayList<>(agm.obtenerTodasAristas());
		if (aristasAGM.size() != vertices.size() - 1) {
			throw new IllegalArgumentException(
					"El grafo debe ser conexo para generar regiones"
					);
		}
		aristasAGM.sort(Comparator.comparingDouble(Arista::getPeso));

		int aristasAConservar = aristasAGM.size() - (k - 1);

		UnionFind ufRegiones = new UnionFind(vertices);

		for (int i = 0; i < aristasAConservar; i++) {
			Arista a = aristasAGM.get(i);
			ufRegiones.union(a.getVertice1(), a.getVertice2());
		}

		Map<Vertice, List<Vertice>> regionesMap = new HashMap<>();

		for (Vertice v : vertices) {
			Vertice representante = ufRegiones.buscar(v);
			regionesMap.putIfAbsent(representante, new ArrayList<>());
			regionesMap.get(representante).add(v);
		}

		return new ArrayList<>(regionesMap.values());
	}
}
