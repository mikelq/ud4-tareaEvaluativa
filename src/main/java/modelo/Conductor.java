package modelo;

import jakarta.persistence.*;

@Entity
@Table(name="conductor")
public class Conductor {
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="nombre", nullable = false, length = 255)
	private String nombre;
	
	@Column(name="vehiculo", nullable = false, length = 255)
	private String vehiculo;
	
	//Constructor
	public Conductor() {
	}
	
	public Conductor(String nombre, String vehiculo) {
		this.nombre = nombre;
		this.vehiculo = vehiculo;
	}

	//Getters y setters obligatorios para poder trabajar con Hibernate
	public long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getVehiculo() {
		return vehiculo;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setVehiculo(String vehiculo) {
		this.vehiculo = vehiculo;
	}

	@Override
	public String toString() {
		return "Id conductor: " + id + "\n" + 
			   "Nombre: " + nombre + "\n" +
				"Vehiculo: " + vehiculo;
	}
}
