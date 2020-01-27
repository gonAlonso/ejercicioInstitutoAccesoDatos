package modeloDao;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import modelo.Alumno;
import modelo.Curso;
import modelo.Modulo;


public class ModuloDao {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio03InstitutoHQL");

	public static void insertarModulo(Modulo modulo) {
		EntityManager em = emf.createEntityManager();
		
		if(em.find(Modulo.class, modulo.getCodigo()) != null) {
			System.out.println("El modulo ya existe!! Cancelado");
			return;
		}
		
		try {
			em.getTransaction().begin();
			em.persist(modulo);
			em.getTransaction().commit();
			System.out.println("Modulo añadido");
		}
		catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			System.out.println("Error al insertar Modulo");
		}
		finally { em.close(); }
	}

	public static List<Modulo> listadoModulos() {
		EntityManager em = emf.createEntityManager();
		String consulta = "select m from Modulo m";
		Query query = em.createQuery(consulta);
		List<Modulo> list = query.getResultList();
		em.close();
		return list;
	}

	public static Modulo getModulo(int idModulo) {
		Modulo modulo;
		EntityManager em = emf.createEntityManager();
		String consulta = "select m from Modulo m where m.codigo = :id";
		Query query = em.createQuery(consulta);
		query.setParameter( "id", idModulo);
		try {
			modulo = (Modulo) query.getSingleResult();
		}
		catch (Exception e) {
			modulo = null;
		}
		finally {
			em.close();
		}
		return modulo;
	}

	
}
