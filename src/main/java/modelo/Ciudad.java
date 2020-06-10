package modelo;

public class Ciudad {

	private String nombre;
	private int id;
	private Pais pais;
	private Continente continente;
	
	public Ciudad() {
		super();
		this.nombre ="";
		this.id =0;
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

	@Override
	public String toString() {
		return "Ciudad [nombre=" + nombre + ", id=" + id + ", pais=" + pais.getNombre() + ", continente=" + continente.getNombre() + "]";
	}
	
	
	

}
