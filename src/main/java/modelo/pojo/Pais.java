package modelo.pojo;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Pais {

	//VALIDACIONES
	@NotBlank ( message = "Escribe el nombre del pais")
	@Size( min = 3, max = 100, message = "La longtitud de ser entre 3 y 100 caracteres")
	private String nombre;
	
	@NotBlank ( message = "Escribe el codigo del pais")
	@Size( min = 2, max = 2, message = "La longtitud debe ser de 2 caracteres")
	private String nombrecorto;
	private int numciudades;
	private int id;
	private String bandera;
	private Continente continente;
	
	public Pais() {
		super();
		this.nombre ="";
		this.id =0;
		this.bandera="";
		this.numciudades=0;
		this.continente= new Continente();
	}


	public int getNumciudades() {
		return numciudades;
	}


	public void setNumciudades(int numciudades) {
		this.numciudades += numciudades;
	}

	public String getNombre() {
		return nombre;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getBandera() {
		return bandera;
	}

	public void setBandera(String bandera) {
		this.bandera = bandera;
	}


	public Continente getContinente() {
		return continente;
	}

	public void setContinente(Continente continente) {
		this.continente = continente;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		
	}
	

	public String getNombrecorto() {
		return nombrecorto;
	}


	public void setNombrecorto(String nombrecorto) {
		this.nombrecorto = nombrecorto;
	}


	@Override
	public String toString() {
		return "Pais [nombre=" + nombre +", nombre corto="+nombrecorto+ ", id=" + id + ", bandera=" + bandera +", numciudades="+numciudades+ ", continente_id=" + continente.getId() + ", continente_nombre="+continente.getNombre()+ "]";
	}
	
}
