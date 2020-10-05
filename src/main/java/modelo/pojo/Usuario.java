package modelo.pojo;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;



public class Usuario {

	private int id;
	
	@NotBlank ( message = "Escribe un email")
	@Email (message="Introduce un email válido")
	private String email;
	
	@NotBlank ( message = "Escribe la contraseña")
	@Size( min = 3, max = 100, message = "La longtitud de ser entre 5 y 15 caracteres")
	private String password;
	
	private String imagen;
	
	@NotBlank ( message = "Escribe tu nombre")
	private String nombre;
	
	@NotBlank ( message = "Escribe tu primer apellido")
	private String ape1;
	
	@NotBlank ( message = "Escribe tu segundo apellido")
	private String ape2;
	
	@NotBlank ( message = "Escribe tu documento de identidad")
	@Size( min = 9, max = 9, message = "La longtitud de ser de 9 caracteres")
	private String DNI_NIE;
	
	
	private Pais nacionalidad;
	
	@NotBlank ( message = "Escribe tu direccion")
	private String residencia;
	
	private String numTarjeta;
	
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
		this.nacionalidad = new Pais();;
		this.residencia = "";
		this.numTarjeta = "";
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





	public int getNacionalidad() {
		return nacionalidad.getId();
	}





	public void setNacionalidad(int nacionalidad) {
		this.nacionalidad.setId(nacionalidad);
	}





	public String getResidencia() {
		return residencia;
	}





	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}





	public String getNumTarjeta() {
		return numTarjeta;
	}





	public void setNumTarjeta(String numTarjeta) {
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
