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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import modeloDao.ModuloDao;

@Entity
@Table (name = "Modulos")
public class Modulo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name = "moCodigo")
	private int codigo;

	@Column (name = "moNombre", length = 50, nullable = false)
	private String nombre;
	
	@Column (name = "moHoras")
	private int horas;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="moDniProfesor")
	private Profesor profesor;
	
	@ManyToOne (cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="moCurso")
	private Curso curso;
	
	@OneToMany(mappedBy = "modulo")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Nota> notas;

	/***********************/
	public Modulo() {}
	public Modulo( String nombre, int horas, Profesor profesor) {
		this();
		this.nombre = nombre;
		this.horas = horas;
		this.profesor = profesor;
		this.notas = null;
	}
	/************************/
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
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public List<Nota> getNotas() {
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	/***********************/
	@Override
	public String toString() {
		return "Modulo [codigo=" + codigo + ", nombre=" + nombre + ", horas=" + horas + ", profesor=" + profesor + "]";
	}
	public void addCurso(Curso curso2) {
		this.curso = curso;
	}
}
