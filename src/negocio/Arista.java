package negocio;

//Se agrega implements Comparable<Arista> y el método compareTo para poder ordenarlas por peso con Kruskal,
public class Arista implements Comparable<Arista> { 
    private Vertice vertice1;
    private Vertice vertice2;
    private int peso;

//public Arista { // necesito: V1 , V2 y el peso
	//private Vertice vertice1;
	//private Vertice vertice2;
	//private int peso;
	public Arista (Vertice vertice1 , Vertice vertice2, int peso) {
		this.vertice1= vertice1;
		this.vertice2= vertice2;
		this.peso = peso;                                               
		
	}
	 public Vertice getVertice1() { // esto lo vamosa  usar para Kruskal
	        return vertice1;
	    }
	    public Vertice getVertice2() {
	        return vertice2;
	    }

	    public int getPeso() {
	        return peso;
	        }
	    
	 // Método necesario para poder ordenar las aristas de menor a mayor en Kruskal
	    @Override
	    public int compareTo(Arista otra) {
	        return Integer.compare(this.peso, otra.peso);
	    }
	    
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (!(obj instanceof Arista)) {
	            return false;
	        }
	        Arista otra = (Arista) obj; 
	        boolean mismoOrden =vertice1.equals(otra.vertice1) && vertice2.equals(otra.vertice2);
	        boolean ordenInverso = vertice1.equals(otra.vertice2) && vertice2.equals(otra.vertice1);

	        return mismoOrden || ordenInverso;
}
	    @Override
	    public int hashCode() {
	        return vertice1.hashCode() + vertice2.hashCode();
	    }}
