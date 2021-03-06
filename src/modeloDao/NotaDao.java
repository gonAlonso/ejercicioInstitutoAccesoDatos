package modeloDao;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import modelo.Alumno;
import modelo.Curso;
import modelo.Modulo;
import modelo.Nota;


public class NotaDao {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio03InstitutoHQL");

	public static List<Nota> listadoNotas() {
		EntityManager em = emf.createEntityManager();
		String consulta = "select n from Nota n";
		Query query = em.createQuery(consulta);
		List<Nota> list = query.getResultList();
		em.close();
		return list;
	}
	
	public static void insertarNota(Nota nota) {
		EntityManager em = emf.createEntityManager();
		if(em.find(Nota.class, nota.getCodigo()) != null) {
			System.out.println("La nota ya existe!! Cancelado");
			return;
		}
		
		try {
			em.getTransaction().begin();
			em.persist(nota);
			em.getTransaction().commit();
			System.out.println("Nota a�adido");
		}
		catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			System.out.println("Error al insertar Nota");
		}
		finally { em.close(); }
	}
}
