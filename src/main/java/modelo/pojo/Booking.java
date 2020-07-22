package modelo.pojo;

public class Booking {
	
	int id;
	Ciudad ci;
	Usuario u;
	Aerolinea ae;
	String fecha_booking;
	String fecha_partida;
	String fecha_vuelta;
	
	
	public Booking() {
		super();
		this.id =0;
		this.ci =null;
		this.u=null;
		this.ae= null;
		this.fecha_booking = "";
		this.fecha_partida = "";
		this.fecha_vuelta = "";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Ciudad getCi() {
		return ci;
	}

	public void setCi(Ciudad ci) {
		this.ci = ci;
	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	public Aerolinea getAe() {
		return ae;
	}

	public void setAe(Aerolinea ae) {
		this.ae = ae;
	}

	public String getFecha_booking() {
		return fecha_booking;
	}
	public void setFecha_booking(String fecha_booking) {
		this.fecha_booking = fecha_booking;
	}
	public String getFecha_partida() {
		return fecha_partida;
	}
	public void setFecha_partida(String fecha_partida) {
		this.fecha_partida = fecha_partida;
	}
	public String getFecha_vuelta() {
		return fecha_vuelta;
	}
	public void setFecha_vuelta(String fecha_vuelta) {
		this.fecha_vuelta = fecha_vuelta;
	}
	
	@Override
	public String toString() {
		return "Booking [id=" + id + ", ciudad=" + ci.getNombre() + ", email_usuario=" + u.getEmail() + ", id_aerolinea="
				+ ae.getNombre() + ", fecha_booking=" + fecha_booking + ", fecha_partida=" + fecha_partida
				+ ", fecha_vuelta=" + fecha_vuelta + "]";
	}

}
