package modelo.pojo;

public class Rol {
	
	private int id;
	private String nombre;
	
	public Rol() {
		super();
		this.id = 1;
		this.nombre = "";
	}
	
	public Rol( int id ) {
		this();
		this.id = id;		
	}

	public Rol(int id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + "]";
	}
	

}
