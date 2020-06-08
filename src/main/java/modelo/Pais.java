package modelo;

public class Pais {

	
	private String name;
	private int id;
	
	public Pais() {
		super();
		this.name ="";
		this.id =0;
	}

	public Pais(int id) {
		this();
		this.id = id;
	}

	
	public Pais(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Pais [name=" + name + ", id=" + id + "]";
	}
	
	
	
}
