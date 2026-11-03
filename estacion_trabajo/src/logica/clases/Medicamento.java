package logica.clases;

import java.time.LocalDate;

import logica.datatypes.DtMedicamento;
import logica.datatypes.DtSolicitudMedicamento;

public class Medicamento {
	private String nombre;
	private String posologia;
	private LocalDate vencimiento;
	private boolean usado;
	private float costoMed;
	private DtSolicitudMedicamento solMed;
	
	public Medicamento(String nombre, String posologia, LocalDate vencimiento, float costoMed) {
		this.nombre = nombre;
		this.posologia = posologia;
		this.vencimiento = vencimiento;
		this.usado = false;
		this.costoMed = costoMed;
		this.solMed = null;
	}
	public String getNombre() {
		return nombre;
	}
	public String getPosologia() {
		return posologia;
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
	public DtSolicitudMedicamento getSolMed() {
		return solMed;
	}
	public void setSolMed(String NicknamePaciente, LocalDate entrega, String direccion, boolean usaBenef) {
		solMed = new DtSolicitudMedicamento(NicknamePaciente, entrega, direccion, usaBenef, costoMed);
	}

}
