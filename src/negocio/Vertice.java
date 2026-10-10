package negocio;
public class Vertice { // provincias

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
				 return nombre.equalsIgnoreCase(otra.nombre);
			 }
			 @Override
			 public int hashCode() { //para comparar vertices, ayuda al hashmap; le asigna valor
				    return nombre.toLowerCase(java.util.Locale.ROOT).hashCode();
			 }

			 @Override
			    public String toString() {
			        return nombre;
			    }
	 }