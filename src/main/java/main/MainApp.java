package main;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.ConexionHibernate;
import modelo.Conductor;
import modelo.Viaje;

public class MainApp {

	private static Scanner sc = new Scanner(System.in);
	
	public static int menu() {
		
		System.out.println("=== Menú de Gestión de Viajes Compartidos ===");
		System.out.println("1. Crear conductor");
		System.out.println("2. Crear viaje");
		System.out.println("3. Buscar viajes disponibles");
		System.out.println("4. Crear pasajero");
		System.out.println("5. Crear reserva");
		System.out.println("6. Cancelar reservar");
		System.out.println("7. Listar viajes");
		System.out.println("8. Salir");
		System.out.println("Introduce opcion: ");
		int opcion = sc.nextInt();
		//Leer el intro que se pulsa detras de la opcion
		sc.nextLine();
		return opcion;
	}
	
	public static void main(String[] args) {
		
		int opcion;
		
		do {
			opcion = menu();
			
			switch(opcion) {
				case 1:	{
					System.out.println("Introduce nombre conductor: ");
					String nombre = sc.nextLine();
					System.out.println("Introduce vehiculo: ");
					String vehiculo = sc.nextLine();
					ConexionHibernate.guardarConductor(nombre, vehiculo);
					break;
				}
				case 2:	{
					//Pedimos el conductor
					System.out.println("Introduce nombre conductor: ");
					String nombre = sc.nextLine();
					System.out.println("Introduce vehiculo: ");
					String vehiculo = sc.nextLine();
				
					//Pedir los datos del viaje
					System.out.println("Introduce destino viaje: ");
					String destino = sc.nextLine();
					System.out.println("Introduce origen viaje: ");
					String origen = sc.nextLine();
					System.out.println("Introduce fecha (dia): ");
					int dia = sc.nextInt();
					System.out.println("Introduce fecha (mes): ");
					int mes = sc.nextInt();
					System.out.println("Introduce fecha (año): ");
					int anyo = sc.nextInt();
					
					//Convertir a fecha Date
					LocalDate fechaLocal = LocalDate.of(anyo,  mes,  dia);
					Date date = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

					System.out.println("Introduce plazas disponibles: ");
					int plazas = sc.nextInt();
					
					ConexionHibernate.guardarViaje(nombre, vehiculo, destino, origen, date, plazas);
			    	break;
				}
				case 3:
					ConexionHibernate.mostrarViajesDisponibles();
					break;
				case 4:	{
					System.out.println("Introduce email pasajero: ");
					String email = sc.nextLine();
					System.out.println("Introduce nombre pasajero: ");
					String nombre = sc.nextLine();
					ConexionHibernate.guardarPasajero(email, nombre);
					break;
				}
				case 5:	{
					
					//Pedir la fecha del viaje
					System.out.println("Introduce fecha (dia): ");
					int dia = sc.nextInt();
					System.out.println("Introduce fecha (mes): ");
					int mes = sc.nextInt();
					System.out.println("Introduce fecha (año): ");
					int anyo = sc.nextInt();
					
					//Convertir a fecha Date
					LocalDate fechaLocal = LocalDate.of(anyo,  mes,  dia);
					Date date = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());

					//Insertar datos de la reserva
					System.out.println("Introduce numero plazas reservadas: ");
					int numeroPlazas = sc.nextInt();
					System.out.println("Introduce id pasajero: ");
					int pasajeroId = sc.nextInt();
					System.out.println("Introduce id viaje: ");
					int viajeId = sc.nextInt();
					
					ConexionHibernate.guardarReserva(date, numeroPlazas, pasajeroId, viajeId);

					break;
				}
				case 6: {
					System.out.println("Introduce id reserva: ");
					int reservaId = sc.nextInt();
					ConexionHibernate.borrarReserva(reservaId);
					break;
				}
					
				case 7: {
					ConexionHibernate.mostrarViajesListado();
					break;
				}
					
			}
			
		} while (opcion != 8);

	}
}
