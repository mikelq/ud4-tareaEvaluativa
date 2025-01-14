package modelo;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "fechaReserva", nullable = false)
	private Date fechaReserva;
	
	@Column(name = "numeroPlazasReservadas", nullable = false)
	private int numeroPlazasReservadas;
	
	@ManyToOne
    @JoinColumn(name = "pasajero_id", nullable = false, referencedColumnName = "id")
	private Pasajero pasajero;
	
	@ManyToOne
    @JoinColumn(name = "viaje_id", nullable = false, referencedColumnName = "id")
	private Viaje viaje;
	
	public Reserva() {
	}
	
	public Reserva(Date fechaReserva, int numeroPlazasReservadas, Pasajero pasajero, Viaje viaje) {
		this.fechaReserva = fechaReserva;
		this.numeroPlazasReservadas = numeroPlazasReservadas;
		this.pasajero = pasajero;
		this.viaje = viaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public int getNumeroPlazasReservadas() {
		return numeroPlazasReservadas;
	}

	public void setNumeroPlazasReservadas(int numeroPlazasReservadas) {
		this.numeroPlazasReservadas = numeroPlazasReservadas;
	}

	public Pasajero getPasajero() {
		return pasajero;
	}

	public void setPasajero(Pasajero pasajero) {
		this.pasajero = pasajero;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
}
