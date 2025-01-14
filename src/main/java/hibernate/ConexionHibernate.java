package hibernate;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import modelo.Conductor;
import modelo.Pasajero;
import modelo.Reserva;
import modelo.Viaje;

public class ConexionHibernate {

    public static Session conectarHibernate() {
    	
    	Session session = null;
    	
        //Cargamos fichero de configuración de hibernate (hibernate.cfg.xml)
    	//de la carpeta por defecto resources
		Configuration cfg = new Configuration().configure();
		System.out.println("Fichero de configuración Hibernate cargado");

		//A partir del fichero de configuracion creamos la sesión Hibernate
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		System.out.println("Sesion creada");
		//abrimos sesión hibernate
		session = sessionFactory.openSession();
		System.out.println("Sesion abierta");

        return session;
    }
    
    public static void cerrarSesion(Session sesion) {
    	if (sesion != null) {
    		sesion.close();
    		System.out.println("Sesión cerrada");
    	}
    }
    
	public static void guardarConductor(String nombre, String vehiculo) {
    	
		Transaction transaccion = null;
		Session sesion = null;
		
		try {
	    	//Conectarnos con Hibernate
	    	sesion = conectarHibernate();
	    	
	    	//Comenzamos transaccion
	    	transaccion = sesion.beginTransaction();
	    	
	    	//Crear un objeto de tipo Conductor
	    	Conductor conductor = new Conductor(nombre, vehiculo);
	    	
	    	//Guardar el objeto en la sesion
	    	sesion.persist(conductor);
	    	
	    	//Guardo la transaccion
	    	transaccion.commit();
	    	
	    	System.out.println("Conductor insertado correctamente");
		}
		catch (Exception ex) {
			transaccion.rollback();
		}
		finally {
			cerrarSesion(sesion);
		}
    }
	
	public static void guardarPasajero(String email, String nombre) {
    	
		Transaction transaccion = null;
		Session sesion = null;
		
		try {
	    	//Conectarnos con Hibernate
	    	sesion = conectarHibernate();
	    	
	    	//Comenzamos transaccion
	    	transaccion = sesion.beginTransaction();
	    	
	    	//Crear un objeto de tipo Pasajero
	    	Pasajero pasajero = new Pasajero(email, nombre);
	    	
	    	//Guardar el objeto en la sesion
	    	sesion.persist(pasajero);
	    	
	    	//Guardo la transaccion
	    	transaccion.commit();
	    	
	    	System.out.println("Pasajero insertado correctamente");
		}
		catch (Exception ex) {
			transaccion.rollback();
		}
		finally {
			cerrarSesion(sesion);
		}
    }
	
	public static void guardarViaje(String nombre, String vehiculo, String destino, String origen, 
			Date fecha, int plazas) {
	
		Transaction transaccion = null;
		Session sesion = null;
		Conductor conductor = null;
		
		try {
			//Conectarnos con Hibernate
	    	sesion = ConexionHibernate.conectarHibernate();
			
			//Buscar si existe el conductor
			//Añadir al criterio de seleccion el nombre del conductor
			CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaQuery<Conductor> query = cb.createQuery(Conductor.class);
			Root<Conductor> root = query.from(Conductor.class);
			query.select(root).where(cb.equal(root.get("nombre"), nombre));

			List<Conductor> lista = sesion.createQuery(query).getResultList();

			//Comenzamos transaccion
	    	transaccion = sesion.beginTransaction();
	    	
			//Si el conductor es null se guarda el nuevo
			if (lista.size() == 0) {
				conductor = new Conductor(nombre, vehiculo);
				sesion.persist(conductor);
			}
			else {
				//Se recoge el conductor de la lista
				conductor = lista.get(0);
			}

			Viaje viaje = new Viaje (destino, origen, fecha, plazas, conductor);
			
	    	//Guardar el objeto en la sesion
	    	sesion.persist(viaje);
	    	
	    	//Guardo la transaccion
	    	transaccion.commit();
	    	
	    	System.out.println("Viaje insertado correctamente");
		}
		catch (Exception ex) {
			transaccion.rollback();
		}
		finally {
			cerrarSesion(sesion);
		}
	}
	
