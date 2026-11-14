package logica.clases;

import java.time.LocalDate;

import logica.datatypes.DtMedicamento;
import logica.datatypes.DtSolicitudMedicamento;

public class Medicamento {
	public static final int PLAZO_DIAS = 30;
	
	private String nombre;
	private String posologia;
	private LocalDate indicacion;
	private LocalDate vencimiento;
	private boolean usado;
	private float costoMed;
	private DtSolicitudMedicamento solMed;
	private String nomC;

	
	public Medicamento(String nombre, String posologia, LocalDate indicacion, float costoMed, String nomC) {
		this.nombre = nombre;
		this.posologia = posologia;
		this.indicacion = indicacion;
		this.vencimiento = indicacion.plusDays(PLAZO_DIAS);
		this.usado = false;
		this.costoMed = costoMed;
		this.solMed = null;
		this.nomC = nomC;
	}
	public String getNombre() {
		return nombre;
	}
	public LocalDate getIndicacion() {
		return indicacion;
	}
	public LocalDate getVencimiento() {
		return vencimiento;
	}
	public boolean getUsado() {
		return usado;
	}
	public float getCostoMed() {
		return costoMed;
	}
	public String getNomC() {
	    return nomC;
	}
	public void setSolMed(String NicknamePaciente, LocalDate entrega, String direccion, boolean usaBenef) {
		solMed = new DtSolicitudMedicamento(NicknamePaciente, entrega, direccion, usaBenef, costoMed);
	}
	
	public boolean esMismo(String _nombre, LocalDate _indicacion) {
		return nombre.equalsIgnoreCase(_nombre) && indicacion.equals(_indicacion);
	}
	public DtMedicamento getDtMedicamento() {
		return new DtMedicamento(nombre, posologia, indicacion, vencimiento, usado, costoMed, solMed, nomC);
	}
	
	public boolean estaVencido(LocalDate hoy) {
		return hoy.isAfter(vencimiento);
	}
	public void registrarSolicitud(DtSolicitudMedicamento solicitud) {
		this.solMed = solicitud;
		this.usado = true;
	}



}
