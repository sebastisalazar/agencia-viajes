package modelo;

public class Pais {

	
	private String nombre;
	private int id;
	private String bandera;
	private Continente continente;
	
	public Pais() {
		super();
		this.nombre ="";
		this.id =0;
		this.bandera="";
		this.continente= new Continente();
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
	
	
	
}
