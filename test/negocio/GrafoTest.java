package negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class GrafoTest {
	 @Test
	    void agregarVerticeNuevoDevuelveTrue() {
	        Grafo grafo = new Grafo();
	        Vertice a = new Vertice("A");

	        assertTrue(grafo.agregarVertice(a));
	    }
	 @Test
	 void agregarVerticeRepetidoDevuelveFalse() {
	     Grafo grafo = new Grafo();
	     Vertice a1 = new Vertice("A");
	     Vertice a2 = new Vertice("A");
	     grafo.agregarVertice(a1);

	     assertFalse(grafo.agregarVertice(a2));
	 }
	 @Test
	 void agregarAristaValidaDevuelveTrue() {
	     Grafo grafo = new Grafo();
	     Vertice a = new Vertice("A");
	     Vertice b = new Vertice("B");
	     grafo.agregarVertice(a);
	     grafo.agregarVertice(b);
	     Arista ab = new Arista(a, b, 5);

	     assertTrue(grafo.agregarArista(ab));
	 }
	 @Test
	 void agregarAristaConVerticeInexistenteDevuelveFalse() {
	     Grafo grafo = new Grafo();
	     Vertice a = new Vertice("A");
	     Vertice c = new Vertice("C");
	     grafo.agregarVertice(a);
	     Arista ac = new Arista(a, c, 7);

	     assertFalse(grafo.agregarArista(ac));
	 }
	 @Test
	 void agregarAristaConMismoVerticeDevuelveFalse() {//A-A
	     Grafo grafo = new Grafo();
	     Vertice a = new Vertice("A");
	     grafo.agregarVertice(a);
	     Arista aa = new Arista(a, a, 5);

	     assertFalse(grafo.agregarArista(aa));
	 }
	 @Test
	 void agregarAristaRepetidaDevuelveFalse() { //A5B , B25A
	     Grafo grafo = new Grafo();
	     Vertice a = new Vertice("A");
	     Vertice b = new Vertice("B");
	     grafo.agregarVertice(a);
	     grafo.agregarVertice(b);
	     Arista ab = new Arista(a, b, 5);
	     Arista ba = new Arista(b, a, 20);
	     grafo.agregarArista(ab);

	     assertFalse(grafo.agregarArista(ba));
	 }
	 @Test
	 void obtenerAristasDeUnVertice() {
	     Grafo grafo = new Grafo();
	     Vertice a = new Vertice("A");
	     Vertice b = new Vertice("B");
	     Vertice c = new Vertice("C");
	     grafo.agregarVertice(a);
	     grafo.agregarVertice(b);
	     grafo.agregarVertice(c);
	     grafo.agregarArista(new Arista(a, b, 5));
	     grafo.agregarArista(new Arista(a, c, 8));

	     assertEquals(2, grafo.obtenerAristas(a).size());// nos devuelve una lisata de los vecinos
	 }
	 
	 @Test
	 void agregarAristaInvertidaRepetidaDevuelveFalse() {
	     Grafo grafo = new Grafo();
	     Vertice a = new Vertice("A");
	     Vertice b = new Vertice("B");
	     grafo.agregarVertice(a);
	     grafo.agregarVertice(b);
	     Arista ab = new Arista(a, b, 5);
	     Arista ba = new Arista(b, a, 5);

	     assertTrue(grafo.agregarArista(ab));
	     assertFalse(grafo.agregarArista(ba));
	 }
	 
	 @Test
	 void agregarAristaConAmbosVerticesInexistentesDevuelveFalse() {
	     Grafo grafo = new Grafo();
	     Vertice a = new Vertice("A");
	     Vertice b = new Vertice("B");
	     Arista ab = new Arista(a, b, 5);

	     assertFalse(grafo.agregarArista(ab));
	 }
	 @Test
	 void obtenerTodasLasAristasSinRepetir() {
		 Grafo grafo = new Grafo();
		 Vertice a = new Vertice("A");
		 Vertice b = new Vertice ("B");
		 Vertice c= new Vertice ("C");
		 grafo.agregarVertice(a);
		 grafo.agregarVertice(b);
		 grafo.agregarVertice(c);
		 grafo.agregarArista(new Arista (a,b,6));
		 grafo.agregarArista( new Arista ( a,c,3));
		 
		 assertEquals(2,grafo.obtenerTodasAristas().size());
	 }
	 
	 //para kruskal
	 @Test
	 void obtenerTodosLosVertices() {
		 Grafo grafo = new Grafo();
		 Vertice a = new Vertice("A");
		 Vertice b = new Vertice ("B");
		 Vertice c= new Vertice ("C");
		 grafo.agregarVertice(a);
		 grafo.agregarVertice(b);
		 grafo.agregarVertice(c);
		 
		 assertEquals(3,grafo.obtenerVertice().size());
	 }
	 
	 @Test
	    void obtenerAristasDeVerticeInexistenteDevuelveNull() {
	        Grafo grafo = new Grafo();
	        Vertice v1 = new Vertice("A");
	        
	        // Como v1 nunca fue agregado con grafo.agregarVertice(), debe devolver null
	        assertNull(grafo.obtenerAristas(v1));
	    }
	
	 @Test
	    void agregarAristaGuardaLaAristaEnAmbosVertices() {
	        Grafo grafo = new Grafo();
	        Vertice v1 = new Vertice("A");
	        Vertice v2 = new Vertice("B");
	        grafo.agregarVertice(v1);
	        grafo.agregarVertice(v2);
	        
	        Arista arista = new Arista(v1, v2, 10);
	        grafo.agregarArista(arista);
	        
	        // Verificamos bidireccionalidad
	        assertTrue(grafo.obtenerAristas(v1).contains(arista));
	        assertTrue(grafo.obtenerAristas(v2).contains(arista));
	    }
	 
	 @Test
	    void obtenerTodasAristasDeGrafoVacioDevuelveListaVacia() {
	        Grafo grafo = new Grafo();
	        
	        // Un grafo recién creado no debería tener aristas
	        assertEquals(0, grafo.obtenerTodasAristas().size());
	    }
}
