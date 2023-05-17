package models;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity 
@Table(name = "Huesped")
public class Huesped {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "nombres")
	private String nombre;
	
	@Column(name = "apellidos")
	private String apellido;
	
	@Column(name = "fecha_nac")
	private LocalDate fechaNacimiento;
	
	@Column(name = "nacionalidad")
	private String nacionalidad;
	
	@Column(name = "tel")
	private String telefono;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name="dni", column = @Column(name = "dni_huesped")),
		@AttributeOverride(name="email", column = @Column(name = "email_huesped"))
	})
	private DatosExtraHuesped datosExtraHuesped;
	
	@OneToMany(
			mappedBy = "huesped", 
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Reserva> reservas;
	

	//CONSTRUCTOR********************************************************************************
	public Huesped() {
		
	}
	
	public Huesped(String nombre, String apellido, LocalDate fechaNacimiento, String nacionalidad,
			String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
	}
	
	//GETTERS & SETTERS**************************************************************************
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<Reserva> getReservas() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public DatosExtraHuesped getDatosExtraHuesped() {
		return datosExtraHuesped;
	}

	public void setDatosExtraHuesped(DatosExtraHuesped datosExtraHuesped) {
		this.datosExtraHuesped = datosExtraHuesped;
	}
}
