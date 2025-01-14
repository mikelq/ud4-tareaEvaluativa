package modelo;

import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name="viaje")
public class Viaje {

	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="ciudadDestino", nullable = false, length = 255)
	private String ciudadDestino;
	
	@Column(name="ciudadOrigen", nullable = false, length = 255)
	private String ciudadOrigen;
	
	@Column(name = "fechaHora", nullable = false)
	private Date fechaHora;
	
	@Column(name = "plazasDisponibles", nullable = false)
	private int plazasDisponibles;
	
	@ManyToOne
    @JoinColumn(name = "conductor_id", nullable = false, referencedColumnName = "id")
	private Conductor conductor;
	
	//Constructor
	public Viaje() {
	}
	
	public Viaje(int id, String ciudadDestino, String ciudadOrigen, Date fechaHora, int plazasDisponibles,
			Conductor conductor) {
		this.id = id;
		this.ciudadDestino = ciudadDestino;
		this.ciudadOrigen = ciudadOrigen;
		this.fechaHora = fechaHora;
		this.plazasDisponibles = plazasDisponibles;
		this.conductor = conductor;
	}
	
	public Viaje(String ciudadDestino, String ciudadOrigen, Date fechaHora, int plazasDisponibles,
			Conductor conductor) {
		this.ciudadDestino = ciudadDestino;
		this.ciudadOrigen = ciudadOrigen;
		this.fechaHora = fechaHora;
		this.plazasDisponibles = plazasDisponibles;
		this.conductor = conductor;
	}

	//Getter y Setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCiudadDestino() {
		return ciudadDestino;
	}

	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}

	public String getCiudadOrigen() {
		return ciudadOrigen;
	}

	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public int getPlazasDisponibles() {
		return plazasDisponibles;
	}

	public void setPlazasDisponibles(int plazasDisponibles) {
		this.plazasDisponibles = plazasDisponibles;
	}

	public Conductor getConductor_id() {
		return conductor;
	}

	public void setConductor_id(Conductor conductor) {
		this.conductor = conductor;
	}

	@Override
	public String toString() {
		return "Id viaje:" + id + "\n" +
				"Destino: " + ciudadDestino + "\n" + 
				"Origen: " + ciudadOrigen + "\n" +
				"Fecha: " + fechaHora + "\n" +
				"Plazas disponibles: " + plazasDisponibles + "\n" + conductor;
	}
}
