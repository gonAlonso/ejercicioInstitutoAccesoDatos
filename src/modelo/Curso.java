package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "Cursos")
public class Curso {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name = "cuCodigo")
	private int codigo;
	
	@Column (name = "cuNombre", length = 50, nullable = false)
	private String nombre;
	
	@Column (name = "cuHoras")
	private int horas;
	
	@OneToMany (mappedBy = "curso", cascade = CascadeType.ALL)
	private List<Modulo> modulos;
	
	@OneToMany (mappedBy = "curso", cascade = CascadeType.ALL)
	private List<Alumno> alumnos;

	/***********************/
	public Curso() {}
	public Curso(String nombre, int horas) {
		this.nombre = nombre;
		this.horas = horas;
		this.modulos = new ArrayList<>();
		this.alumnos = new ArrayList<>();
	}
	public Curso(String nombre, int horas, List<Modulo> modulos, List<Alumno> alumnos) {
		this.nombre = nombre;
		this.horas = horas;
		this.modulos = modulos==null? new ArrayList<>() : modulos;
		this.alumnos = alumnos==null? new ArrayList<>() : alumnos;
	}
	/*****************************/
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public List<Modulo> getModulos() {
		return modulos;
	}
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public int getNumberAlumnos() {
		return alumnos.size();
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	/*********************/
	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nombre=" + nombre + ", horas=" + horas + ", modulos=" + modulos
				+ ", alumnos=" + alumnos + "]";
	}
	
}
