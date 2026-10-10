package negocio;

public class Arista implements Comparable<Arista> {
	private Vertice vertice1;
	private Vertice vertice2;
	private double peso;

	public Arista(Vertice vertice1, Vertice vertice2, double peso) {
		this.vertice1 = vertice1;
		this.vertice2 = vertice2;
		this.peso = peso;
	}

	public Vertice getVertice1() {
		return vertice1;
	}

	public Vertice getVertice2() {
		return vertice2;
	}

	public double getPeso() {
		return peso;
	}

	@Override
	public int compareTo(Arista otra) {
		return Double.compare(this.peso, otra.peso);
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
		boolean mismoOrden = vertice1.equals(otra.vertice1) && vertice2.equals(otra.vertice2);
		boolean ordenInverso = vertice1.equals(otra.vertice2) && vertice2.equals(otra.vertice1);

		return mismoOrden || ordenInverso;
	}

	@Override
	public int hashCode() {
		return vertice1.hashCode() + vertice2.hashCode();
	}
}
