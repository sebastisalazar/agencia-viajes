package modelo.pojo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

public class Usuario {

	private int id;
	
	private String email;
	
	private String password;
	
	private String imagen;
	
	public Usuario() {
		super();
		this.id =0;
		this.email = "";
		this.password = "";
		this.imagen = "";
	}
	
	

	public Usuario(String email, String password) {
		this();
		this.email = email;
		this.password = password;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", imagen=" + imagen + "]";
	}
	
	
	
	

}
