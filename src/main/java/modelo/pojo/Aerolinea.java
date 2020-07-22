package modelo.pojo;

public class Aerolinea {

	int id;
	String nombre;
	
	public Aerolinea() {
		super();
		this.id = 0;
		this.nombre = "";
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
		return "Aerolinea [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
	
}
