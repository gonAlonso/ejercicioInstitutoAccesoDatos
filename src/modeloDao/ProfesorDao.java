package modeloDao;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import modelo.Alumno;
import modelo.Profesor;


public class ProfesorDao {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio03InstitutoHQL");

	public static void insertarProfesor(Profesor profesor) {
		EntityManager em = emf.createEntityManager();
		
		if(em.find(Profesor.class, profesor.getDni()) != null) {
			System.out.println("El profesor ya existe!! Cancelado");
			return;
		}
		
		try {
			em.getTransaction().begin();
			em.persist(profesor);
			em.getTransaction().commit();
			System.out.println("Profesor añadido");
		}
		catch (Exception e) {
			System.out.println("Error al insertar Profesor");
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		finally { em.close();}
	}
	
	public static List<Profesor> listadoProfesores() {
		EntityManager em = emf.createEntityManager();
		String consulta = "select a from Alumno a";
		Query query = em.createQuery(consulta);
		List<Profesor> list = query.getResultList();
		em.close();
		return list;
	}
}
