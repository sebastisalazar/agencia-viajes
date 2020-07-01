package modelo.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import modelo.pojo.Rol;

public class Usuario {

	private int id;
	
	@NotBlank ( message = "Escribe un email")
	@Email (message="Introduce un email válido")
	private String email;
	
	@NotBlank ( message = "Escribe la contraseña")
	@Size( min = 3, max = 100, message = "La longtitud de ser entre 5 y 15 caracteres")
	private String password;
	
	private String imagen;
	
	private Rol rol;
	
	
	public Usuario() {
		super();
		this.id =0;
		this.email = "";
		this.password = "";
		this.imagen = "";
		this.rol = new Rol();
	}
	
	


	public Usuario(String email, String password) {
		this();
		this.email = email;
		this.password = password;
	}

	


	public Rol getRol() {
		return rol;
	}



	public void setRol(Rol rol) {
		this.rol = rol;
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
