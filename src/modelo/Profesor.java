package modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table (name = "profesores")
public class Profesor {
	@Id
	@Column (name = "prDni", length = 9 )
	private String dni;
	
	@Column (name = "prNombre", length = 15, nullable = false)
	private String nombre;
	
	@Column (name = "prApellidos", length = 30, nullable = false)
	private String apellidos;
	
	@Column (name = "prDireccion", length = 50, nullable = false)
	private String direccion;
	
	@Column (name = "prCodPostal")
	private int codPostal;
	
	@Column (name = "prCiudad", length = 30, nullable = false)
	private String ciudad;
	
	@Column (name = "pfTelefono", length = 9, nullable = false)
	private int telefono;
	
	@Column (name = "prFecNacimiento")
	private Date fecnacimiento;
	
	@Column (name = "prSalario")
	private int salario;
	
	@OneToMany (cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name="moCodigo")
	private List<Modulo> modulos;
	/********************/
	public Profesor(String dni, String nombre, String apellidos, String direccion, int codPostal, String ciudad,
			int telefono, Date fecnacimiento, int salario) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.codPostal = codPostal;
		this.ciudad = ciudad;
		this.telefono = telefono;
		this.fecnacimiento = fecnacimiento;
		this.salario = salario;
		this.modulos = null;
	}
	public Profesor() {
		super();
	}
	/********************/
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	public int getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(int codPostal) {
		this.codPostal = codPostal;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public Date getFecnacimiento() {
		return fecnacimiento;
	}
	public void setFecnacimiento(Date fecnacimiento) {
		this.fecnacimiento = fecnacimiento;
	}
	public int getSalario() {
		return salario;
	}
	public void setSalario(int salario) {
		this.salario = salario;
	}
	public List<Modulo> getModulos() {
		return modulos;
	}
	public void setModulos(List<Modulo> modulos) {
		this.modulos = modulos;
	}
	/********************/
	@Override
	public String toString() {
		return "Profesor [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", codPostal=" + codPostal + ", ciudad=" + ciudad + ", telefono=" + telefono + ", fecnacimiento="
				+ fecnacimiento + ", salario=" + salario + "]";
	}
}
