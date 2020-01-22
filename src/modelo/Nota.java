package modelo;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "Notas")
public class Nota {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column (name = "noCodigo")
	private int codigo;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "noDniAl")
	private Alumno alumno;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn (name = "noCodModulo")
	private Modulo modulo;
	
	@Column (name = "noNota")
	private int nota;
	/*******************/
	public Nota(Alumno alumno, Modulo modulo, int nota) {
		this.alumno = alumno;
		this.modulo = modulo;
		this.nota = nota;
	}
	public Nota() {}
	/*******************/
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public Alumno getAlumno() {
		return alumno;
	}
	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	public Modulo getModulo() {
		return modulo;
	}
	public void setModulo(Modulo modulo) {
		this.modulo = modulo;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	/*******************/
	@Override
	public String toString() {
		return "Nota [codigo=" + codigo + ", alumno=" + alumno + ", modulo=" + modulo + ", nota=" + nota + "]";
	}
}
