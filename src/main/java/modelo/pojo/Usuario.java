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
	
	private String nombre;
	
	private String ape1;
	
	private String ape2;
	
	private String DNI_NIE;
	
	private String nacionalidad;
	
	private String residencia;
	
	private int numTarjeta;
	
	private String caducidadTarjeta;
	
	private int numseguridadTarjeta;
	
	private String titular;

	private Rol rol;
	
	public Usuario() {
		super();
		this.id =0;
		this.email = "";
		this.password = "";
		this.imagen = "";
		this.rol = new Rol();
		
		this.nombre = "";
		this.ape1 = "";
		this.ape2 = "";
		this.DNI_NIE = "";
		this.nacionalidad = "";
		this.residencia = "";
		this.numTarjeta = 0;
		this.caducidadTarjeta = "";
		this.numseguridadTarjeta = 0;
		this.titular = "";
		
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





	public String getNombre() {
		return nombre;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public String getApe1() {
		return ape1;
	}





	public void setApe1(String ape1) {
		this.ape1 = ape1;
	}





	public String getApe2() {
		return ape2;
	}





	public void setApe2(String ape2) {
		this.ape2 = ape2;
	}





	public String getDNI_NIE() {
		return DNI_NIE;
	}





	public void setDNI_NIE(String dNI_NIE) {
		DNI_NIE = dNI_NIE;
	}





	public String getNacionalidad() {
		return nacionalidad;
	}





	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}





	public String getResidencia() {
		return residencia;
	}





	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}





	public int getNumTarjeta() {
		return numTarjeta;
	}





	public void setNumTarjeta(int numTarjeta) {
		this.numTarjeta = numTarjeta;
	}





	public String getCaducidadTarjeta() {
		return caducidadTarjeta;
	}





	public void setCaducidadTarjeta(String caducidadTarjeta) {
		this.caducidadTarjeta = caducidadTarjeta;
	}





	public int getNumseguridadTarjeta() {
		return numseguridadTarjeta;
	}





	public void setNumseguridadTarjeta(int numseguridadTarjeta) {
		this.numseguridadTarjeta = numseguridadTarjeta;
	}





	public String getTitular() {
		return titular;
	}





	public void setTitular(String titular) {
		this.titular = titular;
	}





	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + ", password=" + password + ", imagen=" + imagen + ", nombre="
				+ nombre + ", ape1=" + ape1 + ", ape2=" + ape2 + ", DNI_NIE=" + DNI_NIE + ", nacionalidad="
				+ nacionalidad + ", residencia=" + residencia + ", numTarjeta=" + numTarjeta + ", caducidadTarjeta="
				+ caducidadTarjeta + ", numseguridadTarjeta=" + numseguridadTarjeta + ", titular=" + titular + ", rol="
				+ rol.getId() + "]";
	}

	
	
	
	
	

}
