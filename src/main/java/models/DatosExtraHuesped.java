package models;

import javax.persistence.Embeddable;

@Embeddable
public class DatosExtraHuesped {
	private String dni;
	private String email;
	
	//CONSTRUCTOR********************************************************************************
	public DatosExtraHuesped() {
	}
	
	public DatosExtraHuesped(String dni, String email) {
		this.dni = dni;
		this.email = email;
	}

	//GETTERS & SETTERS**************************************************************************
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
