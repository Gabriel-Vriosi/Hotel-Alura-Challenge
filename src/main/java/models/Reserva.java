package models;

import javax.persistence.*;
import java.time.LocalDate;


@Entity 
@Table(name = "Reserva")
public class Reserva {

	public final static double VALOR_RESERVA = 60.0;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "fecha_entrada")
	private LocalDate fechaEntrada;
	
	@Column(name = "fecha_salida")
	private LocalDate fechaSalida;
	
	@Column(name = "forma_pago")
	private String FormaDePago;
	
	private String valor;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "huesped_id")
	private Huesped huesped;
    
	
	//CONSTRUCTOR********************************************************************************
    public Reserva() {
    	
    }
   
	public Reserva(LocalDate fechaEntrada, LocalDate fechaSalida, String formaDePago, String valor , Huesped huesped) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.FormaDePago = formaDePago;
		this.valor = valor;
		this.huesped = huesped;
	}
	
	//GETTERS & SETTERS**************************************************************************
	public int getId() {
		return id;
	}

	public LocalDate getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDate fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getFormaDePago() {
		return FormaDePago;
	}

	public void setFormaDePago(String formaDePago) {
		FormaDePago = formaDePago;
	}
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
