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
import javax.persistence.Table;

import modelo.Alumno;
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
	

}
