package modeloDao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import main.IntroducirDatos;
import modelo.Alumno;
import modelo.Curso;
import modelo.Modulo;

public class AlumnoDao {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio03InstitutoHQL");
	
	public static List<Alumno> listadoAlumnos() {
		EntityManager em = emf.createEntityManager();
		String consulta = "select a from Alumno a";
		Query query = em.createQuery(consulta);
		List<Alumno> list = query.getResultList();
		em.close();
		return list;
	}
	

	
	public static List<Alumno> listadoAlumnosDelegados() {
		List<Alumno> lista;
		EntityManager em =emf.createEntityManager();
		String consulta = "from Alumno a where a.delegado=1";
		Query query = em.createQuery(consulta);
		try {
			lista = query.getResultList();
		}
		catch (Exception e) {
			lista = null;
		}
		finally {
			em.close();
		}
		return lista;
	}
	
	public static void insertarAlumno(Alumno alumno) {
			
		EntityManager em = emf.createEntityManager();
		
		if( em.find(Alumno.class, alumno.getDni()) != null) {
			System.out.println("El alumno ya existe!! Cancelado");
			return;
		}
		
		try {
			em.getTransaction().begin();
			em.persist(alumno);
			em.getTransaction().commit();
			System.out.println("Alumno añadido");
		}
		catch (Exception e) {
			em.getTransaction().rollback();;
			System.out.println("Error al insertar alumno");
		}
		finally {em.close();}
	}
	
	/***********************/
}
