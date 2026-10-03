package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<Vertice, List<Arista>> adyacencias;// comportamineto que queres


public Grafo() {
    adyacencias = new HashMap<>();// es la implementacion de map, osea del comportamiento 
}
public boolean agregarVertice(Vertice vertice) {
    if (adyacencias.containsKey(vertice)) { // el vertice ya esta con clave ?
        return false; // si existe ya
    }
    adyacencias.put(vertice, new ArrayList<>());// si o lo agrega
    return true;
}
public boolean agregarArista(Arista arista) {
    Vertice v1 = arista.getVertice1();
    Vertice v2 = arista.getVertice2();
    if (!adyacencias.containsKey(v1) || !adyacencias.containsKey(v2)) {
        return false;
    }
    if (v1.equals(v2)) {// A-A
        return false;
    }
    if (adyacencias.get(v1).contains(arista)) {// no conexion repetida
        return false;
    }
    adyacencias.get(v1).add(arista); // no usamos grafos dirigidos 
    adyacencias.get(v2).add(arista);// entonces AB es lo mismo que BA
    return true;
}
public List <Arista> obtenerAristas(Vertice vertice) {
	
	return adyacencias.get(vertice);
}

public List<Arista> obtenerTodasAristas(){
	List<Arista> todasAristas = new ArrayList <>();
	for( List<Arista> lista : adyacencias.values()) {
		for (Arista arista : lista) {
			if ( !todasAristas.contains(arista)) {
				todasAristas.add(arista);
			}
		}
	}
	return todasAristas;
}

public List <Vertice> obtenerVertices (){
	return new ArrayList<> (adyacencias.keySet());
}
}
