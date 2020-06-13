package modelo.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Continente {

	
	private int id;
	private String nombre;
	
	public Continente() {
		super();
		this.id =0;
		this.nombre = "";
	}
	

	public Continente(int id) {
		this();
		this.id = id;
	}


	public Continente(int id, String nombre) {
		this();
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
		return "Continente [id=" + id + ", nombre=" + nombre + "]";
	}
	
	

}
