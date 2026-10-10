package negocio;
public class Vertice {

	private String nombre; 
	private double x;
	private double y;
	public Vertice(String nombre) {
		this.nombre = nombre;
	}
	public Vertice(String nombre, double x, double y) {
		this.nombre = nombre;
		this.x = x;
		this.y = y;
	}
	public String getNombre() {
		return nombre;
	}
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	@Override

	public boolean equals (Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (! (obj instanceof Vertice )) {
			return false; 
		}
		Vertice otra= (Vertice) obj;
		return nombre.equals(otra.nombre);
	}
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}


	@Override
	public String toString() {
		return nombre;
	}
}