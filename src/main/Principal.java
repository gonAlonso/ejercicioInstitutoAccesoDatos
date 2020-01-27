package main;


import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import modelo.Alumno;
import modelo.Curso;
import modelo.Modulo;
import modelo.Nota;
import modelo.Profesor;
import modeloDao.AlumnoDao;
import modeloDao.CursoDao;
import modeloDao.ModuloDao;
import modeloDao.NotaDao;
import modeloDao.ProfesorDao;

import java.awt.Menu;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Principal {
	static DecimalFormat formateador = new DecimalFormat("###,###.##");
	static EntityManager em;

	public static void main(String[] args) {
		int opcion = 0;
		em = Persistence.createEntityManagerFactory("Ejercicio03InstitutoHQL").createEntityManager();
		
		while(true){
			System.out.println("1.- Listar alumnos del curso seleccionado");
			System.out.println("2.- Listar alumnos que pasan de curso");
			System.out.println("3.- Listar alumnos delegados");
			System.out.println("4.- Listar notas de modulos seleccionado");
			System.out.println("5.- Listar profesores");
			System.out.println("--");
			System.out.println("6.- Insertar Alumno");
			System.out.println("7.- Insertar Profesor");
			System.out.println("8.- Insertar Curso");	
			System.out.println("9.- Insertar Modulo");
			System.out.println("10.- Insertar Nota");
			System.out.println("--");
			System.out.println("11.- Modificar Nota");
			System.out.println("12.- Eliminar Alumno");
			System.out.println("--");
			System.out.println("13.- Add alumno a Curso");
			System.out.println("99- Insertar Datos Iniciales");
			System.out.println("0.- Salir");
			try{
				opcion = Integer.parseInt(IntroducirDatos.introducirDatos("Elegir opcion: "));
				switch(opcion){
				case 0: { System.out.println("FIN"); System.exit(0);}
				case 1: listarAlumnosCurso(); break;
				case 2: listarAlumnosPasanCurso(); break;
				case 3: listarAlumnosDelegados(); break;
				case 4: listarNotasDeModuloSeleccionado(); break;
				case 5:
				case 6: insertarAlumno(); break;
				case 7:
				case 8: insertarCurso(); break;
				case 9:
				case 10:
				case 11:
				case 12:
				case 13: //CursoDao.addAlumnoACurso(); break;
				case 99: addElementosIniciales(); break;
				default:
					System.out.println("Opcion erronea");
				}// fin switch
			}catch(NumberFormatException e){
				System.out.println("La opcion tiene que ser un numero");
			}
		}
	}
	

	private static void listarAlumnosCurso() {
		int idCurso;
		List<Alumno> listaAlumnos;
	
		while(true) {
			idCurso= IntroducirDatos.introducirInteger("ID del curso [0=Todos, -1=SALIR]: ");
			if(idCurso == -1) {
				System.out.println("Cancelado!");
				return;
			}
	
			try{
				if( idCurso==0) {
					System.out.println("Todos los cursos");
					listaAlumnos = AlumnoDao.listadoAlumnos();
					
					if(listaAlumnos.size() == 0) {
						System.out.println("No se encuentran alumnos!");
						continue;
					}
				}
				else {	// Curso especificado
					System.out.println("Solo el curso: "+ idCurso);
					Curso curso = CursoDao.getCurso(idCurso);
					if(curso==null) {
						System.out.println("Curso no encontrado");
						continue;
					}
					listaAlumnos = curso.getAlumnos();
				}
				
				for(Alumno alumno : listaAlumnos) {
					System.out.println( alumno.toString());
				}

			}
			catch(Exception e) { e.printStackTrace();}
		}
	}
	
	private static void listarNotasDeModuloSeleccionado() {
		int idModulo;
		List<Nota> listaNotas;
		while(true) {
			idModulo = IntroducirDatos.introducirInteger("ID del modulo [0=Todos, -1=SALIR]: ");
			if(idModulo == -1) { System.out.println("Cancelado!"); return; }
			
			try{
				if( idModulo==0) {
					System.out.println("Todos los Modulos");
					listaNotas = NotaDao.listadoNotas();
					
					if(listaNotas.size() == 0) {
						System.out.println("No se encuentran Modulos!");
						continue;
					}
				}
				else {	// Curso especificado
					System.out.println("Solo el Modulo: "+ idModulo);
					Modulo modulo= ModuloDao.getModulo( idModulo );
					if(modulo==null) {
						System.out.println("Modulo no encontrado");
						continue;
					}
					listaNotas = modulo.getNotas();
				}
				
				System.out.println("Lista de Notas");
				for(Nota nota: listaNotas) {
					System.out.print( nota.toString());
					System.out.println(" Profesor: " + nota.getModulo().getProfesor().getNombre());
				}

			}
			catch(Exception e) { e.printStackTrace();}
		}

	}
	
	
	private static void listarAlumnosPasanCurso(){
		List<Alumno> alumnos = AlumnoDao.listadoAlumnos();
		if( alumnos==null || alumnos.size()==0) {
			System.out.println( "No hay alumnos ");
			return;
		}
		System.out.println( ">> Lista de alumnos que pasan de curso ["+alumnos.size()+"]");
		
		for(Alumno alumno : alumnos) {
			float nota = alumno.getNotaMedia();
			if(nota == -1) return;
			System.out.println( "Alumno ["+alumno.getNombre()+" NotaMedia: "+ alumno.getNotaMedia()+"]");
		}
	}
	
	private static void listarAlumnosDelegados() {
		List<Alumno> alumnos = AlumnoDao.listadoAlumnosDelegados();
		if( alumnos==null || alumnos.size()==0) {
			System.out.println( "No hay alumnos delegados");
			return;
		}
		System.out.println( ">> Lista de delegados ["+alumnos.size()+"]");
		for( Alumno alumno : alumnos) {
			System.out.println( alumno.getDni() + " "+
					alumno.getApellidos()+" "+alumno.getCurso());
		}
	}
	
	
	public static void insertarAlumno() {
		String nombre = IntroducirDatos.introducirDatos("Nombre del alumno: ");
		String dni = IntroducirDatos.introducirDatos("DNI: ");
		String apellidos = IntroducirDatos.introducirDatos("Apellidos: ");
		String direccion = IntroducirDatos.introducirDatos("Direccion: ");
		String codPostal = IntroducirDatos.introducirDatos("Codigo postal: ");
		String ciudad = IntroducirDatos.introducirDatos("Ciudad: ");
		String telefono = IntroducirDatos.introducirDatos("Telefono: ");
		Date fecNacimiento = IntroducirDatos.introducirFecha("Fecha de nacimiento: ");
		boolean delegado = IntroducirDatos.introducirBooleano("Es delegado: ");
		//Curso curso
		Alumno alumno = new Alumno(nombre, dni, apellidos, direccion, codPostal, ciudad, telefono, fecNacimiento, delegado);
		AlumnoDao.insertarAlumno( alumno );
	}
	
	public static void insertarCurso() {
		String nombre = IntroducirDatos.introducirDatos("Nombre del curso: ");
		int horas = IntroducirDatos.introducirInteger("Horas del curso: ");
		Curso curso = new Curso( nombre, horas);
		CursoDao.insertarCurso(curso);
	}

	private static void addElementosIniciales(){
		Date fecha = null;
		try { fecha = new SimpleDateFormat("dd/MM/yyyy").parse("29/07/1984");} catch (ParseException e) {e.printStackTrace(); }
		
		Alumno alumno = new Alumno("Zalo", "10000", "Alonso castro", "Bugarin, 6", "36869", "Ponteareas", "626660120", fecha, false);
		Profesor profesor = new Profesor("10001", "Angel", "Lopez", "Palacio Real", 151, "Moaña", 5125, fecha, 2500);
		Modulo modulo = new Modulo( "Programacion", 150, profesor);
		Nota nota = new Nota( alumno, modulo, 5);
		Curso curso = new Curso( "DAM", 600);
		
		alumno.setCurso(curso);
		modulo.setCurso(curso);
		
		NotaDao.insertarNota( nota );
	}
}