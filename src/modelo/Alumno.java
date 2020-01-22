package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table (name = "Alumnos")
public class Alumno implements Serializable{
	
	@Column (name = "alNombre", length = 15, nullable = false)
	private String nombre;
	
	@Id
	@Column (name = "alDni", length = 9 )
	private String dni;
	
	@Column (name = "alapellidos", length = 30, nullable = false)
	private String apellidos;
	
	@Column (name = "alDireccion", length = 50, nullable = false)
	private String direccion;
	
	@Column (name = "alCodPostal", length=5, nullable=false)
	private String codPostal;
	
	@Column (name = "alCiudad", length = 30, nullable = false)
	private String ciudad;
	
	@Column (name = "alTelefono", length = 9, nullable = false)
	private String telefono;
	
	@Column (name = "alFecNacimiento")
	private Date fecNacimiento;
	
	@Column (name = "alDelegado")
	private boolean delegado;
	
	@ManyToOne (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn( name ="alCurso")
	private Curso curso;
	
	@OneToMany (mappedBy = "alumno")//, fetch = FetchType.EAGER)
	private List<Nota> notas;

	/******** CONSTRUCTORES ********/
	public Alumno() {}
	
	public Alumno(String nombre, String dni, String apellidos, String direccion, String codPostal, String ciudad,
			String telefono, Date fecNacimiento, boolean delegado) {
		this.nombre = nombre;
		this.dni = dni;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.codPostal = codPostal;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.fecNacimiento = fecNacimiento;
		this.delegado = delegado;
	}
	
	public Alumno(String nombre, String dni, String apellidos, String direccion, String codPostal, String ciudad,
			String telefono, Date fecNacimiento, boolean delegado, Curso curso, List<Nota> notas) {
		this.nombre = nombre;
		this.dni = dni;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.codPostal = codPostal;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.fecNacimiento = fecNacimiento;
		this.delegado = delegado;
		this.curso = curso;
		this.notas = notas==null? new ArrayList<>() : notas;
	}
	/******** GETTERS Y SETTERS ********/
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Date getFecNacimiento() {
		return fecNacimiento;
	}
	public void setFecNacimiento(Date fecNacimiento) {
		this.fecNacimiento = fecNacimiento;
	}
	public boolean isDelegado() {
		return delegado;
	}
	public void setDelegado(boolean delegado) {
		this.delegado = delegado;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public List<Nota> getNotas() {
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
	/*****************************/
	@Override
	public String toString() {
		return "Alumno [nombre=" + nombre + ", dni=" + dni + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", codPostal=" + codPostal + ", ciudad=" + ciudad + ", telefono=" + telefono + ", fecNacimiento="
				+ fecNacimiento + ", delegado=" + delegado + ", curso=" + curso + ", notas=" + notas + "]";
	}
}
