package negocio;

public class Vertice { // provincias

	private String nombre; 
	public Vertice(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre() {
		return nombre;
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
	 public int hashCode() { //para comparar vertices, ayuda al hashmap; le asigna valor
		 return nombre.hashCode();
	 }
	 
	 @Override
	    public String toString() {
	        return nombre;
	    }
}
