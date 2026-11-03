package logica.datatypes;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DtMedicamento {
	private String nombre;
	private String posologia;
	private LocalDate vencimiento;
	private boolean usado;
	private float costoMed;
	private DtSolicitudMedicamento solMed;
	
	public DtMedicamento(String nombre, String posologia, LocalDate vencimiento, float costoMed) {
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
}
