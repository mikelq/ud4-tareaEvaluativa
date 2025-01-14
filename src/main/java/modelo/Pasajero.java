package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pasajero")
public class Pasajero {
	
	//Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="email", nullable = false, length = 255)
	private String email;
	
	@Column(name="nombre", nullable = false, length = 255)
	private String nombre;
	
	//Constructor
	public Pasajero() {
	}
	
	public Pasajero(String email, String nombre) {
		this.email = email;
		this.nombre = nombre;
	}

	//Getter y setter
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
