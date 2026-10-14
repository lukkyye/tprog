package logica.clases;
//import java.lang.string;??

public class Especialidad {
	private String nombre;
	private int porcentajeAdicional;
	
	public Especialidad(String nombre, int porcentajeAdicional) {
		this.nombre = nombre;
		this.porcentajeAdicional = porcentajeAdicional;
	}
	
	public String getNombre() {
		return nombre;
	}
	public int getPorcentajeAdicional() {
		return porcentajeAdicional;
	}
	
}
