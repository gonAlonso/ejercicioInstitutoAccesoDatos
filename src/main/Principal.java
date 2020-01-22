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
				case 1: listarAlumnosCurso(); break; //CursoDao.listarAlumnosCurso(); break;
				case 2:
				case 3:
				case 4:
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
		List<Alumno> listaAlumnos = null;
		
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
				}
				else {
					System.out.println("Solo el curso: "+ idCurso);
					listaAlumnos = CursoDao.listaAlumnosCurso(idCurso);
				}

				if (listaAlumnos == null) {
					System.out.println("No se encuentran alumnos!");
					continue;
				}

				if (listaAlumnos.size() == 0) {
					System.out.println("No hay alumnos");
				}
				else {
					for( Alumno alumno : listaAlumnos) {
						System.out.println( alumno.toString());
					}
				}
				return;
			}
			catch(Exception e) { e.printStackTrace();}
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
		Alumno alumno = new Alumno(nombre, dni, apellidos, direccion, codPostal, ciudad, telefono, fecNacimiento, delegado, null, null);
		AlumnoDao.insertarAlumno( alumno );
	}
	
	public static void insertarCurso() {
		String nombre = IntroducirDatos.introducirDatos("Nombre del curso: ");
		int horas = IntroducirDatos.introducirInteger("Horas del curso: ");
		Curso curso = new Curso( nombre, horas, null, null);
		
		CursoDao.insertarCurso(curso);
	}

	private static void addElementosIniciales(){
		Date fecha = null;
		try { fecha = new SimpleDateFormat("dd/MM/yyyy").parse("29/07/1984");} catch (ParseException e) {e.printStackTrace(); }
		
		Alumno alumno = new Alumno("Zalo", "10000", "Alonso castro", "Bugarin, 6", "36869", "Ponteareas", "626660120", fecha, false);
		Profesor profesor = new Profesor("10001", "Angel", "Lopez", "Palacio Real", 151, "Moaña", 5125, fecha, 2500, null);
		Modulo modulo = new Modulo( "Programacion", 150, profesor);
		Nota nota = new Nota( alumno, modulo, 5);
		Curso curso = new Curso( "DAM", 600);
		
		//AlumnoDao.insertarAlumno( alumno );
		//ProfesorDao.insertarProfesor( profesor );
		CursoDao.insertarCurso( curso );
		//ModuloDao.insertarModulo( modulo );
		NotaDao.insertarNota( nota );
		
		AlumnoDao.addAlumnoACurso(alumno, curso);
	}
}