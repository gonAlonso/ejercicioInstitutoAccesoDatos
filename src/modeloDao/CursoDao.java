package modeloDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

import main.IntroducirDatos;
import modelo.Alumno;
import modelo.Curso;

public class CursoDao {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ejercicio03InstitutoHQL");

	public static void listarCursos() {
		
		EntityManager em = emf.createEntityManager();
		String consulta = "from modelo.Curso c";
		ArrayList<Curso> listaCursos=null;
		try{
			Query query = em.createQuery(consulta);
			listaCursos = (ArrayList<Curso>) query.getResultList();
			if ( listaCursos.size() == 0) {
				System.out.println("Lista de cursos vacia");
				return;
			}
		}catch (NoResultException e) {e.printStackTrace();}
		finally{ em.close(); }

		for( Curso c  : listaCursos ) {
			c.toString();
		}
	}
	
	public static void insertarCurso(Curso curso) {
		EntityManager em = emf.createEntityManager();
		
		String consulta = "from modelo.Curso c where nombre = :name";
		try{
			Query query = em.createQuery(consulta);
			query.setParameter("name", curso.getNombre());
			if ((Curso) query.getSingleResult() != null) {
				System.out.println("El Curso ya existe!! Cancelado");
				return;
			}
		}catch (NoResultException e) {} // Todo correcto si no encuentra resultado

		try {
			em.getTransaction().begin();
			em.persist( curso );
			em.getTransaction().commit();
			System.out.println("Curso insertado");
		}
		catch(Exception e){
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		finally { em.close(); }
	}

	public static List<Alumno> listaAlumnosCurso(int idCurso) {
		EntityManager em =  emf.createEntityManager();
		Query query = em.createQuery( "select c.alumnos from modelo.Curso c where c.codigo = :cod" );
		query.setParameter("cod", idCurso);
		List<Alumno> lista = query.getResultList(); 
		em.close();
		return lista;
	}

	public static void addAlumnoACurso(Alumno alumno, Curso curso) {
		listarCursos();
		
	}
}