	public static void mostrarViajesDisponibles() {
		Session sesion = null;
		
		try {
			//Conectarnos con Hibernate
	    	sesion = ConexionHibernate.conectarHibernate();
			
			//Buscar todos los viajes disponibles
			//Añadir al criterio de seleccion el numero de plazas a cero
			CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaQuery<Viaje> query = cb.createQuery(Viaje.class);
			Root<Viaje> root = query.from(Viaje.class);
			query.select(root).where(cb.greaterThan(root.get("plazasDisponibles"), 0));

			List<Viaje> lista = sesion.createQuery(query).getResultList();

			//Bucle que recorre la lista y muestra los viajes
			for (Viaje viaje: lista) {
				//Llama al toString de viaje
				System.out.println("--------------------------------------------");
				System.out.println(viaje);
			}
			System.out.println("--------------------------------------------");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			cerrarSesion(sesion);
		}
	}
	
	public static void mostrarViajesListado() {
		Session sesion = null;
		
		try {
			//Conectarnos con Hibernate
	    	sesion = ConexionHibernate.conectarHibernate();
			
			//Buscar todos los viajes disponibles
			//Añadir al criterio de seleccion el numero de plazas a cero
			CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaQuery<Viaje> query = cb.createQuery(Viaje.class);

			List<Viaje> lista = sesion.createQuery(query).getResultList();

			//Bucle que recorre la lista y muestra los viajes
			for (Viaje viaje: lista) {
				//Llama al toString de viaje
				System.out.println("--------------------------------------------");
				System.out.println(viaje);
			}
			System.out.println("--------------------------------------------");
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			cerrarSesion(sesion);
		}
	}
	
	public static void guardarReserva(Date date, int numeroPlazas, int pasajeroId, int viajeId) {
	
		Transaction transaccion = null;
		Session sesion = null;
		
		try {
			//Conectarnos con Hibernate
	    	sesion = ConexionHibernate.conectarHibernate();
			
			//Buscar si existe el pasajero
			CriteriaBuilder cbP = sesion.getCriteriaBuilder();
			CriteriaQuery<Pasajero> queryP = cbP.createQuery(Pasajero.class);
			Root<Pasajero> rootP = queryP.from(Pasajero.class);
			queryP.select(rootP).where(cbP.equal(rootP.get("id"), pasajeroId));

			List<Pasajero> listaP = sesion.createQuery(queryP).getResultList();

			if (listaP.size() > 0) {
				
				CriteriaBuilder cbV = sesion.getCriteriaBuilder();
				CriteriaQuery<Viaje> queryV = cbV.createQuery(Viaje.class);
				Root<Viaje> rootV = queryV.from(Viaje.class);
				queryV.select(rootV).where(cbV.equal(rootV.get("id"), viajeId));

				List<Viaje> listaV = sesion.createQuery(queryV).getResultList();
				
				if (listaV.size() > 0) {
					
					//Comenzamos transaccion
			    	transaccion = sesion.beginTransaction();
			    	
			    	Reserva reserva = new Reserva(date, numeroPlazas, listaP.get(0), listaV.get(0));
					
			    	//Guardar el objeto en la sesion
			    	sesion.persist(reserva);
			    	
			    	//Guardo la transaccion
			    	transaccion.commit();
			    	
			    	System.out.println("Reserva insertada correctamente");
				}
				else {
					System.out.println("No existe el viaje seleccionado:");
				}
			}
			else {
				System.out.println("No existe el pasajero seleccionado:");
			}
		}
		catch (Exception ex) {
			transaccion.rollback();
		}
		finally {
			cerrarSesion(sesion);
		}
	}
	
	public static void borrarReserva(int id) {
		
		Transaction transaccion = null;
		Session sesion = null;

		try {
			//Conectarnos con Hibernate
	    	sesion = ConexionHibernate.conectarHibernate();
			
			//Buscar si existe el pasajero
			CriteriaBuilder cb = sesion.getCriteriaBuilder();
			CriteriaDelete<Reserva> delete = cb.createCriteriaDelete(Reserva.class);
			Root<Reserva> root = delete.from(Reserva.class);

			//Borramos segun el id
			delete.where(cb.equal(root.get("id"), id));
					
			//Comenzamos transaccion
	    	transaccion = sesion.beginTransaction();
	    	
			//Ejecutamos el delete
	    	sesion.createMutationQuery(delete).executeUpdate();
			    	
			//Guardo la transaccion
			transaccion.commit();
			    	
			System.out.println("Reserva cancelada correctamente");
		}
		catch (Exception ex) {
			transaccion.rollback();
		}
		finally {
			cerrarSesion(sesion);
		}
	}
}

