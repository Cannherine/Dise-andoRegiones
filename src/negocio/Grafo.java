package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
	private Map<Vertice, List<Arista>> adyacencias;

	public Grafo() {
		adyacencias = new HashMap<>();
	}

	public boolean agregarVertice(Vertice vertice) {
		if (adyacencias.containsKey(vertice)) { 
			return false; 
		}
		adyacencias.put(vertice, new ArrayList<>());
		return true;
	}

	public boolean agregarArista(Arista arista) {
		Vertice v1 = arista.getVertice1();
		Vertice v2 = arista.getVertice2();
		if (!adyacencias.containsKey(v1) || !adyacencias.containsKey(v2)) {
			return false;
		}
		if (v1.equals(v2)) {
			return false;
		}
		if (adyacencias.get(v1).contains(arista)) {
			return false;
		}
		adyacencias.get(v1).add(arista); 
		adyacencias.get(v2).add(arista);
		return true;
	}

	public List<Arista> obtenerAristas(Vertice vertice) {
	    List<Arista> aristas = adyacencias.get(vertice);
	    if (aristas == null) {
	        throw new IllegalArgumentException("El vértice no existe en el grafo");
	    }
	    return new ArrayList<>(aristas); 
	}

	public List<Arista> obtenerTodasAristas() {
		List<Arista> todasAristas = new ArrayList<>();
		for (List<Arista> lista : adyacencias.values()) {
			for (Arista arista : lista) {
				if (!todasAristas.contains(arista)) {
					todasAristas.add(arista);
				}
			}
		}
		return todasAristas;
	}

	public List<Vertice> obtenerVertices() {
		return new ArrayList<>(adyacencias.keySet());
	}
}
