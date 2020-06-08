package modelo;

public class Pais {

	
	private String nombre;
	private int id;
	
	public Pais() {
		super();
		this.nombre ="";
		this.id =0;
	}

	public Pais(int id) {
		this();
		this.id = id;
	}

	
	public Pais(String nombre, int id) {
		this.nombre = nombre;
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setName(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", id=" + id + "]";
	}
	
	
	
}
