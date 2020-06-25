package modelo.pojo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class Ciudad {

	//VALIDACIONES
	@NotBlank ( message = "Escribe el nombre de la ciudad")
	@Size( min = 3, max = 100, message = "La longtitud de ser entre 3 y 100 caracteres")
	private String nombre;
	
	private String portada;
	
	private int id;
	
	private Pais pais;
	
	private Continente continente;
	
	public Ciudad() {
		super();
		this.nombre ="";
		this.id =0;
		this.portada="";
		this.pais = new Pais();
		this.continente = new Continente();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Continente getContinente() {
		return continente;
	}

	public void setContinente(Continente continente) {
		this.continente = continente;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	@Override
	public String toString() {
		return "Ciudad [nombre=" + nombre + ", id=" + id +", portada="+portada +", pais=" + pais.getNombre()+" bandera="+pais.getBandera() + ", continente=" + continente.getNombre() + "]";
	}
	
	
	

}
