package negocio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TeoremaKruskal {

    
    public static Grafo calcularAGM(Grafo grafo) {
        Grafo agm = new Grafo();
        List<Vertice> vertices = grafo.obtenerVertice();

        // 1. Agregar todos los vértices de las provincias al AGM
        for (Vertice v : vertices) {
            agm.agregarVertice(v);
        }

        // 2. Obtener todas las aristas y ordenarlas de menor a mayor peso
        List<Arista> aristas = new ArrayList<>(grafo.obtenerTodasAristas());
        aristas.sort(Comparator.comparingInt(Arista::getPeso));

        UnionFind uf = new UnionFind(vertices);

        // 3. Agregar aristas sin formar ciclos
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
        List<Vertice> vertices = grafo.obtenerVertice();

        if (k <= 0 || k > vertices.size()) {
            throw new IllegalArgumentException("El número de regiones k debe estar entre 1 y la cantidad total de vértices.");
        }

        // PASO 1: Generar el Árbol Generador Mínimo T
        Grafo agm = calcularAGM(grafo);
        List<Arista> aristasAGM = new ArrayList<>(agm.obtenerTodasAristas());

        // Ordenamos las aristas del AGM por peso (menor a mayor)
        aristasAGM.sort(Comparator.comparingInt(Arista::getPeso));

        // PASO 2: Eliminar las k - 1 aristas de mayor peso.
        // Nos quedamos con las primeras (totalAristas - (k - 1)) aristas más livianas.
        int aristasAConservar = aristasAGM.size() - (k - 1);

        // PASO 3: Construir las k componentes conexas conexas resultantes
        UnionFind ufRegiones = new UnionFind(vertices);

        for (int i = 0; i < aristasAConservar; i++) {
            Arista a = aristasAGM.get(i);
            ufRegiones.union(a.getVertice1(), a.getVertice2());
        }

        // Agrupar los vértices según su representante común en UnionFind
     // Declaración usando List en lugar de Set:
        Map<Vertice, List<Vertice>> regionesMap = new HashMap<>();

        for (Vertice v : vertices) {
            Vertice representante = ufRegiones.buscar(v);
            // Si el representante no está en el mapa, creamos un nuevo ArrayList vacío
            regionesMap.putIfAbsent(representante, new ArrayList<>());
            
            // Agregamos el vértice a la lista correspondiente a su región
            regionesMap.get(representante).add(v);
        }

        // Retorno (esto devolverá un List<List<Vertice>>):
        return new ArrayList<>(regionesMap.values());
    }
}
